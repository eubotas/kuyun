package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamEquipmentProduct extends BaseEntity implements Serializable {
    private Integer id;

    private String equipmentId;

    /**
     * 物料ID
     *
     * @mbg.generated
     */
    private String materielNumber;

    /**
     * 物料名称
     *
     * @mbg.generated
     */
    private String materielName;

    /**
     * 额定产能
     *
     * @mbg.generated
     */
    private Integer capacity;

    /**
     * 包装规格
     *
     * @mbg.generated
     */
    private Integer packing;

    /**
     * 码垛规格
     *
     * @mbg.generated
     */
    private Integer stacking;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getMaterielNumber() {
        return materielNumber;
    }

    public void setMaterielNumber(String materielNumber) {
        this.materielNumber = materielNumber;
    }

    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPacking() {
        return packing;
    }

    public void setPacking(Integer packing) {
        this.packing = packing;
    }

    public Integer getStacking() {
        return stacking;
    }

    public void setStacking(Integer stacking) {
        this.stacking = stacking;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", materielNumber=").append(materielNumber);
        sb.append(", materielName=").append(materielName);
        sb.append(", capacity=").append(capacity);
        sb.append(", packing=").append(packing);
        sb.append(", stacking=").append(stacking);
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
        EamEquipmentProduct other = (EamEquipmentProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getMaterielNumber() == null ? other.getMaterielNumber() == null : this.getMaterielNumber().equals(other.getMaterielNumber()))
            && (this.getMaterielName() == null ? other.getMaterielName() == null : this.getMaterielName().equals(other.getMaterielName()))
            && (this.getCapacity() == null ? other.getCapacity() == null : this.getCapacity().equals(other.getCapacity()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getPacking() == null ? other.getPacking() == null : this.getPacking().equals(other.getPacking()))
            && (this.getStacking() == null ? other.getStacking() == null : this.getStacking().equals(other.getStacking()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getMaterielNumber() == null) ? 0 : getMaterielNumber().hashCode());
        result = prime * result + ((getMaterielName() == null) ? 0 : getMaterielName().hashCode());
        result = prime * result + ((getCapacity() == null) ? 0 : getCapacity().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getPacking() == null) ? 0 : getPacking().hashCode());
        result = prime * result + ((getStacking() == null) ? 0 : getStacking().hashCode());
        return result;
    }
}