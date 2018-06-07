package com.lists.web.descriptorType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */

@Controller
@RequestMapping(path="/descriptorType")
public class DescriptorTypeController {
    @Autowired
    private IDescriptorTypeRepository descTypeRepository;

    @RequestMapping(value = "/{descriptorTypeID}", method = RequestMethod.GET)
    public String getDescriptorType(@PathVariable(value="descriptorTypeID") int descriptorTypeID, Model model) {

        DescriptorType descType = descTypeRepository.findOne(descriptorTypeID);

        model.addAttribute("descriptorType", descType);


        return "descriptortype";
    }

    @RequestMapping(params = {"title","valueType","isNullable"}, method = RequestMethod.POST)
    public String createDescriptorType(@RequestParam("title") String title, @RequestParam("valueType") String valueType, @RequestParam("isNullable") Boolean isNullable, Model model) {

        if(DescriptorTypes.byString(valueType) == null)
            return "400";


        DescriptorType descType = new DescriptorType();
        descType.setTitle(title);
        descType.setValueType(valueType);
        descType.setIsNullable(isNullable);

        descTypeRepository.save(descType);

        return "redirect:/descriptortype/" + descType.getDescriptorTypeID();
    }
}
