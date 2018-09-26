package com.lists.web.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nick on 6/5/2018.
 */

@Controller
@RequestMapping(path="/comparator")
public class ComparatorController {

    @Autowired
    private IComparatorRepository comparatorRepository;

    @RequestMapping(value = "/{comparatorID}", method = RequestMethod.GET)
    public String getComparator(@PathVariable(value="comparatorID") int comparatorID, Model model) {

        Comparator comparator = comparatorRepository.findOne(comparatorID);

        model.addAttribute("comparator", comparator);

        return "comparator";
    }

    @RequestMapping(params = {"title"}, method = RequestMethod.POST)
    public String createComparator(@RequestParam("title") String title, Model model) {

        Comparator comparator = new Comparator();
        comparator.setTitle(title);

        comparatorRepository.save(comparator);

        return "redirect:/comparator/" + comparator.getComparatorID();
    }
}
