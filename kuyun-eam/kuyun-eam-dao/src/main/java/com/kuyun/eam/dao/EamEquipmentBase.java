package com.kuyun.eam.dao;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.eam.dao.model.EamEquipmentModel;
import com.kuyun.eam.dao.model.EamSensor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EamEquipmentBase extends BaseEntity implements Serializable {
    private EamEquipmentModel eamEquipmentModel;

    public EamEquipmentModel getEamEquipmentModel() {
        return eamEquipmentModel;
    }

    public void setEamEquipmentModel(EamEquipmentModel eamEquipmentModel) {
        this.eamEquipmentModel = eamEquipmentModel;
    }

    private List<EamSensor> sensors = Collections.emptyList();

    public List<EamSensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<EamSensor> sensors) {
        this.sensors = sensors;
    }
}