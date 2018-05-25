package com.kuyun.eam.pojo;

import com.kuyun.common.excel.BaseImportBean;

/**
 * Created by user on 2018-05-17.
 */
public class DataElementBean extends BaseImportBean {
    private String id;
    /**
     * 变量名
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 显示名称
     *
     * @mbg.generated
     */
    private String labelName;

    /**
     * 单位
     *
     * @mbg.generated
     */
    private String unit;

    /**
     * 数据类型(analog, digital)
     *
     * @mbg.generated
     */
    private String dataType;

    private String equipmentCategory;

    /**
     * 是否按年、月、日统计
     *
     * @mbg.generated
     */
    private String statisticByDate;

    /**
     * 是否按班次统计
     *
     * @mbg.generated
     */
    private String statisticByShift;

    /**
     * 是否累加统计
     *
     * @mbg.generated
     */
    private String summation;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public String getStatisticByDate() {
        return statisticByDate;
    }

    public void setStatisticByDate(String statisticByDate) {
        this.statisticByDate = statisticByDate;
    }

    public String getStatisticByShift() {
        return statisticByShift;
    }

    public void setStatisticByShift(String statisticByShift) {
        this.statisticByShift = statisticByShift;
    }

    public String getSummation() {
        return summation;
    }

    public void setSummation(String summation) {
        this.summation = summation;
    }

    /**
     * 返回sheet对应的第一行的每一列的中文名字 例如{"第一列名字","第二列名字","第三列名字"}
     */
    @Override
    public String[] getHeaderName() {
        return new String[]{"序号", "变量名", "显示名称","单位", "设备类别", "数据类型", "是否按年、月、日统计", "是否按班次统计", "是否累加统计"};
    }

    /**
     * 返回dto中定义的每个Field按指定顺序返回 例如{"firstColumn","secondColumn","thirdColumn"}
     * 如果只需要导出dto中的一部分字段，只需要添加需要导出的Field
     */
    @Override
    public String[] getSortedProperty() {
        return new String[]{"id", "name", "labelName", "unit", "equipmentCategory", "dataType", "statisticByDate", "statisticByShift", "summation"};
    }
}
