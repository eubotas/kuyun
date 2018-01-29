package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamEquipmentDataGroup;

/**
 * Created by user on 2018-01-25.
 */
public class EamEquipmentDataGroupVO extends EamEquipmentDataGroup {

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String dataGroupName;

    private boolean checked;

    public String getDataGroupName() {
        return dataGroupName;
    }

    public void setDataGroupName(String dataGroupName) {
        this.dataGroupName = dataGroupName;
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
