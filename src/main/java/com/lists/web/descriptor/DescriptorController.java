package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.descriptorType.DescriptorTypes;
import com.lists.web.descriptorType.IDescriptorTypeRepository;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
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
    @Autowired
    private IDescriptorTypeRepository descriptorTypeRepository;
    @Autowired
    private IThingRepository thingRepository;

    @RequestMapping(value = "/{descriptorID}", method = RequestMethod.GET)
    public String getDescriptor(@PathVariable(value="descriptorID") int descriptorID, Model model) {

        Descriptor descriptor = descriptorRepository.findOne(descriptorID);

        model.addAttribute("descriptor", descriptor);

        return "descriptor";
    }

    @RequestMapping(params = {"describedThingID", "descriptorTypeID", "value"}, method = RequestMethod.POST)
    public String createDescriptor(@RequestParam("describedThingID") Integer describedThingID, @RequestParam("descriptorTypeID") Integer descriptorTypeID, @RequestParam("value") String value, Model model) {

        DescriptorType dt = descriptorTypeRepository.findOne(descriptorTypeID);
        Thing thing = thingRepository.findOne(describedThingID);

        DescriptorTypes descriptorTypes = DescriptorTypes.DATE;
        Descriptor descriptor;

        try {
            descriptor = descriptorTypes.castStringByType(DescriptorTypes.byString(dt.getValueType()),value);
        } catch (Exception e) {
            return "400";
        }
        descriptor.setDescribedThing(thing);
        descriptor.setDescriptorType(dt);

        descriptorRepository.save(descriptor);

        return "redirect:/descriptor/" + descriptor.getDescriptorID();
    }
}
