package com.lists.web.thing;

import javax.validation.constraints.NotNull;

public class NewThingRequest {

    @NotNull
    private String title;
    @NotNull
    private Boolean isAbstract;
    @NotNull
    private Integer parentThingId;

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
}
