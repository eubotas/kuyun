package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamGrmVariableData;

import java.util.Date;

/**
 * Created by user on 2018-02-15.
 */
public class EamGrmVariableDataVO extends EamGrmVariableData {
    private Date startDate;
    private Date endDate;
    private String dataElementName;
    private String equipmentIdIsNull;
    private String unit;

    private Integer limit;

    private Integer offset;

    private String orderByClause;


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEquipmentIdIsNull() {
        return equipmentIdIsNull;
    }

    public void setEquipmentIdIsNull(String equipmentIdIsNull) {
        this.equipmentIdIsNull = equipmentIdIsNull;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public String getDataElementName() {
        return dataElementName;
    }

    public void setDataElementName(String dataElementName) {
        this.dataElementName = dataElementName;
    }
}