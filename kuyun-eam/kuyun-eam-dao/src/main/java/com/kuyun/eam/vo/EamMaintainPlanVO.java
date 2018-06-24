package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamMaintainPlan;

/**
 * Created by user on 1/28/2018.
 */
public class EamMaintainPlanVO extends EamMaintainPlan {
    private String equipmentCategoryName;
    private String equipmentName;
    private String orgName;
    private String maintainUsers;
    private String strNextMaintainDate;
    private Integer limit;
    private String search;
    private Integer offset;

    private String orderByClause;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getMaintainUsers() {
        return maintainUsers;
    }

    public void setMaintainUsers(String maintainUsers) {
        this.maintainUsers = maintainUsers;
    }

    public String getStrNextMaintainDate() {
        return strNextMaintainDate;
    }

    public void setStrNextMaintainDate(String strNextMaintainDate) {
        this.strNextMaintainDate = strNextMaintainDate;
    }

    public String getEquipmentCategoryName() {
        return equipmentCategoryName;
    }

    public void setEquipmentCategoryName(String equipmentCategoryName) {
        this.equipmentCategoryName = equipmentCategoryName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

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
