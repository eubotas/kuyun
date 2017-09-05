package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamEquipmentModelProperties extends BaseEntity implements Serializable {
    private Integer equipmentModelPropertyId;

    private Integer equipmentModelId;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    private String label;

    /**
     * 单位
     *
     * @mbg.generated
     */
    private String unit;

    private String address;

    /**
     * 数据类型(analog, digital)
     *
     * @mbg.generated
     */
    private String dataType;

    /**
     * 显示类型(LED, pie, guage)
     *
     * @mbg.generated
     */
    private String displayType;

    /**
     * 报警类型(val_above, val_below, ...)
     *
     * @mbg.generated
     */
    private String alarmType;

    private String refreshPeriod;

    private static final long serialVersionUID = 1L;

    public Integer getEquipmentModelPropertyId() {
        return equipmentModelPropertyId;
    }

    public void setEquipmentModelPropertyId(Integer equipmentModelPropertyId) {
        this.equipmentModelPropertyId = equipmentModelPropertyId;
    }

    public Integer getEquipmentModelId() {
        return equipmentModelId;
    }

    public void setEquipmentModelId(Integer equipmentModelId) {
        this.equipmentModelId = equipmentModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getRefreshPeriod() {
        return refreshPeriod;
    }

    public void setRefreshPeriod(String refreshPeriod) {
        this.refreshPeriod = refreshPeriod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentModelPropertyId=").append(equipmentModelPropertyId);
        sb.append(", equipmentModelId=").append(equipmentModelId);
        sb.append(", name=").append(name);
        sb.append(", label=").append(label);
        sb.append(", unit=").append(unit);
        sb.append(", address=").append(address);
        sb.append(", dataType=").append(dataType);
        sb.append(", displayType=").append(displayType);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", refreshPeriod=").append(refreshPeriod);
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
        EamEquipmentModelProperties other = (EamEquipmentModelProperties) that;
        return (this.getEquipmentModelPropertyId() == null ? other.getEquipmentModelPropertyId() == null : this.getEquipmentModelPropertyId().equals(other.getEquipmentModelPropertyId()))
            && (this.getEquipmentModelId() == null ? other.getEquipmentModelId() == null : this.getEquipmentModelId().equals(other.getEquipmentModelId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getDataType() == null ? other.getDataType() == null : this.getDataType().equals(other.getDataType()))
            && (this.getDisplayType() == null ? other.getDisplayType() == null : this.getDisplayType().equals(other.getDisplayType()))
            && (this.getAlarmType() == null ? other.getAlarmType() == null : this.getAlarmType().equals(other.getAlarmType()))
            && (this.getRefreshPeriod() == null ? other.getRefreshPeriod() == null : this.getRefreshPeriod().equals(other.getRefreshPeriod()))
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
        result = prime * result + ((getEquipmentModelPropertyId() == null) ? 0 : getEquipmentModelPropertyId().hashCode());
        result = prime * result + ((getEquipmentModelId() == null) ? 0 : getEquipmentModelId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getDataType() == null) ? 0 : getDataType().hashCode());
        result = prime * result + ((getDisplayType() == null) ? 0 : getDisplayType().hashCode());
        result = prime * result + ((getAlarmType() == null) ? 0 : getAlarmType().hashCode());
        result = prime * result + ((getRefreshPeriod() == null) ? 0 : getRefreshPeriod().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}