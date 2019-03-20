package com.lists.web.thing;

public class NewThingRequest {

    private String title;
    private Boolean isAbstract;
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
