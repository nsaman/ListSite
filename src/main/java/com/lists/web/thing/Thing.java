package com.lists.web.thing;


import com.lists.web.AuditedEntity;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<Descriptor> descriptors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "thingID")
    private Set<Compares> compares = new HashSet<>();

    public Integer getThingID() {
        return thingID;
    }

    public Boolean getAbstract() {
        return isAbstract;
    }

    public void setAbstract(Boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public Set<Descriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Set<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public Set<Compares> getCompares() {
        return compares;
    }

    public void setCompares(Set<Compares> compares) {
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
