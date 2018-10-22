package com.lists.web.thing;

import com.lists.web.comparator.Comparator;
import com.lists.web.compares.Compares;
import com.lists.web.descriptor.Descriptor;
import com.lists.web.descriptorType.DescriptorType;

import java.util.Map;

/**
 * Created by nick on 10/17/2018.
 */
public class ThingsRowView {
    private Thing thing;
    private Map<Comparator,Compares> comparesMap;
    private Map<DescriptorType,Descriptor> descriptorMap;

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public Map<Comparator, Compares> getComparesMap() {
        return comparesMap;
    }

    public void setComparesMap(Map<Comparator, Compares> comparesMap) {
        this.comparesMap = comparesMap;
    }

    public Map<DescriptorType, Descriptor> getDescriptorMap() {
        return descriptorMap;
    }

    public void setDescriptorMap(Map<DescriptorType, Descriptor> descriptorMap) {
        this.descriptorMap = descriptorMap;
    }
}
