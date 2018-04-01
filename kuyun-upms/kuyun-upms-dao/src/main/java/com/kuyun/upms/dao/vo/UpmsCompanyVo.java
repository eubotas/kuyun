package com.kuyun.upms.dao.vo;

import com.kuyun.upms.dao.model.UpmsCompany;

/**
 * Created by user on 2017-07-25.
 */
public class UpmsCompanyVo extends UpmsCompany {
    private Integer equipmentNumber;
    private Integer onlineNumber;
    private Integer offlineNumber;

    private String search;
    private String adminName;
    private String adminPassword;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
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

    public Integer getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(Integer equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Integer getOnlineNumber() {
        return onlineNumber;
    }

    public void setOnlineNumber(Integer onlineNumber) {
        this.onlineNumber = onlineNumber;
    }

    public Integer getOfflineNumber() {
        return offlineNumber;
    }

    public void setOfflineNumber(Integer offlineNumber) {
        this.offlineNumber = offlineNumber;
    }
}
