package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamParts extends BaseEntity implements Serializable {
    private Integer partId;

    private String equipmentId;

    private Integer categoryId;

    private Integer seqId;

    /**
     * 代号
     *
     * @mbg.generated
     */
    private String symbol;

    /**
     * 名称
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 型号
     *
     * @mbg.generated
     */
    private String model;

    /**
     * 材料
     *
     * @mbg.generated
     */
    private String material;

    /**
     * 数量
     *
     * @mbg.generated
     */
    private Integer quantity;

    private String productLineId;

    private static final long serialVersionUID = 1L;

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", partId=").append(partId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", seqId=").append(seqId);
        sb.append(", symbol=").append(symbol);
        sb.append(", name=").append(name);
        sb.append(", model=").append(model);
        sb.append(", material=").append(material);
        sb.append(", quantity=").append(quantity);
        sb.append(", productLineId=").append(productLineId);
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
        EamParts other = (EamParts) that;
        return (this.getPartId() == null ? other.getPartId() == null : this.getPartId().equals(other.getPartId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getSeqId() == null ? other.getSeqId() == null : this.getSeqId().equals(other.getSeqId()))
            && (this.getSymbol() == null ? other.getSymbol() == null : this.getSymbol().equals(other.getSymbol()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getModel() == null ? other.getModel() == null : this.getModel().equals(other.getModel()))
            && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPartId() == null) ? 0 : getPartId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getSeqId() == null) ? 0 : getSeqId().hashCode());
        result = prime * result + ((getSymbol() == null) ? 0 : getSymbol().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getModel() == null) ? 0 : getModel().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        return result;
    }
}