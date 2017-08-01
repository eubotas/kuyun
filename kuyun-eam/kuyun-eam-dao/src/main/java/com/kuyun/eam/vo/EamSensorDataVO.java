package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamSensorData;

/**
 * Created by user on 2017-08-01.
 */
public class EamSensorDataVO extends EamSensorData {
    private String equipmentId;

    private String equipmentName;

    private String equipmentModelPropertyId;

    private String equipmentModelPropertyName;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModelPropertyId() {
        return equipmentModelPropertyId;
    }

    public void setEquipmentModelPropertyId(String equipmentModelPropertyId) {
        this.equipmentModelPropertyId = equipmentModelPropertyId;
    }

    public String getEquipmentModelPropertyName() {
        return equipmentModelPropertyName;
    }

    public void setEquipmentModelPropertyName(String equipmentModelPropertyName) {
        this.equipmentModelPropertyName = equipmentModelPropertyName;
    }
}
