package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;

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

    private String morningShiftStartTime;

    private String morningShiftEndTime;

    private String middleShiftStartTime;

    private String middleShiftEndTime;

    private String nightShiftStartTime;

    private String nightShiftEndTime;

    private BigDecimal morningStopTime;

    private BigDecimal middleStopTime;

    private BigDecimal nightStopTime;

    /**
     * 存放实际产能 数据点ID
     *
     * @mbg.generated
     */
    private Integer actualCapacityId;

    /**
     * 额定产能
     *
     * @mbg.generated
     */
    private Integer baseCapacityId;

    /**
     * 合格数量
     *
     * @mbg.generated
     */
    private Integer qualifiedQuantityId;

    /**
     * 总数量
     *
     * @mbg.generated
     */
    private Integer totalQuantityId;

    /**
     * 统计变量
     *
     * @mbg.generated
     */
    private Integer statisticVariableId;

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

    public String getMorningShiftStartTime() {
        return morningShiftStartTime;
    }

    public void setMorningShiftStartTime(String morningShiftStartTime) {
        this.morningShiftStartTime = morningShiftStartTime;
    }

    public String getMorningShiftEndTime() {
        return morningShiftEndTime;
    }

    public void setMorningShiftEndTime(String morningShiftEndTime) {
        this.morningShiftEndTime = morningShiftEndTime;
    }

    public String getMiddleShiftStartTime() {
        return middleShiftStartTime;
    }

    public void setMiddleShiftStartTime(String middleShiftStartTime) {
        this.middleShiftStartTime = middleShiftStartTime;
    }

    public String getMiddleShiftEndTime() {
        return middleShiftEndTime;
    }

    public void setMiddleShiftEndTime(String middleShiftEndTime) {
        this.middleShiftEndTime = middleShiftEndTime;
    }

    public String getNightShiftStartTime() {
        return nightShiftStartTime;
    }

    public void setNightShiftStartTime(String nightShiftStartTime) {
        this.nightShiftStartTime = nightShiftStartTime;
    }

    public String getNightShiftEndTime() {
        return nightShiftEndTime;
    }

    public void setNightShiftEndTime(String nightShiftEndTime) {
        this.nightShiftEndTime = nightShiftEndTime;
    }

    public BigDecimal getMorningStopTime() {
        return morningStopTime;
    }

    public void setMorningStopTime(BigDecimal morningStopTime) {
        this.morningStopTime = morningStopTime;
    }

    public BigDecimal getMiddleStopTime() {
        return middleStopTime;
    }

    public void setMiddleStopTime(BigDecimal middleStopTime) {
        this.middleStopTime = middleStopTime;
    }

    public BigDecimal getNightStopTime() {
        return nightStopTime;
    }

    public void setNightStopTime(BigDecimal nightStopTime) {
        this.nightStopTime = nightStopTime;
    }

    public Integer getActualCapacityId() {
        return actualCapacityId;
    }

    public void setActualCapacityId(Integer actualCapacityId) {
        this.actualCapacityId = actualCapacityId;
    }

    public Integer getBaseCapacityId() {
        return baseCapacityId;
    }

    public void setBaseCapacityId(Integer baseCapacityId) {
        this.baseCapacityId = baseCapacityId;
    }

    public Integer getQualifiedQuantityId() {
        return qualifiedQuantityId;
    }

    public void setQualifiedQuantityId(Integer qualifiedQuantityId) {
        this.qualifiedQuantityId = qualifiedQuantityId;
    }

    public Integer getTotalQuantityId() {
        return totalQuantityId;
    }

    public void setTotalQuantityId(Integer totalQuantityId) {
        this.totalQuantityId = totalQuantityId;
    }

    public Integer getStatisticVariableId() {
        return statisticVariableId;
    }

    public void setStatisticVariableId(Integer statisticVariableId) {
        this.statisticVariableId = statisticVariableId;
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
        sb.append(", morningStopTime=").append(morningStopTime);
        sb.append(", middleStopTime=").append(middleStopTime);
        sb.append(", nightStopTime=").append(nightStopTime);
        sb.append(", actualCapacityId=").append(actualCapacityId);
        sb.append(", baseCapacityId=").append(baseCapacityId);
        sb.append(", qualifiedQuantityId=").append(qualifiedQuantityId);
        sb.append(", totalQuantityId=").append(totalQuantityId);
        sb.append(", statisticVariableId=").append(statisticVariableId);
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
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getMorningStopTime() == null ? other.getMorningStopTime() == null : this.getMorningStopTime().equals(other.getMorningStopTime()))
            && (this.getMiddleStopTime() == null ? other.getMiddleStopTime() == null : this.getMiddleStopTime().equals(other.getMiddleStopTime()))
            && (this.getNightStopTime() == null ? other.getNightStopTime() == null : this.getNightStopTime().equals(other.getNightStopTime()))
            && (this.getActualCapacityId() == null ? other.getActualCapacityId() == null : this.getActualCapacityId().equals(other.getActualCapacityId()))
            && (this.getBaseCapacityId() == null ? other.getBaseCapacityId() == null : this.getBaseCapacityId().equals(other.getBaseCapacityId()))
            && (this.getQualifiedQuantityId() == null ? other.getQualifiedQuantityId() == null : this.getQualifiedQuantityId().equals(other.getQualifiedQuantityId()))
            && (this.getTotalQuantityId() == null ? other.getTotalQuantityId() == null : this.getTotalQuantityId().equals(other.getTotalQuantityId()))
            && (this.getStatisticVariableId() == null ? other.getStatisticVariableId() == null : this.getStatisticVariableId().equals(other.getStatisticVariableId()));
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
        result = prime * result + ((getMorningStopTime() == null) ? 0 : getMorningStopTime().hashCode());
        result = prime * result + ((getMiddleStopTime() == null) ? 0 : getMiddleStopTime().hashCode());
        result = prime * result + ((getNightStopTime() == null) ? 0 : getNightStopTime().hashCode());
        result = prime * result + ((getActualCapacityId() == null) ? 0 : getActualCapacityId().hashCode());
        result = prime * result + ((getBaseCapacityId() == null) ? 0 : getBaseCapacityId().hashCode());
        result = prime * result + ((getQualifiedQuantityId() == null) ? 0 : getQualifiedQuantityId().hashCode());
        result = prime * result + ((getTotalQuantityId() == null) ? 0 : getTotalQuantityId().hashCode());
        result = prime * result + ((getStatisticVariableId() == null) ? 0 : getStatisticVariableId().hashCode());
        return result;
    }
}