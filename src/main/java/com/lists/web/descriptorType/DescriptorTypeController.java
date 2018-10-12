package com.lists.web.descriptorType;

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
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/descriptorType")
public class DescriptorTypeController {
    @Autowired
    private IDescriptorTypeRepository descTypeRepository;

    @ModelAttribute("descriptorTypes")
    public DescriptorTypes[] descriptorTypes() {
        return DescriptorTypes.values();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateDescriptorType() {
        return new ModelAndView("createDescriptorType", "descriptorType", new DescriptorType());
    }

    @RequestMapping(value = "/{descriptorTypeID}", method = RequestMethod.GET)
    public String getDescriptorType(@PathVariable(value="descriptorTypeID") int descriptorTypeID, Model model) {

        DescriptorType descType = descTypeRepository.findOne(descriptorTypeID);

        model.addAttribute("descriptorType", descType);

        return "descriptortype";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createDescriptorType(@ModelAttribute("descriptorType") DescriptorType descriptorType) {
        descTypeRepository.save(descriptorType);

        return "redirect:/descriptorType/" + descriptorType.getDescriptorTypeID();
    }
}
