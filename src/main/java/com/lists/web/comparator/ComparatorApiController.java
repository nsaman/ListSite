package com.lists.web.comparator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by nick on 3/26/2019.
 */

@RestController
public class ComparatorApiController {
    @Autowired
    private IComparatorRepository comparatorRepository;

    @RequestMapping(path = "/api/comparators", produces = "application/json")
    public Iterable<Comparator> getComparatorView() {
        return comparatorRepository.findAll();
    }

    @RequestMapping(path = "/api/comparator", produces = "application/json")
    public Comparator getComparatorByComparatorId(@RequestParam(value = "comparatorID", required = true) Comparator comparator) {
        return comparator;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/comparator", method = RequestMethod.POST, consumes={"application/json"})
    public void createComparator(@Valid @RequestBody Comparator comparator) {

        if(StringUtils.isBlank(comparator.getTitle())) {
            throw new IllegalArgumentException("Empty title found");
        } else {
            comparator.setTitle(StringUtils.trim(comparator.getTitle()));
        }

        comparatorRepository.save(comparator);
    }

}
