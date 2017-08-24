package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamSensorDataExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamSensorDataExample() {
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

        public Criteria andSensorDataIdIsNull() {
            addCriterion("sensor_data_id is null");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdIsNotNull() {
            addCriterion("sensor_data_id is not null");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdEqualTo(Integer value) {
            addCriterion("sensor_data_id =", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdNotEqualTo(Integer value) {
            addCriterion("sensor_data_id <>", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdGreaterThan(Integer value) {
            addCriterion("sensor_data_id >", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sensor_data_id >=", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdLessThan(Integer value) {
            addCriterion("sensor_data_id <", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdLessThanOrEqualTo(Integer value) {
            addCriterion("sensor_data_id <=", value, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdIn(List<Integer> values) {
            addCriterion("sensor_data_id in", values, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdNotIn(List<Integer> values) {
            addCriterion("sensor_data_id not in", values, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdBetween(Integer value1, Integer value2) {
            addCriterion("sensor_data_id between", value1, value2, "sensorDataId");
            return (Criteria) this;
        }

        public Criteria andSensorDataIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sensor_data_id not between", value1, value2, "sensorDataId");
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

        public Criteria andStringValueIsNull() {
            addCriterion("string_value is null");
            return (Criteria) this;
        }

        public Criteria andStringValueIsNotNull() {
            addCriterion("string_value is not null");
            return (Criteria) this;
        }

        public Criteria andStringValueEqualTo(String value) {
            addCriterion("string_value =", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotEqualTo(String value) {
            addCriterion("string_value <>", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueGreaterThan(String value) {
            addCriterion("string_value >", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueGreaterThanOrEqualTo(String value) {
            addCriterion("string_value >=", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLessThan(String value) {
            addCriterion("string_value <", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLessThanOrEqualTo(String value) {
            addCriterion("string_value <=", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLike(String value) {
            addCriterion("string_value like", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotLike(String value) {
            addCriterion("string_value not like", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueIn(List<String> values) {
            addCriterion("string_value in", values, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotIn(List<String> values) {
            addCriterion("string_value not in", values, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueBetween(String value1, String value2) {
            addCriterion("string_value between", value1, value2, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotBetween(String value1, String value2) {
            addCriterion("string_value not between", value1, value2, "stringValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueIsNull() {
            addCriterion("number_value is null");
            return (Criteria) this;
        }

        public Criteria andNumberValueIsNotNull() {
            addCriterion("number_value is not null");
            return (Criteria) this;
        }

        public Criteria andNumberValueEqualTo(BigDecimal value) {
            addCriterion("number_value =", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotEqualTo(BigDecimal value) {
            addCriterion("number_value <>", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueGreaterThan(BigDecimal value) {
            addCriterion("number_value >", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("number_value >=", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueLessThan(BigDecimal value) {
            addCriterion("number_value <", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("number_value <=", value, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueIn(List<BigDecimal> values) {
            addCriterion("number_value in", values, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotIn(List<BigDecimal> values) {
            addCriterion("number_value not in", values, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("number_value between", value1, value2, "numberValue");
            return (Criteria) this;
        }

        public Criteria andNumberValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("number_value not between", value1, value2, "numberValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIsNull() {
            addCriterion("boolean_value is null");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIsNotNull() {
            addCriterion("boolean_value is not null");
            return (Criteria) this;
        }

        public Criteria andBooleanValueEqualTo(Boolean value) {
            addCriterion("boolean_value =", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotEqualTo(Boolean value) {
            addCriterion("boolean_value <>", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueGreaterThan(Boolean value) {
            addCriterion("boolean_value >", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueGreaterThanOrEqualTo(Boolean value) {
            addCriterion("boolean_value >=", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueLessThan(Boolean value) {
            addCriterion("boolean_value <", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueLessThanOrEqualTo(Boolean value) {
            addCriterion("boolean_value <=", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIn(List<Boolean> values) {
            addCriterion("boolean_value in", values, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotIn(List<Boolean> values) {
            addCriterion("boolean_value not in", values, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueBetween(Boolean value1, Boolean value2) {
            addCriterion("boolean_value between", value1, value2, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotBetween(Boolean value1, Boolean value2) {
            addCriterion("boolean_value not between", value1, value2, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueIsNull() {
            addCriterion("longitude_value is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueIsNotNull() {
            addCriterion("longitude_value is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueEqualTo(BigDecimal value) {
            addCriterion("longitude_value =", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueNotEqualTo(BigDecimal value) {
            addCriterion("longitude_value <>", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueGreaterThan(BigDecimal value) {
            addCriterion("longitude_value >", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude_value >=", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueLessThan(BigDecimal value) {
            addCriterion("longitude_value <", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("longitude_value <=", value, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueIn(List<BigDecimal> values) {
            addCriterion("longitude_value in", values, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueNotIn(List<BigDecimal> values) {
            addCriterion("longitude_value not in", values, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude_value between", value1, value2, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLongitudeValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("longitude_value not between", value1, value2, "longitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueIsNull() {
            addCriterion("latitude_value is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueIsNotNull() {
            addCriterion("latitude_value is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueEqualTo(BigDecimal value) {
            addCriterion("latitude_value =", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueNotEqualTo(BigDecimal value) {
            addCriterion("latitude_value <>", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueGreaterThan(BigDecimal value) {
            addCriterion("latitude_value >", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude_value >=", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueLessThan(BigDecimal value) {
            addCriterion("latitude_value <", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("latitude_value <=", value, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueIn(List<BigDecimal> values) {
            addCriterion("latitude_value in", values, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueNotIn(List<BigDecimal> values) {
            addCriterion("latitude_value not in", values, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude_value between", value1, value2, "latitudeValue");
            return (Criteria) this;
        }

        public Criteria andLatitudeValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("latitude_value not between", value1, value2, "latitudeValue");
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