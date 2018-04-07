package com.kuyun.eam.pojo;

import com.kuyun.common.excel.BaseImportBean;

import java.io.Serializable;

/**
 * Created by user on 2018-03-25.
 */
public class OrderBean extends BaseImportBean{
    private String id;

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
    private String hasCxg;

    /**
     * 是否含智能立库
     *
     * @mbg.generated
     */
    private String hasZnlk;

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





    /**
     * 返回sheet对应的第一行的每一列的中文名字 例如{"第一列名字","第二列名字","第三列名字"}
     */
    @Override
    public String[] getHeaderName() {
        return new String[]{"序号", "客户名称", "年份", "任务单号", "洲", "国家", "省/州", "地/市", "所属行业", "产线类型", "是否含吹灌旋", "是否含智能立库", "生产线产能", "包装材质", "产品规格", "主要设备", "备注"};
    }

    /**
     * 返回dto中定义的每个Field按指定顺序返回 例如{"firstColumn","secondColumn","thirdColumn"}
     * 如果只需要导出dto中的一部分字段，只需要添加需要导出的Field
     */
    @Override
    public String[] getSortedProperty() {
        return new String[]{"id", "companyName", "year", "taskNumber", "state", "country", "province", "city", "industry", "productLineType", "hasCxg", "hasZnlk", "productLineCapacity", "packagingMaterial", "productSpec", "majorEquipment", "comment"};
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getHasCxg() {
        return hasCxg;
    }

    public void setHasCxg(String hasCxg) {
        this.hasCxg = hasCxg;
    }

    public String getHasZnlk() {
        return hasZnlk;
    }

    public void setHasZnlk(String hasZnlk) {
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
}
