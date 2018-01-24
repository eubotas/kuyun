package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EamMaintenance extends BaseEntity implements Serializable {
    private Integer maintenanceId;

    private String equipmentId;

    private Integer partId;

    private String reason;

    private String content;

    private BigDecimal partQuantity;

    private Integer maintainUserId;

    private Date maintainTime;

    private static final long serialVersionUID = 1L;

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getPartQuantity() {
        return partQuantity;
    }

    public void setPartQuantity(BigDecimal partQuantity) {
        this.partQuantity = partQuantity;
    }

    public Integer getMaintainUserId() {
        return maintainUserId;
    }

    public void setMaintainUserId(Integer maintainUserId) {
        this.maintainUserId = maintainUserId;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", maintenanceId=").append(maintenanceId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", partId=").append(partId);
        sb.append(", reason=").append(reason);
        sb.append(", content=").append(content);
        sb.append(", partQuantity=").append(partQuantity);
        sb.append(", maintainUserId=").append(maintainUserId);
        sb.append(", maintainTime=").append(maintainTime);
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
        EamMaintenance other = (EamMaintenance) that;
        return (this.getMaintenanceId() == null ? other.getMaintenanceId() == null : this.getMaintenanceId().equals(other.getMaintenanceId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getPartId() == null ? other.getPartId() == null : this.getPartId().equals(other.getPartId()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPartQuantity() == null ? other.getPartQuantity() == null : this.getPartQuantity().equals(other.getPartQuantity()))
            && (this.getMaintainUserId() == null ? other.getMaintainUserId() == null : this.getMaintainUserId().equals(other.getMaintainUserId()))
            && (this.getMaintainTime() == null ? other.getMaintainTime() == null : this.getMaintainTime().equals(other.getMaintainTime()))
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
        result = prime * result + ((getMaintenanceId() == null) ? 0 : getMaintenanceId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getPartId() == null) ? 0 : getPartId().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPartQuantity() == null) ? 0 : getPartQuantity().hashCode());
        result = prime * result + ((getMaintainUserId() == null) ? 0 : getMaintainUserId().hashCode());
        result = prime * result + ((getMaintainTime() == null) ? 0 : getMaintainTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}