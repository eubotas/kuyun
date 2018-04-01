package com.kuyun.upms.common.pojo;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class AuthRequest implements Serializable {
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
