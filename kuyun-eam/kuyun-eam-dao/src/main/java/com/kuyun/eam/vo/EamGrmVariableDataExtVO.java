package com.kuyun.eam.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2018-02-15.
 */
public class EamGrmVariableDataExtVO implements Serializable {
    private String dataElementName;
    private String unit;
    private String value;
    private Date updateTime;

    public String getDataElementName() {
        return dataElementName;
    }

    public void setDataElementName(String dataElementName) {
        this.dataElementName = dataElementName;
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
