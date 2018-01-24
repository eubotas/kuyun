package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamLocation;

/**
 * Created by user on 4/30/2017.
 */
public class EamLocationVO extends EamLocation {
    private String warehouseName;

    private Integer limit;

    private Integer offset;

    private String orderByClause;


    public String getWarehouseName() {
        return warehouseName;
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

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
