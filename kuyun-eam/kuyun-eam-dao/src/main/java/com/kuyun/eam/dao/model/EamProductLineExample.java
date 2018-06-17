package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamProductLineExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamProductLineExample() {
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

        public Criteria andProductLineIdIsNull() {
            addCriterion("eam_product_line.product_line_id is null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIsNotNull() {
            addCriterion("eam_product_line.product_line_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdEqualTo(String value) {
            addCriterion("eam_product_line.product_line_id =", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotEqualTo(String value) {
            addCriterion("eam_product_line.product_line_id <>", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThan(String value) {
            addCriterion("eam_product_line.product_line_id >", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.product_line_id >=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThan(String value) {
            addCriterion("eam_product_line.product_line_id <", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.product_line_id <=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLike(String value) {
            addCriterion("eam_product_line.product_line_id like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotLike(String value) {
            addCriterion("eam_product_line.product_line_id not like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIn(List<String> values) {
            addCriterion("eam_product_line.product_line_id in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotIn(List<String> values) {
            addCriterion("eam_product_line.product_line_id not in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdBetween(String value1, String value2) {
            addCriterion("eam_product_line.product_line_id between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.product_line_id not between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("eam_product_line.name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("eam_product_line.name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("eam_product_line.name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("eam_product_line.name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("eam_product_line.name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("eam_product_line.name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("eam_product_line.name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("eam_product_line.name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("eam_product_line.name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("eam_product_line.name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("eam_product_line.name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNull() {
            addCriterion("eam_product_line.image_path is null");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNotNull() {
            addCriterion("eam_product_line.image_path is not null");
            return (Criteria) this;
        }

        public Criteria andImagePathEqualTo(String value) {
            addCriterion("eam_product_line.image_path =", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotEqualTo(String value) {
            addCriterion("eam_product_line.image_path <>", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThan(String value) {
            addCriterion("eam_product_line.image_path >", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.image_path >=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThan(String value) {
            addCriterion("eam_product_line.image_path <", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.image_path <=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLike(String value) {
            addCriterion("eam_product_line.image_path like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotLike(String value) {
            addCriterion("eam_product_line.image_path not like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathIn(List<String> values) {
            addCriterion("eam_product_line.image_path in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotIn(List<String> values) {
            addCriterion("eam_product_line.image_path not in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathBetween(String value1, String value2) {
            addCriterion("eam_product_line.image_path between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.image_path not between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("eam_product_line.longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("eam_product_line.longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("eam_product_line.longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("eam_product_line.longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("eam_product_line.latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("eam_product_line.latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("eam_product_line.latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("eam_product_line.latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("eam_product_line.province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("eam_product_line.province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("eam_product_line.province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("eam_product_line.province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("eam_product_line.province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("eam_product_line.province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("eam_product_line.province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("eam_product_line.province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("eam_product_line.province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("eam_product_line.province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("eam_product_line.province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("eam_product_line.city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("eam_product_line.city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("eam_product_line.city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("eam_product_line.city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("eam_product_line.city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("eam_product_line.city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("eam_product_line.city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("eam_product_line.city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("eam_product_line.city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("eam_product_line.city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("eam_product_line.city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andGrmIsNull() {
            addCriterion("eam_product_line.grm is null");
            return (Criteria) this;
        }

        public Criteria andGrmIsNotNull() {
            addCriterion("eam_product_line.grm is not null");
            return (Criteria) this;
        }

        public Criteria andGrmEqualTo(String value) {
            addCriterion("eam_product_line.grm =", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotEqualTo(String value) {
            addCriterion("eam_product_line.grm <>", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmGreaterThan(String value) {
            addCriterion("eam_product_line.grm >", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.grm >=", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLessThan(String value) {
            addCriterion("eam_product_line.grm <", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.grm <=", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLike(String value) {
            addCriterion("eam_product_line.grm like", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotLike(String value) {
            addCriterion("eam_product_line.grm not like", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmIn(List<String> values) {
            addCriterion("eam_product_line.grm in", values, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotIn(List<String> values) {
            addCriterion("eam_product_line.grm not in", values, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmBetween(String value1, String value2) {
            addCriterion("eam_product_line.grm between", value1, value2, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.grm not between", value1, value2, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIsNull() {
            addCriterion("eam_product_line.grm_password is null");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIsNotNull() {
            addCriterion("eam_product_line.grm_password is not null");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordEqualTo(String value) {
            addCriterion("eam_product_line.grm_password =", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotEqualTo(String value) {
            addCriterion("eam_product_line.grm_password <>", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordGreaterThan(String value) {
            addCriterion("eam_product_line.grm_password >", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.grm_password >=", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLessThan(String value) {
            addCriterion("eam_product_line.grm_password <", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.grm_password <=", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLike(String value) {
            addCriterion("eam_product_line.grm_password like", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotLike(String value) {
            addCriterion("eam_product_line.grm_password not like", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIn(List<String> values) {
            addCriterion("eam_product_line.grm_password in", values, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotIn(List<String> values) {
            addCriterion("eam_product_line.grm_password not in", values, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordBetween(String value1, String value2) {
            addCriterion("eam_product_line.grm_password between", value1, value2, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.grm_password not between", value1, value2, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNull() {
            addCriterion("eam_product_line.grm_period is null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNotNull() {
            addCriterion("eam_product_line.grm_period is not null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodEqualTo(Integer value) {
            addCriterion("eam_product_line.grm_period =", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotEqualTo(Integer value) {
            addCriterion("eam_product_line.grm_period <>", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThan(Integer value) {
            addCriterion("eam_product_line.grm_period >", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.grm_period >=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThan(Integer value) {
            addCriterion("eam_product_line.grm_period <", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.grm_period <=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIn(List<Integer> values) {
            addCriterion("eam_product_line.grm_period in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotIn(List<Integer> values) {
            addCriterion("eam_product_line.grm_period not in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.grm_period between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.grm_period not between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNull() {
            addCriterion("eam_product_line.collect_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNotNull() {
            addCriterion("eam_product_line.collect_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusEqualTo(String value) {
            addCriterion("eam_product_line.collect_status =", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotEqualTo(String value) {
            addCriterion("eam_product_line.collect_status <>", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThan(String value) {
            addCriterion("eam_product_line.collect_status >", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.collect_status >=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThan(String value) {
            addCriterion("eam_product_line.collect_status <", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.collect_status <=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLike(String value) {
            addCriterion("eam_product_line.collect_status like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotLike(String value) {
            addCriterion("eam_product_line.collect_status not like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIn(List<String> values) {
            addCriterion("eam_product_line.collect_status in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotIn(List<String> values) {
            addCriterion("eam_product_line.collect_status not in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusBetween(String value1, String value2) {
            addCriterion("eam_product_line.collect_status between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.collect_status not between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNull() {
            addCriterion("eam_product_line.is_online is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNotNull() {
            addCriterion("eam_product_line.is_online is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineEqualTo(Boolean value) {
            addCriterion("eam_product_line.is_online =", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotEqualTo(Boolean value) {
            addCriterion("eam_product_line.is_online <>", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThan(Boolean value) {
            addCriterion("eam_product_line.is_online >", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_product_line.is_online >=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThan(Boolean value) {
            addCriterion("eam_product_line.is_online <", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_product_line.is_online <=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIn(List<Boolean> values) {
            addCriterion("eam_product_line.is_online in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotIn(List<Boolean> values) {
            addCriterion("eam_product_line.is_online not in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_product_line.is_online between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_product_line.is_online not between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeIsNull() {
            addCriterion("eam_product_line.morning_shift_start_time is null");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeIsNotNull() {
            addCriterion("eam_product_line.morning_shift_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_start_time =", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_start_time <>", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeGreaterThan(String value) {
            addCriterion("eam_product_line.morning_shift_start_time >", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_start_time >=", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeLessThan(String value) {
            addCriterion("eam_product_line.morning_shift_start_time <", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_start_time <=", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeLike(String value) {
            addCriterion("eam_product_line.morning_shift_start_time like", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeNotLike(String value) {
            addCriterion("eam_product_line.morning_shift_start_time not like", value, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeIn(List<String> values) {
            addCriterion("eam_product_line.morning_shift_start_time in", values, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.morning_shift_start_time not in", values, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.morning_shift_start_time between", value1, value2, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftStartTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.morning_shift_start_time not between", value1, value2, "morningShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeIsNull() {
            addCriterion("eam_product_line.morning_shift_end_time is null");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeIsNotNull() {
            addCriterion("eam_product_line.morning_shift_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_end_time =", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_end_time <>", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeGreaterThan(String value) {
            addCriterion("eam_product_line.morning_shift_end_time >", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_end_time >=", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeLessThan(String value) {
            addCriterion("eam_product_line.morning_shift_end_time <", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.morning_shift_end_time <=", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeLike(String value) {
            addCriterion("eam_product_line.morning_shift_end_time like", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeNotLike(String value) {
            addCriterion("eam_product_line.morning_shift_end_time not like", value, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeIn(List<String> values) {
            addCriterion("eam_product_line.morning_shift_end_time in", values, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.morning_shift_end_time not in", values, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.morning_shift_end_time between", value1, value2, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningShiftEndTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.morning_shift_end_time not between", value1, value2, "morningShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeIsNull() {
            addCriterion("eam_product_line.morning_stop_time is null");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeIsNotNull() {
            addCriterion("eam_product_line.morning_stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time =", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeNotEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time <>", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeGreaterThan(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time >", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time >=", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeLessThan(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time <", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.morning_stop_time <=", value, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.morning_stop_time in", values, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeNotIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.morning_stop_time not in", values, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.morning_stop_time between", value1, value2, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMorningStopTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.morning_stop_time not between", value1, value2, "morningStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeIsNull() {
            addCriterion("eam_product_line.middle_shift_start_time is null");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeIsNotNull() {
            addCriterion("eam_product_line.middle_shift_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_start_time =", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_start_time <>", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeGreaterThan(String value) {
            addCriterion("eam_product_line.middle_shift_start_time >", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_start_time >=", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeLessThan(String value) {
            addCriterion("eam_product_line.middle_shift_start_time <", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_start_time <=", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeLike(String value) {
            addCriterion("eam_product_line.middle_shift_start_time like", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeNotLike(String value) {
            addCriterion("eam_product_line.middle_shift_start_time not like", value, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeIn(List<String> values) {
            addCriterion("eam_product_line.middle_shift_start_time in", values, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.middle_shift_start_time not in", values, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.middle_shift_start_time between", value1, value2, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftStartTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.middle_shift_start_time not between", value1, value2, "middleShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeIsNull() {
            addCriterion("eam_product_line.middle_shift_end_time is null");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeIsNotNull() {
            addCriterion("eam_product_line.middle_shift_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_end_time =", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_end_time <>", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeGreaterThan(String value) {
            addCriterion("eam_product_line.middle_shift_end_time >", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_end_time >=", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeLessThan(String value) {
            addCriterion("eam_product_line.middle_shift_end_time <", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.middle_shift_end_time <=", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeLike(String value) {
            addCriterion("eam_product_line.middle_shift_end_time like", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeNotLike(String value) {
            addCriterion("eam_product_line.middle_shift_end_time not like", value, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeIn(List<String> values) {
            addCriterion("eam_product_line.middle_shift_end_time in", values, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.middle_shift_end_time not in", values, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.middle_shift_end_time between", value1, value2, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleShiftEndTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.middle_shift_end_time not between", value1, value2, "middleShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeIsNull() {
            addCriterion("eam_product_line.middle_stop_time is null");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeIsNotNull() {
            addCriterion("eam_product_line.middle_stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time =", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeNotEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time <>", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeGreaterThan(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time >", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time >=", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeLessThan(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time <", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.middle_stop_time <=", value, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.middle_stop_time in", values, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeNotIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.middle_stop_time not in", values, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.middle_stop_time between", value1, value2, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andMiddleStopTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.middle_stop_time not between", value1, value2, "middleStopTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeIsNull() {
            addCriterion("eam_product_line.night_shift_start_time is null");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeIsNotNull() {
            addCriterion("eam_product_line.night_shift_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_start_time =", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_start_time <>", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeGreaterThan(String value) {
            addCriterion("eam_product_line.night_shift_start_time >", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_start_time >=", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeLessThan(String value) {
            addCriterion("eam_product_line.night_shift_start_time <", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_start_time <=", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeLike(String value) {
            addCriterion("eam_product_line.night_shift_start_time like", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeNotLike(String value) {
            addCriterion("eam_product_line.night_shift_start_time not like", value, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeIn(List<String> values) {
            addCriterion("eam_product_line.night_shift_start_time in", values, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.night_shift_start_time not in", values, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.night_shift_start_time between", value1, value2, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftStartTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.night_shift_start_time not between", value1, value2, "nightShiftStartTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeIsNull() {
            addCriterion("eam_product_line.night_shift_end_time is null");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeIsNotNull() {
            addCriterion("eam_product_line.night_shift_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_end_time =", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeNotEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_end_time <>", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeGreaterThan(String value) {
            addCriterion("eam_product_line.night_shift_end_time >", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_end_time >=", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeLessThan(String value) {
            addCriterion("eam_product_line.night_shift_end_time <", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeLessThanOrEqualTo(String value) {
            addCriterion("eam_product_line.night_shift_end_time <=", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeLike(String value) {
            addCriterion("eam_product_line.night_shift_end_time like", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeNotLike(String value) {
            addCriterion("eam_product_line.night_shift_end_time not like", value, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeIn(List<String> values) {
            addCriterion("eam_product_line.night_shift_end_time in", values, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeNotIn(List<String> values) {
            addCriterion("eam_product_line.night_shift_end_time not in", values, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeBetween(String value1, String value2) {
            addCriterion("eam_product_line.night_shift_end_time between", value1, value2, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightShiftEndTimeNotBetween(String value1, String value2) {
            addCriterion("eam_product_line.night_shift_end_time not between", value1, value2, "nightShiftEndTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeIsNull() {
            addCriterion("eam_product_line.night_stop_time is null");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeIsNotNull() {
            addCriterion("eam_product_line.night_stop_time is not null");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time =", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeNotEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time <>", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeGreaterThan(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time >", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time >=", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeLessThan(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time <", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_product_line.night_stop_time <=", value, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.night_stop_time in", values, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeNotIn(List<BigDecimal> values) {
            addCriterion("eam_product_line.night_stop_time not in", values, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.night_stop_time between", value1, value2, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andNightStopTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_product_line.night_stop_time not between", value1, value2, "nightStopTime");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdIsNull() {
            addCriterion("eam_product_line.actual_capacity_id is null");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdIsNotNull() {
            addCriterion("eam_product_line.actual_capacity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdEqualTo(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id =", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id <>", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id >", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id >=", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdLessThan(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id <", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.actual_capacity_id <=", value, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdIn(List<Integer> values) {
            addCriterion("eam_product_line.actual_capacity_id in", values, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.actual_capacity_id not in", values, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.actual_capacity_id between", value1, value2, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andActualCapacityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.actual_capacity_id not between", value1, value2, "actualCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdIsNull() {
            addCriterion("eam_product_line.base_capacity_id is null");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdIsNotNull() {
            addCriterion("eam_product_line.base_capacity_id is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdEqualTo(Integer value) {
            addCriterion("eam_product_line.base_capacity_id =", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.base_capacity_id <>", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.base_capacity_id >", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.base_capacity_id >=", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdLessThan(Integer value) {
            addCriterion("eam_product_line.base_capacity_id <", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.base_capacity_id <=", value, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdIn(List<Integer> values) {
            addCriterion("eam_product_line.base_capacity_id in", values, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.base_capacity_id not in", values, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.base_capacity_id between", value1, value2, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andBaseCapacityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.base_capacity_id not between", value1, value2, "baseCapacityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdIsNull() {
            addCriterion("eam_product_line.qualified_quantity_id is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdIsNotNull() {
            addCriterion("eam_product_line.qualified_quantity_id is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdEqualTo(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id =", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id <>", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id >", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id >=", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdLessThan(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id <", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.qualified_quantity_id <=", value, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdIn(List<Integer> values) {
            addCriterion("eam_product_line.qualified_quantity_id in", values, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.qualified_quantity_id not in", values, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.qualified_quantity_id between", value1, value2, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andQualifiedQuantityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.qualified_quantity_id not between", value1, value2, "qualifiedQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdIsNull() {
            addCriterion("eam_product_line.total_quantity_id is null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdIsNotNull() {
            addCriterion("eam_product_line.total_quantity_id is not null");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdEqualTo(Integer value) {
            addCriterion("eam_product_line.total_quantity_id =", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.total_quantity_id <>", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.total_quantity_id >", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.total_quantity_id >=", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdLessThan(Integer value) {
            addCriterion("eam_product_line.total_quantity_id <", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.total_quantity_id <=", value, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdIn(List<Integer> values) {
            addCriterion("eam_product_line.total_quantity_id in", values, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.total_quantity_id not in", values, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.total_quantity_id between", value1, value2, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andTotalQuantityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.total_quantity_id not between", value1, value2, "totalQuantityId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdIsNull() {
            addCriterion("eam_product_line.statistic_variable_id is null");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdIsNotNull() {
            addCriterion("eam_product_line.statistic_variable_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdEqualTo(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id =", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id <>", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id >", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id >=", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdLessThan(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id <", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.statistic_variable_id <=", value, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdIn(List<Integer> values) {
            addCriterion("eam_product_line.statistic_variable_id in", values, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.statistic_variable_id not in", values, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.statistic_variable_id between", value1, value2, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andStatisticVariableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.statistic_variable_id not between", value1, value2, "statisticVariableId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_product_line.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_product_line.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_product_line.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_product_line.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_product_line.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_product_line.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_product_line.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_product_line.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_product_line.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_product_line.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_product_line.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_product_line.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_product_line.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_product_line.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_product_line.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_product_line.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_product_line.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_product_line.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_product_line.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_product_line.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_product_line.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_product_line.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_product_line.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_product_line.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_product_line.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_product_line.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_product_line.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_product_line.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_product_line.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_product_line.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_product_line.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_product_line.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_product_line.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_product_line.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_product_line.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_product_line.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_product_line.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_product_line.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_product_line.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_product_line.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_product_line.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_product_line.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_product_line.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_product_line.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_product_line.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_product_line.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdIsNull() {
            addCriterion("eam_product_line.bottle_quantity_id is null");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdIsNotNull() {
            addCriterion("eam_product_line.bottle_quantity_id is not null");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdEqualTo(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id =", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id <>", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id >", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id >=", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdLessThan(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id <", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.bottle_quantity_id <=", value, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdIn(List<Integer> values) {
            addCriterion("eam_product_line.bottle_quantity_id in", values, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.bottle_quantity_id not in", values, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.bottle_quantity_id between", value1, value2, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andBottleQuantityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.bottle_quantity_id not between", value1, value2, "bottleQuantityId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdIsNull() {
            addCriterion("eam_product_line.start_produce_id is null");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdIsNotNull() {
            addCriterion("eam_product_line.start_produce_id is not null");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdEqualTo(Integer value) {
            addCriterion("eam_product_line.start_produce_id =", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.start_produce_id <>", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.start_produce_id >", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.start_produce_id >=", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdLessThan(Integer value) {
            addCriterion("eam_product_line.start_produce_id <", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.start_produce_id <=", value, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdIn(List<Integer> values) {
            addCriterion("eam_product_line.start_produce_id in", values, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.start_produce_id not in", values, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.start_produce_id between", value1, value2, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andStartProduceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.start_produce_id not between", value1, value2, "startProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdIsNull() {
            addCriterion("eam_product_line.end_produce_id is null");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdIsNotNull() {
            addCriterion("eam_product_line.end_produce_id is not null");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdEqualTo(Integer value) {
            addCriterion("eam_product_line.end_produce_id =", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdNotEqualTo(Integer value) {
            addCriterion("eam_product_line.end_produce_id <>", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdGreaterThan(Integer value) {
            addCriterion("eam_product_line.end_produce_id >", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.end_produce_id >=", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdLessThan(Integer value) {
            addCriterion("eam_product_line.end_produce_id <", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_product_line.end_produce_id <=", value, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdIn(List<Integer> values) {
            addCriterion("eam_product_line.end_produce_id in", values, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdNotIn(List<Integer> values) {
            addCriterion("eam_product_line.end_produce_id not in", values, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.end_produce_id between", value1, value2, "endProduceId");
            return (Criteria) this;
        }

        public Criteria andEndProduceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_product_line.end_produce_id not between", value1, value2, "endProduceId");
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