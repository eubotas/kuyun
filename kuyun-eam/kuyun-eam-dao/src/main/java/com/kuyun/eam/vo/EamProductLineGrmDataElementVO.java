package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamGrmVariable;

public class EamProductLineGrmDataElementVO extends EamGrmVariable {
    private static final long serialVersionUID = 1L;

    private String morningShiftStartTime;

    private String morningShiftEndTime;

    private String middleShiftStartTime;

    private String middleShiftEndTime;

    private String nightShiftStartTime;

    private String nightShiftEndTime;

    private String dataType;

    private Boolean isSummation;
    private Boolean isStatistic;

    public String getMorningShiftStartTime() {
        return morningShiftStartTime;
    }

    public void setMorningShiftStartTime(String morningShiftStartTime) {
        this.morningShiftStartTime = morningShiftStartTime;
    }

    public String getMorningShiftEndTime() {
        return morningShiftEndTime;
    }

    public void setMorningShiftEndTime(String morningShiftEndTime) {
        this.morningShiftEndTime = morningShiftEndTime;
    }

    public String getMiddleShiftStartTime() {
        return middleShiftStartTime;
    }

    public void setMiddleShiftStartTime(String middleShiftStartTime) {
        this.middleShiftStartTime = middleShiftStartTime;
    }

    public String getMiddleShiftEndTime() {
        return middleShiftEndTime;
    }

    public void setMiddleShiftEndTime(String middleShiftEndTime) {
        this.middleShiftEndTime = middleShiftEndTime;
    }

    public String getNightShiftStartTime() {
        return nightShiftStartTime;
    }

    public void setNightShiftStartTime(String nightShiftStartTime) {
        this.nightShiftStartTime = nightShiftStartTime;
    }

    public String getNightShiftEndTime() {
        return nightShiftEndTime;
    }

    public void setNightShiftEndTime(String nightShiftEndTime) {
        this.nightShiftEndTime = nightShiftEndTime;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getSummation() {
        return isSummation;
    }

    public void setSummation(Boolean summation) {
        isSummation = summation;
    }

    public Boolean getStatistic() {
        return isStatistic;
    }

    public void setStatistic(Boolean statistic) {
        isStatistic = statistic;
    }
}
