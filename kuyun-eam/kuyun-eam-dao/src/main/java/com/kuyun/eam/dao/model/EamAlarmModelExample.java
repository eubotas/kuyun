package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamAlarmModelExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamAlarmModelExample() {
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

        public Criteria andAlarmModelIdIsNull() {
            addCriterion("eam_alarm_model.alarm_model_id is null");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdIsNotNull() {
            addCriterion("eam_alarm_model.alarm_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdEqualTo(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id =", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id <>", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdGreaterThan(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id >", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id >=", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdLessThan(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id <", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.alarm_model_id <=", value, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdIn(List<Integer> values) {
            addCriterion("eam_alarm_model.alarm_model_id in", values, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm_model.alarm_model_id not in", values, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.alarm_model_id between", value1, value2, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andAlarmModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.alarm_model_id not between", value1, value2, "alarmModelId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("eam_alarm_model.name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("eam_alarm_model.name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("eam_alarm_model.name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("eam_alarm_model.name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("eam_alarm_model.name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alarm_model.name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("eam_alarm_model.name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("eam_alarm_model.name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("eam_alarm_model.name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("eam_alarm_model.name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("eam_alarm_model.name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("eam_alarm_model.name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("eam_alarm_model.name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("eam_alarm_model.name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdIsNull() {
            addCriterion("eam_alarm_model.eam_data_element_id is null");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdIsNotNull() {
            addCriterion("eam_alarm_model.eam_data_element_id is not null");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdEqualTo(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id =", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id <>", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdGreaterThan(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id >", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id >=", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdLessThan(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id <", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.eam_data_element_id <=", value, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdIn(List<Integer> values) {
            addCriterion("eam_alarm_model.eam_data_element_id in", values, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm_model.eam_data_element_id not in", values, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.eam_data_element_id between", value1, value2, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andEamDataElementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.eam_data_element_id not between", value1, value2, "eamDataElementId");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIsNull() {
            addCriterion("eam_alarm_model.alarm_type is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIsNotNull() {
            addCriterion("eam_alarm_model.alarm_type is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeEqualTo(String value) {
            addCriterion("eam_alarm_model.alarm_type =", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotEqualTo(String value) {
            addCriterion("eam_alarm_model.alarm_type <>", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThan(String value) {
            addCriterion("eam_alarm_model.alarm_type >", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alarm_model.alarm_type >=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThan(String value) {
            addCriterion("eam_alarm_model.alarm_type <", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLessThanOrEqualTo(String value) {
            addCriterion("eam_alarm_model.alarm_type <=", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeLike(String value) {
            addCriterion("eam_alarm_model.alarm_type like", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotLike(String value) {
            addCriterion("eam_alarm_model.alarm_type not like", value, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeIn(List<String> values) {
            addCriterion("eam_alarm_model.alarm_type in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotIn(List<String> values) {
            addCriterion("eam_alarm_model.alarm_type not in", values, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeBetween(String value1, String value2) {
            addCriterion("eam_alarm_model.alarm_type between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andAlarmTypeNotBetween(String value1, String value2) {
            addCriterion("eam_alarm_model.alarm_type not between", value1, value2, "alarmType");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIsNull() {
            addCriterion("eam_alarm_model.upper_Bound is null");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIsNotNull() {
            addCriterion("eam_alarm_model.upper_Bound is not null");
            return (Criteria) this;
        }

        public Criteria andUpperBoundEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound =", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound <>", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound >", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound >=", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundLessThan(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound <", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.upper_Bound <=", value, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.upper_Bound in", values, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.upper_Bound not in", values, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.upper_Bound between", value1, value2, "upperBound");
            return (Criteria) this;
        }

        public Criteria andUpperBoundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.upper_Bound not between", value1, value2, "upperBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIsNull() {
            addCriterion("eam_alarm_model.lower_Bound is null");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIsNotNull() {
            addCriterion("eam_alarm_model.lower_Bound is not null");
            return (Criteria) this;
        }

        public Criteria andLowerBoundEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound =", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound <>", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound >", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound >=", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundLessThan(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound <", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.lower_Bound <=", value, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.lower_Bound in", values, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.lower_Bound not in", values, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.lower_Bound between", value1, value2, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andLowerBoundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.lower_Bound not between", value1, value2, "lowerBound");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("eam_alarm_model.duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("eam_alarm_model.duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(BigDecimal value) {
            addCriterion("eam_alarm_model.duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(BigDecimal value) {
            addCriterion("eam_alarm_model.duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_alarm_model.duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<BigDecimal> values) {
            addCriterion("eam_alarm_model.duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_alarm_model.duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_alarm_model.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_alarm_model.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_alarm_model.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm_model.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alarm_model.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_alarm_model.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_alarm_model.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm_model.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_alarm_model.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_alarm_model.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_alarm_model.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_alarm_model.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_alarm_model.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alarm_model.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_alarm_model.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alarm_model.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_alarm_model.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_alarm_model.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alarm_model.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alarm_model.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_alarm_model.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_alarm_model.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_alarm_model.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm_model.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alarm_model.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_alarm_model.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_alarm_model.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm_model.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_alarm_model.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_alarm_model.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_alarm_model.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_alarm_model.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_alarm_model.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alarm_model.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_alarm_model.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alarm_model.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_alarm_model.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_alarm_model.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alarm_model.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alarm_model.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_alarm_model.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_alarm_model.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_alarm_model.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_alarm_model.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm_model.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm_model.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("eam_alarm_model.company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("eam_alarm_model.company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("eam_alarm_model.company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("eam_alarm_model.company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("eam_alarm_model.company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("eam_alarm_model.company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alarm_model.company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("eam_alarm_model.company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("eam_alarm_model.company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alarm_model.company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIsNull() {
            addCriterion("eam_alarm_model.is_create_ticket is null");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIsNotNull() {
            addCriterion("eam_alarm_model.is_create_ticket is not null");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket =", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket <>", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketGreaterThan(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket >", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket >=", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketLessThan(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket <", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alarm_model.is_create_ticket <=", value, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketIn(List<Boolean> values) {
            addCriterion("eam_alarm_model.is_create_ticket in", values, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotIn(List<Boolean> values) {
            addCriterion("eam_alarm_model.is_create_ticket not in", values, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm_model.is_create_ticket between", value1, value2, "isCreateTicket");
            return (Criteria) this;
        }

        public Criteria andIsCreateTicketNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alarm_model.is_create_ticket not between", value1, value2, "isCreateTicket");
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