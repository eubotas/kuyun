package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EamEquipment extends BaseEntity implements Serializable {
    private String equipmentId;

    private Integer equipmentCategoryId;

    private String productLineId;

    private String name;

    /**
     * 任务单号
     *
     * @mbg.generated
     */
    private String taskNumber;

    /**
     * 设备型号
     *
     * @mbg.generated
     */
    private String number;

    /**
     * 出厂编号
     *
     * @mbg.generated
     */
    private String serialNumber;

    private String imagePath;

    /**
     * 产量
     *
     * @mbg.generated
     */
    private String output;

    /**
     * 外形尺寸
     *
     * @mbg.generated
     */
    private String dimension;

    /**
     * 设备重量
     *
     * @mbg.generated
     */
    private String weight;

    /**
     * 使用电源
     *
     * @mbg.generated
     */
    private String power;

    /**
     * 装机容量
     *
     * @mbg.generated
     */
    private String capacity;

    private String user;

    /**
     * 采集状态
     *
     * @mbg.generated
     */
    private String collectStatus;

    /**
     * 巨控设备ID
     *
     * @mbg.generated
     */
    private String grm;

    /**
     * 巨控设备密码
     *
     * @mbg.generated
     */
    private String grmPassword;

    /**
     * 巨控采集频率单位秒
     *
     * @mbg.generated
     */
    private Integer grmPeriod;

    private Date factoryDate;

    private Date commissioningDate;

    private Date warrantyStartDate;

    private Date warrantyEndDate;

    private String maintenancePeriod;

    private BigDecimal leftPosition;

    private BigDecimal topPosition;

    private Boolean isOnline;

    private static final long serialVersionUID = 1L;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public String getGrm() {
        return grm;
    }

    public void setGrm(String grm) {
        this.grm = grm;
    }

    public String getGrmPassword() {
        return grmPassword;
    }

    public void setGrmPassword(String grmPassword) {
        this.grmPassword = grmPassword;
    }

    public Integer getGrmPeriod() {
        return grmPeriod;
    }

    public void setGrmPeriod(Integer grmPeriod) {
        this.grmPeriod = grmPeriod;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public Date getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(Date commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    public Date getWarrantyStartDate() {
        return warrantyStartDate;
    }

    public void setWarrantyStartDate(Date warrantyStartDate) {
        this.warrantyStartDate = warrantyStartDate;
    }

    public Date getWarrantyEndDate() {
        return warrantyEndDate;
    }

    public void setWarrantyEndDate(Date warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    public String getMaintenancePeriod() {
        return maintenancePeriod;
    }

    public void setMaintenancePeriod(String maintenancePeriod) {
        this.maintenancePeriod = maintenancePeriod;
    }

    public BigDecimal getLeftPosition() {
        return leftPosition;
    }

    public void setLeftPosition(BigDecimal leftPosition) {
        this.leftPosition = leftPosition;
    }

    public BigDecimal getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(BigDecimal topPosition) {
        this.topPosition = topPosition;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", equipmentCategoryId=").append(equipmentCategoryId);
        sb.append(", productLineId=").append(productLineId);
        sb.append(", name=").append(name);
        sb.append(", taskNumber=").append(taskNumber);
        sb.append(", number=").append(number);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", output=").append(output);
        sb.append(", dimension=").append(dimension);
        sb.append(", weight=").append(weight);
        sb.append(", power=").append(power);
        sb.append(", capacity=").append(capacity);
        sb.append(", user=").append(user);
        sb.append(", collectStatus=").append(collectStatus);
        sb.append(", grm=").append(grm);
        sb.append(", grmPassword=").append(grmPassword);
        sb.append(", grmPeriod=").append(grmPeriod);
        sb.append(", factoryDate=").append(factoryDate);
        sb.append(", commissioningDate=").append(commissioningDate);
        sb.append(", warrantyStartDate=").append(warrantyStartDate);
        sb.append(", warrantyEndDate=").append(warrantyEndDate);
        sb.append(", maintenancePeriod=").append(maintenancePeriod);
        sb.append(", leftPosition=").append(leftPosition);
        sb.append(", topPosition=").append(topPosition);
        sb.append(", isOnline=").append(isOnline);
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
        EamEquipment other = (EamEquipment) that;
        return (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getEquipmentCategoryId() == null ? other.getEquipmentCategoryId() == null : this.getEquipmentCategoryId().equals(other.getEquipmentCategoryId()))
            && (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTaskNumber() == null ? other.getTaskNumber() == null : this.getTaskNumber().equals(other.getTaskNumber()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getSerialNumber() == null ? other.getSerialNumber() == null : this.getSerialNumber().equals(other.getSerialNumber()))
            && (this.getImagePath() == null ? other.getImagePath() == null : this.getImagePath().equals(other.getImagePath()))
            && (this.getOutput() == null ? other.getOutput() == null : this.getOutput().equals(other.getOutput()))
            && (this.getDimension() == null ? other.getDimension() == null : this.getDimension().equals(other.getDimension()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getPower() == null ? other.getPower() == null : this.getPower().equals(other.getPower()))
            && (this.getCapacity() == null ? other.getCapacity() == null : this.getCapacity().equals(other.getCapacity()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getCollectStatus() == null ? other.getCollectStatus() == null : this.getCollectStatus().equals(other.getCollectStatus()))
            && (this.getGrm() == null ? other.getGrm() == null : this.getGrm().equals(other.getGrm()))
            && (this.getGrmPassword() == null ? other.getGrmPassword() == null : this.getGrmPassword().equals(other.getGrmPassword()))
            && (this.getGrmPeriod() == null ? other.getGrmPeriod() == null : this.getGrmPeriod().equals(other.getGrmPeriod()))
            && (this.getFactoryDate() == null ? other.getFactoryDate() == null : this.getFactoryDate().equals(other.getFactoryDate()))
            && (this.getCommissioningDate() == null ? other.getCommissioningDate() == null : this.getCommissioningDate().equals(other.getCommissioningDate()))
            && (this.getWarrantyStartDate() == null ? other.getWarrantyStartDate() == null : this.getWarrantyStartDate().equals(other.getWarrantyStartDate()))
            && (this.getWarrantyEndDate() == null ? other.getWarrantyEndDate() == null : this.getWarrantyEndDate().equals(other.getWarrantyEndDate()))
            && (this.getMaintenancePeriod() == null ? other.getMaintenancePeriod() == null : this.getMaintenancePeriod().equals(other.getMaintenancePeriod()))
            && (this.getLeftPosition() == null ? other.getLeftPosition() == null : this.getLeftPosition().equals(other.getLeftPosition()))
            && (this.getTopPosition() == null ? other.getTopPosition() == null : this.getTopPosition().equals(other.getTopPosition()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getIsOnline() == null ? other.getIsOnline() == null : this.getIsOnline().equals(other.getIsOnline()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getEquipmentCategoryId() == null) ? 0 : getEquipmentCategoryId().hashCode());
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTaskNumber() == null) ? 0 : getTaskNumber().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        result = prime * result + ((getImagePath() == null) ? 0 : getImagePath().hashCode());
        result = prime * result + ((getOutput() == null) ? 0 : getOutput().hashCode());
        result = prime * result + ((getDimension() == null) ? 0 : getDimension().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getPower() == null) ? 0 : getPower().hashCode());
        result = prime * result + ((getCapacity() == null) ? 0 : getCapacity().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getCollectStatus() == null) ? 0 : getCollectStatus().hashCode());
        result = prime * result + ((getGrm() == null) ? 0 : getGrm().hashCode());
        result = prime * result + ((getGrmPassword() == null) ? 0 : getGrmPassword().hashCode());
        result = prime * result + ((getGrmPeriod() == null) ? 0 : getGrmPeriod().hashCode());
        result = prime * result + ((getFactoryDate() == null) ? 0 : getFactoryDate().hashCode());
        result = prime * result + ((getCommissioningDate() == null) ? 0 : getCommissioningDate().hashCode());
        result = prime * result + ((getWarrantyStartDate() == null) ? 0 : getWarrantyStartDate().hashCode());
        result = prime * result + ((getWarrantyEndDate() == null) ? 0 : getWarrantyEndDate().hashCode());
        result = prime * result + ((getMaintenancePeriod() == null) ? 0 : getMaintenancePeriod().hashCode());
        result = prime * result + ((getLeftPosition() == null) ? 0 : getLeftPosition().hashCode());
        result = prime * result + ((getTopPosition() == null) ? 0 : getTopPosition().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getIsOnline() == null) ? 0 : getIsOnline().hashCode());
        return result;
    }
}