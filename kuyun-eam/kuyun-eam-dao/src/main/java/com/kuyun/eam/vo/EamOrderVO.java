package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamOrder;

/**
 * Created by user on 5/1/2017.
 */
public class EamOrderVO extends EamOrder {
    private Integer limit;

    private Integer offset;

    private String orderByClause;

    private String search;

    private String stateName;
    private String industryName;
    private String productLineTypeName;
    private String productLineCapacityName;
    private String packagingMaterialName;
    private String productSpecName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getProductLineTypeName() {
        return productLineTypeName;
    }

    public void setProductLineTypeName(String productLineTypeName) {
        this.productLineTypeName = productLineTypeName;
    }

    public String getProductLineCapacityName() {
        return productLineCapacityName;
    }

    public void setProductLineCapacityName(String productLineCapacityName) {
        this.productLineCapacityName = productLineCapacityName;
    }

    public String getPackagingMaterialName() {
        return packagingMaterialName;
    }

    public void setPackagingMaterialName(String packagingMaterialName) {
        this.packagingMaterialName = packagingMaterialName;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

   }
