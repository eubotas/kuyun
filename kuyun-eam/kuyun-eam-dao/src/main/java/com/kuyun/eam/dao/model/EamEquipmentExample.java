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
            addCriterion("eam_equipment.equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("eam_equipment.equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(String value) {
            addCriterion("eam_equipment.equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(String value) {
            addCriterion("eam_equipment.equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(String value) {
            addCriterion("eam_equipment.equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(String value) {
            addCriterion("eam_equipment.equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLike(String value) {
            addCriterion("eam_equipment.equipment_id like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotLike(String value) {
            addCriterion("eam_equipment.equipment_id not like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<String> values) {
            addCriterion("eam_equipment.equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<String> values) {
            addCriterion("eam_equipment.equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(String value1, String value2) {
            addCriterion("eam_equipment.equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdIsNull() {
            addCriterion("eam_equipment.equipment_model_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdIsNotNull() {
            addCriterion("eam_equipment.equipment_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_model_id =", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdNotEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_model_id <>", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdGreaterThan(Integer value) {
            addCriterion("eam_equipment.equipment_model_id >", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_model_id >=", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdLessThan(Integer value) {
            addCriterion("eam_equipment.equipment_model_id <", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_model_id <=", value, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdIn(List<Integer> values) {
            addCriterion("eam_equipment.equipment_model_id in", values, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdNotIn(List<Integer> values) {
            addCriterion("eam_equipment.equipment_model_id not in", values, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.equipment_model_id between", value1, value2, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.equipment_model_id not between", value1, value2, "equipmentModelId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNull() {
            addCriterion("eam_equipment.equipment_category_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIsNotNull() {
            addCriterion("eam_equipment.equipment_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_category_id =", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_category_id <>", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThan(Integer value) {
            addCriterion("eam_equipment.equipment_category_id >", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_category_id >=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThan(Integer value) {
            addCriterion("eam_equipment.equipment_category_id <", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.equipment_category_id <=", value, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdIn(List<Integer> values) {
            addCriterion("eam_equipment.equipment_category_id in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotIn(List<Integer> values) {
            addCriterion("eam_equipment.equipment_category_id not in", values, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.equipment_category_id between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andEquipmentCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.equipment_category_id not between", value1, value2, "equipmentCategoryId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("eam_equipment.name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("eam_equipment.name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("eam_equipment.name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("eam_equipment.name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("eam_equipment.name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("eam_equipment.name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("eam_equipment.name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("eam_equipment.name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("eam_equipment.name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("eam_equipment.name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("eam_equipment.name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("eam_equipment.number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("eam_equipment.number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("eam_equipment.number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("eam_equipment.number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("eam_equipment.number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("eam_equipment.number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("eam_equipment.number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("eam_equipment.number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("eam_equipment.number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("eam_equipment.number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("eam_equipment.number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("eam_equipment.serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("eam_equipment.serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(String value) {
            addCriterion("eam_equipment.serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(String value) {
            addCriterion("eam_equipment.serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(String value) {
            addCriterion("eam_equipment.serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(String value) {
            addCriterion("eam_equipment.serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLike(String value) {
            addCriterion("eam_equipment.serial_number like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotLike(String value) {
            addCriterion("eam_equipment.serial_number not like", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<String> values) {
            addCriterion("eam_equipment.serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<String> values) {
            addCriterion("eam_equipment.serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(String value1, String value2) {
            addCriterion("eam_equipment.serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNull() {
            addCriterion("eam_equipment.image_path is null");
            return (Criteria) this;
        }

        public Criteria andImagePathIsNotNull() {
            addCriterion("eam_equipment.image_path is not null");
            return (Criteria) this;
        }

        public Criteria andImagePathEqualTo(String value) {
            addCriterion("eam_equipment.image_path =", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotEqualTo(String value) {
            addCriterion("eam_equipment.image_path <>", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThan(String value) {
            addCriterion("eam_equipment.image_path >", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.image_path >=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThan(String value) {
            addCriterion("eam_equipment.image_path <", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.image_path <=", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathLike(String value) {
            addCriterion("eam_equipment.image_path like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotLike(String value) {
            addCriterion("eam_equipment.image_path not like", value, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathIn(List<String> values) {
            addCriterion("eam_equipment.image_path in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotIn(List<String> values) {
            addCriterion("eam_equipment.image_path not in", values, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathBetween(String value1, String value2) {
            addCriterion("eam_equipment.image_path between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andImagePathNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.image_path not between", value1, value2, "imagePath");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("eam_equipment.longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("eam_equipment.longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(BigDecimal value) {
            addCriterion("eam_equipment.longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(BigDecimal value) {
            addCriterion("eam_equipment.longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<BigDecimal> values) {
            addCriterion("eam_equipment.longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<BigDecimal> values) {
            addCriterion("eam_equipment.longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_equipment.longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_equipment.longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("eam_equipment.latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("eam_equipment.latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(BigDecimal value) {
            addCriterion("eam_equipment.latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(BigDecimal value) {
            addCriterion("eam_equipment.latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eam_equipment.latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<BigDecimal> values) {
            addCriterion("eam_equipment.latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<BigDecimal> values) {
            addCriterion("eam_equipment.latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_equipment.latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eam_equipment.latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("eam_equipment.province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("eam_equipment.province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("eam_equipment.province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("eam_equipment.province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("eam_equipment.province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("eam_equipment.province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("eam_equipment.province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("eam_equipment.province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("eam_equipment.province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("eam_equipment.province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("eam_equipment.province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("eam_equipment.city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("eam_equipment.city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("eam_equipment.city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("eam_equipment.city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("eam_equipment.city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("eam_equipment.city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("eam_equipment.city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("eam_equipment.city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("eam_equipment.city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("eam_equipment.city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("eam_equipment.city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("eam_equipment.user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("eam_equipment.user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(String value) {
            addCriterion("eam_equipment.user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(String value) {
            addCriterion("eam_equipment.user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(String value) {
            addCriterion("eam_equipment.user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(String value) {
            addCriterion("eam_equipment.user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLike(String value) {
            addCriterion("eam_equipment.user like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotLike(String value) {
            addCriterion("eam_equipment.user not like", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<String> values) {
            addCriterion("eam_equipment.user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<String> values) {
            addCriterion("eam_equipment.user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(String value1, String value2) {
            addCriterion("eam_equipment.user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.user not between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNull() {
            addCriterion("eam_equipment.collect_status is null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIsNotNull() {
            addCriterion("eam_equipment.collect_status is not null");
            return (Criteria) this;
        }

        public Criteria andCollectStatusEqualTo(String value) {
            addCriterion("eam_equipment.collect_status =", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotEqualTo(String value) {
            addCriterion("eam_equipment.collect_status <>", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThan(String value) {
            addCriterion("eam_equipment.collect_status >", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.collect_status >=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThan(String value) {
            addCriterion("eam_equipment.collect_status <", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.collect_status <=", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusLike(String value) {
            addCriterion("eam_equipment.collect_status like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotLike(String value) {
            addCriterion("eam_equipment.collect_status not like", value, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusIn(List<String> values) {
            addCriterion("eam_equipment.collect_status in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotIn(List<String> values) {
            addCriterion("eam_equipment.collect_status not in", values, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusBetween(String value1, String value2) {
            addCriterion("eam_equipment.collect_status between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andCollectStatusNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.collect_status not between", value1, value2, "collectStatus");
            return (Criteria) this;
        }

        public Criteria andSalveIdIsNull() {
            addCriterion("eam_equipment.salve_id is null");
            return (Criteria) this;
        }

        public Criteria andSalveIdIsNotNull() {
            addCriterion("eam_equipment.salve_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalveIdEqualTo(Integer value) {
            addCriterion("eam_equipment.salve_id =", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotEqualTo(Integer value) {
            addCriterion("eam_equipment.salve_id <>", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdGreaterThan(Integer value) {
            addCriterion("eam_equipment.salve_id >", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.salve_id >=", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdLessThan(Integer value) {
            addCriterion("eam_equipment.salve_id <", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.salve_id <=", value, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdIn(List<Integer> values) {
            addCriterion("eam_equipment.salve_id in", values, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotIn(List<Integer> values) {
            addCriterion("eam_equipment.salve_id not in", values, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.salve_id between", value1, value2, "salveId");
            return (Criteria) this;
        }

        public Criteria andSalveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.salve_id not between", value1, value2, "salveId");
            return (Criteria) this;
        }

        public Criteria andHeartDataIsNull() {
            addCriterion("eam_equipment.heart_data is null");
            return (Criteria) this;
        }

        public Criteria andHeartDataIsNotNull() {
            addCriterion("eam_equipment.heart_data is not null");
            return (Criteria) this;
        }

        public Criteria andHeartDataEqualTo(String value) {
            addCriterion("eam_equipment.heart_data =", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataNotEqualTo(String value) {
            addCriterion("eam_equipment.heart_data <>", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataGreaterThan(String value) {
            addCriterion("eam_equipment.heart_data >", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.heart_data >=", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataLessThan(String value) {
            addCriterion("eam_equipment.heart_data <", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.heart_data <=", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataLike(String value) {
            addCriterion("eam_equipment.heart_data like", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataNotLike(String value) {
            addCriterion("eam_equipment.heart_data not like", value, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataIn(List<String> values) {
            addCriterion("eam_equipment.heart_data in", values, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataNotIn(List<String> values) {
            addCriterion("eam_equipment.heart_data not in", values, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataBetween(String value1, String value2) {
            addCriterion("eam_equipment.heart_data between", value1, value2, "heartData");
            return (Criteria) this;
        }

        public Criteria andHeartDataNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.heart_data not between", value1, value2, "heartData");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodIsNull() {
            addCriterion("eam_equipment.modbus_rtu_period is null");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodIsNotNull() {
            addCriterion("eam_equipment.modbus_rtu_period is not null");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodEqualTo(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period =", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodNotEqualTo(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period <>", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodGreaterThan(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period >", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period >=", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodLessThan(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period <", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.modbus_rtu_period <=", value, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodIn(List<Integer> values) {
            addCriterion("eam_equipment.modbus_rtu_period in", values, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodNotIn(List<Integer> values) {
            addCriterion("eam_equipment.modbus_rtu_period not in", values, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.modbus_rtu_period between", value1, value2, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andModbusRtuPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.modbus_rtu_period not between", value1, value2, "modbusRtuPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNull() {
            addCriterion("eam_equipment.grm_period is null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIsNotNull() {
            addCriterion("eam_equipment.grm_period is not null");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodEqualTo(Integer value) {
            addCriterion("eam_equipment.grm_period =", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotEqualTo(Integer value) {
            addCriterion("eam_equipment.grm_period <>", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThan(Integer value) {
            addCriterion("eam_equipment.grm_period >", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.grm_period >=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThan(Integer value) {
            addCriterion("eam_equipment.grm_period <", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.grm_period <=", value, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodIn(List<Integer> values) {
            addCriterion("eam_equipment.grm_period in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotIn(List<Integer> values) {
            addCriterion("eam_equipment.grm_period not in", values, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.grm_period between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andGrmPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.grm_period not between", value1, value2, "grmPeriod");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIsNull() {
            addCriterion("eam_equipment.factory_date is null");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIsNotNull() {
            addCriterion("eam_equipment.factory_date is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryDateEqualTo(Date value) {
            addCriterion("eam_equipment.factory_date =", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotEqualTo(Date value) {
            addCriterion("eam_equipment.factory_date <>", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateGreaterThan(Date value) {
            addCriterion("eam_equipment.factory_date >", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.factory_date >=", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateLessThan(Date value) {
            addCriterion("eam_equipment.factory_date <", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.factory_date <=", value, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateIn(List<Date> values) {
            addCriterion("eam_equipment.factory_date in", values, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotIn(List<Date> values) {
            addCriterion("eam_equipment.factory_date not in", values, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.factory_date between", value1, value2, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andFactoryDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.factory_date not between", value1, value2, "factoryDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIsNull() {
            addCriterion("eam_equipment.commissioning_date is null");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIsNotNull() {
            addCriterion("eam_equipment.commissioning_date is not null");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateEqualTo(Date value) {
            addCriterion("eam_equipment.commissioning_date =", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotEqualTo(Date value) {
            addCriterion("eam_equipment.commissioning_date <>", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateGreaterThan(Date value) {
            addCriterion("eam_equipment.commissioning_date >", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.commissioning_date >=", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateLessThan(Date value) {
            addCriterion("eam_equipment.commissioning_date <", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.commissioning_date <=", value, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateIn(List<Date> values) {
            addCriterion("eam_equipment.commissioning_date in", values, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotIn(List<Date> values) {
            addCriterion("eam_equipment.commissioning_date not in", values, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.commissioning_date between", value1, value2, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andCommissioningDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.commissioning_date not between", value1, value2, "commissioningDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIsNull() {
            addCriterion("eam_equipment.warranty_start_date is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIsNotNull() {
            addCriterion("eam_equipment.warranty_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_start_date =", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_start_date <>", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateGreaterThan(Date value) {
            addCriterion("eam_equipment.warranty_start_date >", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_start_date >=", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateLessThan(Date value) {
            addCriterion("eam_equipment.warranty_start_date <", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_start_date <=", value, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateIn(List<Date> values) {
            addCriterion("eam_equipment.warranty_start_date in", values, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotIn(List<Date> values) {
            addCriterion("eam_equipment.warranty_start_date not in", values, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.warranty_start_date between", value1, value2, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyStartDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.warranty_start_date not between", value1, value2, "warrantyStartDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIsNull() {
            addCriterion("eam_equipment.warranty_end_date is null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIsNotNull() {
            addCriterion("eam_equipment.warranty_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_end_date =", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_end_date <>", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateGreaterThan(Date value) {
            addCriterion("eam_equipment.warranty_end_date >", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_end_date >=", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateLessThan(Date value) {
            addCriterion("eam_equipment.warranty_end_date <", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.warranty_end_date <=", value, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateIn(List<Date> values) {
            addCriterion("eam_equipment.warranty_end_date in", values, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotIn(List<Date> values) {
            addCriterion("eam_equipment.warranty_end_date not in", values, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.warranty_end_date between", value1, value2, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andWarrantyEndDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.warranty_end_date not between", value1, value2, "warrantyEndDate");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIsNull() {
            addCriterion("eam_equipment.maintenance_period is null");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIsNotNull() {
            addCriterion("eam_equipment.maintenance_period is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodEqualTo(String value) {
            addCriterion("eam_equipment.maintenance_period =", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotEqualTo(String value) {
            addCriterion("eam_equipment.maintenance_period <>", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodGreaterThan(String value) {
            addCriterion("eam_equipment.maintenance_period >", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodGreaterThanOrEqualTo(String value) {
            addCriterion("eam_equipment.maintenance_period >=", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLessThan(String value) {
            addCriterion("eam_equipment.maintenance_period <", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLessThanOrEqualTo(String value) {
            addCriterion("eam_equipment.maintenance_period <=", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodLike(String value) {
            addCriterion("eam_equipment.maintenance_period like", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotLike(String value) {
            addCriterion("eam_equipment.maintenance_period not like", value, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodIn(List<String> values) {
            addCriterion("eam_equipment.maintenance_period in", values, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotIn(List<String> values) {
            addCriterion("eam_equipment.maintenance_period not in", values, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodBetween(String value1, String value2) {
            addCriterion("eam_equipment.maintenance_period between", value1, value2, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andMaintenancePeriodNotBetween(String value1, String value2) {
            addCriterion("eam_equipment.maintenance_period not between", value1, value2, "maintenancePeriod");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_equipment.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_equipment.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_equipment.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_equipment.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_equipment.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_equipment.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_equipment.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_equipment.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_equipment.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_equipment.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_equipment.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_equipment.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_equipment.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_equipment.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_equipment.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_equipment.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_equipment.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_equipment.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_equipment.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_equipment.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_equipment.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_equipment.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_equipment.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_equipment.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_equipment.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_equipment.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_equipment.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_equipment.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_equipment.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_equipment.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_equipment.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_equipment.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_equipment.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_equipment.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_equipment.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_equipment.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_equipment.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_equipment.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_equipment.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_equipment.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_equipment.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_equipment.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_equipment.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_equipment.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_equipment.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_equipment.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_equipment.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_equipment.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNull() {
            addCriterion("eam_equipment.is_online is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIsNotNull() {
            addCriterion("eam_equipment.is_online is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlineEqualTo(Boolean value) {
            addCriterion("eam_equipment.is_online =", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotEqualTo(Boolean value) {
            addCriterion("eam_equipment.is_online <>", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThan(Boolean value) {
            addCriterion("eam_equipment.is_online >", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_equipment.is_online >=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThan(Boolean value) {
            addCriterion("eam_equipment.is_online <", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_equipment.is_online <=", value, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineIn(List<Boolean> values) {
            addCriterion("eam_equipment.is_online in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotIn(List<Boolean> values) {
            addCriterion("eam_equipment.is_online not in", values, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_equipment.is_online between", value1, value2, "isOnline");
            return (Criteria) this;
        }

        public Criteria andIsOnlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_equipment.is_online not between", value1, value2, "isOnline");
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