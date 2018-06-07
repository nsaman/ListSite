package com.lists.web.comparator;

import javax.persistence.*;

/**
 * Created by nick on 6/5/2018.
 */
@Entity
public class Comparator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comparatorID;
    private String title;
    private String createUserID;
    private String changeUserID;

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
