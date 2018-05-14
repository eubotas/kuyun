package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamOrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamOrderExample() {
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
            addCriterion("eam_order.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("eam_order.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("eam_order.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("eam_order.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("eam_order.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_order.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("eam_order.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_order.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("eam_order.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("eam_order.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("eam_order.company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("eam_order.company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("eam_order.company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("eam_order.company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("eam_order.company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("eam_order.company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("eam_order.company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("eam_order.company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("eam_order.company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("eam_order.company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("eam_order.company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("eam_order.company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("eam_order.company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNull() {
            addCriterion("eam_order.short_name is null");
            return (Criteria) this;
        }

        public Criteria andShortNameIsNotNull() {
            addCriterion("eam_order.short_name is not null");
            return (Criteria) this;
        }

        public Criteria andShortNameEqualTo(String value) {
            addCriterion("eam_order.short_name =", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotEqualTo(String value) {
            addCriterion("eam_order.short_name <>", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThan(String value) {
            addCriterion("eam_order.short_name >", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.short_name >=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThan(String value) {
            addCriterion("eam_order.short_name <", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLessThanOrEqualTo(String value) {
            addCriterion("eam_order.short_name <=", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameLike(String value) {
            addCriterion("eam_order.short_name like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotLike(String value) {
            addCriterion("eam_order.short_name not like", value, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameIn(List<String> values) {
            addCriterion("eam_order.short_name in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotIn(List<String> values) {
            addCriterion("eam_order.short_name not in", values, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameBetween(String value1, String value2) {
            addCriterion("eam_order.short_name between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andShortNameNotBetween(String value1, String value2) {
            addCriterion("eam_order.short_name not between", value1, value2, "shortName");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("eam_order.year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("eam_order.year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("eam_order.year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("eam_order.year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("eam_order.year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("eam_order.year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("eam_order.year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("eam_order.year like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("eam_order.year not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("eam_order.year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("eam_order.year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("eam_order.year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("eam_order.year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIsNull() {
            addCriterion("eam_order.task_number is null");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIsNotNull() {
            addCriterion("eam_order.task_number is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNumberEqualTo(String value) {
            addCriterion("eam_order.task_number =", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotEqualTo(String value) {
            addCriterion("eam_order.task_number <>", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberGreaterThan(String value) {
            addCriterion("eam_order.task_number >", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.task_number >=", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberLessThan(String value) {
            addCriterion("eam_order.task_number <", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberLessThanOrEqualTo(String value) {
            addCriterion("eam_order.task_number <=", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberLike(String value) {
            addCriterion("eam_order.task_number like", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotLike(String value) {
            addCriterion("eam_order.task_number not like", value, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberIn(List<String> values) {
            addCriterion("eam_order.task_number in", values, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotIn(List<String> values) {
            addCriterion("eam_order.task_number not in", values, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberBetween(String value1, String value2) {
            addCriterion("eam_order.task_number between", value1, value2, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andTaskNumberNotBetween(String value1, String value2) {
            addCriterion("eam_order.task_number not between", value1, value2, "taskNumber");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("eam_order.state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("eam_order.state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("eam_order.state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("eam_order.state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("eam_order.state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("eam_order.state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("eam_order.state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("eam_order.state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("eam_order.state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("eam_order.state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("eam_order.state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("eam_order.state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("eam_order.state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("eam_order.country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("eam_order.country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("eam_order.country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("eam_order.country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("eam_order.country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("eam_order.country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("eam_order.country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("eam_order.country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("eam_order.country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("eam_order.country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("eam_order.country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("eam_order.country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("eam_order.country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("eam_order.province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("eam_order.province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("eam_order.province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("eam_order.province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("eam_order.province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("eam_order.province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("eam_order.province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("eam_order.province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("eam_order.province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("eam_order.province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("eam_order.province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("eam_order.province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("eam_order.province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("eam_order.city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("eam_order.city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("eam_order.city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("eam_order.city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("eam_order.city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("eam_order.city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("eam_order.city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("eam_order.city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("eam_order.city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("eam_order.city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("eam_order.city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("eam_order.city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("eam_order.city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("eam_order.industry is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("eam_order.industry is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("eam_order.industry =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("eam_order.industry <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("eam_order.industry >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.industry >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("eam_order.industry <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("eam_order.industry <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("eam_order.industry like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("eam_order.industry not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("eam_order.industry in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("eam_order.industry not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("eam_order.industry between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("eam_order.industry not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeIsNull() {
            addCriterion("eam_order.product_line_type is null");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeIsNotNull() {
            addCriterion("eam_order.product_line_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeEqualTo(String value) {
            addCriterion("eam_order.product_line_type =", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeNotEqualTo(String value) {
            addCriterion("eam_order.product_line_type <>", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeGreaterThan(String value) {
            addCriterion("eam_order.product_line_type >", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.product_line_type >=", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeLessThan(String value) {
            addCriterion("eam_order.product_line_type <", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeLessThanOrEqualTo(String value) {
            addCriterion("eam_order.product_line_type <=", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeLike(String value) {
            addCriterion("eam_order.product_line_type like", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeNotLike(String value) {
            addCriterion("eam_order.product_line_type not like", value, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeIn(List<String> values) {
            addCriterion("eam_order.product_line_type in", values, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeNotIn(List<String> values) {
            addCriterion("eam_order.product_line_type not in", values, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeBetween(String value1, String value2) {
            addCriterion("eam_order.product_line_type between", value1, value2, "productLineType");
            return (Criteria) this;
        }

        public Criteria andProductLineTypeNotBetween(String value1, String value2) {
            addCriterion("eam_order.product_line_type not between", value1, value2, "productLineType");
            return (Criteria) this;
        }

        public Criteria andHasCxgIsNull() {
            addCriterion("eam_order.has_cxg is null");
            return (Criteria) this;
        }

        public Criteria andHasCxgIsNotNull() {
            addCriterion("eam_order.has_cxg is not null");
            return (Criteria) this;
        }

        public Criteria andHasCxgEqualTo(Boolean value) {
            addCriterion("eam_order.has_cxg =", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgNotEqualTo(Boolean value) {
            addCriterion("eam_order.has_cxg <>", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgGreaterThan(Boolean value) {
            addCriterion("eam_order.has_cxg >", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.has_cxg >=", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgLessThan(Boolean value) {
            addCriterion("eam_order.has_cxg <", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.has_cxg <=", value, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgIn(List<Boolean> values) {
            addCriterion("eam_order.has_cxg in", values, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgNotIn(List<Boolean> values) {
            addCriterion("eam_order.has_cxg not in", values, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.has_cxg between", value1, value2, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasCxgNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.has_cxg not between", value1, value2, "hasCxg");
            return (Criteria) this;
        }

        public Criteria andHasZnlkIsNull() {
            addCriterion("eam_order.has_znlk is null");
            return (Criteria) this;
        }

        public Criteria andHasZnlkIsNotNull() {
            addCriterion("eam_order.has_znlk is not null");
            return (Criteria) this;
        }

        public Criteria andHasZnlkEqualTo(Boolean value) {
            addCriterion("eam_order.has_znlk =", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkNotEqualTo(Boolean value) {
            addCriterion("eam_order.has_znlk <>", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkGreaterThan(Boolean value) {
            addCriterion("eam_order.has_znlk >", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.has_znlk >=", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkLessThan(Boolean value) {
            addCriterion("eam_order.has_znlk <", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.has_znlk <=", value, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkIn(List<Boolean> values) {
            addCriterion("eam_order.has_znlk in", values, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkNotIn(List<Boolean> values) {
            addCriterion("eam_order.has_znlk not in", values, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.has_znlk between", value1, value2, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andHasZnlkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.has_znlk not between", value1, value2, "hasZnlk");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityIsNull() {
            addCriterion("eam_order.product_line_capacity is null");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityIsNotNull() {
            addCriterion("eam_order.product_line_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityEqualTo(String value) {
            addCriterion("eam_order.product_line_capacity =", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityNotEqualTo(String value) {
            addCriterion("eam_order.product_line_capacity <>", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityGreaterThan(String value) {
            addCriterion("eam_order.product_line_capacity >", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.product_line_capacity >=", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityLessThan(String value) {
            addCriterion("eam_order.product_line_capacity <", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityLessThanOrEqualTo(String value) {
            addCriterion("eam_order.product_line_capacity <=", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityLike(String value) {
            addCriterion("eam_order.product_line_capacity like", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityNotLike(String value) {
            addCriterion("eam_order.product_line_capacity not like", value, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityIn(List<String> values) {
            addCriterion("eam_order.product_line_capacity in", values, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityNotIn(List<String> values) {
            addCriterion("eam_order.product_line_capacity not in", values, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityBetween(String value1, String value2) {
            addCriterion("eam_order.product_line_capacity between", value1, value2, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andProductLineCapacityNotBetween(String value1, String value2) {
            addCriterion("eam_order.product_line_capacity not between", value1, value2, "productLineCapacity");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialIsNull() {
            addCriterion("eam_order.packaging_material is null");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialIsNotNull() {
            addCriterion("eam_order.packaging_material is not null");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialEqualTo(String value) {
            addCriterion("eam_order.packaging_material =", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialNotEqualTo(String value) {
            addCriterion("eam_order.packaging_material <>", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialGreaterThan(String value) {
            addCriterion("eam_order.packaging_material >", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.packaging_material >=", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialLessThan(String value) {
            addCriterion("eam_order.packaging_material <", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialLessThanOrEqualTo(String value) {
            addCriterion("eam_order.packaging_material <=", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialLike(String value) {
            addCriterion("eam_order.packaging_material like", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialNotLike(String value) {
            addCriterion("eam_order.packaging_material not like", value, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialIn(List<String> values) {
            addCriterion("eam_order.packaging_material in", values, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialNotIn(List<String> values) {
            addCriterion("eam_order.packaging_material not in", values, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialBetween(String value1, String value2) {
            addCriterion("eam_order.packaging_material between", value1, value2, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andPackagingMaterialNotBetween(String value1, String value2) {
            addCriterion("eam_order.packaging_material not between", value1, value2, "packagingMaterial");
            return (Criteria) this;
        }

        public Criteria andProductSpecIsNull() {
            addCriterion("eam_order.product_spec is null");
            return (Criteria) this;
        }

        public Criteria andProductSpecIsNotNull() {
            addCriterion("eam_order.product_spec is not null");
            return (Criteria) this;
        }

        public Criteria andProductSpecEqualTo(String value) {
            addCriterion("eam_order.product_spec =", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecNotEqualTo(String value) {
            addCriterion("eam_order.product_spec <>", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecGreaterThan(String value) {
            addCriterion("eam_order.product_spec >", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.product_spec >=", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecLessThan(String value) {
            addCriterion("eam_order.product_spec <", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecLessThanOrEqualTo(String value) {
            addCriterion("eam_order.product_spec <=", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecLike(String value) {
            addCriterion("eam_order.product_spec like", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecNotLike(String value) {
            addCriterion("eam_order.product_spec not like", value, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecIn(List<String> values) {
            addCriterion("eam_order.product_spec in", values, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecNotIn(List<String> values) {
            addCriterion("eam_order.product_spec not in", values, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecBetween(String value1, String value2) {
            addCriterion("eam_order.product_spec between", value1, value2, "productSpec");
            return (Criteria) this;
        }

        public Criteria andProductSpecNotBetween(String value1, String value2) {
            addCriterion("eam_order.product_spec not between", value1, value2, "productSpec");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentIsNull() {
            addCriterion("eam_order.major_equipment is null");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentIsNotNull() {
            addCriterion("eam_order.major_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentEqualTo(String value) {
            addCriterion("eam_order.major_equipment =", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentNotEqualTo(String value) {
            addCriterion("eam_order.major_equipment <>", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentGreaterThan(String value) {
            addCriterion("eam_order.major_equipment >", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("eam_order.major_equipment >=", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentLessThan(String value) {
            addCriterion("eam_order.major_equipment <", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentLessThanOrEqualTo(String value) {
            addCriterion("eam_order.major_equipment <=", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentLike(String value) {
            addCriterion("eam_order.major_equipment like", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentNotLike(String value) {
            addCriterion("eam_order.major_equipment not like", value, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentIn(List<String> values) {
            addCriterion("eam_order.major_equipment in", values, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentNotIn(List<String> values) {
            addCriterion("eam_order.major_equipment not in", values, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentBetween(String value1, String value2) {
            addCriterion("eam_order.major_equipment between", value1, value2, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andMajorEquipmentNotBetween(String value1, String value2) {
            addCriterion("eam_order.major_equipment not between", value1, value2, "majorEquipment");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_order.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_order.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_order.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_order.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_order.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_order.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_order.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_order.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_order.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_order.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_order.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_order.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_order.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_order.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_order.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_order.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_order.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_order.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_order.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_order.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_order.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_order.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_order.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_order.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_order.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_order.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_order.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_order.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_order.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_order.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_order.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_order.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_order.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_order.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_order.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_order.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_order.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_order.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_order.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_order.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_order.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_order.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_order.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_order.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_order.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_order.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_order.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_order.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_order.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_order.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_order.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_order.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_order.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_order.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_order.delete_flag not between", value1, value2, "deleteFlag");
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