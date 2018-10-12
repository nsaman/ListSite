package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
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
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/descriptor")
public class DescriptorController {
    @Autowired
    private IDescriptorRepository descriptorRepository;
    @Autowired
    private IDescriptorTypeRepository descriptorTypeRepository;
    @Autowired
    private IThingRepository thingRepository;

    @ModelAttribute("descriptorTypeList")
    public Iterable<DescriptorType> descriptorTypeList() {
        return descriptorTypeRepository.findAll();
    }

    @ModelAttribute("thingList")
    public Iterable<Thing> thingList() {
        return thingRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView getCreateDescriptor() {
        return new ModelAndView("createDescriptor", "descriptor", new Descriptor());
    }

    @RequestMapping(value = "/{descriptorID}", method = RequestMethod.GET)
    public String getDescriptor(@PathVariable(value="descriptorID") int descriptorID, Model model) {

        Descriptor descriptor = descriptorRepository.findOne(descriptorID);

        model.addAttribute("descriptor", descriptor);

        return "descriptor";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @RequestMapping(method = RequestMethod.POST)
    public String createDescriptor(@ModelAttribute("descriptor") Descriptor descriptor) {
        descriptorRepository.save(descriptor);

        return "redirect:/descriptor/" + descriptor.getDescriptorID();
    }
}
