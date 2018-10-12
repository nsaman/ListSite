package com.lists.web.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
}
