package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamEquipmentExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamEquipmentExample() {
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

        public Criteria andEquipmentCategoryIdIsNull() {
            addCriterion("equipment_category_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNotNull() {
            addCriterion("equipment_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdEqualTo(Integer value) {
            addCriterion("equipment_category_id =", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotEqualTo(Integer value) {
            addCriterion("equipment_category_id <>", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThan(Integer value) {
            addCriterion("equipment_category_id >", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("equipment_category_id >=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThan(Integer value) {
            addCriterion("equipment_category_id <", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("equipment_category_id <=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIn(List<Integer> values) {
            addCriterion("equipment_category_id in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotIn(List<Integer> values) {
            addCriterion("equipment_category_id not in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("equipment_category_id between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("equipment_category_id not between", value1, value2, "equipmentCategoryId");
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("serial_number like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("serial_number not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNull() {
            addCriterion("image_path is null");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNotNull() {
            addCriterion("image_path is not null");
            return (Criteria) this;
        }

        public Criteria andImagePathEqualTo(String value) {
            addCriterion("image_path =", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotEqualTo(String value) {
            addCriterion("image_path <>", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThan(String value) {
            addCriterion("image_path >", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("image_path >=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThan(String value) {
            addCriterion("image_path <", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThanOrEqualTo(String value) {
            addCriterion("image_path <=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLike(String value) {
            addCriterion("image_path like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotLike(String value) {
            addCriterion("image_path not like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathIn(List<String> values) {
            addCriterion("image_path in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotIn(List<String> values) {
            addCriterion("image_path not in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathBetween(String value1, String value2) {
            addCriterion("image_path between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotBetween(String value1, String value2) {
            addCriterion("image_path not between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andOutputIsNull() {
            addCriterion("output is null");
            return (Criteria) this;
        }

        public Criteria andOutputIsNotNull() {
            addCriterion("output is not null");
            return (Criteria) this;
        }

        public Criteria andOutputEqualTo(String value) {
            addCriterion("output =", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputNotEqualTo(String value) {
            addCriterion("output <>", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputGreaterThan(String value) {
            addCriterion("output >", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputGreaterThanOrEqualTo(String value) {
            addCriterion("output >=", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputLessThan(String value) {
            addCriterion("output <", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputLessThanOrEqualTo(String value) {
            addCriterion("output <=", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputLike(String value) {
            addCriterion("output like", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputNotLike(String value) {
            addCriterion("output not like", value, "output");
            return (Criteria) this;
        }

        public Criteria andOutputIn(List<String> values) {
            addCriterion("output in", values, "output");
            return (Criteria) this;
        }

        public Criteria andOutputNotIn(List<String> values) {
            addCriterion("output not in", values, "output");
            return (Criteria) this;
        }

        public Criteria andOutputBetween(String value1, String value2) {
            addCriterion("output between", value1, value2, "output");
            return (Criteria) this;
        }

        public Criteria andOutputNotBetween(String value1, String value2) {
            addCriterion("output not between", value1, value2, "output");
            return (Criteria) this;
        }

        public Criteria andDimensionIsNull() {
            addCriterion("dimension is null");
            return (Criteria) this;
        }

        public Criteria andDimensionIsNotNull() {
            addCriterion("dimension is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionEqualTo(String value) {
            addCriterion("dimension =", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotEqualTo(String value) {
            addCriterion("dimension <>", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionGreaterThan(String value) {
            addCriterion("dimension >", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionGreaterThanOrEqualTo(String value) {
            addCriterion("dimension >=", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionLessThan(String value) {
            addCriterion("dimension <", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionLessThanOrEqualTo(String value) {
            addCriterion("dimension <=", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionLike(String value) {
            addCriterion("dimension like", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotLike(String value) {
            addCriterion("dimension not like", value, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionIn(List<String> values) {
            addCriterion("dimension in", values, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotIn(List<String> values) {
            addCriterion("dimension not in", values, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionBetween(String value1, String value2) {
            addCriterion("dimension between", value1, value2, "dimension");
            return (Criteria) this;
        }

        public Criteria andDimensionNotBetween(String value1, String value2) {
            addCriterion("dimension not between", value1, value2, "dimension");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(String value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(String value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(String value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(String value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(String value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(String value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLike(String value) {
            addCriterion("power like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotLike(String value) {
            addCriterion("power not like", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<String> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<String> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(String value1, String value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(String value1, String value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andCapacityIsNull() {
            addCriterion("capacity is null");
            return (Criteria) this;
        }

        public Criteria andCapacityIsNotNull() {
            addCriterion("capacity is not null");
            return (Criteria) this;
        }

        public Criteria andCapacityEqualTo(String value) {
            addCriterion("capacity =", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityNotEqualTo(String value) {
            addCriterion("capacity <>", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityGreaterThan(String value) {
            addCriterion("capacity >", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityGreaterThanOrEqualTo(String value) {
            addCriterion("capacity >=", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityLessThan(String value) {
            addCriterion("capacity <", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityLessThanOrEqualTo(String value) {
            addCriterion("capacity <=", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityLike(String value) {
            addCriterion("capacity like", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityNotLike(String value) {
            addCriterion("capacity not like", value, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityIn(List<String> values) {
            addCriterion("capacity in", values, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityNotIn(List<String> values) {
            addCriterion("capacity not in", values, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityBetween(String value1, String value2) {
            addCriterion("capacity between", value1, value2, "capacity");
            return (Criteria) this;
        }

        public Criteria andCapacityNotBetween(String value1, String value2) {
            addCriterion("capacity not between", value1, value2, "capacity");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("user like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("user not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("user not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNull() {
            addCriterion("collect_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNotNull() {
            addCriterion("collect_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusEqualTo(String value) {
            addCriterion("collect_status =", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotEqualTo(String value) {
            addCriterion("collect_status <>", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThan(String value) {
            addCriterion("collect_status >", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("collect_status >=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThan(String value) {
            addCriterion("collect_status <", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThanOrEqualTo(String value) {
            addCriterion("collect_status <=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLike(String value) {
            addCriterion("collect_status like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotLike(String value) {
            addCriterion("collect_status not like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIn(List<String> values) {
            addCriterion("collect_status in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotIn(List<String> values) {
            addCriterion("collect_status not in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusBetween(String value1, String value2) {
            addCriterion("collect_status between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotBetween(String value1, String value2) {
            addCriterion("collect_status not between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andGrmIsNull() {
            addCriterion("grm is null");
            return (Criteria) this;
        }

        public Criteria andGrmIsNotNull() {
            addCriterion("grm is not null");
            return (Criteria) this;
        }

        public Criteria andGrmEqualTo(String value) {
            addCriterion("grm =", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotEqualTo(String value) {
            addCriterion("grm <>", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmGreaterThan(String value) {
            addCriterion("grm >", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmGreaterThanOrEqualTo(String value) {
            addCriterion("grm >=", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLessThan(String value) {
            addCriterion("grm <", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLessThanOrEqualTo(String value) {
            addCriterion("grm <=", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmLike(String value) {
            addCriterion("grm like", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotLike(String value) {
            addCriterion("grm not like", value, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmIn(List<String> values) {
            addCriterion("grm in", values, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotIn(List<String> values) {
            addCriterion("grm not in", values, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmBetween(String value1, String value2) {
            addCriterion("grm between", value1, value2, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmNotBetween(String value1, String value2) {
            addCriterion("grm not between", value1, value2, "grm");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIsNull() {
            addCriterion("grm_password is null");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIsNotNull() {
            addCriterion("grm_password is not null");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordEqualTo(String value) {
            addCriterion("grm_password =", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotEqualTo(String value) {
            addCriterion("grm_password <>", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordGreaterThan(String value) {
            addCriterion("grm_password >", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("grm_password >=", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLessThan(String value) {
            addCriterion("grm_password <", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLessThanOrEqualTo(String value) {
            addCriterion("grm_password <=", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordLike(String value) {
            addCriterion("grm_password like", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotLike(String value) {
            addCriterion("grm_password not like", value, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordIn(List<String> values) {
            addCriterion("grm_password in", values, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotIn(List<String> values) {
            addCriterion("grm_password not in", values, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordBetween(String value1, String value2) {
            addCriterion("grm_password between", value1, value2, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPasswordNotBetween(String value1, String value2) {
            addCriterion("grm_password not between", value1, value2, "grmPassword");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNull() {
            addCriterion("grm_period is null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNotNull() {
            addCriterion("grm_period is not null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodEqualTo(Integer value) {
            addCriterion("grm_period =", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotEqualTo(Integer value) {
            addCriterion("grm_period <>", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThan(Integer value) {
            addCriterion("grm_period >", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("grm_period >=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThan(Integer value) {
            addCriterion("grm_period <", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("grm_period <=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIn(List<Integer> values) {
            addCriterion("grm_period in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotIn(List<Integer> values) {
            addCriterion("grm_period not in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodBetween(Integer value1, Integer value2) {
            addCriterion("grm_period between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("grm_period not between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIsNull() {
            addCriterion("factory_date is null");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIsNotNull() {
            addCriterion("factory_date is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryDateEqualTo(Date value) {
            addCriterion("factory_date =", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotEqualTo(Date value) {
            addCriterion("factory_date <>", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateGreaterThan(Date value) {
            addCriterion("factory_date >", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("factory_date >=", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateLessThan(Date value) {
            addCriterion("factory_date <", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateLessThanOrEqualTo(Date value) {
            addCriterion("factory_date <=", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIn(List<Date> values) {
            addCriterion("factory_date in", values, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotIn(List<Date> values) {
            addCriterion("factory_date not in", values, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateBetween(Date value1, Date value2) {
            addCriterion("factory_date between", value1, value2, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotBetween(Date value1, Date value2) {
            addCriterion("factory_date not between", value1, value2, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIsNull() {
            addCriterion("commissioning_date is null");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIsNotNull() {
            addCriterion("commissioning_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateEqualTo(Date value) {
            addCriterion("commissioning_date =", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotEqualTo(Date value) {
            addCriterion("commissioning_date <>", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateGreaterThan(Date value) {
            addCriterion("commissioning_date >", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateGreaterThanOrEqualTo(Date value) {
            addCriterion("commissioning_date >=", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateLessThan(Date value) {
            addCriterion("commissioning_date <", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateLessThanOrEqualTo(Date value) {
            addCriterion("commissioning_date <=", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIn(List<Date> values) {
            addCriterion("commissioning_date in", values, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotIn(List<Date> values) {
            addCriterion("commissioning_date not in", values, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateBetween(Date value1, Date value2) {
            addCriterion("commissioning_date between", value1, value2, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotBetween(Date value1, Date value2) {
            addCriterion("commissioning_date not between", value1, value2, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIsNull() {
            addCriterion("warranty_start_date is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIsNotNull() {
            addCriterion("warranty_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateEqualTo(Date value) {
            addCriterion("warranty_start_date =", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotEqualTo(Date value) {
            addCriterion("warranty_start_date <>", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateGreaterThan(Date value) {
            addCriterion("warranty_start_date >", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("warranty_start_date >=", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateLessThan(Date value) {
            addCriterion("warranty_start_date <", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateLessThanOrEqualTo(Date value) {
            addCriterion("warranty_start_date <=", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIn(List<Date> values) {
            addCriterion("warranty_start_date in", values, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotIn(List<Date> values) {
            addCriterion("warranty_start_date not in", values, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateBetween(Date value1, Date value2) {
            addCriterion("warranty_start_date between", value1, value2, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotBetween(Date value1, Date value2) {
            addCriterion("warranty_start_date not between", value1, value2, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIsNull() {
            addCriterion("warranty_end_date is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIsNotNull() {
            addCriterion("warranty_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateEqualTo(Date value) {
            addCriterion("warranty_end_date =", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotEqualTo(Date value) {
            addCriterion("warranty_end_date <>", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateGreaterThan(Date value) {
            addCriterion("warranty_end_date >", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("warranty_end_date >=", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateLessThan(Date value) {
            addCriterion("warranty_end_date <", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateLessThanOrEqualTo(Date value) {
            addCriterion("warranty_end_date <=", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIn(List<Date> values) {
            addCriterion("warranty_end_date in", values, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotIn(List<Date> values) {
            addCriterion("warranty_end_date not in", values, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateBetween(Date value1, Date value2) {
            addCriterion("warranty_end_date between", value1, value2, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotBetween(Date value1, Date value2) {
            addCriterion("warranty_end_date not between", value1, value2, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIsNull() {
            addCriterion("maintenance_period is null");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIsNotNull() {
            addCriterion("maintenance_period is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodEqualTo(String value) {
            addCriterion("maintenance_period =", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotEqualTo(String value) {
            addCriterion("maintenance_period <>", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodGreaterThan(String value) {
            addCriterion("maintenance_period >", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("maintenance_period >=", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLessThan(String value) {
            addCriterion("maintenance_period <", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLessThanOrEqualTo(String value) {
            addCriterion("maintenance_period <=", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLike(String value) {
            addCriterion("maintenance_period like", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotLike(String value) {
            addCriterion("maintenance_period not like", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIn(List<String> values) {
            addCriterion("maintenance_period in", values, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotIn(List<String> values) {
            addCriterion("maintenance_period not in", values, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodBetween(String value1, String value2) {
            addCriterion("maintenance_period between", value1, value2, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotBetween(String value1, String value2) {
            addCriterion("maintenance_period not between", value1, value2, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPositionIsNull() {
            addCriterion("left_position is null");
            return (Criteria) this;
        }

        public Criteria andLeftPositionIsNotNull() {
            addCriterion("left_position is not null");
            return (Criteria) this;
        }

        public Criteria andLeftPositionEqualTo(BigDecimal value) {
            addCriterion("left_position =", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionNotEqualTo(BigDecimal value) {
            addCriterion("left_position <>", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionGreaterThan(BigDecimal value) {
            addCriterion("left_position >", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("left_position >=", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionLessThan(BigDecimal value) {
            addCriterion("left_position <", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("left_position <=", value, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionIn(List<BigDecimal> values) {
            addCriterion("left_position in", values, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionNotIn(List<BigDecimal> values) {
            addCriterion("left_position not in", values, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("left_position between", value1, value2, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andLeftPositionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("left_position not between", value1, value2, "leftPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionIsNull() {
            addCriterion("top_position is null");
            return (Criteria) this;
        }

        public Criteria andTopPositionIsNotNull() {
            addCriterion("top_position is not null");
            return (Criteria) this;
        }

        public Criteria andTopPositionEqualTo(BigDecimal value) {
            addCriterion("top_position =", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionNotEqualTo(BigDecimal value) {
            addCriterion("top_position <>", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionGreaterThan(BigDecimal value) {
            addCriterion("top_position >", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("top_position >=", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionLessThan(BigDecimal value) {
            addCriterion("top_position <", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("top_position <=", value, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionIn(List<BigDecimal> values) {
            addCriterion("top_position in", values, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionNotIn(List<BigDecimal> values) {
            addCriterion("top_position not in", values, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_position between", value1, value2, "topPosition");
            return (Criteria) this;
        }

        public Criteria andTopPositionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("top_position not between", value1, value2, "topPosition");
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

        public Criteria andIsOnlineIsNull() {
            addCriterion("is_online is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNotNull() {
            addCriterion("is_online is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineEqualTo(Boolean value) {
            addCriterion("is_online =", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotEqualTo(Boolean value) {
            addCriterion("is_online <>", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThan(Boolean value) {
            addCriterion("is_online >", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_online >=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThan(Boolean value) {
            addCriterion("is_online <", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThanOrEqualTo(Boolean value) {
            addCriterion("is_online <=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIn(List<Boolean> values) {
            addCriterion("is_online in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotIn(List<Boolean> values) {
            addCriterion("is_online not in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineBetween(Boolean value1, Boolean value2) {
            addCriterion("is_online between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_online not between", value1, value2, "isOnline");
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