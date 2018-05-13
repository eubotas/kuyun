package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamEquipmentProperty extends BaseEntity implements Serializable {
    private Integer equipmentPropertyId;

    private String equipmentId;

    private String propertyLabel;

    private String propertyValue;

    private static final long serialVersionUID = 1L;

    public Integer getEquipmentPropertyId() {
        return equipmentPropertyId;
    }

    public void setEquipmentPropertyId(Integer equipmentPropertyId) {
        this.equipmentPropertyId = equipmentPropertyId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getPropertyLabel() {
        return propertyLabel;
    }

    public void setPropertyLabel(String propertyLabel) {
        this.propertyLabel = propertyLabel;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentPropertyId=").append(equipmentPropertyId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", propertyLabel=").append(propertyLabel);
        sb.append(", propertyValue=").append(propertyValue);
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
        EamEquipmentProperty other = (EamEquipmentProperty) that;
        return (this.getEquipmentPropertyId() == null ? other.getEquipmentPropertyId() == null : this.getEquipmentPropertyId().equals(other.getEquipmentPropertyId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getPropertyLabel() == null ? other.getPropertyLabel() == null : this.getPropertyLabel().equals(other.getPropertyLabel()))
            && (this.getPropertyValue() == null ? other.getPropertyValue() == null : this.getPropertyValue().equals(other.getPropertyValue()))
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
        result = prime * result + ((getEquipmentPropertyId() == null) ? 0 : getEquipmentPropertyId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getPropertyLabel() == null) ? 0 : getPropertyLabel().hashCode());
        result = prime * result + ((getPropertyValue() == null) ? 0 : getPropertyValue().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}