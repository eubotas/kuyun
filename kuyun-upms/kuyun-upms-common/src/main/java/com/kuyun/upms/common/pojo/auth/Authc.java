package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class Authc implements Serializable{
    private Principal principal;
    private Credentials credentials;

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
