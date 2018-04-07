package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamOrder extends BaseEntity implements Serializable {
    private Integer id;

    private String companyName;

    /**
     * 年份
     *
     * @mbg.generated
     */
    private String year;

    /**
     * 任务单号
     *
     * @mbg.generated
     */
    private String taskNumber;

    /**
     * 洲
     *
     * @mbg.generated
     */
    private String state;

    /**
     * 国家
     *
     * @mbg.generated
     */
    private String country;

    /**
     * 省/州
     *
     * @mbg.generated
     */
    private String province;

    /**
     * 地/市
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 所属行业
     *
     * @mbg.generated
     */
    private String industry;

    /**
     * 产线类型
     *
     * @mbg.generated
     */
    private String productLineType;

    /**
     * 是否含吹灌旋
     *
     * @mbg.generated
     */
    private Boolean hasCxg;

    /**
     * 是否含智能立库
     *
     * @mbg.generated
     */
    private Boolean hasZnlk;

    /**
     * 生产线产能
     *
     * @mbg.generated
     */
    private String productLineCapacity;

    /**
     * 包装材质
     *
     * @mbg.generated
     */
    private String packagingMaterial;

    /**
     * 产品规格
     *
     * @mbg.generated
     */
    private String productSpec;

    /**
     * 主要设备
     *
     * @mbg.generated
     */
    private String majorEquipment;

    /**
     * 备注
     *
     * @mbg.generated
     */
    private String comment;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }

    public Boolean getHasCxg() {
        return hasCxg;
    }

    public void setHasCxg(Boolean hasCxg) {
        this.hasCxg = hasCxg;
    }

    public Boolean getHasZnlk() {
        return hasZnlk;
    }

    public void setHasZnlk(Boolean hasZnlk) {
        this.hasZnlk = hasZnlk;
    }

    public String getProductLineCapacity() {
        return productLineCapacity;
    }

    public void setProductLineCapacity(String productLineCapacity) {
        this.productLineCapacity = productLineCapacity;
    }

    public String getPackagingMaterial() {
        return packagingMaterial;
    }

    public void setPackagingMaterial(String packagingMaterial) {
        this.packagingMaterial = packagingMaterial;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getMajorEquipment() {
        return majorEquipment;
    }

    public void setMajorEquipment(String majorEquipment) {
        this.majorEquipment = majorEquipment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", year=").append(year);
        sb.append(", taskNumber=").append(taskNumber);
        sb.append(", state=").append(state);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", industry=").append(industry);
        sb.append(", productLineType=").append(productLineType);
        sb.append(", hasCxg=").append(hasCxg);
        sb.append(", hasZnlk=").append(hasZnlk);
        sb.append(", productLineCapacity=").append(productLineCapacity);
        sb.append(", packagingMaterial=").append(packagingMaterial);
        sb.append(", productSpec=").append(productSpec);
        sb.append(", majorEquipment=").append(majorEquipment);
        sb.append(", comment=").append(comment);
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
        EamOrder other = (EamOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getTaskNumber() == null ? other.getTaskNumber() == null : this.getTaskNumber().equals(other.getTaskNumber()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCountry() == null ? other.getCountry() == null : this.getCountry().equals(other.getCountry()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getIndustry() == null ? other.getIndustry() == null : this.getIndustry().equals(other.getIndustry()))
            && (this.getProductLineType() == null ? other.getProductLineType() == null : this.getProductLineType().equals(other.getProductLineType()))
            && (this.getHasCxg() == null ? other.getHasCxg() == null : this.getHasCxg().equals(other.getHasCxg()))
            && (this.getHasZnlk() == null ? other.getHasZnlk() == null : this.getHasZnlk().equals(other.getHasZnlk()))
            && (this.getProductLineCapacity() == null ? other.getProductLineCapacity() == null : this.getProductLineCapacity().equals(other.getProductLineCapacity()))
            && (this.getPackagingMaterial() == null ? other.getPackagingMaterial() == null : this.getPackagingMaterial().equals(other.getPackagingMaterial()))
            && (this.getProductSpec() == null ? other.getProductSpec() == null : this.getProductSpec().equals(other.getProductSpec()))
            && (this.getMajorEquipment() == null ? other.getMajorEquipment() == null : this.getMajorEquipment().equals(other.getMajorEquipment()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getTaskNumber() == null) ? 0 : getTaskNumber().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getIndustry() == null) ? 0 : getIndustry().hashCode());
        result = prime * result + ((getProductLineType() == null) ? 0 : getProductLineType().hashCode());
        result = prime * result + ((getHasCxg() == null) ? 0 : getHasCxg().hashCode());
        result = prime * result + ((getHasZnlk() == null) ? 0 : getHasZnlk().hashCode());
        result = prime * result + ((getProductLineCapacity() == null) ? 0 : getProductLineCapacity().hashCode());
        result = prime * result + ((getPackagingMaterial() == null) ? 0 : getPackagingMaterial().hashCode());
        result = prime * result + ((getProductSpec() == null) ? 0 : getProductSpec().hashCode());
        result = prime * result + ((getMajorEquipment() == null) ? 0 : getMajorEquipment().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        return result;
    }
}