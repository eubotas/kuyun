package com.kuyun.upms.common.pojo.auth;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018-03-29.
 */
public class Authz implements Serializable{
    private List<String> roles;
    private List<String> permissions;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
