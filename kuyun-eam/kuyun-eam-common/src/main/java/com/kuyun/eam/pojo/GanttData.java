package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2018-03-21.
 */
public class GanttData implements Serializable {
    private Date startDate;
    private Date endDate;
    private Integer dataElementId;

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

    public Integer getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(Integer dataElementId) {
        this.dataElementId = dataElementId;
    }
}
