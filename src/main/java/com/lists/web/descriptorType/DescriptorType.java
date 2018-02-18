package com.lists.web.descriptorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nick on 2/18/2018.
 */

@Entity
public class DescriptorType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer descriptorTypeID;

    private String Title;
    private String valueType;
    private Boolean isNullable;
    private String createUserID;
    private String changeUserID;

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

    public String getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserID = createUserID;
    }

    public String getChangeUserID() {
        return changeUserID;
    }

    public void setChangeUserID(String changeUserID) {
        this.changeUserID = changeUserID;
    }
}
