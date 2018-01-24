package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamEquipmentDataGroupElemets extends BaseEntity implements Serializable {
    private Integer id;

    /**
     * 设备数据分组ID
     *
     * @mbg.generated
     */
    private Integer equipmentDataGroupId;

    private String equipmentId;

    private Integer dataGroupId;

    /**
     * 数据点ID
     *
     * @mbg.generated
     */
    private Integer dataElementId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipmentDataGroupId() {
        return equipmentDataGroupId;
    }

    public void setEquipmentDataGroupId(Integer equipmentDataGroupId) {
        this.equipmentDataGroupId = equipmentDataGroupId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Integer dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public Integer getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(Integer dataElementId) {
        this.dataElementId = dataElementId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", equipmentDataGroupId=").append(equipmentDataGroupId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", dataGroupId=").append(dataGroupId);
        sb.append(", dataElementId=").append(dataElementId);
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
        EamEquipmentDataGroupElemets other = (EamEquipmentDataGroupElemets) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEquipmentDataGroupId() == null ? other.getEquipmentDataGroupId() == null : this.getEquipmentDataGroupId().equals(other.getEquipmentDataGroupId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getDataGroupId() == null ? other.getDataGroupId() == null : this.getDataGroupId().equals(other.getDataGroupId()))
            && (this.getDataElementId() == null ? other.getDataElementId() == null : this.getDataElementId().equals(other.getDataElementId()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEquipmentDataGroupId() == null) ? 0 : getEquipmentDataGroupId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getDataGroupId() == null) ? 0 : getDataGroupId().hashCode());
        result = prime * result + ((getDataElementId() == null) ? 0 : getDataElementId().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }
}