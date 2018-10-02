package com.lists.web.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by nick on 9/30/2018.
 */

@Entity
@Table(name="users")
public class UserStub {
    @Id
    private String username;

    private Boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
