package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;

public class EamAlarm extends BaseEntity implements Serializable {
    private Integer alarmId;

    private String productLineId;

    private String equipmentId;

    /**
     * 报警名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 数据点ID
     *
     * @mbg.generated
     */
    private Integer eamDataElementId;

    private String alarmType;

    private BigDecimal upperBound;

    private BigDecimal lowerBound;

    private BigDecimal duration;

    private String alarmTarget;

    private Boolean isCreateTicket;

    private static final long serialVersionUID = 1L;

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEamDataElementId() {
        return eamDataElementId;
    }

    public void setEamDataElementId(Integer eamDataElementId) {
        this.eamDataElementId = eamDataElementId;
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

    public Boolean getIsCreateTicket() {
        return isCreateTicket;
    }

    public void setIsCreateTicket(Boolean isCreateTicket) {
        this.isCreateTicket = isCreateTicket;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", alarmId=").append(alarmId);
        sb.append(", productLineId=").append(productLineId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", name=").append(name);
        sb.append(", eamDataElementId=").append(eamDataElementId);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", upperBound=").append(upperBound);
        sb.append(", lowerBound=").append(lowerBound);
        sb.append(", duration=").append(duration);
        sb.append(", alarmTarget=").append(alarmTarget);
        sb.append(", isCreateTicket=").append(isCreateTicket);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EamAlarm other = (EamAlarm) that;
        return (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEamDataElementId() == null ? other.getEamDataElementId() == null : this.getEamDataElementId().equals(other.getEamDataElementId()))
            && (this.getAlarmType() == null ? other.getAlarmType() == null : this.getAlarmType().equals(other.getAlarmType()))
            && (this.getUpperBound() == null ? other.getUpperBound() == null : this.getUpperBound().equals(other.getUpperBound()))
            && (this.getLowerBound() == null ? other.getLowerBound() == null : this.getLowerBound().equals(other.getLowerBound()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getAlarmTarget() == null ? other.getAlarmTarget() == null : this.getAlarmTarget().equals(other.getAlarmTarget()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getIsCreateTicket() == null ? other.getIsCreateTicket() == null : this.getIsCreateTicket().equals(other.getIsCreateTicket()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEamDataElementId() == null) ? 0 : getEamDataElementId().hashCode());
        result = prime * result + ((getAlarmType() == null) ? 0 : getAlarmType().hashCode());
        result = prime * result + ((getUpperBound() == null) ? 0 : getUpperBound().hashCode());
        result = prime * result + ((getLowerBound() == null) ? 0 : getLowerBound().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getAlarmTarget() == null) ? 0 : getAlarmTarget().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getIsCreateTicket() == null) ? 0 : getIsCreateTicket().hashCode());
        return result;
    }
}