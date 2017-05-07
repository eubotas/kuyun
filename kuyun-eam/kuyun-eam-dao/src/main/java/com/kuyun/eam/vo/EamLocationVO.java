package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamLocation;

/**
 * Created by user on 4/30/2017.
 */
public class EamLocationVO extends EamLocation {
    private String warehouseName;

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
