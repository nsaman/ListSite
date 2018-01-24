package com.lists.web.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/thing")
public class ThingController {
    @Autowired
    private ThingRepository thingRepository;

    @RequestMapping(value = "/{thingID}", method = RequestMethod.GET)
    public String getThing(@PathVariable(value="thingID") int thingID, Model model) {

        Thing thing = thingRepository.findOne(thingID);

        model.addAttribute("thing", thing);

        return "thing";
    }

    @RequestMapping(params = {"title"}, method = RequestMethod.POST)
    public String createThing(@RequestParam("title") String title, Model model) {

        Thing thing = new Thing();
        thing.setTitle(title);

        thingRepository.save(thing);

        return "redirect:/thing/" + thing.getThingID();
    }
}
