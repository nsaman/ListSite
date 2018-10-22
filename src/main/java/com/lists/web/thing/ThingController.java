package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                 @RequestParam(value="comparatorID") Comparator comparator, Model model) {

        Iterable<Thing> thingList = thingRepository.findThingAndComparesByParentAndComparatorDesc(parentThing.getThingID(), comparator.getComparatorID());

        ThingsTableView thingsTableView = thingsToThingsTableView(thingList);

        model.addAttribute("thingsTableView", thingsTableView);

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

    private ThingsTableView thingsToThingsTableView(Iterable<Thing> thingList) {

        ThingsTableView thingTableView = new ThingsTableView();

        Map<Thing,ThingsRowView> thingsToRowMap = new HashMap<>();
        List<Comparator> thingTableCompararesHeaders = new ArrayList<>();
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

            thingsToRowMap.put(thing, thingsRowView);
        }
        thingTableView.setThingsMap(thingsToRowMap);
        thingTableView.setComparesHeaders(thingTableCompararesHeaders);

        thingTableThingHeaders.add("Title");
        thingTableView.setThingHeaders(thingTableThingHeaders);

        return thingTableView;
    }
}
