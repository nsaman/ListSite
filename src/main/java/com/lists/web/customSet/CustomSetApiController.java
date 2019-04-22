package com.lists.web.customSet;

import com.lists.web.CustomSetThing.CustomSetThing;
import com.lists.web.CustomSetThing.CustomSetThingRequest;
import com.lists.web.CustomSetThing.ICustomSetThingRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private ICustomSetThingRepository customSetThingRepository;
    @Autowired
    private IThingRepository thingRepository;

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

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/customSet/thing", method = RequestMethod.POST, consumes={"application/json"})
    public void createCustomSetThing(@Valid @RequestBody CustomSetThingRequest customSetThingRequest) {

        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomSet customSet = customSetRepository.findOne(customSetThingRequest.getCustomSetID());

        if(!loggedInUser.equals(customSet.getCreateUserID()))
            throw new IllegalArgumentException("CustomSetThing with CustomSetID=" + customSet.getCustomSetID()
                    + " and createUser " + customSet.getCreateUserID() + " cannot be created from non-authorized-user=" + loggedInUser);

        Thing thing = thingRepository.findOne(customSetThingRequest.getThingID());

        if(customSetThingRepository.findByThingAndCustomSet(thing, customSet).size() > 0)
            throw new IllegalArgumentException("CustomSetThing with thingID=" + thing.getThingID()
                    + " and customSetID=" + customSet.getCustomSetID() + " already exists!");

        CustomSetThing customSetThing = new CustomSetThing();

        customSetThing.setThing(thing);
        customSetThing.setCustomSet(customSet);

        customSetThingRepository.save(customSetThing);
    }
}
