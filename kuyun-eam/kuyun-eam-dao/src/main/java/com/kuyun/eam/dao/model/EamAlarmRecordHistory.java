package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamAlarmRecordHistory extends BaseEntity implements Serializable {
    private Integer id;

    private Integer alarmId;

    private String productLineId;

    private String equipmentId;

    private String alarmValue;

    private String alarmStatus;

    private String alarmClearValue;

    private String alarmContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmClearValue() {
        return alarmClearValue;
    }

    public void setAlarmClearValue(String alarmClearValue) {
        this.alarmClearValue = alarmClearValue;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alarmId=").append(alarmId);
        sb.append(", productLineId=").append(productLineId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", alarmValue=").append(alarmValue);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", alarmClearValue=").append(alarmClearValue);
        sb.append(", alarmContent=").append(alarmContent);
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
        EamAlarmRecordHistory other = (EamAlarmRecordHistory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getAlarmValue() == null ? other.getAlarmValue() == null : this.getAlarmValue().equals(other.getAlarmValue()))
            && (this.getAlarmStatus() == null ? other.getAlarmStatus() == null : this.getAlarmStatus().equals(other.getAlarmStatus()))
            && (this.getAlarmClearValue() == null ? other.getAlarmClearValue() == null : this.getAlarmClearValue().equals(other.getAlarmClearValue()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getAlarmContent() == null ? other.getAlarmContent() == null : this.getAlarmContent().equals(other.getAlarmContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getAlarmValue() == null) ? 0 : getAlarmValue().hashCode());
        result = prime * result + ((getAlarmStatus() == null) ? 0 : getAlarmStatus().hashCode());
        result = prime * result + ((getAlarmClearValue() == null) ? 0 : getAlarmClearValue().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getAlarmContent() == null) ? 0 : getAlarmContent().hashCode());
        return result;
    }
}