package com.lists.web;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by nick on 10/9/2018.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity {
    @Column(name = "createUserID")
    @CreatedBy
    private String createUserID;

    @Column(name = "changeUserID")
    @LastModifiedBy
    private String changeUserID;

    @Column(name = "createTimestamp", nullable = false, updatable = false)
    @CreatedDate
    private Date createTimestamp;

    @Column(name = "changeTimestamp")
    @LastModifiedDate
    private Date changeTimestamp;

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

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getChangeTimestamp() {
        return changeTimestamp;
    }

    public void setChangeTimestamp(Date changeTimestamp) {
        this.changeTimestamp = changeTimestamp;
    }
}