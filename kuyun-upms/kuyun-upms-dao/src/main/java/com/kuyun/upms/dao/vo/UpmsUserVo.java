package com.kuyun.upms.dao.vo;

import com.kuyun.upms.dao.model.UpmsUser;

/**
 * Created by user on 2017-07-25.
 */
public class UpmsUserVo extends UpmsUser {

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
