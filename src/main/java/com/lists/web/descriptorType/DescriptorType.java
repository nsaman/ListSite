package com.lists.web.descriptorType;

import com.lists.web.AuditedEntity;

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
}
