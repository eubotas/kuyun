package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;

/**
 * Created by user on 2018-03-29.
 */
public class Info implements Serializable{
    private Authc authc;

    private Authz authz;

    public Authc getAuthc() {
        return authc;
    }

    public void setAuthc(Authc authc) {
        this.authc = authc;
    }

    public Authz getAuthz() {
        return authz;
    }

    public void setAuthz(Authz authz) {
        this.authz = authz;
    }
}
