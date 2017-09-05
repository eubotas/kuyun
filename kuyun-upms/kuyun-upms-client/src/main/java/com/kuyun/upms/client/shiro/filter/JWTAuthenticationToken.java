package com.kuyun.upms.client.shiro.filter;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTAuthenticationToken implements AuthenticationToken {

    private Object username;
    private String token;

    public JWTAuthenticationToken(Object username, String token) {
        this.username = username;
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return getUsername();
    }

    @Override
    public Object getCredentials() {
        return getToken();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }
}
