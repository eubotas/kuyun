package com.kuyun.eam.vo;

import com.kuyun.eam.common.constant.GrmVariableAttribute;
import com.kuyun.eam.common.constant.GrmVariableNtworkPermisstion;
import com.kuyun.eam.common.constant.GrmVariableType;
import com.kuyun.eam.dao.model.EamGrmVariable;

import java.util.List;

/**
 * Created by user on 2018-01-02.
 */
public class EamGrmVariableVO extends EamGrmVariable {
    private Boolean checked;
    private String productLineName;
    private String equipmentName;
    private String dataGroupId;

    private String equipmentDataGroupId;

    private List<Integer> grmVariableIds;
    private List<Integer> dataElementIds;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public String getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(String dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public String getEquipmentDataGroupId() {
        return equipmentDataGroupId;
    }

    public void setEquipmentDataGroupId(String equipmentDataGroupId) {
        this.equipmentDataGroupId = equipmentDataGroupId;
    }

    public List<Integer> getDataElementIds() {
        return dataElementIds;
    }

    public void setDataElementIds(List<Integer> dataElementIds) {
        this.dataElementIds = dataElementIds;
    }

    public List<Integer> getGrmVariableIds() {
        return grmVariableIds;
    }

    public void setGrmVariableIds(List<Integer> grmVariableIds) {
        this.grmVariableIds = grmVariableIds;
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

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

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
