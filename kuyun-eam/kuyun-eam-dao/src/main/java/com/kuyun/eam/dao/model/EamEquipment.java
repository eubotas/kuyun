package com.kuyun.eam.dao.model;

import com.kuyun.eam.dao.EamEquipmentBase;

import java.math.BigDecimal;
import java.util.Date;

public class EamEquipment extends EamEquipmentBase {
    private String equipmentId;

    private Integer equipmentModelId;

    private String name;

    private String number;

    private String serialNumber;

    private String imagePath;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String province;

    private String city;

    private String user;

    /**
     * 采集状态
     *
     * @mbg.generated
     */
    private String collectStatus;

    /**
     * Modbus RTU
     *
     * @mbg.generated
     */
    private String heartData;

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

    private Boolean isOnline;

    private Integer modbusRtuPeriod;

    /**
     * Modbus RTU 从站地址
     *
     * @mbg.generated
     */
    private Integer salveId;

    private Integer equipmentCategoryId;

    private static final long serialVersionUID = 1L;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getEquipmentModelId() {
        return equipmentModelId;
    }

    public void setEquipmentModelId(Integer equipmentModelId) {
        this.equipmentModelId = equipmentModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getHeartData() {
        return heartData;
    }

    public void setHeartData(String heartData) {
        this.heartData = heartData;
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

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getModbusRtuPeriod() {
        return modbusRtuPeriod;
    }

    public void setModbusRtuPeriod(Integer modbusRtuPeriod) {
        this.modbusRtuPeriod = modbusRtuPeriod;
    }

    public Integer getSalveId() {
        return salveId;
    }

    public void setSalveId(Integer salveId) {
        this.salveId = salveId;
    }

    public Integer getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    public void setEquipmentCategoryId(Integer equipmentCategoryId) {
        this.equipmentCategoryId = equipmentCategoryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", equipmentModelId=").append(equipmentModelId);
        sb.append(", name=").append(name);
        sb.append(", number=").append(number);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", user=").append(user);
        sb.append(", collectStatus=").append(collectStatus);
        sb.append(", heartData=").append(heartData);
        sb.append(", grm=").append(grm);
        sb.append(", grmPassword=").append(grmPassword);
        sb.append(", grmPeriod=").append(grmPeriod);
        sb.append(", factoryDate=").append(factoryDate);
        sb.append(", commissioningDate=").append(commissioningDate);
        sb.append(", warrantyStartDate=").append(warrantyStartDate);
        sb.append(", warrantyEndDate=").append(warrantyEndDate);
        sb.append(", maintenancePeriod=").append(maintenancePeriod);
        sb.append(", isOnline=").append(isOnline);
        sb.append(", modbusRtuPeriod=").append(modbusRtuPeriod);
        sb.append(", salveId=").append(salveId);
        sb.append(", equipmentCategoryId=").append(equipmentCategoryId);
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
            && (this.getEquipmentModelId() == null ? other.getEquipmentModelId() == null : this.getEquipmentModelId().equals(other.getEquipmentModelId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getSerialNumber() == null ? other.getSerialNumber() == null : this.getSerialNumber().equals(other.getSerialNumber()))
            && (this.getImagePath() == null ? other.getImagePath() == null : this.getImagePath().equals(other.getImagePath()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getCollectStatus() == null ? other.getCollectStatus() == null : this.getCollectStatus().equals(other.getCollectStatus()))
            && (this.getHeartData() == null ? other.getHeartData() == null : this.getHeartData().equals(other.getHeartData()))
            && (this.getGrm() == null ? other.getGrm() == null : this.getGrm().equals(other.getGrm()))
            && (this.getGrmPassword() == null ? other.getGrmPassword() == null : this.getGrmPassword().equals(other.getGrmPassword()))
            && (this.getGrmPeriod() == null ? other.getGrmPeriod() == null : this.getGrmPeriod().equals(other.getGrmPeriod()))
            && (this.getFactoryDate() == null ? other.getFactoryDate() == null : this.getFactoryDate().equals(other.getFactoryDate()))
            && (this.getCommissioningDate() == null ? other.getCommissioningDate() == null : this.getCommissioningDate().equals(other.getCommissioningDate()))
            && (this.getWarrantyStartDate() == null ? other.getWarrantyStartDate() == null : this.getWarrantyStartDate().equals(other.getWarrantyStartDate()))
            && (this.getWarrantyEndDate() == null ? other.getWarrantyEndDate() == null : this.getWarrantyEndDate().equals(other.getWarrantyEndDate()))
            && (this.getMaintenancePeriod() == null ? other.getMaintenancePeriod() == null : this.getMaintenancePeriod().equals(other.getMaintenancePeriod()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getIsOnline() == null ? other.getIsOnline() == null : this.getIsOnline().equals(other.getIsOnline()))
            && (this.getModbusRtuPeriod() == null ? other.getModbusRtuPeriod() == null : this.getModbusRtuPeriod().equals(other.getModbusRtuPeriod()))
            && (this.getSalveId() == null ? other.getSalveId() == null : this.getSalveId().equals(other.getSalveId()))
            && (this.getEquipmentCategoryId() == null ? other.getEquipmentCategoryId() == null : this.getEquipmentCategoryId().equals(other.getEquipmentCategoryId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getEquipmentModelId() == null) ? 0 : getEquipmentModelId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        result = prime * result + ((getImagePath() == null) ? 0 : getImagePath().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getCollectStatus() == null) ? 0 : getCollectStatus().hashCode());
        result = prime * result + ((getHeartData() == null) ? 0 : getHeartData().hashCode());
        result = prime * result + ((getGrm() == null) ? 0 : getGrm().hashCode());
        result = prime * result + ((getGrmPassword() == null) ? 0 : getGrmPassword().hashCode());
        result = prime * result + ((getGrmPeriod() == null) ? 0 : getGrmPeriod().hashCode());
        result = prime * result + ((getFactoryDate() == null) ? 0 : getFactoryDate().hashCode());
        result = prime * result + ((getCommissioningDate() == null) ? 0 : getCommissioningDate().hashCode());
        result = prime * result + ((getWarrantyStartDate() == null) ? 0 : getWarrantyStartDate().hashCode());
        result = prime * result + ((getWarrantyEndDate() == null) ? 0 : getWarrantyEndDate().hashCode());
        result = prime * result + ((getMaintenancePeriod() == null) ? 0 : getMaintenancePeriod().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getIsOnline() == null) ? 0 : getIsOnline().hashCode());
        result = prime * result + ((getModbusRtuPeriod() == null) ? 0 : getModbusRtuPeriod().hashCode());
        result = prime * result + ((getSalveId() == null) ? 0 : getSalveId().hashCode());
        result = prime * result + ((getEquipmentCategoryId() == null) ? 0 : getEquipmentCategoryId().hashCode());
        return result;
    }
}