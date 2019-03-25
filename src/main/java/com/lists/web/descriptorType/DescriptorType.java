package com.lists.web.descriptorType;

import com.lists.web.AuditedEntity;
import com.lists.web.descriptor.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nick on 2/18/2018.
 */

@Entity
public class DescriptorType extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer descriptorTypeID;

    private String Title;
    private String valueType;
    private Boolean isNullable;
    private Boolean isUnique;

    public Integer getDescriptorTypeID() {
        return descriptorTypeID;
    }

    public void setDescriptorTypeID(Integer descriptorTypeID) {
        this.descriptorTypeID = descriptorTypeID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public Boolean getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(Boolean nullable) {
        isNullable = nullable;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean unique) {
        isUnique = unique;
    }

    public Descriptor createEmptyChild() {
        Descriptor descriptor;
        if (valueType.equals(DescriptorTypes.DATE.getTypeName())) {
            descriptor = new DateDescriptor();
        } else if (valueType.equals(DescriptorTypes.DOUBLE.getTypeName())) {
            descriptor = new DoubleDescriptor();
        } else if (valueType.equals(DescriptorTypes.INTEGER.getTypeName())) {
            descriptor = new IntegerDescriptor();
        } else if (valueType.equals(DescriptorTypes.LOCATION.getTypeName())) {
            descriptor = new LocationDescriptor();
        } else if (valueType.equals(DescriptorTypes.REFERENCE_THING.getTypeName())) {
            descriptor = new ReferenceThingDescriptor();
        } else if (valueType.equals(DescriptorTypes.RESOURCE.getTypeName())) {
            descriptor = new ResourceDescriptor();
        } else if (valueType.equals(DescriptorTypes.STRING.getTypeName())) {
            descriptor = new StringDescriptor();
        } else {
            throw new IllegalStateException("DescriptorType tried to make child with valueType=" + valueType);
        }
        descriptor.setDescriptorType(this);
        return descriptor;
    }
}
