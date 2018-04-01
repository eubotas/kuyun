package com.kuyun.upms.common.pojo;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class Token implements Serializable {
    private String principal;
    private String credentials;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}
