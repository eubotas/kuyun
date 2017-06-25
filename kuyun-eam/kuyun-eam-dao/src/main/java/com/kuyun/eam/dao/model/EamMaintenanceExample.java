package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamMaintenanceExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamMaintenanceExample() {
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

        public Criteria andMaintenanceIdIsNull() {
            addCriterion("t.maintenance_id is null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdIsNotNull() {
            addCriterion("t.maintenance_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdEqualTo(Integer value) {
            addCriterion("t.maintenance_id =", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdNotEqualTo(Integer value) {
            addCriterion("t.maintenance_id <>", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdGreaterThan(Integer value) {
            addCriterion("t.maintenance_id >", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.maintenance_id >=", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdLessThan(Integer value) {
            addCriterion("t.maintenance_id <", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.maintenance_id <=", value, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdIn(List<Integer> values) {
            addCriterion("t.maintenance_id in", values, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdNotIn(List<Integer> values) {
            addCriterion("t.maintenance_id not in", values, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdBetween(Integer value1, Integer value2) {
            addCriterion("t.maintenance_id between", value1, value2, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andMaintenanceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.maintenance_id not between", value1, value2, "maintenanceId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNull() {
            addCriterion("t.equipment_id is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIsNotNull() {
            addCriterion("t.equipment_id is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdEqualTo(String value) {
            addCriterion("t.equipment_id =", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotEqualTo(String value) {
            addCriterion("t.equipment_id <>", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThan(String value) {
            addCriterion("t.equipment_id >", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("t.equipment_id >=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThan(String value) {
            addCriterion("t.equipment_id <", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.equipment_id <=", value, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdIn(List<String> values) {
            addCriterion("t.equipment_id in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotIn(List<String> values) {
            addCriterion("t.equipment_id not in", values, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdBetween(String value1, String value2) {
            addCriterion("t.equipment_id between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andEquipmentIdNotBetween(String value1, String value2) {
            addCriterion("t.equipment_id not between", value1, value2, "equipmentId");
            return (Criteria) this;
        }

        public Criteria andPartIdIsNull() {
            addCriterion("t.part_id is null");
            return (Criteria) this;
        }

        public Criteria andPartIdIsNotNull() {
            addCriterion("t.part_id is not null");
            return (Criteria) this;
        }

        public Criteria andPartIdEqualTo(Integer value) {
            addCriterion("t.part_id =", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdNotEqualTo(Integer value) {
            addCriterion("t.part_id <>", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdGreaterThan(Integer value) {
            addCriterion("t.part_id >", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.part_id >=", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdLessThan(Integer value) {
            addCriterion("t.part_id <", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.part_id <=", value, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdIn(List<Integer> values) {
            addCriterion("t.part_id in", values, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdNotIn(List<Integer> values) {
            addCriterion("t.part_id not in", values, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdBetween(Integer value1, Integer value2) {
            addCriterion("t.part_id between", value1, value2, "partId");
            return (Criteria) this;
        }

        public Criteria andPartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.part_id not between", value1, value2, "partId");
            return (Criteria) this;
        }

        public Criteria andPartQuantityIsNull() {
            addCriterion("t.part_quantity is null");
            return (Criteria) this;
        }

        public Criteria andPartQuantityIsNotNull() {
            addCriterion("t.part_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andPartQuantityEqualTo(Integer value) {
            addCriterion("t.part_quantity =", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityNotEqualTo(Integer value) {
            addCriterion("t.part_quantity <>", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityGreaterThan(Integer value) {
            addCriterion("t.part_quantity >", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.part_quantity >=", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityLessThan(Integer value) {
            addCriterion("t.part_quantity <", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("t.part_quantity <=", value, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityIn(List<Integer> values) {
            addCriterion("t.part_quantity in", values, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityNotIn(List<Integer> values) {
            addCriterion("t.part_quantity not in", values, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityBetween(Integer value1, Integer value2) {
            addCriterion("t.part_quantity between", value1, value2, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andPartQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("t.part_quantity not between", value1, value2, "partQuantity");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("t.reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("t.reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("t.reason =", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("t.reason <>", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("t.reason >", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("t.reason >=", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("t.reason <", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("t.reason <=", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("t.reason like", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("t.reason not like", value, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("t.reason in", values, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("t.reason not in", values, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("t.reason between", value1, value2, "t.reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("t.reason not between", value1, value2, "t.reason");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("t.content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("t.content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("t.content =", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("t.content <>", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("t.content >", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("t.content >=", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("t.content <", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("t.content <=", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("t.content like", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("t.content not like", value, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("t.content in", values, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("t.content not in", values, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("t.content between", value1, value2, "t.content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("t.content not between", value1, value2, "t.content");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdIsNull() {
            addCriterion("t.maintain_user_id is null");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdIsNotNull() {
            addCriterion("t.maintain_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdEqualTo(Integer value) {
            addCriterion("t.maintain_user_id =", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdNotEqualTo(Integer value) {
            addCriterion("t.maintain_user_id <>", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdGreaterThan(Integer value) {
            addCriterion("t.maintain_user_id >", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.maintain_user_id >=", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdLessThan(Integer value) {
            addCriterion("t.maintain_user_id <", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.maintain_user_id <=", value, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdIn(List<Integer> values) {
            addCriterion("t.maintain_user_id in", values, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdNotIn(List<Integer> values) {
            addCriterion("t.maintain_user_id not in", values, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdBetween(Integer value1, Integer value2) {
            addCriterion("t.maintain_user_id between", value1, value2, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.maintain_user_id not between", value1, value2, "maintainUserId");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeIsNull() {
            addCriterion("t.maintain_time is null");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeIsNotNull() {
            addCriterion("t.maintain_time is not null");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeEqualTo(Date value) {
            addCriterion("t.maintain_time =", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeNotEqualTo(Date value) {
            addCriterion("t.maintain_time <>", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeGreaterThan(Date value) {
            addCriterion("t.maintain_time >", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("t.maintain_time >=", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeLessThan(Date value) {
            addCriterion("t.maintain_time <", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeLessThanOrEqualTo(Date value) {
            addCriterion("t.maintain_time <=", value, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeIn(List<Date> values) {
            addCriterion("t.maintain_time in", values, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeNotIn(List<Date> values) {
            addCriterion("t.maintain_time not in", values, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeBetween(Date value1, Date value2) {
            addCriterion("t.maintain_time between", value1, value2, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andMaintainTimeNotBetween(Date value1, Date value2) {
            addCriterion("t.maintain_time not between", value1, value2, "maintainTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("t.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("t.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("t.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("t.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("t.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("t.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("t.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("t.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("t.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("t.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("t.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("t.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("t.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("t.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("t.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("t.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("t.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("t.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("t.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("t.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("t.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("t.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("t.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("t.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("t.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("t.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("t.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("t.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("t.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("t.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("t.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("t.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("t.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("t.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("t.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("t.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("t.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("t.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("t.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("t.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("t.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("t.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("t.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("t.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("t.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("t.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("t.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("t.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("t.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("t.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("t.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("t.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("t.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("t.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("t.organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("t.organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Integer value) {
            addCriterion("t.organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Integer value) {
            addCriterion("t.organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Integer value) {
            addCriterion("t.organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("t.organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Integer value) {
            addCriterion("t.organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Integer value) {
            addCriterion("t.organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Integer> values) {
            addCriterion("t.organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Integer> values) {
            addCriterion("t.organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Integer value1, Integer value2) {
            addCriterion("t.organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("t.organization_id not between", value1, value2, "organizationId");
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