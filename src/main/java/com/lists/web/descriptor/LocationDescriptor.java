package com.lists.web.descriptor;

import com.lists.web.descriptorType.DescriptorTypes;

import javax.persistence.Entity;

/**
 * Created by nick on 10/28/2018.
 */

@Entity
public class LocationDescriptor extends Descriptor {

    private Double latitude;
    private Double longitude;

    public LocationDescriptor() {
        super(DescriptorTypes.LOCATION);
    }

    @Override
    public String getReadableString() {
        return (latitude!=null?latitude.toString():"") + "," + (longitude!=null?longitude.toString():"");
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
