package com.lists.web.thing;


import javax.persistence.*;

/**
 * Created by nick on 1/23/2018.
 */

@Entity
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer thingID;

    private String title;
    private String createUserID;
    private String changeUserID;
    private Boolean isAbstract;
    @ManyToOne
    @JoinColumn(name="parentThingID",foreignKey=@ForeignKey(name="FK_ParentThing"))
    private Thing parentThing;

    public Integer getThingID() {
        return thingID;
    }

    public void setThingID(Integer thingID) {
        this.thingID = thingID;
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

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public void setIsAbstract(Boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public Thing getParentThing() {
        return parentThing;
    }

    public void setParentThing(Thing parentThing) {
        this.parentThing = parentThing;
    }
}
