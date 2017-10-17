package com.kuyun.eam.vo;

import com.kuyun.eam.common.constant.AlarmTarget;
import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarmRecord;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2017-09-11.
 */
public class EamAlarmRecordVO extends EamAlarmRecord {
    private Date startDate;
    private Date endDate;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String alarmType;
    private BigDecimal upperBound;
    private BigDecimal lowerBound;
    private BigDecimal duration;
    private String alarmTarget;
    private String phone;
    private String email;
    private String userName;
    private String equipmentName;
    private String equipmentNumber;
    private String equipmentModelPropertyName;
    private List<String> equipmentIds;

    public List<String> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(List<String> equipmentIds) {
        this.equipmentIds = equipmentIds;
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

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getAlarmTarget() {
        return alarmTarget;
    }

    public void setAlarmTarget(String alarmTarget) {
        this.alarmTarget = alarmTarget;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModelPropertyName() {
        return equipmentModelPropertyName;
    }

    public void setEquipmentModelPropertyName(String equipmentModelPropertyName) {
        this.equipmentModelPropertyName = equipmentModelPropertyName;
    }

    public Date getAlarmTime(){
        return this.getCreateTime();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getAlarmContent(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getEquipmentName());
        stringBuilder.append("(");
        stringBuilder.append(getEquipmentNumber());
        stringBuilder.append(")  ");
        stringBuilder.append(getEquipmentModelPropertyName() + "  ");
        stringBuilder.append(buildAlarmMessage(getAlarmType()));
        return stringBuilder.toString();
    }

    private String buildAlarmMessage(String alarmType) {
        String result = StringUtils.EMPTY;
        if (AlarmType.VAL_ABOVE.match(alarmType)) {
            result = AlarmType.VAL_ABOVE.getName().replace("X", String.valueOf(getUpperBound()));

        } else if (AlarmType.VAL_BELOW.match(alarmType)) {
            result = AlarmType.VAL_BELOW.getName().replace("Y", String.valueOf(getLowerBound()));

        }
//        else if (AlarmType.VAL_ABOVE_BELOW.match(alarmType)) {
//            result = AlarmType.VAL_ABOVE_BELOW.getName().replace("X", String.valueOf(getUpperBound()))
//                    .replace("Y", String.valueOf(getLowerBound()));
//
//        }
        else if (AlarmType.VAL_ABOVE_BELOW_OFM.match(alarmType)) {
            result = AlarmType.VAL_ABOVE_BELOW_OFM.getName().replace("X", String.valueOf(getUpperBound()))
                    .replace("Y", String.valueOf(getLowerBound()))
                    .replace("M", String.valueOf(getDuration()));

        }
//        else if (AlarmType.X_TIR_Y_REC.match(alarmType)) {
//            result = AlarmType.X_TIR_Y_REC.getName().replace("X", String.valueOf(getUpperBound()))
//                    .replace("Y", String.valueOf(getLowerBound()));
//
//        }
        else if (AlarmType.VAL_BETWEEN.match(alarmType)) {
            result = AlarmType.VAL_BETWEEN.getName().replace("X", String.valueOf(getUpperBound()))
                    .replace("Y", String.valueOf(getLowerBound()));

        } else if (AlarmType.VAL_ABOVE_BOUND.match(alarmType)) {
            result = AlarmType.VAL_ABOVE_BOUND.getName().replace("X", String.valueOf(getUpperBound()))
                    .replace("M", String.valueOf(getDuration()));

        } else if (AlarmType.VAL_BELOW_BOUND.match(alarmType)) {
            result = AlarmType.VAL_BELOW_BOUND.getName().replace("Y", String.valueOf(getLowerBound()))
                    .replace("M", String.valueOf(getDuration()));

        } else if (AlarmType.OFFLINE.match(alarmType)) {
            result = AlarmType.OFFLINE.getName();

        }
//        else if (AlarmType.OFFLINE_FOR_MINUTES.match(alarmType)) {
//            handler = offlineForMinutesHandler;
//
//        }
        else if (AlarmType.SWITCH_ON.match(alarmType)) {

            result = AlarmType.SWITCH_ON.getName();
        } else if (AlarmType.SWITCH_OFF.match(alarmType)) {

            result = AlarmType.SWITCH_OFF.getName();
        }

        return result;
    }

    public String getAlarmTargetUser(){
        StringBuilder result = new StringBuilder();
        if (AlarmTarget.EMAIL.match(getAlarmTarget())){
            result.append(getUserName());
            result.append("(");
            result.append(getEmail());
            result.append(")");
        }else if (AlarmTarget.SMS.match(getAlarmTarget())){
            result.append(getUserName());
            result.append("(");
            result.append(getPhone());
            result.append(")");
        }

        return result.toString();
    }

}
