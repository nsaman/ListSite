package com.lists.web.descriptor;

import com.lists.web.AuditedEntity;
import com.lists.web.descriptorType.DescriptorType;
import com.lists.web.thing.Thing;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nick on 1/24/2018.
 */

@Entity
public class Descriptor extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer descriptorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="describedThingID",foreignKey=@ForeignKey(name="FK_descriptor_1"))
    private Thing describedThing;

    @ManyToOne
    @JoinColumn(name="descriptorTypeID",foreignKey=@ForeignKey(name="FK_descriptor_3"))
    private DescriptorType descriptorType;

    @ManyToOne
    @JoinColumn(name="referenceThingID",foreignKey=@ForeignKey(name="FK_descriptor_2"))
    private Thing referenceThing;
    private String stringValue;
    private Integer intValue;
    private Double doubleValue;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateValue;
    private Double longitudeValue;
    private Double latitudeValue;
    private String resourceValue;

    public Integer getDescriptorID() {
        return descriptorID;
    }

    public void setDescriptorID(Integer descriptorID) {
        this.descriptorID = descriptorID;
    }

    public Thing getDescribedThing() {
        return describedThing;
    }

    public void setDescribedThing(Thing describedThing) {
        this.describedThing = describedThing;
    }

    public Thing getReferenceThing() {
        return referenceThing;
    }

    public void setReferenceThing(Thing referenceThing) {
        this.referenceThing = referenceThing;
    }

    public DescriptorType getDescriptorType() {
        return descriptorType;
    }

    public void setDescriptorType(DescriptorType descriptorType) {
        this.descriptorType = descriptorType;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Double getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(Double longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public Double getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(Double latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public String getResourceValue() {
        return resourceValue;
    }

    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }
}
