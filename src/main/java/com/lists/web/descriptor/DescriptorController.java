package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/descriptor")
public class DescriptorController {
    @Autowired
    private IDescriptorRepository descriptorRepository;

    @RequestMapping(value = "/{descriptorID}", method = RequestMethod.GET)
    public String getDescriptor(@PathVariable(value="descriptorID") int descriptorID, Model model) {

        Descriptor descriptor = descriptorRepository.findOne(descriptorID);

        model.addAttribute("descriptor", descriptor);

        return "descriptor";
    }

    @RequestMapping(params = {"describedThingID","valueType", ,"isNullable"}, method = RequestMethod.POST)
    public String createDescriptorType(@RequestParam("title") String title, @RequestParam("valueType") String valueType, @RequestParam("isNullable") Boolean isNullable, Model model) {

        DescriptorType descType = new DescriptorType();
        descType.setTitle(title);
        descType.setValueType(valueType);
        descType.setIsNullable(isNullable);

        descTypeRepository.save(descType);

        return "redirect:/descriptortype/" + descType.getDescriptorTypeID();
    }
}
