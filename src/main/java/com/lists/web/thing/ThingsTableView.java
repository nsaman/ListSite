package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.descriptorType.DescriptorType;

import java.util.List;
import java.util.Map;

/**
 * Created by nick on 10/17/2018.
 */
public class ThingsTableView {

    private Map<Thing, ThingsRowView> thingsMap;
    private List<Comparator> comparesHeaders;
    private List<String> thingHeaders;
    private List<DescriptorType> descriptorHeaders;

    public Map<Thing, ThingsRowView> getThingsMap() {
        return thingsMap;
    }

    public void setThingsMap(Map<Thing, ThingsRowView> thingsMap) {
        this.thingsMap = thingsMap;
    }

    public List<Comparator> getComparesHeaders() {
        return comparesHeaders;
    }

    public void setComparesHeaders(List<Comparator> comparesHeaders) {
        this.comparesHeaders = comparesHeaders;
    }

    public List<String> getThingHeaders() {
        return thingHeaders;
    }

    public void setThingHeaders(List<String> thingHeaders) {
        this.thingHeaders = thingHeaders;
    }

    public List<DescriptorType> getDescriptorHeaders() {
        return descriptorHeaders;
    }

    public void setDescriptorHeaders(List<DescriptorType> descriptorHeaders) {
        this.descriptorHeaders = descriptorHeaders;
    }
}
