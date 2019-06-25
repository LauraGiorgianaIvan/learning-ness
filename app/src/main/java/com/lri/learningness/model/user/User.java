package com.lri.learningness.model.user;

import com.lri.learningness.tools.Tools;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String id;
    private String username;
    private String password;
    private String dateRegistered;
    private UserType userType;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public User setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
        return this;
    }

    public User setDateRegistered(Date date) {
        this.dateRegistered = Tools.dateToString(date);
        return this;
    }

    public boolean isAdmin() {
        return userType == UserType.ADMIN;
    }
}
