package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptorType.DescriptorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/thing")
public class ThingController {
    @Autowired
    private IThingRepository thingRepository;

    @ModelAttribute("thingList")
    public Iterable<Thing> thingList() {
        return thingRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateThing() {
        return new ModelAndView("createThing", "thing", new Thing());
    }

    @GetMapping
    public String getThingsByParentAndComparator(@RequestParam(value="thingID") Thing parentThing,
                                                 @RequestParam(value="comparatorID", defaultValue="1") Comparator comparator,
                                                 @RequestParam(value="descriptorTypeSearchedIDs", defaultValue="") Set<DescriptorType> descriptorTypeSearchedIDs,
                                                 @RequestParam(value="descriptorTypeRetrievedIDs", defaultValue="") Set<DescriptorType> descriptorTypeRetrievedIDs, Model model) {

        Iterable<Thing> thingList = thingRepository.findThingAndComparesAndDescriptorsByParentAndComparatorAndDescriptorTypesDesc(parentThing.getThingID(),
                comparator.getComparatorID(),
                descriptorTypeSearchedIDs.stream().filter(descriptorType -> descriptorType != null).map(DescriptorType::getDescriptorTypeID).collect(Collectors.toList()),
                descriptorTypeRetrievedIDs.stream().filter(descriptorType -> descriptorType != null).map(DescriptorType::getDescriptorTypeID).collect(Collectors.toList()));

        ThingsTableView thingsTableView = thingsToThingsTableView(thingList);

        model.addAttribute("thingsTableView", thingsTableView);
        model.addAttribute("parentThingDescriptors", parentThing.getDescriptors());

        return "things";
    }

    @RequestMapping(value = "/{thingID}", method = RequestMethod.GET)
    public String getThing(@PathVariable(value="thingID") int thingID, Model model) {

        Thing thing = thingRepository.findOne(thingID);

        List<Thing> children = thingRepository.findByParentThing(thing);

        model.addAttribute("thing", thing);
        model.addAttribute("children", children);


        return "thing";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createThing(@ModelAttribute("thing") Thing thing) {
        thingRepository.save(thing);

        return "redirect:/thing/" + thing.getThingID();
    }

    private ThingsTableView thingsToThingsTableView(Iterable<Thing> thingList, Set<DescriptorType> descriptorTypeRetrievedIDs) {
        return thingsToThingsTableView(thingList);
    }

    private ThingsTableView thingsToThingsTableView(Iterable<Thing> thingList) {

        ThingsTableView thingTableView = new ThingsTableView();

        Map<Thing,ThingsRowView> thingsToRowMap = new HashMap<>();
        List<Comparator> thingTableCompararesHeaders = new ArrayList<>();
        List<DescriptorType> thingTableDescriptorHeaders = new ArrayList<>();
        List<String> thingTableThingHeaders = new ArrayList<>();

        for (Thing thing : thingList) {
            ThingsRowView thingsRowView = new ThingsRowView();
            thingsRowView.setThing(thing);

            Map<Comparator,Compares> comparesMap = new HashMap<>();
            for (Compares compares : thing.getCompares()) {
                if(!thingTableCompararesHeaders.contains(compares.getComparator()))
                    thingTableCompararesHeaders.add(compares.getComparator());
                comparesMap.put(compares.getComparator(),compares);
            }
            thingsRowView.setComparesMap(comparesMap);

            Map<DescriptorType,Descriptor> descriptorMap = new HashMap<>();
            for (Descriptor descriptor : thing.getDescriptors()) {
                if(!thingTableDescriptorHeaders.contains(descriptor.getDescriptorType()))
                    thingTableDescriptorHeaders.add(descriptor.getDescriptorType());
                descriptorMap.put(descriptor.getDescriptorType(),descriptor);
            }
            thingsRowView.setDescriptorMap(descriptorMap);

            thingsToRowMap.put(thing, thingsRowView);
        }
        thingTableView.setThingsMap(thingsToRowMap);
        thingTableView.setComparesHeaders(thingTableCompararesHeaders);
        thingTableView.setDescriptorHeaders(thingTableDescriptorHeaders);

        thingTableThingHeaders.add("Title");
        thingTableView.setThingHeaders(thingTableThingHeaders);

        return thingTableView;
    }
}
