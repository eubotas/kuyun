package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamGrmVariableDataHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018-02-15.
 */
public class EamGrmVariableDataHistoryVO extends EamGrmVariableDataHistory {

    private Date startDate;
    private Date endDate;
    private String dataElementName;
    private String equipmentIdIsNull;
    private List<Integer> dataElementIds;
    /**
     *  时间间隔 以采集频率的倍数， 单位为秒
     *
     */
    private Integer interval;
    private Integer grmPeriod;

    private Integer dataGroupId;
    private Integer equipmentDataGroupId;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public Integer getDataGroupId() {
        return dataGroupId;
    }

    public void setDataGroupId(Integer dataGroupId) {
        this.dataGroupId = dataGroupId;
    }

    public Integer getEquipmentDataGroupId() {
        return equipmentDataGroupId;
    }

    public void setEquipmentDataGroupId(Integer equipmentDataGroupId) {
        this.equipmentDataGroupId = equipmentDataGroupId;
    }

    public List<Integer> getDataElementIds() {
        return dataElementIds;
    }

    public void setDataElementIds(List<Integer> dataElementIds) {
        this.dataElementIds = dataElementIds;
    }

    public Integer getGrmPeriod() {
        return grmPeriod;
    }

    public void setGrmPeriod(Integer grmPeriod) {
        this.grmPeriod = grmPeriod;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEquipmentIdIsNull() {
        return equipmentIdIsNull;
    }

    public void setEquipmentIdIsNull(String equipmentIdIsNull) {
        this.equipmentIdIsNull = equipmentIdIsNull;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public String getDataElementName() {
        return dataElementName;
    }

    public void setDataElementName(String dataElementName) {
        this.dataElementName = dataElementName;
    }
}
