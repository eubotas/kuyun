package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;

public class EamSensorData extends BaseEntity implements Serializable {
    private Integer sensorDataId;

    private String equipmentId;

    private Integer sensorId;

    private String stringValue;

    private BigDecimal numberValue;

    private Boolean booleanValue;

    private BigDecimal longitudeValue;

    private BigDecimal latitudeValue;

    private static final long serialVersionUID = 1L;

    public Integer getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(Integer sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public BigDecimal getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(BigDecimal numberValue) {
        this.numberValue = numberValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public BigDecimal getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(BigDecimal longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public BigDecimal getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(BigDecimal latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sensorDataId=").append(sensorDataId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", sensorId=").append(sensorId);
        sb.append(", stringValue=").append(stringValue);
        sb.append(", numberValue=").append(numberValue);
        sb.append(", booleanValue=").append(booleanValue);
        sb.append(", longitudeValue=").append(longitudeValue);
        sb.append(", latitudeValue=").append(latitudeValue);
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
        EamSensorData other = (EamSensorData) that;
        return (this.getSensorDataId() == null ? other.getSensorDataId() == null : this.getSensorDataId().equals(other.getSensorDataId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getSensorId() == null ? other.getSensorId() == null : this.getSensorId().equals(other.getSensorId()))
            && (this.getStringValue() == null ? other.getStringValue() == null : this.getStringValue().equals(other.getStringValue()))
            && (this.getNumberValue() == null ? other.getNumberValue() == null : this.getNumberValue().equals(other.getNumberValue()))
            && (this.getBooleanValue() == null ? other.getBooleanValue() == null : this.getBooleanValue().equals(other.getBooleanValue()))
            && (this.getLongitudeValue() == null ? other.getLongitudeValue() == null : this.getLongitudeValue().equals(other.getLongitudeValue()))
            && (this.getLatitudeValue() == null ? other.getLatitudeValue() == null : this.getLatitudeValue().equals(other.getLatitudeValue()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSensorDataId() == null) ? 0 : getSensorDataId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getSensorId() == null) ? 0 : getSensorId().hashCode());
        result = prime * result + ((getStringValue() == null) ? 0 : getStringValue().hashCode());
        result = prime * result + ((getNumberValue() == null) ? 0 : getNumberValue().hashCode());
        result = prime * result + ((getBooleanValue() == null) ? 0 : getBooleanValue().hashCode());
        result = prime * result + ((getLongitudeValue() == null) ? 0 : getLongitudeValue().hashCode());
        result = prime * result + ((getLatitudeValue() == null) ? 0 : getLatitudeValue().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}