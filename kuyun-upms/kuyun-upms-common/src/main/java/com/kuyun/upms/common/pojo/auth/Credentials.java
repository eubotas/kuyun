package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class Credentials implements Serializable{
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
