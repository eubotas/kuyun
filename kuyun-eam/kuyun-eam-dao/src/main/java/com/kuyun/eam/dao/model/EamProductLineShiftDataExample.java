package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamProductLineShiftDataExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamProductLineShiftDataExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIsNull() {
            addCriterion("product_line_id is null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIsNotNull() {
            addCriterion("product_line_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdEqualTo(String value) {
            addCriterion("product_line_id =", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotEqualTo(String value) {
            addCriterion("product_line_id <>", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThan(String value) {
            addCriterion("product_line_id >", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_line_id >=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThan(String value) {
            addCriterion("product_line_id <", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThanOrEqualTo(String value) {
            addCriterion("product_line_id <=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLike(String value) {
            addCriterion("product_line_id like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotLike(String value) {
            addCriterion("product_line_id not like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIn(List<String> values) {
            addCriterion("product_line_id in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotIn(List<String> values) {
            addCriterion("product_line_id not in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdBetween(String value1, String value2) {
            addCriterion("product_line_id between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotBetween(String value1, String value2) {
            addCriterion("product_line_id not between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIsNull() {
            addCriterion("actual_capacity is null");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIsNotNull() {
            addCriterion("actual_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andActualCapacityEqualTo(BigDecimal value) {
            addCriterion("actual_capacity =", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityNotEqualTo(BigDecimal value) {
            addCriterion("actual_capacity <>", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityGreaterThan(BigDecimal value) {
            addCriterion("actual_capacity >", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_capacity >=", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityLessThan(BigDecimal value) {
            addCriterion("actual_capacity <", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_capacity <=", value, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIn(List<BigDecimal> values) {
            addCriterion("actual_capacity in", values, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityNotIn(List<BigDecimal> values) {
            addCriterion("actual_capacity not in", values, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_capacity between", value1, value2, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andActualCapacityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_capacity not between", value1, value2, "actualCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIsNull() {
            addCriterion("base_capacity is null");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIsNotNull() {
            addCriterion("base_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityEqualTo(BigDecimal value) {
            addCriterion("base_capacity =", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityNotEqualTo(BigDecimal value) {
            addCriterion("base_capacity <>", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityGreaterThan(BigDecimal value) {
            addCriterion("base_capacity >", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("base_capacity >=", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityLessThan(BigDecimal value) {
            addCriterion("base_capacity <", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("base_capacity <=", value, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIn(List<BigDecimal> values) {
            addCriterion("base_capacity in", values, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityNotIn(List<BigDecimal> values) {
            addCriterion("base_capacity not in", values, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_capacity between", value1, value2, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("base_capacity not between", value1, value2, "baseCapacity");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateIsNull() {
            addCriterion("performance_rate is null");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateIsNotNull() {
            addCriterion("performance_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateEqualTo(BigDecimal value) {
            addCriterion("performance_rate =", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateNotEqualTo(BigDecimal value) {
            addCriterion("performance_rate <>", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateGreaterThan(BigDecimal value) {
            addCriterion("performance_rate >", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("performance_rate >=", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateLessThan(BigDecimal value) {
            addCriterion("performance_rate <", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("performance_rate <=", value, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateIn(List<BigDecimal> values) {
            addCriterion("performance_rate in", values, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateNotIn(List<BigDecimal> values) {
            addCriterion("performance_rate not in", values, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("performance_rate between", value1, value2, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andPerformanceRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("performance_rate not between", value1, value2, "performanceRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateIsNull() {
            addCriterion("qualified_rate is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateIsNotNull() {
            addCriterion("qualified_rate is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateEqualTo(BigDecimal value) {
            addCriterion("qualified_rate =", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateNotEqualTo(BigDecimal value) {
            addCriterion("qualified_rate <>", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateGreaterThan(BigDecimal value) {
            addCriterion("qualified_rate >", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qualified_rate >=", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateLessThan(BigDecimal value) {
            addCriterion("qualified_rate <", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qualified_rate <=", value, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateIn(List<BigDecimal> values) {
            addCriterion("qualified_rate in", values, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateNotIn(List<BigDecimal> values) {
            addCriterion("qualified_rate not in", values, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qualified_rate between", value1, value2, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andQualifiedRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qualified_rate not between", value1, value2, "qualifiedRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateIsNull() {
            addCriterion("rejection_rate is null");
            return (Criteria) this;
        }

        public Criteria andRejectionRateIsNotNull() {
            addCriterion("rejection_rate is not null");
            return (Criteria) this;
        }

        public Criteria andRejectionRateEqualTo(BigDecimal value) {
            addCriterion("rejection_rate =", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateNotEqualTo(BigDecimal value) {
            addCriterion("rejection_rate <>", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateGreaterThan(BigDecimal value) {
            addCriterion("rejection_rate >", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rejection_rate >=", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateLessThan(BigDecimal value) {
            addCriterion("rejection_rate <", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rejection_rate <=", value, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateIn(List<BigDecimal> values) {
            addCriterion("rejection_rate in", values, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateNotIn(List<BigDecimal> values) {
            addCriterion("rejection_rate not in", values, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rejection_rate between", value1, value2, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andRejectionRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rejection_rate not between", value1, value2, "rejectionRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateIsNull() {
            addCriterion("time_starting_rate is null");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateIsNotNull() {
            addCriterion("time_starting_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateEqualTo(BigDecimal value) {
            addCriterion("time_starting_rate =", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateNotEqualTo(BigDecimal value) {
            addCriterion("time_starting_rate <>", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateGreaterThan(BigDecimal value) {
            addCriterion("time_starting_rate >", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("time_starting_rate >=", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateLessThan(BigDecimal value) {
            addCriterion("time_starting_rate <", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("time_starting_rate <=", value, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateIn(List<BigDecimal> values) {
            addCriterion("time_starting_rate in", values, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateNotIn(List<BigDecimal> values) {
            addCriterion("time_starting_rate not in", values, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("time_starting_rate between", value1, value2, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andTimeStartingRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("time_starting_rate not between", value1, value2, "timeStartingRate");
            return (Criteria) this;
        }

        public Criteria andOeeIsNull() {
            addCriterion("oee is null");
            return (Criteria) this;
        }

        public Criteria andOeeIsNotNull() {
            addCriterion("oee is not null");
            return (Criteria) this;
        }

        public Criteria andOeeEqualTo(BigDecimal value) {
            addCriterion("oee =", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeNotEqualTo(BigDecimal value) {
            addCriterion("oee <>", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeGreaterThan(BigDecimal value) {
            addCriterion("oee >", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("oee >=", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeLessThan(BigDecimal value) {
            addCriterion("oee <", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("oee <=", value, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeIn(List<BigDecimal> values) {
            addCriterion("oee in", values, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeNotIn(List<BigDecimal> values) {
            addCriterion("oee not in", values, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("oee between", value1, value2, "oee");
            return (Criteria) this;
        }

        public Criteria andOeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("oee not between", value1, value2, "oee");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrIsNull() {
            addCriterion("sum_time_str is null");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrIsNotNull() {
            addCriterion("sum_time_str is not null");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrEqualTo(String value) {
            addCriterion("sum_time_str =", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrNotEqualTo(String value) {
            addCriterion("sum_time_str <>", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrGreaterThan(String value) {
            addCriterion("sum_time_str >", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrGreaterThanOrEqualTo(String value) {
            addCriterion("sum_time_str >=", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrLessThan(String value) {
            addCriterion("sum_time_str <", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrLessThanOrEqualTo(String value) {
            addCriterion("sum_time_str <=", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrLike(String value) {
            addCriterion("sum_time_str like", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrNotLike(String value) {
            addCriterion("sum_time_str not like", value, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrIn(List<String> values) {
            addCriterion("sum_time_str in", values, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrNotIn(List<String> values) {
            addCriterion("sum_time_str not in", values, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrBetween(String value1, String value2) {
            addCriterion("sum_time_str between", value1, value2, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeStrNotBetween(String value1, String value2) {
            addCriterion("sum_time_str not between", value1, value2, "sumTimeStr");
            return (Criteria) this;
        }

        public Criteria andSumTimeIsNull() {
            addCriterion("sum_time is null");
            return (Criteria) this;
        }

        public Criteria andSumTimeIsNotNull() {
            addCriterion("sum_time is not null");
            return (Criteria) this;
        }

        public Criteria andSumTimeEqualTo(BigDecimal value) {
            addCriterion("sum_time =", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeNotEqualTo(BigDecimal value) {
            addCriterion("sum_time <>", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeGreaterThan(BigDecimal value) {
            addCriterion("sum_time >", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_time >=", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeLessThan(BigDecimal value) {
            addCriterion("sum_time <", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sum_time <=", value, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeIn(List<BigDecimal> values) {
            addCriterion("sum_time in", values, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeNotIn(List<BigDecimal> values) {
            addCriterion("sum_time not in", values, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_time between", value1, value2, "sumTime");
            return (Criteria) this;
        }

        public Criteria andSumTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sum_time not between", value1, value2, "sumTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeIsNull() {
            addCriterion("shirt_time is null");
            return (Criteria) this;
        }

        public Criteria andShirtTimeIsNotNull() {
            addCriterion("shirt_time is not null");
            return (Criteria) this;
        }

        public Criteria andShirtTimeEqualTo(BigDecimal value) {
            addCriterion("shirt_time =", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeNotEqualTo(BigDecimal value) {
            addCriterion("shirt_time <>", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeGreaterThan(BigDecimal value) {
            addCriterion("shirt_time >", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shirt_time >=", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeLessThan(BigDecimal value) {
            addCriterion("shirt_time <", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shirt_time <=", value, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeIn(List<BigDecimal> values) {
            addCriterion("shirt_time in", values, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeNotIn(List<BigDecimal> values) {
            addCriterion("shirt_time not in", values, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shirt_time between", value1, value2, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andShirtTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shirt_time not between", value1, value2, "shirtTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeIsNull() {
            addCriterion("plan_stop_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeIsNotNull() {
            addCriterion("plan_stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeEqualTo(BigDecimal value) {
            addCriterion("plan_stop_time =", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeNotEqualTo(BigDecimal value) {
            addCriterion("plan_stop_time <>", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeGreaterThan(BigDecimal value) {
            addCriterion("plan_stop_time >", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_stop_time >=", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeLessThan(BigDecimal value) {
            addCriterion("plan_stop_time <", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_stop_time <=", value, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeIn(List<BigDecimal> values) {
            addCriterion("plan_stop_time in", values, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeNotIn(List<BigDecimal> values) {
            addCriterion("plan_stop_time not in", values, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_stop_time between", value1, value2, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andPlanStopTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_stop_time not between", value1, value2, "planStopTime");
            return (Criteria) this;
        }

        public Criteria andShiftNameIsNull() {
            addCriterion("shift_name is null");
            return (Criteria) this;
        }

        public Criteria andShiftNameIsNotNull() {
            addCriterion("shift_name is not null");
            return (Criteria) this;
        }

        public Criteria andShiftNameEqualTo(String value) {
            addCriterion("shift_name =", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameNotEqualTo(String value) {
            addCriterion("shift_name <>", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameGreaterThan(String value) {
            addCriterion("shift_name >", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameGreaterThanOrEqualTo(String value) {
            addCriterion("shift_name >=", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameLessThan(String value) {
            addCriterion("shift_name <", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameLessThanOrEqualTo(String value) {
            addCriterion("shift_name <=", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameLike(String value) {
            addCriterion("shift_name like", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameNotLike(String value) {
            addCriterion("shift_name not like", value, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameIn(List<String> values) {
            addCriterion("shift_name in", values, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameNotIn(List<String> values) {
            addCriterion("shift_name not in", values, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameBetween(String value1, String value2) {
            addCriterion("shift_name between", value1, value2, "shiftName");
            return (Criteria) this;
        }

        public Criteria andShiftNameNotBetween(String value1, String value2) {
            addCriterion("shift_name not between", value1, value2, "shiftName");
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

        public Criteria andPreformConsumeIsNull() {
            addCriterion("preform_consume is null");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeIsNotNull() {
            addCriterion("preform_consume is not null");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeEqualTo(Integer value) {
            addCriterion("preform_consume =", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeNotEqualTo(Integer value) {
            addCriterion("preform_consume <>", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeGreaterThan(Integer value) {
            addCriterion("preform_consume >", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("preform_consume >=", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeLessThan(Integer value) {
            addCriterion("preform_consume <", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeLessThanOrEqualTo(Integer value) {
            addCriterion("preform_consume <=", value, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeIn(List<Integer> values) {
            addCriterion("preform_consume in", values, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeNotIn(List<Integer> values) {
            addCriterion("preform_consume not in", values, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeBetween(Integer value1, Integer value2) {
            addCriterion("preform_consume between", value1, value2, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andPreformConsumeNotBetween(Integer value1, Integer value2) {
            addCriterion("preform_consume not between", value1, value2, "preformConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeIsNull() {
            addCriterion("cap_consume is null");
            return (Criteria) this;
        }

        public Criteria andCapConsumeIsNotNull() {
            addCriterion("cap_consume is not null");
            return (Criteria) this;
        }

        public Criteria andCapConsumeEqualTo(Integer value) {
            addCriterion("cap_consume =", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeNotEqualTo(Integer value) {
            addCriterion("cap_consume <>", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeGreaterThan(Integer value) {
            addCriterion("cap_consume >", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cap_consume >=", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeLessThan(Integer value) {
            addCriterion("cap_consume <", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeLessThanOrEqualTo(Integer value) {
            addCriterion("cap_consume <=", value, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeIn(List<Integer> values) {
            addCriterion("cap_consume in", values, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeNotIn(List<Integer> values) {
            addCriterion("cap_consume not in", values, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeBetween(Integer value1, Integer value2) {
            addCriterion("cap_consume between", value1, value2, "capConsume");
            return (Criteria) this;
        }

        public Criteria andCapConsumeNotBetween(Integer value1, Integer value2) {
            addCriterion("cap_consume not between", value1, value2, "capConsume");
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