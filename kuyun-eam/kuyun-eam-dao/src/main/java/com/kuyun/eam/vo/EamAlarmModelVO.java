package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamAlarmModel;

/**
 * Created by user on 2018-01-25.
 */
public class EamAlarmModelVO extends EamAlarmModel {
    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String dataElementName;

    private String alarmContent;


    public String getDataElementName() {
        return dataElementName;
    }

    public void setDataElementName(String dataElementName) {
        this.dataElementName = dataElementName;
    }

    public String getAlarmContent() {
        return AlarmUtil.getAlarmContent(getAlarmType(), getUpperBound(), getLowerBound(), getDuration());
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

}
