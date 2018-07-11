package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EamProductLineShiftData extends BaseEntity implements Serializable {
    private Long id;

    private String productLineId;

    private BigDecimal actualCapacity;

    private BigDecimal baseCapacity;

    private BigDecimal performanceRate;

    private BigDecimal qualifiedRate;

    private BigDecimal rejectionRate;

    private BigDecimal timeStartingRate;

    private BigDecimal oee;

    private String productName;

    private Date startDate;

    private Date endDate;

    private String sumTimeStr;

    private BigDecimal sumTime;

    private BigDecimal shirtTime;

    private BigDecimal planStopTime;

    private String shiftName;

    /**
     * 瓶坯损耗
     *
     * @mbg.generated
     */
    private Integer preformConsume;

    /**
     * 瓶盖消耗
     *
     * @mbg.generated
     */
    private Integer capConsume;

    /**
     * 生产数量
     *
     * @mbg.generated
     */
    private BigDecimal actualQuantity;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public BigDecimal getActualCapacity() {
        return actualCapacity;
    }

    public void setActualCapacity(BigDecimal actualCapacity) {
        this.actualCapacity = actualCapacity;
    }

    public BigDecimal getBaseCapacity() {
        return baseCapacity;
    }

    public void setBaseCapacity(BigDecimal baseCapacity) {
        this.baseCapacity = baseCapacity;
    }

    public BigDecimal getPerformanceRate() {
        return performanceRate;
    }

    public void setPerformanceRate(BigDecimal performanceRate) {
        this.performanceRate = performanceRate;
    }

    public BigDecimal getQualifiedRate() {
        return qualifiedRate;
    }

    public void setQualifiedRate(BigDecimal qualifiedRate) {
        this.qualifiedRate = qualifiedRate;
    }

    public BigDecimal getRejectionRate() {
        return rejectionRate;
    }

    public void setRejectionRate(BigDecimal rejectionRate) {
        this.rejectionRate = rejectionRate;
    }

    public BigDecimal getTimeStartingRate() {
        return timeStartingRate;
    }

    public void setTimeStartingRate(BigDecimal timeStartingRate) {
        this.timeStartingRate = timeStartingRate;
    }

    public BigDecimal getOee() {
        return oee;
    }

    public void setOee(BigDecimal oee) {
        this.oee = oee;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSumTimeStr() {
        return sumTimeStr;
    }

    public void setSumTimeStr(String sumTimeStr) {
        this.sumTimeStr = sumTimeStr;
    }

    public BigDecimal getSumTime() {
        return sumTime;
    }

    public void setSumTime(BigDecimal sumTime) {
        this.sumTime = sumTime;
    }

    public BigDecimal getShirtTime() {
        return shirtTime;
    }

    public void setShirtTime(BigDecimal shirtTime) {
        this.shirtTime = shirtTime;
    }

    public BigDecimal getPlanStopTime() {
        return planStopTime;
    }

    public void setPlanStopTime(BigDecimal planStopTime) {
        this.planStopTime = planStopTime;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Integer getPreformConsume() {
        return preformConsume;
    }

    public void setPreformConsume(Integer preformConsume) {
        this.preformConsume = preformConsume;
    }

    public Integer getCapConsume() {
        return capConsume;
    }

    public void setCapConsume(Integer capConsume) {
        this.capConsume = capConsume;
    }

    public BigDecimal getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(BigDecimal actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productLineId=").append(productLineId);
        sb.append(", actualCapacity=").append(actualCapacity);
        sb.append(", baseCapacity=").append(baseCapacity);
        sb.append(", performanceRate=").append(performanceRate);
        sb.append(", qualifiedRate=").append(qualifiedRate);
        sb.append(", rejectionRate=").append(rejectionRate);
        sb.append(", timeStartingRate=").append(timeStartingRate);
        sb.append(", oee=").append(oee);
        sb.append(", productName=").append(productName);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", sumTimeStr=").append(sumTimeStr);
        sb.append(", sumTime=").append(sumTime);
        sb.append(", shirtTime=").append(shirtTime);
        sb.append(", planStopTime=").append(planStopTime);
        sb.append(", shiftName=").append(shiftName);
        sb.append(", preformConsume=").append(preformConsume);
        sb.append(", capConsume=").append(capConsume);
        sb.append(", actualQuantity=").append(actualQuantity);
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
        EamProductLineShiftData other = (EamProductLineShiftData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getActualCapacity() == null ? other.getActualCapacity() == null : this.getActualCapacity().equals(other.getActualCapacity()))
            && (this.getBaseCapacity() == null ? other.getBaseCapacity() == null : this.getBaseCapacity().equals(other.getBaseCapacity()))
            && (this.getPerformanceRate() == null ? other.getPerformanceRate() == null : this.getPerformanceRate().equals(other.getPerformanceRate()))
            && (this.getQualifiedRate() == null ? other.getQualifiedRate() == null : this.getQualifiedRate().equals(other.getQualifiedRate()))
            && (this.getRejectionRate() == null ? other.getRejectionRate() == null : this.getRejectionRate().equals(other.getRejectionRate()))
            && (this.getTimeStartingRate() == null ? other.getTimeStartingRate() == null : this.getTimeStartingRate().equals(other.getTimeStartingRate()))
            && (this.getOee() == null ? other.getOee() == null : this.getOee().equals(other.getOee()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getSumTimeStr() == null ? other.getSumTimeStr() == null : this.getSumTimeStr().equals(other.getSumTimeStr()))
            && (this.getSumTime() == null ? other.getSumTime() == null : this.getSumTime().equals(other.getSumTime()))
            && (this.getShirtTime() == null ? other.getShirtTime() == null : this.getShirtTime().equals(other.getShirtTime()))
            && (this.getPlanStopTime() == null ? other.getPlanStopTime() == null : this.getPlanStopTime().equals(other.getPlanStopTime()))
            && (this.getShiftName() == null ? other.getShiftName() == null : this.getShiftName().equals(other.getShiftName()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getPreformConsume() == null ? other.getPreformConsume() == null : this.getPreformConsume().equals(other.getPreformConsume()))
            && (this.getCapConsume() == null ? other.getCapConsume() == null : this.getCapConsume().equals(other.getCapConsume()))
            && (this.getActualQuantity() == null ? other.getActualQuantity() == null : this.getActualQuantity().equals(other.getActualQuantity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getActualCapacity() == null) ? 0 : getActualCapacity().hashCode());
        result = prime * result + ((getBaseCapacity() == null) ? 0 : getBaseCapacity().hashCode());
        result = prime * result + ((getPerformanceRate() == null) ? 0 : getPerformanceRate().hashCode());
        result = prime * result + ((getQualifiedRate() == null) ? 0 : getQualifiedRate().hashCode());
        result = prime * result + ((getRejectionRate() == null) ? 0 : getRejectionRate().hashCode());
        result = prime * result + ((getTimeStartingRate() == null) ? 0 : getTimeStartingRate().hashCode());
        result = prime * result + ((getOee() == null) ? 0 : getOee().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getSumTimeStr() == null) ? 0 : getSumTimeStr().hashCode());
        result = prime * result + ((getSumTime() == null) ? 0 : getSumTime().hashCode());
        result = prime * result + ((getShirtTime() == null) ? 0 : getShirtTime().hashCode());
        result = prime * result + ((getPlanStopTime() == null) ? 0 : getPlanStopTime().hashCode());
        result = prime * result + ((getShiftName() == null) ? 0 : getShiftName().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getPreformConsume() == null) ? 0 : getPreformConsume().hashCode());
        result = prime * result + ((getCapConsume() == null) ? 0 : getCapConsume().hashCode());
        result = prime * result + ((getActualQuantity() == null) ? 0 : getActualQuantity().hashCode());
        return result;
    }
}