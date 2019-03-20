package com.lists.web.thing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lists.web.AuditedEntity;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
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

    @JsonIgnore
    @OneToMany
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "parentThingID")
    private Set<Thing> childThings;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parentThingID", foreignKey = @ForeignKey(name = "FK_thing_1"))
    private Thing parentThing;

    @JsonManagedReference(value="thingCompares")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "thingID")
    private Set<Compares> compares = new HashSet<>();

    @JsonManagedReference
    public Set<Descriptor> getDescriptors() {
        Set<Descriptor> returnDescriptors = new HashSet<>();
        returnDescriptors.addAll(dateDescriptors);
        returnDescriptors.addAll(doubleDescriptors);
        returnDescriptors.addAll(integerDescriptors);
        returnDescriptors.addAll(locationDescriptors);
        returnDescriptors.addAll(referenceThingDescriptors);
        returnDescriptors.addAll(resourceDescriptors);
        returnDescriptors.addAll(stringDescriptors);

        return returnDescriptors;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<DateDescriptor> dateDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<DoubleDescriptor> doubleDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<IntegerDescriptor> integerDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<LocationDescriptor> locationDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<ReferenceThingDescriptor> referenceThingDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<ResourceDescriptor> resourceDescriptors = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "describedThingID")
    private Set<StringDescriptor> stringDescriptors = new HashSet<>();

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

    public Set<Compares> getCompares() {
        return compares;
    }

    public void setCompares(Set<Compares> compares) {
        this.compares = compares;
    }

    public Set<DateDescriptor> getDateDescriptors() {
        return dateDescriptors;
    }

    public void setDateDescriptors(Set<DateDescriptor> dateDescriptors) {
        this.dateDescriptors = dateDescriptors;
    }

    public Set<DoubleDescriptor> getDoubleDescriptors() {
        return doubleDescriptors;
    }

    public void setDoubleDescriptors(Set<DoubleDescriptor> doubleDescriptors) {
        this.doubleDescriptors = doubleDescriptors;
    }

    public Set<IntegerDescriptor> getIntegerDescriptors() {
        return integerDescriptors;
    }

    public void setIntegerDescriptors(Set<IntegerDescriptor> integerDescriptors) {
        this.integerDescriptors = integerDescriptors;
    }

    public Set<LocationDescriptor> getLocationDescriptors() {
        return locationDescriptors;
    }

    public void setLocationDescriptors(Set<LocationDescriptor> locationDescriptors) {
        this.locationDescriptors = locationDescriptors;
    }

    public Set<ReferenceThingDescriptor> getReferenceThingDescriptors() {
        return referenceThingDescriptors;
    }

    public void setReferenceThingDescriptors(Set<ReferenceThingDescriptor> referenceThingDescriptors) {
        this.referenceThingDescriptors = referenceThingDescriptors;
    }

    public Set<ResourceDescriptor> getResourceDescriptors() {
        return resourceDescriptors;
    }

    public void setResourceDescriptors(Set<ResourceDescriptor> resourceDescriptors) {
        this.resourceDescriptors = resourceDescriptors;
    }

    public Set<StringDescriptor> getStringDescriptors() {
        return stringDescriptors;
    }

    public void setStringDescriptors(Set<StringDescriptor> stringDescriptors) {
        this.stringDescriptors = stringDescriptors;
    }

    public Integer getParentThingID() {
        return parentThing==null?null:parentThing.thingID;
    }
}
