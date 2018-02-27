package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class EamMaintainPlan extends BaseEntity implements Serializable {
    private Integer planId;

    private Integer equipmentCategoryId;

    private String equipmentId;

    /**
     * 维保内容
     *
     * @mbg.generated
     */
    private String workContent;

    /**
     * 负责部门
     *
     * @mbg.generated
     */
    private Integer orgId;

    /**
     * 由job产生ticket时修改
     *
     * @mbg.generated
     */
    private Date nextMaintainDate;

    /**
     * 年/月/天
     *
     * @mbg.generated
     */
    private String maintainFrequencyUnit;

    /**
     * 1年/3月/10天
     *
     * @mbg.generated
     */
    private Integer maintainFrequencyQuantity;

    /**
     * 年检/常规
     *
     * @mbg.generated
     */
    private String maintainType;

    /**
     * 到维修期提前提醒时间(天)
     *
     * @mbg.generated
     */
    private Integer remindTime;

    private String productLineId;

    private static final long serialVersionUID = 1L;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getNextMaintainDate() {
        return nextMaintainDate;
    }

    public void setNextMaintainDate(Date nextMaintainDate) {
        this.nextMaintainDate = nextMaintainDate;
    }

    public String getMaintainFrequencyUnit() {
        return maintainFrequencyUnit;
    }

    public void setMaintainFrequencyUnit(String maintainFrequencyUnit) {
        this.maintainFrequencyUnit = maintainFrequencyUnit;
    }

    public Integer getMaintainFrequencyQuantity() {
        return maintainFrequencyQuantity;
    }

    public void setMaintainFrequencyQuantity(Integer maintainFrequencyQuantity) {
        this.maintainFrequencyQuantity = maintainFrequencyQuantity;
    }

    public String getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(String maintainType) {
        this.maintainType = maintainType;
    }

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
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
        sb.append(", planId=").append(planId);
        sb.append(", equipmentCategoryId=").append(equipmentCategoryId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", workContent=").append(workContent);
        sb.append(", orgId=").append(orgId);
        sb.append(", nextMaintainDate=").append(nextMaintainDate);
        sb.append(", maintainFrequencyUnit=").append(maintainFrequencyUnit);
        sb.append(", maintainFrequencyQuantity=").append(maintainFrequencyQuantity);
        sb.append(", maintainType=").append(maintainType);
        sb.append(", remindTime=").append(remindTime);
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
        EamMaintainPlan other = (EamMaintainPlan) that;
        return (this.getPlanId() == null ? other.getPlanId() == null : this.getPlanId().equals(other.getPlanId()))
            && (this.getEquipmentCategoryId() == null ? other.getEquipmentCategoryId() == null : this.getEquipmentCategoryId().equals(other.getEquipmentCategoryId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getWorkContent() == null ? other.getWorkContent() == null : this.getWorkContent().equals(other.getWorkContent()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getNextMaintainDate() == null ? other.getNextMaintainDate() == null : this.getNextMaintainDate().equals(other.getNextMaintainDate()))
            && (this.getMaintainFrequencyUnit() == null ? other.getMaintainFrequencyUnit() == null : this.getMaintainFrequencyUnit().equals(other.getMaintainFrequencyUnit()))
            && (this.getMaintainFrequencyQuantity() == null ? other.getMaintainFrequencyQuantity() == null : this.getMaintainFrequencyQuantity().equals(other.getMaintainFrequencyQuantity()))
            && (this.getMaintainType() == null ? other.getMaintainType() == null : this.getMaintainType().equals(other.getMaintainType()))
            && (this.getRemindTime() == null ? other.getRemindTime() == null : this.getRemindTime().equals(other.getRemindTime()))
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
        result = prime * result + ((getPlanId() == null) ? 0 : getPlanId().hashCode());
        result = prime * result + ((getEquipmentCategoryId() == null) ? 0 : getEquipmentCategoryId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getWorkContent() == null) ? 0 : getWorkContent().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getNextMaintainDate() == null) ? 0 : getNextMaintainDate().hashCode());
        result = prime * result + ((getMaintainFrequencyUnit() == null) ? 0 : getMaintainFrequencyUnit().hashCode());
        result = prime * result + ((getMaintainFrequencyQuantity() == null) ? 0 : getMaintainFrequencyQuantity().hashCode());
        result = prime * result + ((getMaintainType() == null) ? 0 : getMaintainType().hashCode());
        result = prime * result + ((getRemindTime() == null) ? 0 : getRemindTime().hashCode());
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