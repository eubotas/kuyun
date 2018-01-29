package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamDataElement;

/**
 * Created by user on 2018-01-25.
 */
public class EamDataElementVO extends EamDataElement {

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String equipmentCategoryName;

    private boolean checked;

    public String getEquipmentCategoryName() {
        return equipmentCategoryName;
    }

    public void setEquipmentCategoryName(String equipmentCategoryName) {
        this.equipmentCategoryName = equipmentCategoryName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

}
