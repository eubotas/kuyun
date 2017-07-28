package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EamInventory extends BaseEntity implements Serializable {
    private Integer inventoryId;

    private Integer warehouseId;

    private Integer locationId;

    private Integer partId;

    private BigDecimal quantity;

    private Date inTaskDate;

    private static final long serialVersionUID = 1L;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getInTaskDate() {
        return inTaskDate;
    }

    public void setInTaskDate(Date inTaskDate) {
        this.inTaskDate = inTaskDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inventoryId=").append(inventoryId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", locationId=").append(locationId);
        sb.append(", partId=").append(partId);
        sb.append(", quantity=").append(quantity);
        sb.append(", inTaskDate=").append(inTaskDate);
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
        EamInventory other = (EamInventory) that;
        return (this.getInventoryId() == null ? other.getInventoryId() == null : this.getInventoryId().equals(other.getInventoryId()))
            && (this.getWarehouseId() == null ? other.getWarehouseId() == null : this.getWarehouseId().equals(other.getWarehouseId()))
            && (this.getLocationId() == null ? other.getLocationId() == null : this.getLocationId().equals(other.getLocationId()))
            && (this.getPartId() == null ? other.getPartId() == null : this.getPartId().equals(other.getPartId()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getInTaskDate() == null ? other.getInTaskDate() == null : this.getInTaskDate().equals(other.getInTaskDate()))
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
        result = prime * result + ((getInventoryId() == null) ? 0 : getInventoryId().hashCode());
        result = prime * result + ((getWarehouseId() == null) ? 0 : getWarehouseId().hashCode());
        result = prime * result + ((getLocationId() == null) ? 0 : getLocationId().hashCode());
        result = prime * result + ((getPartId() == null) ? 0 : getPartId().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getInTaskDate() == null) ? 0 : getInTaskDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}