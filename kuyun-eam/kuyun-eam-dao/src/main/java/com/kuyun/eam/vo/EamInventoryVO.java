package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamInventory;

/**
 * Created by user on 5/1/2017.
 */
public class EamInventoryVO extends EamInventory {
    private String warehouseName;
    private String locationNumber;
    private String partName;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(String locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
