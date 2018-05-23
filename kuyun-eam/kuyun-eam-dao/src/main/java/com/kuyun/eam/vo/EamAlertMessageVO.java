package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamAlertMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018-05-23.
 */
public class EamAlertMessageVO extends EamAlertMessage {
    private Date startDate;
    private Date endDate;

    private String productLineName;
    private String equipmentName;
    private String workContent;
    private Date nextMaintainDate;
    private String realname;
    private List<String> productLineIds;

    private Integer limit;

    private Integer offset;

    public List<String> getProductLineIds() {
        return productLineIds;
    }

    public void setProductLineIds(List<String> productLineIds) {
        this.productLineIds = productLineIds;
    }

    private String orderByClause;

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Date getNextMaintainDate() {
        return nextMaintainDate;
    }

    public void setNextMaintainDate(Date nextMaintainDate) {
        this.nextMaintainDate = nextMaintainDate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

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
