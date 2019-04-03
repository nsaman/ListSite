package com.lists.web.thing;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class UpdateThingRequest {

    @NotNull
    private Integer thingID;
    @NotNull
    private String title;
    @NotNull
    private Boolean isAbstract;
    @NotNull
    private Integer parentThingId;
    @NotNull
    private Map<Integer, String> descriptors;

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

    public Integer getParentThingId() {
        return parentThingId;
    }

    public void setParentThingId(Integer parentThingId) {
        this.parentThingId = parentThingId;
    }

    public Map<Integer, String> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Map<Integer, String> descriptors) {
        this.descriptors = descriptors;
    }
}
