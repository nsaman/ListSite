package com.lists.web.compares;

import com.lists.web.comparator.Comparator;
import com.lists.web.comparator.IComparatorRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @ModelAttribute("comparatorList")
    public Iterable<Comparator> comparatorList() {
        return comparatorRepository.findAll();
    }

    @ModelAttribute("thingList")
    public Iterable<Thing> thingList() {
        return thingRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateCompares() {
        return new ModelAndView("createCompares", "compares", new Compares());
    }

    @RequestMapping(value = "/{comparesID}", method = RequestMethod.GET)
    public String getCompares(@PathVariable(value="comparesID") int comparesID, Model model) {

        Compares compares = comparesRepository.findOne(comparesID);

        model.addAttribute("compares", compares);

        return "compares";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createCompares(@ModelAttribute("compares") Compares compares) {
        compares.setScore(1600);
        comparesRepository.save(compares);

        return "redirect:/compares/" + compares.getComparesID();
    }
}
