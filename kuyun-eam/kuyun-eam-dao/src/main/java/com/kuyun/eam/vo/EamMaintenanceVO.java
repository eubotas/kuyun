package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamMaintenance;

/**
 * Created by user on 4/24/2017.
 */
public class EamMaintenanceVO extends EamMaintenance{
    //设备名称
    private String equipmentName;
    //设备编号
    private String equipmentNumber;

    //name 配件名称
    private  String partName;

    private String maintainUserName;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }


    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getMaintainUserName() {
        return maintainUserName;
    }

    public void setMaintainUserName(String maintainUserName) {
        this.maintainUserName = maintainUserName;
    }
}
