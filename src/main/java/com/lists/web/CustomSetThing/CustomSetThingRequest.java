package com.lists.web.CustomSetThing;

import javax.validation.constraints.NotNull;

public class CustomSetThingRequest {

    @NotNull
    private Integer thingID;
    @NotNull
    private Integer customSetID;

    public Integer getThingID() {
        return thingID;
    }

    public void setThingID(Integer thingID) {
        this.thingID = thingID;
    }

    public Integer getCustomSetID() {
        return customSetID;
    }

    public void setCustomSetID(Integer customSetID) {
        this.customSetID = customSetID;
    }
}
