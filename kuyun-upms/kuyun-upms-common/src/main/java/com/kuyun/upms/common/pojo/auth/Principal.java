package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class Principal implements Serializable{
    private String login;
    private String apiKey;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
