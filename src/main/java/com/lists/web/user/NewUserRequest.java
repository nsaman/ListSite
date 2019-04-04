package com.lists.web.user;

import javax.validation.constraints.NotNull;

public class NewUserRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String repeatPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
