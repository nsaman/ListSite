package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.comparator.IComparatorRepository;
import com.lists.web.compares.Compares;
import com.lists.web.compares.IComparesRepository;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptor.DescriptorRepositoryHelper;
import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import com.sun.deploy.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    private IComparatorRepository comparatorRepository;
    @Autowired
    private IDescriptorTypeRepository descriptorTypeRepository;
    @Autowired
    private DescriptorRepositoryHelper descriptorRepositoryHelper;

    @RequestMapping(path="/api/things", produces = "application/json")
    public ThingsTableView getThingsByParentAndComparator(@RequestParam(value="thingID") Thing parentThing,
                                                 @RequestParam(value="comparatorID", defaultValue="1") Set<Comparator> comparators,
                                                 @RequestParam(value="descriptorTypeSearchedIDs", defaultValue="") Set<DescriptorType> descriptorTypeSearchedIDs,
                                                 @RequestParam(value="descriptorTypeRetrievedIDs", defaultValue="") Set<DescriptorType> descriptorTypeRetrievedIDs) {

        if(comparators.contains(null))
            comparators.remove(null);
        if(descriptorTypeSearchedIDs.contains(null))
            descriptorTypeSearchedIDs.remove(null);
        if(descriptorTypeRetrievedIDs.contains(null))
            descriptorTypeRetrievedIDs.remove(null);

        List<Specification<Thing>> searchItems = new ArrayList<>();
        if(parentThing!=null)
            searchItems.add(IThingRepository.hasParentThing(parentThing));
        comparators.forEach(x -> searchItems.add(IThingRepository.hasComparator(x)));


        Iterable<Thing> thingList;

        if(!searchItems.isEmpty()) {
            Specifications<Thing> searchSpecifications = Specifications.where(searchItems.get(0));
            for (int i = 1; i < searchItems.size(); i++)
                searchSpecifications.and(searchItems.get(i));
            thingList = thingRepository.findAll(searchSpecifications);
        } else {
            thingList = thingRepository.findAll();
        }

        return thingsToThingsTableView(thingList, comparators, descriptorTypeRetrievedIDs);
    }

    @RequestMapping(path="/api/thing", produces = "application/json")
    public Thing getThingByThingId(@RequestParam(value="thingID",required = true) Thing thing) {
        return thing;
    }

    @RequestMapping(path="/api/thing/abstractThingAndParentAndChildren", produces = "application/json")
    public Set<Thing> getThingAndParentAndAbstractChildrenByThingId(@RequestParam(value="thingID",required = true) Thing thing) {

        if(thing == null)
            return null;

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


        Thing parentThing = thingRepository.findOne(newThingRequest.getParentThingId());

        List<Descriptor> undefinedDescriptorList = new ArrayList<>();
        for(Descriptor parentDescriptor : parentThing.getDescriptors()) {
            if(!parentDescriptor.getDescriptorType().getIsNullable() && !newThingRequest.getDescriptors().containsKey(parentDescriptor.getDescriptorType().getDescriptorTypeID())) {
                undefinedDescriptorList.add(parentDescriptor);
            }
        }
        if (!undefinedDescriptorList.isEmpty())
            throw new IllegalArgumentException("Undefined descriptors in parent or new thing not abstract=" + StringUtils.join(undefinedDescriptorList.stream().map(Descriptor::getDescriptorType).map(DescriptorType::getTitle).collect(Collectors.toList()), ", "));


        Thing thing = new Thing();

        thing.setTitle(newThingRequest.getTitle());
        thing.setIsAbstract(newThingRequest.getIsAbstract());
        thing.setParentThing(parentThing);

        thingRepository.save(thing);

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

        Set<Compares> comparesList = new HashSet<>();
        Set<Comparator> parentComparators = new HashSet<>();

        for(Compares parentCompares : thing.getParentThing().getCompares()) {

            parentComparators.add(parentCompares.getComparator());

            Compares compares = new Compares();
            compares.setThing(thing);
            compares.setComparator(parentCompares.getComparator());
            compares.setScore(Compares.DEFAULT_SCORE);

            comparesList.add(compares);
        }

        if(thing.getIsAbstract()) {
            for (Comparator inputComparator : newThingRequest.getChildComparators()) {
                Comparator comparator = comparatorRepository.findOne(inputComparator.getComparatorID());
                if(!parentComparators.contains(comparator)) {

                    Compares compares = new Compares();
                    compares.setThing(thing);
                    compares.setComparator(comparator);
                    compares.setScore(Compares.DEFAULT_SCORE);

                    comparesList.add(compares);
                }
            }
        }

        comparesRepository.save(comparesList);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/thing", method = RequestMethod.PUT, consumes={"application/json"})
    public void updateThing(@Valid @RequestBody UpdateThingRequest updateThingRequest) {

        Thing thing = thingRepository.findOne(updateThingRequest.getThingID());
        if(thing == null)
            throw new IllegalArgumentException("Could find thing to update thingID=" + updateThingRequest.getThingID());

        List<Descriptor> undefinedDescriptorList = new ArrayList<>();
        if (thing.getParentThing() != null) {
            for(Descriptor parentDescriptor : thing.getParentThing().getDescriptors()) {
                if(!parentDescriptor.getDescriptorType().getIsNullable() && !updateThingRequest.getDescriptors().containsKey(parentDescriptor.getDescriptorType().getDescriptorTypeID())) {
                    undefinedDescriptorList.add(parentDescriptor);
                }
            }
        }
        if (!undefinedDescriptorList.isEmpty())
            throw new IllegalArgumentException("Undefined descriptors in parent or new thing not abstract=" + StringUtils.join(undefinedDescriptorList.stream().map(Descriptor::getDescriptorType).map(DescriptorType::getTitle).collect(Collectors.toList()), ", "));


        thing.setTitle(updateThingRequest.getTitle());
        thing.setIsAbstract(updateThingRequest.getIsAbstract());
        thing.setParentThing(updateThingRequest.getParentThingId()!=null?thingRepository.findOne(updateThingRequest.getParentThingId()):null);

        thingRepository.save(thing);

        // todo add logic for logical deletion
        Set<Descriptor> descriptors = new HashSet<>();
        Map<Integer,Descriptor> descriptorTypeIdMap = new HashMap<>();
        for (Descriptor descriptor : thing.getDescriptors()) {
            descriptorTypeIdMap.put(descriptor.getDescriptorType().getDescriptorTypeID(),descriptor);
        }
        for (Integer descriptorTypeId : updateThingRequest.getDescriptors().keySet()) {
            Descriptor descriptor = null;
            DescriptorType descriptorType = descriptorTypeRepository.findOne(descriptorTypeId);
            if(descriptorTypeIdMap.containsKey(descriptorTypeId)) {
                descriptor = descriptorRepositoryHelper.findOne(descriptorType,descriptorTypeId);
            } else {
                descriptor = descriptorType.createEmptyChild();
                descriptor.setDescribedThing(thing);
            }
            if (!updateThingRequest.getIsAbstract()) {
                try {
                    descriptorRepositoryHelper.setDescriptorValueFromString(descriptor,updateThingRequest.getDescriptors().get(descriptorTypeId));
                } catch (Exception e) {
                    throw new IllegalStateException("Error parsing and setting descriptor value for descriptorType=" + descriptorType.getTitle()
                            + " value=" + updateThingRequest.getDescriptors().get(descriptorTypeId), e);
                }
            }
            descriptors.add(descriptor);
        }

        descriptorRepositoryHelper.save(descriptors);

        // todo add logic for logical deletion
        Set<Compares> comparesList = new HashSet<>();

        Set<Integer> thingComparatorIds = thing.getCompares().stream().map(Compares::getComparator).map(Comparator::getComparatorID).collect(Collectors.toSet());
        Set<Integer> parentThingComparatorIds = new HashSet<>();
        if (thing.getParentThing() != null)
            parentThingComparatorIds = thing.getParentThing().getCompares().stream().map(Compares::getComparator).map(Comparator::getComparatorID).collect(Collectors.toSet());

        for(Comparator inputComparator : updateThingRequest.getChildComparators()) {
            if(!parentThingComparatorIds.contains(inputComparator.getComparatorID()) && !thingComparatorIds.contains((inputComparator.getComparatorID()))) {
                Comparator comparator = comparatorRepository.findOne(inputComparator.getComparatorID());
                Compares compares = new Compares();
                compares.setThing(thing);
                compares.setComparator(comparator);
                compares.setScore(Compares.DEFAULT_SCORE);

                comparesList.add(compares);
            }
        }
        comparesRepository.save(comparesList);
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
