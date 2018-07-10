package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamEquipment;

import java.math.BigDecimal;

/**
 * Created by user on 5/1/2017.
 */
public class EamEquipmentVO extends EamEquipment {
    private String morningShiftStartTime;
    private String morningShiftEndTime;
    private String middleShiftStartTime;
    private String middleShiftEndTime;
    private String nightShiftStartTime;
    private String nightShiftEndTime;
    private BigDecimal electricityUnitPrice;


    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String dtuId;

    private Boolean checked;

    public BigDecimal getElectricityUnitPrice() {
        return electricityUnitPrice;
    }

    public void setElectricityUnitPrice(BigDecimal electricityUnitPrice) {
        this.electricityUnitPrice = electricityUnitPrice;
    }

    public String getMorningShiftStartTime() {
        return morningShiftStartTime;
    }

    public void setMorningShiftStartTime(String morningShiftStartTime) {
        this.morningShiftStartTime = morningShiftStartTime;
    }

    public String getMorningShiftEndTime() {
        return morningShiftEndTime;
    }

    public void setMorningShiftEndTime(String morningShiftEndTime) {
        this.morningShiftEndTime = morningShiftEndTime;
    }

    public String getMiddleShiftStartTime() {
        return middleShiftStartTime;
    }

    public void setMiddleShiftStartTime(String middleShiftStartTime) {
        this.middleShiftStartTime = middleShiftStartTime;
    }

    public String getMiddleShiftEndTime() {
        return middleShiftEndTime;
    }

    public void setMiddleShiftEndTime(String middleShiftEndTime) {
        this.middleShiftEndTime = middleShiftEndTime;
    }

    public String getNightShiftStartTime() {
        return nightShiftStartTime;
    }

    public void setNightShiftStartTime(String nightShiftStartTime) {
        this.nightShiftStartTime = nightShiftStartTime;
    }

    public String getNightShiftEndTime() {
        return nightShiftEndTime;
    }

    public void setNightShiftEndTime(String nightShiftEndTime) {
        this.nightShiftEndTime = nightShiftEndTime;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }
}
