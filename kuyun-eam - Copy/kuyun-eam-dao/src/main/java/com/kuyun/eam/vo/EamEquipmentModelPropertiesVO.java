package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamEquipmentModelProperties;

/**
 * Created by user on 2017-10-19.
 */
public class EamEquipmentModelPropertiesVO extends EamEquipmentModelProperties {

    private Integer sensorId;
    private String writeValue;
    private String equipmentId;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getWriteValue() {
        return writeValue;
    }

    public void setWriteValue(String writeValue) {
        this.writeValue = writeValue;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
}
