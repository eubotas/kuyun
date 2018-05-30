package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class EamGrmVariableDataByDay extends BaseEntity implements Serializable {
    private Long id;

    private Integer eamGrmVariableId;

    private String equipmentId;

    private String productLineId;

    private Integer dataElementId;

    private String value;

    private Date date;

    /**
     * 开关量 --有值,模拟量--空
     *
     * @mbg.generated
     */
    private Boolean switchValue;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEamGrmVariableId() {
        return eamGrmVariableId;
    }

    public void setEamGrmVariableId(Integer eamGrmVariableId) {
        this.eamGrmVariableId = eamGrmVariableId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public Integer getDataElementId() {
        return dataElementId;
    }

    public void setDataElementId(Integer dataElementId) {
        this.dataElementId = dataElementId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getSwitchValue() {
        return switchValue;
    }

    public void setSwitchValue(Boolean switchValue) {
        this.switchValue = switchValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eamGrmVariableId=").append(eamGrmVariableId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", productLineId=").append(productLineId);
        sb.append(", dataElementId=").append(dataElementId);
        sb.append(", value=").append(value);
        sb.append(", date=").append(date);
        sb.append(", switchValue=").append(switchValue);
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
        EamGrmVariableDataByDay other = (EamGrmVariableDataByDay) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEamGrmVariableId() == null ? other.getEamGrmVariableId() == null : this.getEamGrmVariableId().equals(other.getEamGrmVariableId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getDataElementId() == null ? other.getDataElementId() == null : this.getDataElementId().equals(other.getDataElementId()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getSwitchValue() == null ? other.getSwitchValue() == null : this.getSwitchValue().equals(other.getSwitchValue()))
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
        result = prime * result + ((getEamGrmVariableId() == null) ? 0 : getEamGrmVariableId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getDataElementId() == null) ? 0 : getDataElementId().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getSwitchValue() == null) ? 0 : getSwitchValue().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }
}