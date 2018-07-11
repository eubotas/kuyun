package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by user on 2018-06-07.
 */
public class ShirtInfo implements Serializable {
    private String name;
    private String code;
    private String startTime;
    private String endTime;
    private Date startDate;
    private Date endDate;
    private BigDecimal stopTime;
    private BigDecimal shirtTime;



    /**
     * 生产累计时间(按分钟统计)
     * */
    private BigDecimal sumTime;

    public BigDecimal getSumTime() {
        return sumTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setSumTime(BigDecimal sumTime) {
        this.sumTime = sumTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public BigDecimal getStopTime() {
        return stopTime;
    }

    public void setStopTime(BigDecimal stopTime) {
        this.stopTime = stopTime;
    }

    public BigDecimal getShirtTime() {
        return shirtTime;
    }

    public void setShirtTime(BigDecimal shirtTime) {
        this.shirtTime = shirtTime;
    }
}
