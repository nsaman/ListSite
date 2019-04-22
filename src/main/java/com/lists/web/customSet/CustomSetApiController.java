package com.lists.web.customSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by nick on 4/21/2019.
 */

@RestController
public class CustomSetApiController {
    @Autowired
    private ICustomSetRepository customSetRepository;

    @RequestMapping(path = "/api/customSets", produces = "application/json")
    public Iterable<CustomSet> getCustomSetView() {
        return customSetRepository.findAll();
    }

    @RequestMapping(path = "/api/customSet", produces = "application/json")
    public CustomSet getCustomSetByCustomSetId(@RequestParam(value = "customSetID", required = true) CustomSet customSet) {
        return customSet;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/customSet", method = RequestMethod.POST, consumes={"application/json"})
    public void createCustomSet(@Valid @RequestBody CustomSet customSet) {

        if(StringUtils.isBlank(customSet.getTitle())) {
            throw new IllegalArgumentException("Empty title found");
        } else {
            customSet.setTitle(StringUtils.trim(customSet.getTitle()));
        }

        customSetRepository.save(customSet);
    }
}
