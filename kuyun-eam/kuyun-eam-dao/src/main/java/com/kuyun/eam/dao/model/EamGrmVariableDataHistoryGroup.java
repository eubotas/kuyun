package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamGrmVariableDataHistoryGroup extends BaseEntity implements Serializable {
    private Integer id;

    private Integer eamGrmVariableDataHistoryId;

    private Integer dataGroupId;

    /**
     * 设备数据分组ID
     *
     * @mbg.generated
     */
    private Integer equipmentDataGroupId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEamGrmVariableDataHistoryId() {
        return eamGrmVariableDataHistoryId;
    }

    public void setEamGrmVariableDataHistoryId(Integer eamGrmVariableDataHistoryId) {
        this.eamGrmVariableDataHistoryId = eamGrmVariableDataHistoryId;
    }

    public Integer getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Integer dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public Integer getEquipmentDataGroupId() {
        return equipmentDataGroupId;
    }

    public void setEquipmentDataGroupId(Integer equipmentDataGroupId) {
        this.equipmentDataGroupId = equipmentDataGroupId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eamGrmVariableDataHistoryId=").append(eamGrmVariableDataHistoryId);
        sb.append(", dataGroupId=").append(dataGroupId);
        sb.append(", equipmentDataGroupId=").append(equipmentDataGroupId);
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
        EamGrmVariableDataHistoryGroup other = (EamGrmVariableDataHistoryGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEamGrmVariableDataHistoryId() == null ? other.getEamGrmVariableDataHistoryId() == null : this.getEamGrmVariableDataHistoryId().equals(other.getEamGrmVariableDataHistoryId()))
            && (this.getDataGroupId() == null ? other.getDataGroupId() == null : this.getDataGroupId().equals(other.getDataGroupId()))
            && (this.getEquipmentDataGroupId() == null ? other.getEquipmentDataGroupId() == null : this.getEquipmentDataGroupId().equals(other.getEquipmentDataGroupId()))
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
        result = prime * result + ((getEamGrmVariableDataHistoryId() == null) ? 0 : getEamGrmVariableDataHistoryId().hashCode());
        result = prime * result + ((getDataGroupId() == null) ? 0 : getDataGroupId().hashCode());
        result = prime * result + ((getEquipmentDataGroupId() == null) ? 0 : getEquipmentDataGroupId().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }
}