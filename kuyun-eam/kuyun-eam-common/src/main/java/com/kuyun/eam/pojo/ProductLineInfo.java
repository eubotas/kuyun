package com.kuyun.eam.pojo;

import java.io.Serializable;

/**
 * Created by user on 2018-06-04.
 */
public class ProductLineInfo implements Serializable{

    private Integer totalQuantity;
    private Integer onLineQuantity;
    private Integer alarmQuantity;

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getOnLineQuantity() {
        return onLineQuantity;
    }

    public void setOnLineQuantity(Integer onLineQuantity) {
        this.onLineQuantity = onLineQuantity;
    }

    public Integer getAlarmQuantity() {
        return alarmQuantity;
    }

    public void setAlarmQuantity(Integer alarmQuantity) {
        this.alarmQuantity = alarmQuantity;
    }
}
