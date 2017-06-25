package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.Date;

public class EamSensor extends BaseEntity implements Serializable {
    private Integer sensorId;

    private String equipmentId;

    private Integer equipmentModelPropertyId;

    private Integer salveId;

    private Integer functionCode;

    private Integer address;

    private String dataFormat;

    private Integer period;

    private Integer writeNumber;

    private Integer quantity;

    private static final long serialVersionUID = 1L;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getEquipmentModelPropertyId() {
        return equipmentModelPropertyId;
    }

    public void setEquipmentModelPropertyId(Integer equipmentModelPropertyId) {
        this.equipmentModelPropertyId = equipmentModelPropertyId;
    }

    public Integer getSalveId() {
        return salveId;
    }

    public void setSalveId(Integer salveId) {
        this.salveId = salveId;
    }

    public Integer getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(Integer functionCode) {
        this.functionCode = functionCode;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer address) {
        this.address = address;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getWriteNumber() {
        return writeNumber;
    }

    public void setWriteNumber(Integer writeNumber) {
        this.writeNumber = writeNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sensorId=").append(sensorId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", equipmentModelPropertyId=").append(equipmentModelPropertyId);
        sb.append(", salveId=").append(salveId);
        sb.append(", functionCode=").append(functionCode);
        sb.append(", address=").append(address);
        sb.append(", dataFormat=").append(dataFormat);
        sb.append(", period=").append(period);
        sb.append(", writeNumber=").append(writeNumber);
        sb.append(", createUserId=").append(getCreateUserId());
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateUserId=").append(getUpdateUserId());
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append(", deleteFlag=").append(getDeleteFlag());
        sb.append(", organizationId=").append(getOrganizationId());
        sb.append(", quantity=").append(quantity);
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
        EamSensor other = (EamSensor) that;
        return (this.getSensorId() == null ? other.getSensorId() == null : this.getSensorId().equals(other.getSensorId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getEquipmentModelPropertyId() == null ? other.getEquipmentModelPropertyId() == null : this.getEquipmentModelPropertyId().equals(other.getEquipmentModelPropertyId()))
            && (this.getSalveId() == null ? other.getSalveId() == null : this.getSalveId().equals(other.getSalveId()))
            && (this.getFunctionCode() == null ? other.getFunctionCode() == null : this.getFunctionCode().equals(other.getFunctionCode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDataFormat() == null ? other.getDataFormat() == null : this.getDataFormat().equals(other.getDataFormat()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getWriteNumber() == null ? other.getWriteNumber() == null : this.getWriteNumber().equals(other.getWriteNumber()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSensorId() == null) ? 0 : getSensorId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getEquipmentModelPropertyId() == null) ? 0 : getEquipmentModelPropertyId().hashCode());
        result = prime * result + ((getSalveId() == null) ? 0 : getSalveId().hashCode());
        result = prime * result + ((getFunctionCode() == null) ? 0 : getFunctionCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDataFormat() == null) ? 0 : getDataFormat().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getWriteNumber() == null) ? 0 : getWriteNumber().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        return result;
    }
}