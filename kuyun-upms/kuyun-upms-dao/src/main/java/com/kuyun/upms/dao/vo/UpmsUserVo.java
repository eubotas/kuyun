package com.kuyun.upms.dao.vo;

import com.kuyun.upms.dao.model.UpmsUser;

/**
 * Created by user on 2017-07-25.
 */
public class UpmsUserVo extends UpmsUser {

    private String orgName;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
