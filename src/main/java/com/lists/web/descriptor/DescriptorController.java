package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.DescriptorTypes;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/descriptor")
public class DescriptorController {
    @Autowired
    private DescriptorRepositoryHelper descriptorRepositoryHelper;
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
    @RequestMapping(value = "/{descriptorType}/new", method = RequestMethod.GET)
    public ModelAndView getCreateDescriptor(@PathVariable(value="descriptorType") String descriptorType) {
        try {
            return new ModelAndView("create" + DescriptorTypes.byString(descriptorType).getTypeName() + "Descriptor", "descriptor", DescriptorTypes.byString(descriptorType).getDescriptorClazz().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("500");
        }
    }

    @RequestMapping(value = "/{descriptorType}/{descriptorID}", method = RequestMethod.GET)
    public String getDescriptor(@PathVariable(value="descriptorType") String descriptorType, @PathVariable(value="descriptorID") int descriptorID, Model model) {

        Descriptor descriptor = descriptorRepositoryHelper.findOne(descriptorType, descriptorID);

        model.addAttribute("descriptor", descriptor);

        return descriptor.type.getTypeName() + "Descriptor";
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/date")
    public String createDateDescriptor(@ModelAttribute("descriptor") DateDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/double")
    public String createDoubleDescriptor(@ModelAttribute("descriptor") DoubleDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/integer")
    public String createIntegerDescriptor(@ModelAttribute("descriptor") IntegerDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/location")
    public String createLocationDescriptor(@ModelAttribute("descriptor") LocationDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/referenceThing")
    public String createReferenceThingDescriptor(@ModelAttribute("descriptor") ReferenceThingDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/resource")
    public String createResourceDescriptor(@ModelAttribute("descriptor") ResourceDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }

    @PreAuthorize("hasRole('ROLE_VIEWER')")
    @PostMapping(value = "/string")
    public String createStringDescriptor(@ModelAttribute("descriptor") StringDescriptor descriptor) {
        descriptorRepositoryHelper.save(descriptor);

        return "redirect:/descriptor/" + descriptor.type.getTypeName() + "/" + descriptor.getDescriptorID();
    }
}
