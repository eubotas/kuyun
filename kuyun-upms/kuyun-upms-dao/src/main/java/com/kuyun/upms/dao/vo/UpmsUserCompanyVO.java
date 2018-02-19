package com.kuyun.upms.dao.vo;

import com.kuyun.upms.dao.model.UpmsUserCompany;

/**
 * Created by user on 2018-01-31.
 */
public class UpmsUserCompanyVO extends UpmsUserCompany {

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}
