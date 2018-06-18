package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 2018-06-17.
 */
public class ShiftStatus implements Serializable {
    private String productLineName;
    private String status;
    private Date date;


    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
