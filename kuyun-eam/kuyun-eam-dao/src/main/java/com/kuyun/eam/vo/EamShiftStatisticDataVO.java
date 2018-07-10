package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamShiftStatisticData;

/**
 * Created by user on 2018-07-09.
 */
public class EamShiftStatisticDataVO extends EamShiftStatisticData {
    /**
     * format: "YYYY-MM-DD""
     */
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
