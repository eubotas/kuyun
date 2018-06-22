package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamShiftDataElementValue;

import java.util.Date;

/**
 * Created by user on 2018-06-06.
 */
public class EamShiftDataElementValueVO extends EamShiftDataElementValue {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
