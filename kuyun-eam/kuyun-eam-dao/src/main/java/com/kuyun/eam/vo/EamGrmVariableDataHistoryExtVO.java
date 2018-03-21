package com.kuyun.eam.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2018-02-15.
 */
public class EamGrmVariableDataHistoryExtVO implements Serializable {

    private String unit;
    private String lableName;
    private Integer dataElementId;
    private String value;
    private Date updateTime;

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public Integer getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(Integer dataElementId) {
        this.dataElementId = dataElementId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
