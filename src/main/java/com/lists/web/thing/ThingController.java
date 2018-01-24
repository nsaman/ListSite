package com.lists.web.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/thing")
public class ThingController {
    @Autowired
    private IThingRepository thingRepository;

    @RequestMapping(value = "/{thingID}", method = RequestMethod.GET)
    public String getThing(@PathVariable(value="thingID") int thingID, Model model) {

        Thing thing = thingRepository.findOne(thingID);

        List<Thing> children = thingRepository.findByParentThing(thing);

        model.addAttribute("thing", thing);
        model.addAttribute("children", children);


        return "thing";
    }

    @RequestMapping(params = {"title","isAbstract","parentThingID"}, method = RequestMethod.POST)
    public String createThing(@RequestParam("title") String title, @RequestParam("isAbstract") Boolean isAbstract, @RequestParam("parentThingID") int parentThingID, Model model) {

        Thing thing = new Thing();
        thing.setTitle(title);
        thing.setIsAbstract(isAbstract);

        Thing parent = thingRepository.findOne(parentThingID);

        thing.setParentThing(parent);

        thingRepository.save(thing);

        return "redirect:/thing/" + thing.getThingID();
    }
}
