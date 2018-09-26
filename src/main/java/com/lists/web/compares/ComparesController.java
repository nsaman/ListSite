package com.lists.web.compares;

import com.lists.web.comparator.Comparator;
import com.lists.web.comparator.IComparatorRepository;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptor.IDescriptorRepository;
import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.DescriptorTypes;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nick on 9/25/2018.
 */

@Controller
@RequestMapping(path="/compares")
public class ComparesController {
    @Autowired
    private IComparesRepository comparesRepository;
    @Autowired
    private IComparatorRepository comparatorRepository;
    @Autowired
    private IThingRepository thingRepository;

    @RequestMapping(value = "/{comparesID}", method = RequestMethod.GET)
    public String getCompares(@PathVariable(value="comparesID") int comparesID, Model model) {

        Compares compares = comparesRepository.findOne(comparesID);

        model.addAttribute("compares", compares);

        return "compares";
    }

    @RequestMapping(params = {"comparatorID", "thingID"}, method = RequestMethod.POST)
    public String createCompares(@RequestParam("comparatorID") Integer comparatorID, @RequestParam("thingID") Integer thingID) {

        Comparator comparator = comparatorRepository.findOne(comparatorID);
        Thing thing = thingRepository.findOne(thingID);

        Compares compares = new Compares();

        compares.setComparator(comparator);
        compares.setThing(thing);
        compares.setScore(1600);

        comparesRepository.save(compares);

        return "redirect:/compares/" + compares.getComparesID();
    }
}
