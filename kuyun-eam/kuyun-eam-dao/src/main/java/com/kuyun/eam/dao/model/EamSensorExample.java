package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamSensorExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamSensorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSensorIdIsNull() {
            addCriterion("sensor_id is null");
            return (Criteria) this;
        }

        public Criteria andSensorIdIsNotNull() {
            addCriterion("sensor_id is not null");
            return (Criteria) this;
        }

        public Criteria andSensorIdEqualTo(Integer value) {
            addCriterion("sensor_id =", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdNotEqualTo(Integer value) {
            addCriterion("sensor_id <>", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdGreaterThan(Integer value) {
            addCriterion("sensor_id >", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sensor_id >=", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdLessThan(Integer value) {
            addCriterion("sensor_id <", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdLessThanOrEqualTo(Integer value) {
            addCriterion("sensor_id <=", value, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdIn(List<Integer> values) {
            addCriterion("sensor_id in", values, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdNotIn(List<Integer> values) {
            addCriterion("sensor_id not in", values, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdBetween(Integer value1, Integer value2) {
            addCriterion("sensor_id between", value1, value2, "sensorId");
            return (Criteria) this;
        }

        public Criteria andSensorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sensor_id not between", value1, value2, "sensorId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNull() {
            addCriterion("equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(String value) {
            addCriterion("equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(String value) {
            addCriterion("equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(String value) {
            addCriterion("equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(String value) {
            addCriterion("equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(String value) {
            addCriterion("equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLike(String value) {
            addCriterion("equipment_id like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotLike(String value) {
            addCriterion("equipment_id not like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<String> values) {
            addCriterion("equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<String> values) {
            addCriterion("equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(String value1, String value2) {
            addCriterion("equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIsNull() {
            addCriterion("equipment_model_property_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIsNotNull() {
            addCriterion("equipment_model_property_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdEqualTo(Integer value) {
            addCriterion("equipment_model_property_id =", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotEqualTo(Integer value) {
            addCriterion("equipment_model_property_id <>", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdGreaterThan(Integer value) {
            addCriterion("equipment_model_property_id >", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipment_model_property_id >=", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdLessThan(Integer value) {
            addCriterion("equipment_model_property_id <", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdLessThanOrEqualTo(Integer value) {
            addCriterion("equipment_model_property_id <=", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIn(List<Integer> values) {
            addCriterion("equipment_model_property_id in", values, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotIn(List<Integer> values) {
            addCriterion("equipment_model_property_id not in", values, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdBetween(Integer value1, Integer value2) {
            addCriterion("equipment_model_property_id between", value1, value2, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("equipment_model_property_id not between", value1, value2, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andSalveIdIsNull() {
            addCriterion("salve_id is null");
            return (Criteria) this;
        }

        public Criteria andSalveIdIsNotNull() {
            addCriterion("salve_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalveIdEqualTo(Integer value) {
            addCriterion("salve_id =", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotEqualTo(Integer value) {
            addCriterion("salve_id <>", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdGreaterThan(Integer value) {
            addCriterion("salve_id >", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("salve_id >=", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdLessThan(Integer value) {
            addCriterion("salve_id <", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdLessThanOrEqualTo(Integer value) {
            addCriterion("salve_id <=", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdIn(List<Integer> values) {
            addCriterion("salve_id in", values, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotIn(List<Integer> values) {
            addCriterion("salve_id not in", values, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdBetween(Integer value1, Integer value2) {
            addCriterion("salve_id between", value1, value2, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("salve_id not between", value1, value2, "salveId");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNull() {
            addCriterion("function_code is null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNotNull() {
            addCriterion("function_code is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeEqualTo(Integer value) {
            addCriterion("function_code =", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotEqualTo(Integer value) {
            addCriterion("function_code <>", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThan(Integer value) {
            addCriterion("function_code >", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("function_code >=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThan(Integer value) {
            addCriterion("function_code <", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThanOrEqualTo(Integer value) {
            addCriterion("function_code <=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIn(List<Integer> values) {
            addCriterion("function_code in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotIn(List<Integer> values) {
            addCriterion("function_code not in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeBetween(Integer value1, Integer value2) {
            addCriterion("function_code between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("function_code not between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(Integer value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(Integer value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(Integer value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(Integer value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(Integer value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(Integer value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<Integer> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<Integer> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(Integer value1, Integer value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(Integer value1, Integer value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNull() {
            addCriterion("data_format is null");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNotNull() {
            addCriterion("data_format is not null");
            return (Criteria) this;
        }

        public Criteria andDataFormatEqualTo(String value) {
            addCriterion("data_format =", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotEqualTo(String value) {
            addCriterion("data_format <>", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThan(String value) {
            addCriterion("data_format >", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThanOrEqualTo(String value) {
            addCriterion("data_format >=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThan(String value) {
            addCriterion("data_format <", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThanOrEqualTo(String value) {
            addCriterion("data_format <=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLike(String value) {
            addCriterion("data_format like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotLike(String value) {
            addCriterion("data_format not like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatIn(List<String> values) {
            addCriterion("data_format in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotIn(List<String> values) {
            addCriterion("data_format not in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatBetween(String value1, String value2) {
            addCriterion("data_format between", value1, value2, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotBetween(String value1, String value2) {
            addCriterion("data_format not between", value1, value2, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andBitOrderIsNull() {
            addCriterion("bit_order is null");
            return (Criteria) this;
        }

        public Criteria andBitOrderIsNotNull() {
            addCriterion("bit_order is not null");
            return (Criteria) this;
        }

        public Criteria andBitOrderEqualTo(String value) {
            addCriterion("bit_order =", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderNotEqualTo(String value) {
            addCriterion("bit_order <>", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderGreaterThan(String value) {
            addCriterion("bit_order >", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderGreaterThanOrEqualTo(String value) {
            addCriterion("bit_order >=", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderLessThan(String value) {
            addCriterion("bit_order <", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderLessThanOrEqualTo(String value) {
            addCriterion("bit_order <=", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderLike(String value) {
            addCriterion("bit_order like", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderNotLike(String value) {
            addCriterion("bit_order not like", value, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderIn(List<String> values) {
            addCriterion("bit_order in", values, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderNotIn(List<String> values) {
            addCriterion("bit_order not in", values, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderBetween(String value1, String value2) {
            addCriterion("bit_order between", value1, value2, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andBitOrderNotBetween(String value1, String value2) {
            addCriterion("bit_order not between", value1, value2, "bitOrder");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Integer value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Integer value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Integer value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Integer value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Integer> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Integer> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Integer value1, Integer value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andWriteNumberIsNull() {
            addCriterion("write_number is null");
            return (Criteria) this;
        }

        public Criteria andWriteNumberIsNotNull() {
            addCriterion("write_number is not null");
            return (Criteria) this;
        }

        public Criteria andWriteNumberEqualTo(Integer value) {
            addCriterion("write_number =", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberNotEqualTo(Integer value) {
            addCriterion("write_number <>", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberGreaterThan(Integer value) {
            addCriterion("write_number >", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("write_number >=", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberLessThan(Integer value) {
            addCriterion("write_number <", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberLessThanOrEqualTo(Integer value) {
            addCriterion("write_number <=", value, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberIn(List<Integer> values) {
            addCriterion("write_number in", values, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberNotIn(List<Integer> values) {
            addCriterion("write_number not in", values, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberBetween(Integer value1, Integer value2) {
            addCriterion("write_number between", value1, value2, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andWriteNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("write_number not between", value1, value2, "writeNumber");
            return (Criteria) this;
        }

        public Criteria andGrmActionIsNull() {
            addCriterion("grm_action is null");
            return (Criteria) this;
        }

        public Criteria andGrmActionIsNotNull() {
            addCriterion("grm_action is not null");
            return (Criteria) this;
        }

        public Criteria andGrmActionEqualTo(String value) {
            addCriterion("grm_action =", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionNotEqualTo(String value) {
            addCriterion("grm_action <>", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionGreaterThan(String value) {
            addCriterion("grm_action >", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionGreaterThanOrEqualTo(String value) {
            addCriterion("grm_action >=", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionLessThan(String value) {
            addCriterion("grm_action <", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionLessThanOrEqualTo(String value) {
            addCriterion("grm_action <=", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionLike(String value) {
            addCriterion("grm_action like", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionNotLike(String value) {
            addCriterion("grm_action not like", value, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionIn(List<String> values) {
            addCriterion("grm_action in", values, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionNotIn(List<String> values) {
            addCriterion("grm_action not in", values, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionBetween(String value1, String value2) {
            addCriterion("grm_action between", value1, value2, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmActionNotBetween(String value1, String value2) {
            addCriterion("grm_action not between", value1, value2, "grmAction");
            return (Criteria) this;
        }

        public Criteria andGrmVariableIsNull() {
            addCriterion("grm_variable is null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableIsNotNull() {
            addCriterion("grm_variable is not null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableEqualTo(String value) {
            addCriterion("grm_variable =", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableNotEqualTo(String value) {
            addCriterion("grm_variable <>", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableGreaterThan(String value) {
            addCriterion("grm_variable >", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableGreaterThanOrEqualTo(String value) {
            addCriterion("grm_variable >=", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableLessThan(String value) {
            addCriterion("grm_variable <", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableLessThanOrEqualTo(String value) {
            addCriterion("grm_variable <=", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableLike(String value) {
            addCriterion("grm_variable like", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableNotLike(String value) {
            addCriterion("grm_variable not like", value, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableIn(List<String> values) {
            addCriterion("grm_variable in", values, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableNotIn(List<String> values) {
            addCriterion("grm_variable not in", values, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableBetween(String value1, String value2) {
            addCriterion("grm_variable between", value1, value2, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableNotBetween(String value1, String value2) {
            addCriterion("grm_variable not between", value1, value2, "grmVariable");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueIsNull() {
            addCriterion("grm_variable_value is null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueIsNotNull() {
            addCriterion("grm_variable_value is not null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueEqualTo(String value) {
            addCriterion("grm_variable_value =", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueNotEqualTo(String value) {
            addCriterion("grm_variable_value <>", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueGreaterThan(String value) {
            addCriterion("grm_variable_value >", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueGreaterThanOrEqualTo(String value) {
            addCriterion("grm_variable_value >=", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueLessThan(String value) {
            addCriterion("grm_variable_value <", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueLessThanOrEqualTo(String value) {
            addCriterion("grm_variable_value <=", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueLike(String value) {
            addCriterion("grm_variable_value like", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueNotLike(String value) {
            addCriterion("grm_variable_value not like", value, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueIn(List<String> values) {
            addCriterion("grm_variable_value in", values, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueNotIn(List<String> values) {
            addCriterion("grm_variable_value not in", values, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueBetween(String value1, String value2) {
            addCriterion("grm_variable_value between", value1, value2, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableValueNotBetween(String value1, String value2) {
            addCriterion("grm_variable_value not between", value1, value2, "grmVariableValue");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderIsNull() {
            addCriterion("grm_variable_order is null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderIsNotNull() {
            addCriterion("grm_variable_order is not null");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderEqualTo(Integer value) {
            addCriterion("grm_variable_order =", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderNotEqualTo(Integer value) {
            addCriterion("grm_variable_order <>", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderGreaterThan(Integer value) {
            addCriterion("grm_variable_order >", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("grm_variable_order >=", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderLessThan(Integer value) {
            addCriterion("grm_variable_order <", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderLessThanOrEqualTo(Integer value) {
            addCriterion("grm_variable_order <=", value, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderIn(List<Integer> values) {
            addCriterion("grm_variable_order in", values, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderNotIn(List<Integer> values) {
            addCriterion("grm_variable_order not in", values, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderBetween(Integer value1, Integer value2) {
            addCriterion("grm_variable_order between", value1, value2, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andGrmVariableOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("grm_variable_order not between", value1, value2, "grmVariableOrder");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Integer value) {
            addCriterion("organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Integer value) {
            addCriterion("organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Integer value) {
            addCriterion("organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Integer value) {
            addCriterion("organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Integer value) {
            addCriterion("organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Integer> values) {
            addCriterion("organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Integer> values) {
            addCriterion("organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Integer value1, Integer value2) {
            addCriterion("organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("organization_id not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOshIsNull() {
            addCriterion("osh is null");
            return (Criteria) this;
        }

        public Criteria andOshIsNotNull() {
            addCriterion("osh is not null");
            return (Criteria) this;
        }

        public Criteria andOshEqualTo(BigDecimal value) {
            addCriterion("osh =", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshNotEqualTo(BigDecimal value) {
            addCriterion("osh <>", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshGreaterThan(BigDecimal value) {
            addCriterion("osh >", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("osh >=", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshLessThan(BigDecimal value) {
            addCriterion("osh <", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshLessThanOrEqualTo(BigDecimal value) {
            addCriterion("osh <=", value, "osh");
            return (Criteria) this;
        }

        public Criteria andOshIn(List<BigDecimal> values) {
            addCriterion("osh in", values, "osh");
            return (Criteria) this;
        }

        public Criteria andOshNotIn(List<BigDecimal> values) {
            addCriterion("osh not in", values, "osh");
            return (Criteria) this;
        }

        public Criteria andOshBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("osh between", value1, value2, "osh");
            return (Criteria) this;
        }

        public Criteria andOshNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("osh not between", value1, value2, "osh");
            return (Criteria) this;
        }

        public Criteria andOslIsNull() {
            addCriterion("osl is null");
            return (Criteria) this;
        }

        public Criteria andOslIsNotNull() {
            addCriterion("osl is not null");
            return (Criteria) this;
        }

        public Criteria andOslEqualTo(BigDecimal value) {
            addCriterion("osl =", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslNotEqualTo(BigDecimal value) {
            addCriterion("osl <>", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslGreaterThan(BigDecimal value) {
            addCriterion("osl >", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("osl >=", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslLessThan(BigDecimal value) {
            addCriterion("osl <", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslLessThanOrEqualTo(BigDecimal value) {
            addCriterion("osl <=", value, "osl");
            return (Criteria) this;
        }

        public Criteria andOslIn(List<BigDecimal> values) {
            addCriterion("osl in", values, "osl");
            return (Criteria) this;
        }

        public Criteria andOslNotIn(List<BigDecimal> values) {
            addCriterion("osl not in", values, "osl");
            return (Criteria) this;
        }

        public Criteria andOslBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("osl between", value1, value2, "osl");
            return (Criteria) this;
        }

        public Criteria andOslNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("osl not between", value1, value2, "osl");
            return (Criteria) this;
        }

        public Criteria andIshIsNull() {
            addCriterion("ish is null");
            return (Criteria) this;
        }

        public Criteria andIshIsNotNull() {
            addCriterion("ish is not null");
            return (Criteria) this;
        }

        public Criteria andIshEqualTo(BigDecimal value) {
            addCriterion("ish =", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshNotEqualTo(BigDecimal value) {
            addCriterion("ish <>", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshGreaterThan(BigDecimal value) {
            addCriterion("ish >", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ish >=", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshLessThan(BigDecimal value) {
            addCriterion("ish <", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ish <=", value, "ish");
            return (Criteria) this;
        }

        public Criteria andIshIn(List<BigDecimal> values) {
            addCriterion("ish in", values, "ish");
            return (Criteria) this;
        }

        public Criteria andIshNotIn(List<BigDecimal> values) {
            addCriterion("ish not in", values, "ish");
            return (Criteria) this;
        }

        public Criteria andIshBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ish between", value1, value2, "ish");
            return (Criteria) this;
        }

        public Criteria andIshNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ish not between", value1, value2, "ish");
            return (Criteria) this;
        }

        public Criteria andIslIsNull() {
            addCriterion("isl is null");
            return (Criteria) this;
        }

        public Criteria andIslIsNotNull() {
            addCriterion("isl is not null");
            return (Criteria) this;
        }

        public Criteria andIslEqualTo(BigDecimal value) {
            addCriterion("isl =", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslNotEqualTo(BigDecimal value) {
            addCriterion("isl <>", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslGreaterThan(BigDecimal value) {
            addCriterion("isl >", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("isl >=", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslLessThan(BigDecimal value) {
            addCriterion("isl <", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslLessThanOrEqualTo(BigDecimal value) {
            addCriterion("isl <=", value, "isl");
            return (Criteria) this;
        }

        public Criteria andIslIn(List<BigDecimal> values) {
            addCriterion("isl in", values, "isl");
            return (Criteria) this;
        }

        public Criteria andIslNotIn(List<BigDecimal> values) {
            addCriterion("isl not in", values, "isl");
            return (Criteria) this;
        }

        public Criteria andIslBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("isl between", value1, value2, "isl");
            return (Criteria) this;
        }

        public Criteria andIslNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("isl not between", value1, value2, "isl");
            return (Criteria) this;
        }

        public Criteria addConditionSql(String conditionSql) {
            addCriterion(conditionSql);
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}