package com.lists.web.descriptorType;

import com.lists.web.descriptor.DescriptorRepositoryHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nick on 3/25/2019.
 */

@RestController
public class DescriptorTypesApiController {
    @Autowired
    private IDescriptorTypeRepository descriptorTypeRepository;
    @Autowired
    private DescriptorRepositoryHelper descriptorRepositoryHelper;

    @RequestMapping(path = "/api/descriptorTypes", produces = "application/json")
    public Iterable<DescriptorType> getDescriptorTypeView() {
        return descriptorTypeRepository.findAll();
    }

    @RequestMapping(path = "/api/descriptorType", produces = "application/json")
    public DescriptorType getDescriptorTypeByDescriptorTypeId(@RequestParam(value = "descriptorTypeID", required = true) DescriptorType descriptorType) {
        return descriptorType;
    }

    @RequestMapping(path = "/api/descriptorTypeTypes", produces = "application/json")
    public List<String> getDescriptorTypeTypes() {
        return Arrays.stream(DescriptorTypes.values()).map(DescriptorTypes::getTypeName).collect(Collectors.toList());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(path="/api/descriptorType", method = RequestMethod.POST, consumes={"application/json"})
    public void createThing(@Valid @RequestBody DescriptorType descriptorType) {

        if(StringUtils.isBlank(descriptorType.getTitle())) {
            throw new IllegalArgumentException("Empty title found");
        } else {
            descriptorType.setTitle(StringUtils.trim(descriptorType.getTitle()));
        }
        if(DescriptorTypes.byString(descriptorType.getValueType()) == null) {
            throw new IllegalArgumentException("Unregistered descriptor type value found value=" + descriptorType.getValueType());
        }

        descriptorTypeRepository.save(descriptorType);
    }

}
