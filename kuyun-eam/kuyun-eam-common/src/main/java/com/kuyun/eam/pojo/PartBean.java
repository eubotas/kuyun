package com.kuyun.eam.pojo;

import com.kuyun.common.excel.BaseImportBean;

/**
 * Created by user on 2018-03-31.
 */
public class PartBean extends BaseImportBean {
    private String seqId;
    private String symbol;
    private String name;
    private String model;
    private String material;
    private String quantity;

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * 返回sheet对应的第一行的每一列的中文名字 例如{"第一列名字","第二列名字","第三列名字"}
     */
    @Override
    public String[] getHeaderName() {
        return new String[]{"序号", "代号", "名称", "型号", "材料", "数量", "所属部件"};
    }

    /**
     * 返回dto中定义的每个Field按指定顺序返回 例如{"firstColumn","secondColumn","thirdColumn"}
     * 如果只需要导出dto中的一部分字段，只需要添加需要导出的Field
     */
    @Override
    public String[] getSortedProperty() {
        return new String[]{"seqId", "symbol", "name", "model", "material", "quantity", "categoryName"};
    }
}
