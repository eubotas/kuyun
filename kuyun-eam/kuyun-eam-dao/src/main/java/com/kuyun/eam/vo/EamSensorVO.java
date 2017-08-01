package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamSensor;

import java.util.Date;

/**
 * Created by user on 2017-08-01.
 */
public class EamSensorVO extends EamSensor {
    private Date startDate;
    private Date endDate;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
