package com.kuyun.eam.vo;

import java.io.Serializable;

public class EamHomeStatusSummaryVO implements Serializable {

	private String statusName;
    private Integer count;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
