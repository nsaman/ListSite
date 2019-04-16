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

    public static Double[] formatLocationPair(String string) {
        String[] split = string.split(",");
        if(split.length != 2)
            throw new IllegalArgumentException("Malformed lattitude,longitude=" + string);
        Double[] locationPair = new Double[2];
        locationPair[0] = Double.parseDouble(split[0]);
        locationPair[1] = Double.parseDouble(split[1]);
        return locationPair;
    }
}
