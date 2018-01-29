package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamAlarm;

/**
 * Created by user on 2018-01-28.
 */
public class EamAlarmVO extends EamAlarm {

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String targetUserName;
    private String alarmContent;
    private String alarmLableName;

    private boolean checked;

    public String getTargetUserName() {
        return targetUserName;
    }

    public void setTargetUserName(String targetUserName) {
        this.targetUserName = targetUserName;
    }

    public String getAlarmContent() {
        return AlarmUtil.getAlarmContent(getAlarmType(), getUpperBound(), getLowerBound(), getDuration());
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public String getAlarmLableName() {
        return alarmLableName;
    }

    public void setAlarmLableName(String alarmLableName) {
        this.alarmLableName = alarmLableName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
