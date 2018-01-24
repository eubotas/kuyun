package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EamProductLine extends BaseEntity implements Serializable {
    private String productLineId;

    private String name;

    private String imagePath;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String province;

    private String city;

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

    /**
     * 采集状态
     *
     * @mbg.generated
     */
    private String collectStatus;

    private Boolean isOnline;

    private Date morningShiftStartTime;

    private Date morningShiftEndTime;

    private Date middleShiftStartTime;

    private Date middleShiftEndTime;

    private Date nightShiftStartTime;

    private Date nightShiftEndTime;

    private static final long serialVersionUID = 1L;

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

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Date getMorningShiftStartTime() {
        return morningShiftStartTime;
    }

    public void setMorningShiftStartTime(Date morningShiftStartTime) {
        this.morningShiftStartTime = morningShiftStartTime;
    }

    public Date getMorningShiftEndTime() {
        return morningShiftEndTime;
    }

    public void setMorningShiftEndTime(Date morningShiftEndTime) {
        this.morningShiftEndTime = morningShiftEndTime;
    }

    public Date getMiddleShiftStartTime() {
        return middleShiftStartTime;
    }

    public void setMiddleShiftStartTime(Date middleShiftStartTime) {
        this.middleShiftStartTime = middleShiftStartTime;
    }

    public Date getMiddleShiftEndTime() {
        return middleShiftEndTime;
    }

    public void setMiddleShiftEndTime(Date middleShiftEndTime) {
        this.middleShiftEndTime = middleShiftEndTime;
    }

    public Date getNightShiftStartTime() {
        return nightShiftStartTime;
    }

    public void setNightShiftStartTime(Date nightShiftStartTime) {
        this.nightShiftStartTime = nightShiftStartTime;
    }

    public Date getNightShiftEndTime() {
        return nightShiftEndTime;
    }

    public void setNightShiftEndTime(Date nightShiftEndTime) {
        this.nightShiftEndTime = nightShiftEndTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productLineId=").append(productLineId);
        sb.append(", name=").append(name);
        sb.append(", imagePath=").append(imagePath);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", grm=").append(grm);
        sb.append(", grmPassword=").append(grmPassword);
        sb.append(", grmPeriod=").append(grmPeriod);
        sb.append(", collectStatus=").append(collectStatus);
        sb.append(", isOnline=").append(isOnline);
        sb.append(", morningShiftStartTime=").append(morningShiftStartTime);
        sb.append(", morningShiftEndTime=").append(morningShiftEndTime);
        sb.append(", middleShiftStartTime=").append(middleShiftStartTime);
        sb.append(", middleShiftEndTime=").append(middleShiftEndTime);
        sb.append(", nightShiftStartTime=").append(nightShiftStartTime);
        sb.append(", nightShiftEndTime=").append(nightShiftEndTime);
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
        EamProductLine other = (EamProductLine) that;
        return (this.getProductLineId() == null ? other.getProductLineId() == null : this.getProductLineId().equals(other.getProductLineId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImagePath() == null ? other.getImagePath() == null : this.getImagePath().equals(other.getImagePath()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getGrm() == null ? other.getGrm() == null : this.getGrm().equals(other.getGrm()))
            && (this.getGrmPassword() == null ? other.getGrmPassword() == null : this.getGrmPassword().equals(other.getGrmPassword()))
            && (this.getGrmPeriod() == null ? other.getGrmPeriod() == null : this.getGrmPeriod().equals(other.getGrmPeriod()))
            && (this.getCollectStatus() == null ? other.getCollectStatus() == null : this.getCollectStatus().equals(other.getCollectStatus()))
            && (this.getIsOnline() == null ? other.getIsOnline() == null : this.getIsOnline().equals(other.getIsOnline()))
            && (this.getMorningShiftStartTime() == null ? other.getMorningShiftStartTime() == null : this.getMorningShiftStartTime().equals(other.getMorningShiftStartTime()))
            && (this.getMorningShiftEndTime() == null ? other.getMorningShiftEndTime() == null : this.getMorningShiftEndTime().equals(other.getMorningShiftEndTime()))
            && (this.getMiddleShiftStartTime() == null ? other.getMiddleShiftStartTime() == null : this.getMiddleShiftStartTime().equals(other.getMiddleShiftStartTime()))
            && (this.getMiddleShiftEndTime() == null ? other.getMiddleShiftEndTime() == null : this.getMiddleShiftEndTime().equals(other.getMiddleShiftEndTime()))
            && (this.getNightShiftStartTime() == null ? other.getNightShiftStartTime() == null : this.getNightShiftStartTime().equals(other.getNightShiftStartTime()))
            && (this.getNightShiftEndTime() == null ? other.getNightShiftEndTime() == null : this.getNightShiftEndTime().equals(other.getNightShiftEndTime()))
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
        result = prime * result + ((getProductLineId() == null) ? 0 : getProductLineId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImagePath() == null) ? 0 : getImagePath().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getGrm() == null) ? 0 : getGrm().hashCode());
        result = prime * result + ((getGrmPassword() == null) ? 0 : getGrmPassword().hashCode());
        result = prime * result + ((getGrmPeriod() == null) ? 0 : getGrmPeriod().hashCode());
        result = prime * result + ((getCollectStatus() == null) ? 0 : getCollectStatus().hashCode());
        result = prime * result + ((getIsOnline() == null) ? 0 : getIsOnline().hashCode());
        result = prime * result + ((getMorningShiftStartTime() == null) ? 0 : getMorningShiftStartTime().hashCode());
        result = prime * result + ((getMorningShiftEndTime() == null) ? 0 : getMorningShiftEndTime().hashCode());
        result = prime * result + ((getMiddleShiftStartTime() == null) ? 0 : getMiddleShiftStartTime().hashCode());
        result = prime * result + ((getMiddleShiftEndTime() == null) ? 0 : getMiddleShiftEndTime().hashCode());
        result = prime * result + ((getNightShiftStartTime() == null) ? 0 : getNightShiftStartTime().hashCode());
        result = prime * result + ((getNightShiftEndTime() == null) ? 0 : getNightShiftEndTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        return result;
    }
}