package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;

import javax.persistence.Entity;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class DoubleDescriptor extends Descriptor {

    private Double value;

    public DoubleDescriptor() {
        super(DescriptorTypes.DOUBLE);
    }

    @Override
    public String getReadableString() {
        return value!=null?value.toString():"";
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
