package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamStatisticDataByDay;

/**
 * Created by user on 2018-07-04.
 */
public class EamStatisticDataByDayVO extends EamStatisticDataByDay {

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
