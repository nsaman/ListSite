package com.lists.web.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by nick on 6/5/2018.
 */

@Controller
@RequestMapping(path="/comparator")
public class ComparatorController {

    @Autowired
    private IComparatorRepository comparatorRepository;

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateComparator() {
        return new ModelAndView("createComparator", "comparator", new Comparator());
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createComparator(@Valid @ModelAttribute("comparator")Comparator comparator,
                                                     BindingResult result, ModelMap model) {

        comparatorRepository.save(comparator);

        return "redirect:/comparator/" + comparator.getComparatorID();
    }

    @RequestMapping(value = "/{comparatorID}", method = RequestMethod.GET)
    public String getComparator(@PathVariable(value="comparatorID") int comparatorID, Model model) {

        Comparator comparator = comparatorRepository.findOne(comparatorID);

        model.addAttribute("comparator", comparator);

        return "comparator";
    }
}
