package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamAlertMessageExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamAlertMessageExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("eam_alert_message.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("eam_alert_message.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("eam_alert_message.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNull() {
            addCriterion("eam_alert_message.plan_id is null");
            return (Criteria) this;
        }

        public Criteria andPlanIdIsNotNull() {
            addCriterion("eam_alert_message.plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlanIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.plan_id =", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.plan_id <>", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.plan_id >", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.plan_id >=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThan(Integer value) {
            addCriterion("eam_alert_message.plan_id <", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.plan_id <=", value, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.plan_id in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.plan_id not in", values, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.plan_id between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andPlanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.plan_id not between", value1, value2, "planId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("eam_alert_message.user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("eam_alert_message.user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("eam_alert_message.user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNull() {
            addCriterion("eam_alert_message.message_title is null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIsNotNull() {
            addCriterion("eam_alert_message.message_title is not null");
            return (Criteria) this;
        }

        public Criteria andMessageTitleEqualTo(String value) {
            addCriterion("eam_alert_message.message_title =", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotEqualTo(String value) {
            addCriterion("eam_alert_message.message_title <>", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThan(String value) {
            addCriterion("eam_alert_message.message_title >", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alert_message.message_title >=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThan(String value) {
            addCriterion("eam_alert_message.message_title <", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLessThanOrEqualTo(String value) {
            addCriterion("eam_alert_message.message_title <=", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleLike(String value) {
            addCriterion("eam_alert_message.message_title like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotLike(String value) {
            addCriterion("eam_alert_message.message_title not like", value, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleIn(List<String> values) {
            addCriterion("eam_alert_message.message_title in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotIn(List<String> values) {
            addCriterion("eam_alert_message.message_title not in", values, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleBetween(String value1, String value2) {
            addCriterion("eam_alert_message.message_title between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andMessageTitleNotBetween(String value1, String value2) {
            addCriterion("eam_alert_message.message_title not between", value1, value2, "messageTitle");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("eam_alert_message.content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("eam_alert_message.content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("eam_alert_message.content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("eam_alert_message.content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("eam_alert_message.content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("eam_alert_message.content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("eam_alert_message.content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("eam_alert_message.content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("eam_alert_message.content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("eam_alert_message.content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("eam_alert_message.content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("eam_alert_message.content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("eam_alert_message.content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("eam_alert_message.content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andReadFlagIsNull() {
            addCriterion("eam_alert_message.read_flag is null");
            return (Criteria) this;
        }

        public Criteria andReadFlagIsNotNull() {
            addCriterion("eam_alert_message.read_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReadFlagEqualTo(Boolean value) {
            addCriterion("eam_alert_message.read_flag =", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotEqualTo(Boolean value) {
            addCriterion("eam_alert_message.read_flag <>", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagGreaterThan(Boolean value) {
            addCriterion("eam_alert_message.read_flag >", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alert_message.read_flag >=", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagLessThan(Boolean value) {
            addCriterion("eam_alert_message.read_flag <", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alert_message.read_flag <=", value, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagIn(List<Boolean> values) {
            addCriterion("eam_alert_message.read_flag in", values, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotIn(List<Boolean> values) {
            addCriterion("eam_alert_message.read_flag not in", values, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alert_message.read_flag between", value1, value2, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alert_message.read_flag not between", value1, value2, "readFlag");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNull() {
            addCriterion("eam_alert_message.read_time is null");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNotNull() {
            addCriterion("eam_alert_message.read_time is not null");
            return (Criteria) this;
        }

        public Criteria andReadTimeEqualTo(Date value) {
            addCriterion("eam_alert_message.read_time =", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotEqualTo(Date value) {
            addCriterion("eam_alert_message.read_time <>", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThan(Date value) {
            addCriterion("eam_alert_message.read_time >", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.read_time >=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThan(Date value) {
            addCriterion("eam_alert_message.read_time <", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.read_time <=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeIn(List<Date> values) {
            addCriterion("eam_alert_message.read_time in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotIn(List<Date> values) {
            addCriterion("eam_alert_message.read_time not in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.read_time between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.read_time not between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateIsNull() {
            addCriterion("eam_alert_message.alert_start_date is null");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateIsNotNull() {
            addCriterion("eam_alert_message.alert_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_start_date =", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateNotEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_start_date <>", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateGreaterThan(Date value) {
            addCriterion("eam_alert_message.alert_start_date >", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_start_date >=", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateLessThan(Date value) {
            addCriterion("eam_alert_message.alert_start_date <", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_start_date <=", value, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateIn(List<Date> values) {
            addCriterion("eam_alert_message.alert_start_date in", values, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateNotIn(List<Date> values) {
            addCriterion("eam_alert_message.alert_start_date not in", values, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.alert_start_date between", value1, value2, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertStartDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.alert_start_date not between", value1, value2, "alertStartDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateIsNull() {
            addCriterion("eam_alert_message.alert_end_date is null");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateIsNotNull() {
            addCriterion("eam_alert_message.alert_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_end_date =", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateNotEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_end_date <>", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateGreaterThan(Date value) {
            addCriterion("eam_alert_message.alert_end_date >", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_end_date >=", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateLessThan(Date value) {
            addCriterion("eam_alert_message.alert_end_date <", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.alert_end_date <=", value, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateIn(List<Date> values) {
            addCriterion("eam_alert_message.alert_end_date in", values, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateNotIn(List<Date> values) {
            addCriterion("eam_alert_message.alert_end_date not in", values, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.alert_end_date between", value1, value2, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andAlertEndDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.alert_end_date not between", value1, value2, "alertEndDate");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_alert_message.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_alert_message.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_alert_message.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_alert_message.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_alert_message.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_alert_message.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_alert_message.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_alert_message.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_alert_message.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_alert_message.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_alert_message.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_alert_message.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_alert_message.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_alert_message.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_alert_message.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_alert_message.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_alert_message.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_alert_message.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_alert_message.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_alert_message.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_alert_message.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_alert_message.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_alert_message.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_alert_message.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_alert_message.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_alert_message.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_alert_message.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_alert_message.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_alert_message.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_alert_message.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_alert_message.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_alert_message.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_alert_message.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_alert_message.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alert_message.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_alert_message.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("eam_alert_message.company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("eam_alert_message.company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("eam_alert_message.company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("eam_alert_message.company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("eam_alert_message.company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("eam_alert_message.company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_alert_message.company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("eam_alert_message.company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("eam_alert_message.company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_alert_message.company_id not between", value1, value2, "companyId");
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