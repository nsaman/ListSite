package com.lists.web.descriptorType;

import com.lists.web.descriptor.*;

/**
 * Created by nick on 3/4/2018.
 */
public enum DescriptorTypes {
    REFERENCE_THING("referenceThing", ReferenceThingDescriptor.class),
    STRING("string", StringDescriptor.class),
    INTEGER("integer", IntegerDescriptor.class),
    DOUBLE("double", DoubleDescriptor.class),
    DATE("date", DateDescriptor.class),
    LOCATION("location", LocationDescriptor.class),
    RESOURCE("resource", ResourceDescriptor.class);

    private final String typeName;
    private final Class descriptorClazz;

    DescriptorTypes(String typeName, Class descriptorClazz) {
        this.typeName = typeName;
        this.descriptorClazz = descriptorClazz;
    }

    public String getTypeName() {
        return typeName;
    }

    public Class getDescriptorClazz() {
        return descriptorClazz;
    }

    public static DescriptorTypes byString(String type) {
        switch (type) {
            case "referenceThing":
                return REFERENCE_THING;
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
        }

        return null;
    }
}
