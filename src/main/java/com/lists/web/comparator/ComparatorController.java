package com.lists.web.comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nick on 6/5/2018.
 */

@Controller
@RequestMapping(path="/comparator")
public class ComparatorController {

    @Autowired
    private IComparatorRepository comparatorRepository;

//    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateComparator(Model model) {

        return "createComparator";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Comparator createComparator(@RequestBody ComparatorRequest comparatorRequest) {

        Comparator comparator = new Comparator();
        comparator.setTitle(comparatorRequest.getTitle());

        comparatorRepository.save(comparator);

        return comparator;
    }

    @RequestMapping(value = "/{comparatorID}", method = RequestMethod.GET)
    public String getComparator(@PathVariable(value="comparatorID") int comparatorID, Model model) {

        Comparator comparator = comparatorRepository.findOne(comparatorID);

        model.addAttribute("comparator", comparator);

        return "comparator";
    }
}
