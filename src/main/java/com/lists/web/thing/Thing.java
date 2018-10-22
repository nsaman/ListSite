package com.lists.web.thing;


import com.lists.web.AuditedEntity;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nick on 1/23/2018.
 */

@Entity
public class Thing extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer thingID;

    private String title;
    private Boolean isAbstract;
    @ManyToOne
    @JoinColumn(name="parentThingID",foreignKey=@ForeignKey(name="FK_thing_1"))
    private Thing parentThing;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "describedThingID")
    private List<Descriptor> descriptors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "thingID")
    private List<Compares> compares = new ArrayList<>();

    public Integer getThingID() {
        return thingID;
    }

    public Boolean getAbstract() {
        return isAbstract;
    }

    public void setAbstract(Boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public List<Descriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(List<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public List<Compares> getCompares() {
        return compares;
    }

    public void setCompares(List<Compares> compares) {
        this.compares = compares;
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
