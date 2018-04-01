package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class AuthResponse implements Serializable {
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
