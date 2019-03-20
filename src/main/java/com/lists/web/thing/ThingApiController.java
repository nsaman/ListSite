package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptorType.DescriptorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by nick on 1/23/2018.
 */

@RestController
public class ThingApiController {
    @Autowired
    private IThingRepository thingRepository;

    @RequestMapping(path="/api/things", produces = "application/json")
    public ThingsTableView getThingsByParentAndComparator(@RequestParam(value="thingID") Thing parentThing,
                                                 @RequestParam(value="comparatorID", defaultValue="1") Set<Comparator> comparators,
                                                 @RequestParam(value="descriptorTypeSearchedIDs", defaultValue="") Set<DescriptorType> descriptorTypeSearchedIDs,
                                                 @RequestParam(value="descriptorTypeRetrievedIDs", defaultValue="") Set<DescriptorType> descriptorTypeRetrievedIDs) {

        Iterable<Thing> thingList = thingRepository.findAll(Specifications.where(IThingRepository.hasComparators(comparators)).and(IThingRepository.hasParentThing(parentThing)));

        return thingsToThingsTableView(thingList, comparators, descriptorTypeRetrievedIDs);
    }

    @RequestMapping(path="/api/thing", produces = "application/json")
    public Thing getThingsByParentAndComparator(@RequestParam(value="thingID",required = true) Thing thing) {
        return thing;
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/thing", method = RequestMethod.POST, consumes={"application/json"})
    public void createThing(@RequestBody NewThingRequest newThingRequest) {

        Thing thing = new Thing();

        thing.setTitle(newThingRequest.getTitle());
        thing.setIsAbstract(newThingRequest.getIsAbstract());
        thing.setParentThing(thingRepository.findOne(newThingRequest.getParentThingId()));

        thingRepository.save(thing);
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
