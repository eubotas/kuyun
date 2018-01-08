package com.kuyun.eam.vo;

import com.kuyun.eam.common.constant.GrmVariableAttribute;
import com.kuyun.eam.common.constant.GrmVariableNtworkPermisstion;
import com.kuyun.eam.common.constant.GrmVariableType;
import com.kuyun.eam.dao.model.EamGrmEquipmentVariable;

/**
 * Created by user on 2018-01-02.
 */
public class EamGrmEquipmentVariableVO extends EamGrmEquipmentVariable {
    private Boolean checked;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getTypeDesc(){
        return GrmVariableType.getName(getType());
    }

    public String getAttributeDesc(){
        return GrmVariableAttribute.getName(getAttribute());
    }

    public String getNetworkPermisstionDesc(){
        return GrmVariableNtworkPermisstion.getName(getNetworkPermisstion());
    }

}
