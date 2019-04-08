package com.lists.web.customSet;

import com.lists.web.AuditedEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nick on 4/7/2019.
 */

@Entity
public class CustomSet extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customSetID;
    private String title;

    public Integer getCustomSetID() {
        return customSetID;
    }

    public void setCustomSetID(Integer customSetID) {
        this.customSetID = customSetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
