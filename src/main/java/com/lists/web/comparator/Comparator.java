package com.lists.web.comparator;

import com.lists.web.AuditedEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nick on 6/5/2018.
 */
@Entity
public class Comparator extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comparatorID;
    private String title;

    public Integer getComparatorID() {
        return comparatorID;
    }

    public void setComparatorID(Integer comparatorID) {
        this.comparatorID = comparatorID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
