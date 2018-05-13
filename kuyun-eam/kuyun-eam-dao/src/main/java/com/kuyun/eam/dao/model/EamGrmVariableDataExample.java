package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamGrmVariableDataExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamGrmVariableDataExample() {
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
            addCriterion("eam_grm_variable_data.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("eam_grm_variable_data.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNull() {
            addCriterion("eam_grm_variable_data.equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("eam_grm_variable_data.equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(String value) {
            addCriterion("eam_grm_variable_data.equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(String value) {
            addCriterion("eam_grm_variable_data.equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(String value) {
            addCriterion("eam_grm_variable_data.equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(String value) {
            addCriterion("eam_grm_variable_data.equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLike(String value) {
            addCriterion("eam_grm_variable_data.equipment_id like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotLike(String value) {
            addCriterion("eam_grm_variable_data.equipment_id not like", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<String> values) {
            addCriterion("eam_grm_variable_data.equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<String> values) {
            addCriterion("eam_grm_variable_data.equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIsNull() {
            addCriterion("eam_grm_variable_data.product_line_id is null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIsNotNull() {
            addCriterion("eam_grm_variable_data.product_line_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductLineIdEqualTo(String value) {
            addCriterion("eam_grm_variable_data.product_line_id =", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotEqualTo(String value) {
            addCriterion("eam_grm_variable_data.product_line_id <>", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThan(String value) {
            addCriterion("eam_grm_variable_data.product_line_id >", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdGreaterThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.product_line_id >=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThan(String value) {
            addCriterion("eam_grm_variable_data.product_line_id <", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLessThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.product_line_id <=", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdLike(String value) {
            addCriterion("eam_grm_variable_data.product_line_id like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotLike(String value) {
            addCriterion("eam_grm_variable_data.product_line_id not like", value, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdIn(List<String> values) {
            addCriterion("eam_grm_variable_data.product_line_id in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotIn(List<String> values) {
            addCriterion("eam_grm_variable_data.product_line_id not in", values, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.product_line_id between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andProductLineIdNotBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.product_line_id not between", value1, value2, "productLineId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdIsNull() {
            addCriterion("eam_grm_variable_data.data_group_id is null");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdIsNotNull() {
            addCriterion("eam_grm_variable_data.data_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id =", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id <>", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id >", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id >=", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id <", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_group_id <=", value, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.data_group_id in", values, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.data_group_id not in", values, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.data_group_id between", value1, value2, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.data_group_id not between", value1, value2, "dataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdIsNull() {
            addCriterion("eam_grm_variable_data.equipment_data_group_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdIsNotNull() {
            addCriterion("eam_grm_variable_data.equipment_data_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id =", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id <>", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id >", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id >=", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id <", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id <=", value, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id in", values, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id not in", values, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id between", value1, value2, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andEquipmentDataGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.equipment_data_group_id not between", value1, value2, "equipmentDataGroupId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdIsNull() {
            addCriterion("eam_grm_variable_data.data_element_id is null");
            return (Criteria) this;
        }

        public Criteria andDataElementIdIsNotNull() {
            addCriterion("eam_grm_variable_data.data_element_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataElementIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id =", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id <>", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id >", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id >=", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id <", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.data_element_id <=", value, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.data_element_id in", values, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.data_element_id not in", values, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.data_element_id between", value1, value2, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andDataElementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.data_element_id not between", value1, value2, "dataElementId");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("eam_grm_variable_data.value is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("eam_grm_variable_data.value is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(String value) {
            addCriterion("eam_grm_variable_data.value =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(String value) {
            addCriterion("eam_grm_variable_data.value <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(String value) {
            addCriterion("eam_grm_variable_data.value >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.value >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(String value) {
            addCriterion("eam_grm_variable_data.value <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(String value) {
            addCriterion("eam_grm_variable_data.value <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLike(String value) {
            addCriterion("eam_grm_variable_data.value like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotLike(String value) {
            addCriterion("eam_grm_variable_data.value not like", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<String> values) {
            addCriterion("eam_grm_variable_data.value in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<String> values) {
            addCriterion("eam_grm_variable_data.value not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.value between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(String value1, String value2) {
            addCriterion("eam_grm_variable_data.value not between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_grm_variable_data.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_grm_variable_data.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_grm_variable_data.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_grm_variable_data.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_grm_variable_data.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_grm_variable_data.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_grm_variable_data.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_grm_variable_data.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_grm_variable_data.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_grm_variable_data.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_grm_variable_data.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_grm_variable_data.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_grm_variable_data.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_grm_variable_data.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_grm_variable_data.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_grm_variable_data.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_grm_variable_data.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_grm_variable_data.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_grm_variable_data.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_grm_variable_data.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_grm_variable_data.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_grm_variable_data.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_grm_variable_data.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_grm_variable_data.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_grm_variable_data.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_grm_variable_data.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_grm_variable_data.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_grm_variable_data.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdIsNull() {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id is null");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdIsNotNull() {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id is not null");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id =", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdNotEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id <>", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdGreaterThan(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id >", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id >=", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdLessThan(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id <", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id <=", value, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id in", values, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdNotIn(List<Integer> values) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id not in", values, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id between", value1, value2, "eamGrmVariableId");
            return (Criteria) this;
        }

        public Criteria andEamGrmVariableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_grm_variable_data.eam_grm_variable_id not between", value1, value2, "eamGrmVariableId");
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