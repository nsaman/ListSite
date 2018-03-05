package com.lists.web.descriptorType;

import com.lists.web.descriptor.Descriptor;
import com.lists.web.thing.IThingRepository;
import com.lists.web.thing.Thing;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by nick on 3/4/2018.
 */
public enum DescriptorTypes {
    THING_REFERENCE, STRING, INTEGER, DOUBLE, DATE, LOCATION, RESOURCE, LONGTEXT;

    @Autowired
    private IThingRepository thingRepository;

    public static DescriptorTypes byString(String type) {
        switch (type) {
            case "thingReference":
                return THING_REFERENCE;
            case "string":
                return STRING;
            case "integer":
                return INTEGER;
            case "double":
                return DOUBLE;
            case "date":
                return DATE;
            case "location":
                return LOCATION;
            case "resource":
                return RESOURCE;
            case "longText":
                return LONGTEXT;
        }

        return null;
    }

    public Descriptor castStringByType(DescriptorTypes type, String value) throws Exception {

        Descriptor descriptor = new Descriptor();

        switch (type) {
            case THING_REFERENCE:
                Thing thing = thingRepository.findOne(Integer.getInteger(value));
                descriptor.setDescribedThing(thing);
                return descriptor;
            case STRING:
                descriptor.setStringValue(value);
                return descriptor;
            case INTEGER:
                descriptor.setIntValue(Integer.getInteger(value));
                return descriptor;
            case DOUBLE:
                descriptor.setDoubleValue(Double.parseDouble(value));
                return descriptor;
            case DATE:
                LocalDateTime dateTime = LocalDateTime.parse(value);
                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                descriptor.setDateValue(date);
                return descriptor;
            case LOCATION:
                String[] values = value.split(",");
                descriptor.setLongitudeValue(Double.parseDouble(values[0]));
                descriptor.setLatitudeValue(Double.parseDouble(values[1]));
                return descriptor;
            case RESOURCE:
                descriptor.setResourceValue(value);
                return descriptor;
            case LONGTEXT:
                /*descriptor.set(Integer.getInteger(value));
                return descriptor;*/
        }

        return null;
    }
}
