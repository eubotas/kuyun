package com.kuyun.upms.dao.vo;

import com.kuyun.upms.dao.model.UpmsUserCompany;

/**
 * Created by user on 2018-01-31.
 */
public class UpmsUserCompanyVO extends UpmsUserCompany {

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
