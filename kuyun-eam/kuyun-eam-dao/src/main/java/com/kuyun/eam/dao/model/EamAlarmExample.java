package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamAlarmExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamAlarmExample() {
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

        public Criteria andAlarmIdIsNull() {
            addCriterion("eam_alarm.alarm_id is null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIsNotNull() {
            addCriterion("eam_alarm.alarm_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmIdEqualTo(Integer value) {
            addCriterion("eam_alarm.alarm_id =", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm.alarm_id <>", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThan(Integer value) {
            addCriterion("eam_alarm.alarm_id >", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.alarm_id >=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThan(Integer value) {
            addCriterion("eam_alarm.alarm_id <", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.alarm_id <=", value, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdIn(List<Integer> values) {
            addCriterion("eam_alarm.alarm_id in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm.alarm_id not in", values, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.alarm_id between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.alarm_id not between", value1, value2, "alarmId");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIsNull() {
            addCriterion("eam_alarm.alarm_type is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIsNotNull() {
            addCriterion("eam_alarm.alarm_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeEqualTo(String value) {
            addCriterion("eam_alarm.alarm_type =", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotEqualTo(String value) {
            addCriterion("eam_alarm.alarm_type <>", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThan(String value) {
            addCriterion("eam_alarm.alarm_type >", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alarm.alarm_type >=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThan(String value) {
            addCriterion("eam_alarm.alarm_type <", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThanOrEqualTo(String value) {
            addCriterion("eam_alarm.alarm_type <=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLike(String value) {
            addCriterion("eam_alarm.alarm_type like", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotLike(String value) {
            addCriterion("eam_alarm.alarm_type not like", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIn(List<String> values) {
            addCriterion("eam_alarm.alarm_type in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotIn(List<String> values) {
            addCriterion("eam_alarm.alarm_type not in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeBetween(String value1, String value2) {
            addCriterion("eam_alarm.alarm_type between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotBetween(String value1, String value2) {
            addCriterion("eam_alarm.alarm_type not between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIsNull() {
            addCriterion("eam_alarm.upper_Bound is null");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIsNotNull() {
            addCriterion("eam_alarm.upper_Bound is not null");
            return (Criteria) this;
        }

        public Criteria andUpperBoundEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound =", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound <>", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound >", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound >=", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundLessThan(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound <", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.upper_Bound <=", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.upper_Bound in", values, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.upper_Bound not in", values, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.upper_Bound between", value1, value2, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.upper_Bound not between", value1, value2, "upperBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIsNull() {
            addCriterion("eam_alarm.lower_Bound is null");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIsNotNull() {
            addCriterion("eam_alarm.lower_Bound is not null");
            return (Criteria) this;
        }

        public Criteria andLowerBoundEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound =", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound <>", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound >", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound >=", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundLessThan(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound <", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.lower_Bound <=", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.lower_Bound in", values, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.lower_Bound not in", values, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.lower_Bound between", value1, value2, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.lower_Bound not between", value1, value2, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("eam_alarm.duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("eam_alarm.duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm.duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(BigDecimal value) {
            addCriterion("eam_alarm.duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm.duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm.duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm.duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetIsNull() {
            addCriterion("eam_alarm.alarm_target is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetIsNotNull() {
            addCriterion("eam_alarm.alarm_target is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetEqualTo(String value) {
            addCriterion("eam_alarm.alarm_target =", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetNotEqualTo(String value) {
            addCriterion("eam_alarm.alarm_target <>", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetGreaterThan(String value) {
            addCriterion("eam_alarm.alarm_target >", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alarm.alarm_target >=", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetLessThan(String value) {
            addCriterion("eam_alarm.alarm_target <", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetLessThanOrEqualTo(String value) {
            addCriterion("eam_alarm.alarm_target <=", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetLike(String value) {
            addCriterion("eam_alarm.alarm_target like", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetNotLike(String value) {
            addCriterion("eam_alarm.alarm_target not like", value, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetIn(List<String> values) {
            addCriterion("eam_alarm.alarm_target in", values, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetNotIn(List<String> values) {
            addCriterion("eam_alarm.alarm_target not in", values, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetBetween(String value1, String value2) {
            addCriterion("eam_alarm.alarm_target between", value1, value2, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andAlarmTargetNotBetween(String value1, String value2) {
            addCriterion("eam_alarm.alarm_target not between", value1, value2, "alarmTarget");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIsNull() {
            addCriterion("eam_alarm.equipment_model_property_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIsNotNull() {
            addCriterion("eam_alarm.equipment_model_property_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdEqualTo(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id =", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id <>", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdGreaterThan(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id >", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id >=", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdLessThan(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id <", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.equipment_model_property_id <=", value, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdIn(List<Integer> values) {
            addCriterion("eam_alarm.equipment_model_property_id in", values, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm.equipment_model_property_id not in", values, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.equipment_model_property_id between", value1, value2, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelPropertyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.equipment_model_property_id not between", value1, value2, "equipmentModelPropertyId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_alarm.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_alarm.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_alarm.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alarm.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_alarm.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_alarm.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_alarm.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_alarm.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_alarm.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_alarm.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_alarm.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alarm.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_alarm.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alarm.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_alarm.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_alarm.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alarm.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alarm.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_alarm.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_alarm.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_alarm.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alarm.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_alarm.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_alarm.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_alarm.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_alarm.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_alarm.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_alarm.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_alarm.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alarm.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_alarm.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alarm.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_alarm.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_alarm.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alarm.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alarm.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_alarm.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_alarm.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_alarm.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_alarm.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_alarm.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_alarm.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_alarm.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_alarm.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIsNull() {
            addCriterion("eam_alarm.is_create_ticket is null");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIsNotNull() {
            addCriterion("eam_alarm.is_create_ticket is not null");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketEqualTo(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket =", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotEqualTo(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket <>", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketGreaterThan(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket >", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket >=", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketLessThan(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket <", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm.is_create_ticket <=", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIn(List<Boolean> values) {
            addCriterion("eam_alarm.is_create_ticket in", values, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotIn(List<Boolean> values) {
            addCriterion("eam_alarm.is_create_ticket not in", values, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm.is_create_ticket between", value1, value2, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm.is_create_ticket not between", value1, value2, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("eam_alarm.company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("eam_alarm.company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("eam_alarm.company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm.company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("eam_alarm.company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("eam_alarm.company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm.company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("eam_alarm.company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm.company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm.company_id not between", value1, value2, "companyId");
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