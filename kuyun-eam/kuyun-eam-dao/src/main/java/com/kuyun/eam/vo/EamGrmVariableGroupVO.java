package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamGrmVariableGroup;

/**
 * Created by user on 2018-05-30.
 */
public class EamGrmVariableGroupVO extends EamGrmVariableGroup {
    private String productLineId;
    private String equipmentId;


    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

}
