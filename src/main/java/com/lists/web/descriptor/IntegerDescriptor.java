package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;

import javax.persistence.Entity;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class IntegerDescriptor extends Descriptor {

    private Integer value;

    public IntegerDescriptor() {
        super(DescriptorTypes.INTEGER);
    }

    @Override
    public String getReadableString() {
        return value.toString();
    }

    @Override
    public void setValueFromString(String value) {
        this.value = Integer.parseInt(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
