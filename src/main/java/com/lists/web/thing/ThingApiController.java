package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.compares.IComparesRepository;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptor.DescriptorRepositoryHelper;
import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nick on 1/23/2018.
 */

@RestController
public class ThingApiController {
    @Autowired
    private IThingRepository thingRepository;
    @Autowired
    private IComparesRepository comparesRepository;
    @Autowired
    private IDescriptorTypeRepository descriptorTypeRepository;
    @Autowired
    private DescriptorRepositoryHelper descriptorRepositoryHelper;

    @RequestMapping(path="/api/things", produces = "application/json")
    public ThingsTableView getThingsByParentAndComparator(@RequestParam(value="thingID") Thing parentThing,
                                                 @RequestParam(value="comparatorID", defaultValue="1") Set<Comparator> comparators,
                                                 @RequestParam(value="descriptorTypeSearchedIDs", defaultValue="") Set<DescriptorType> descriptorTypeSearchedIDs,
                                                 @RequestParam(value="descriptorTypeRetrievedIDs", defaultValue="") Set<DescriptorType> descriptorTypeRetrievedIDs) {

        Iterable<Thing> thingList = thingRepository.findAll(Specifications.where(IThingRepository.hasComparators(comparators)).and(IThingRepository.hasParentThing(parentThing)));

        return thingsToThingsTableView(thingList, comparators, descriptorTypeRetrievedIDs);
    }

    @RequestMapping(path="/api/thing", produces = "application/json")
    public Thing getThingByThingId(@RequestParam(value="thingID",required = true) Thing thing) {
        return thing;
    }

    @RequestMapping(path="/api/thing/abstractThingAndParentAndChildren", produces = "application/json")
    public Set<Thing> getThingAndParentAndAbstractChildrenByThingId(@RequestParam(value="thingID",required = true) Thing thing) {
        LinkedHashSet<Thing> things = new LinkedHashSet<>();
        if(thing.getParentThing() != null)
            things.add(thing.getParentThing());
        things.add(thing);

        for(Thing childThing : thing.getChildThings())
            if(childThing.getIsAbstract())
                things.add(childThing);

        return things;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/thing", method = RequestMethod.POST, consumes={"application/json"})
    public void createThing(@Valid @RequestBody NewThingRequest newThingRequest) {

        Thing thing = new Thing();

        thing.setTitle(newThingRequest.getTitle());
        thing.setIsAbstract(newThingRequest.getIsAbstract());
        thing.setParentThing(thingRepository.findOne(newThingRequest.getParentThingId()));

        thingRepository.save(thing);

        for(Compares parentCompares : thing.getParentThing().getCompares()) {
            Compares compares = new Compares();
            compares.setThing(thing);
            compares.setComparator(parentCompares.getComparator());
            compares.setScore(Compares.DEFAULT_SCORE);

            comparesRepository.save(compares);
        }

        List<Descriptor> undefinedDescriptorList = new ArrayList<>();
        for(Descriptor parentDescriptor : thing.getParentThing().getDescriptors()) {
            if(!parentDescriptor.getDescriptorType().getIsNullable() && !newThingRequest.getDescriptors().containsKey(parentDescriptor.getDescriptorType().getDescriptorTypeID())) {
                undefinedDescriptorList.add(parentDescriptor);
            }
        }
        if (!undefinedDescriptorList.isEmpty())
            throw new IllegalArgumentException("Undefined descriptors in parent or new thing not abstract=" + StringUtils.join(undefinedDescriptorList.stream().map(Descriptor::getDescriptorType).map(DescriptorType::getTitle).collect(Collectors.toList()), ", "));

        Set<Descriptor> descriptors = new HashSet<>();

        for (Integer descriptorTypeId : newThingRequest.getDescriptors().keySet()) {
            DescriptorType descriptorType = descriptorTypeRepository.findOne(descriptorTypeId);
            Descriptor descriptor = descriptorType.createEmptyChild();
            descriptor.setDescribedThing(thing);
            if (!newThingRequest.getIsAbstract()) {
                try {
                    descriptorRepositoryHelper.setDescriptorValueFromString(descriptor,newThingRequest.getDescriptors().get(descriptorTypeId));
                } catch (Exception e) {
                    throw new IllegalStateException("Error parsing and setting descriptor value for descriptorType=" + descriptorType.getTitle()
                            + " value=" + newThingRequest.getDescriptors().get(descriptorTypeId), e);
                }
            }
            descriptors.add(descriptor);
        }

        descriptorRepositoryHelper.save(descriptors);
    }

    private ThingsTableView thingsToThingsTableView(Iterable<Thing> thingList, Collection<Comparator> showComparators, Collection<DescriptorType> showDescriptorTypes) {

        ThingsTableView thingTableView = new ThingsTableView();

        List<ThingsRowView> thingsToRowList = new ArrayList<>();
        List<Comparator> thingTableCompararesHeaders = new ArrayList<>();
        List<DescriptorType> thingTableDescriptorHeaders = new ArrayList<>();
        List<String> thingTableThingHeaders = new ArrayList<>();

        for (Thing thing : thingList) {
            ThingsRowView thingsRowView = new ThingsRowView();
            thingsRowView.setThing(thing);

            Map<String,Compares> comparesMap = new HashMap<>();
            for (Compares compares : thing.getCompares()) {
                if (showComparators.contains(compares.getComparator())) {
                    if(!thingTableCompararesHeaders.contains(compares.getComparator()))
                        thingTableCompararesHeaders.add(compares.getComparator());
                    comparesMap.put(compares.getComparator().getTitle(),compares);
                }
            }
            thingsRowView.setComparesMap(comparesMap);

            Map<String,Collection<Descriptor>> descriptorMap = new HashMap<>();
            for (Descriptor descriptor : thing.getDescriptors()) {
                if (showDescriptorTypes.contains(descriptor.getDescriptorType())) {
                    if (!thingTableDescriptorHeaders.contains(descriptor.getDescriptorType()))
                        thingTableDescriptorHeaders.add(descriptor.getDescriptorType());
                    if (descriptorMap.containsKey(descriptor.getDescriptorType().getTitle())) {
                        descriptorMap.get(descriptor.getDescriptorType().getTitle()).add(descriptor);
                    } else {
                        Set<Descriptor> newDescriptors = new HashSet<>();
                        newDescriptors.add(descriptor);
                        descriptorMap.put(descriptor.getDescriptorType().getTitle(), newDescriptors);
                    }
                }
            }
            thingsRowView.setDescriptorMap(descriptorMap);

            thingsToRowList.add(thingsRowView);
        }
        thingTableView.setThingsRowViewList(thingsToRowList);
        thingTableView.setComparesHeaders(thingTableCompararesHeaders);
        thingTableView.setDescriptorHeaders(thingTableDescriptorHeaders);

        thingTableThingHeaders.add("Title");
        thingTableView.setThingHeaders(thingTableThingHeaders);

        return thingTableView;
    }
}
