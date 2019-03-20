package com.lists.web.thing;

import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;

import java.util.Collection;
import java.util.Map;

/**
 * Created by nick on 10/17/2018.
 */
public class ThingsRowView {
    private Thing thing;
    private Map<String,Compares> comparesMap;
    private Map<String,Collection<Descriptor>> descriptorMap;

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Map<String, Compares> getComparesMap() {
        return comparesMap;
    }

    public void setComparesMap(Map<String, Compares> comparesMap) {
        this.comparesMap = comparesMap;
    }

    public Map<String, Collection<Descriptor>> getDescriptorMap() {
        return descriptorMap;
    }

    public void setDescriptorMap(Map<String, Collection<Descriptor>> descriptorMap) {
        this.descriptorMap = descriptorMap;
    }
}
