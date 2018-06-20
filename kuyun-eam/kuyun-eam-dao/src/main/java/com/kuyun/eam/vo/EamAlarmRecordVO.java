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


    private String productLineName;
    private String alarmName;
    private String alarmType;
    private String alarmClearValue;
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
    private List<String> productLineIds;


    public List<String> getProductLineIds() {
        return productLineIds;
    }

    public void setProductLineIds(List<String> productLineIds) {
        this.productLineIds = productLineIds;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getAlarmClearValue() {
        return alarmClearValue;
    }

    public void setAlarmClearValue(String alarmClearValue) {
        this.alarmClearValue = alarmClearValue;
    }

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


    public String getAlarmTargetUser(){
        StringBuilder result = new StringBuilder();
        if (AlarmTarget.EMAIL.match(getAlarmTarget())){
            result.append(getUserName());
            if (StringUtils.isNotEmpty(getEmail())){
                result.append("(");
                result.append(getEmail());
                result.append(")");
            }
        }else if (AlarmTarget.SMS.match(getAlarmTarget())){
            result.append(getUserName());
            if (StringUtils.isNotEmpty(getPhone())){
                result.append("(");
                result.append(getPhone());
                result.append(")");
            }

        }

        return result.toString();
    }

}
