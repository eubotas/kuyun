package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamEquipment;

/**
 * Created by user on 5/1/2017.
 */
public class EamEquipmentVO extends EamEquipment {

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String dtuId;
    private String grmId;
    private String equipmentModelName;

    private Boolean checked;

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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getGrmId() {
        return grmId;
    }

    public void setGrmId(String grmId) {
        this.grmId = grmId;
    }

    public String getDtuId() {
        return dtuId;
    }

    public void setDtuId(String dtuId) {
        this.dtuId = dtuId;
    }

    public String getEquipmentModelName() {
        return equipmentModelName;
    }

    public void setEquipmentModelName(String equipmentModelName) {
        this.equipmentModelName = equipmentModelName;
    }
}
