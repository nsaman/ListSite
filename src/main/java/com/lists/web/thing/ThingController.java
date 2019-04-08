package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptorType.DescriptorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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

//    @GetMapping
//    public String getThingsByParentAndComparator(@RequestParam(value="thingID") Thing parentThing,
//                                                 @RequestParam(value="comparatorID", defaultValue="1") Set<Comparator> comparators,
//                                                 @RequestParam(value="descriptorTypeSearchedIDs", defaultValue="") Set<DescriptorType> descriptorTypeSearchedIDs,
//                                                 @RequestParam(value="descriptorTypeRetrievedIDs", defaultValue="") Set<DescriptorType> descriptorTypeRetrievedIDs, Model model) {
//
//        Iterable<Thing> thingList = thingRepository.findAll(IThingRepository.hasComparator(comparators));
//
//        ThingsTableView thingsTableView = thingsToThingsTableView(thingList, comparators, descriptorTypeRetrievedIDs);
//
//        model.addAttribute("thingsTableView", thingsTableView);
//        model.addAttribute("parentThingDescriptors", parentThing.getDescriptors());
//
//        return "things";
//    }

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
                    if (descriptorMap.containsKey(descriptor.getDescriptorType())) {
                        descriptorMap.get(descriptor.getDescriptorType()).add(descriptor);
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
