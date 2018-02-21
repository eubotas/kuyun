package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by user on 2018-02-21.
 */
public class Position implements Serializable{
    private String equipmentId;
    private BigDecimal left;
    private BigDecimal top;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public BigDecimal getLeft() {
        return left;
    }

    public void setLeft(BigDecimal left) {
        this.left = left;
    }

    public BigDecimal getTop() {
        return top;
    }

    public void setTop(BigDecimal top) {
        this.top = top;
    }
}
