package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamGrmVariableDataByDay;

/**
 * Created by user on 2018-07-04.
 */
public class EamGrmVariableDataByDayVO extends EamGrmVariableDataByDay {

    public Integer year;
    public Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
