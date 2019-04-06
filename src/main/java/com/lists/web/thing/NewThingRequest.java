package com.lists.web.thing;

import com.lists.web.comparator.Comparator;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

public class NewThingRequest {

    @NotNull
    private String title;
    @NotNull
    private Boolean isAbstract;
    @NotNull
    private Integer parentThingId;
    @NotNull
    private Map<Integer, String> descriptors;
    @NotNull
    private Set<Comparator> childComparators;

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

    public Set<Comparator> getChildComparators() {
        return childComparators;
    }

    public void setChildComparators(Set<Comparator> childComparators) {
        this.childComparators = childComparators;
    }
}
