package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;

import javax.persistence.Entity;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class StringDescriptor extends Descriptor {

    private String value;

    public StringDescriptor() {
        super(DescriptorTypes.STRING);
    }

    @Override
    public String getReadableString() {
        return value;
    }

    @Override
    public void setValueFromString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
