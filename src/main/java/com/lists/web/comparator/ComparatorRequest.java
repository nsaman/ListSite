package com.lists.web.comparator;

import javax.validation.constraints.NotNull;

/**
 * Created by nick on 10/3/2018.
 */
public class ComparatorRequest {
    @NotNull
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
