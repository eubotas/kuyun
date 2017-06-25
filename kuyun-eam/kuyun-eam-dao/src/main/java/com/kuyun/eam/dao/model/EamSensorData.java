package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.Date;

public class EamSensorData implements Serializable {
    private Integer sensorDataId;

    private Integer sensorId;

    private String stringValue;

    private Long numberValue;

    private Boolean booleanValue;

    private Long longitudeValue;

    private Long latitudeValue;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

    private Boolean deleteFlag;

    private Integer organizationId;

    private static final long serialVersionUID = 1L;

    public Integer getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(Integer sensorDataId) {
        this.sensorDataId = sensorDataId;
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

    public Long getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Long numberValue) {
        this.numberValue = numberValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Long getLongitudeValue() {
        return longitudeValue;
    }

    public void setLongitudeValue(Long longitudeValue) {
        this.longitudeValue = longitudeValue;
    }

    public Long getLatitudeValue() {
        return latitudeValue;
    }

    public void setLatitudeValue(Long latitudeValue) {
        this.latitudeValue = latitudeValue;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sensorDataId=").append(sensorDataId);
        sb.append(", sensorId=").append(sensorId);
        sb.append(", stringValue=").append(stringValue);
        sb.append(", numberValue=").append(numberValue);
        sb.append(", booleanValue=").append(booleanValue);
        sb.append(", longitudeValue=").append(longitudeValue);
        sb.append(", latitudeValue=").append(latitudeValue);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", organizationId=").append(organizationId);
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
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSensorDataId() == null) ? 0 : getSensorDataId().hashCode());
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
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}