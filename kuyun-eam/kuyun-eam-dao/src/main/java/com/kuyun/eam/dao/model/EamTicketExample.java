package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EamTicketExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public EamTicketExample() {
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

        public Criteria andTicketIdIsNull() {
            addCriterion("eam_ticket.ticket_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketIdIsNotNull() {
            addCriterion("eam_ticket.ticket_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketIdEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_id =", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_id <>", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.ticket_id >", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_id >=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThan(Integer value) {
            addCriterion("eam_ticket.ticket_id <", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_id <=", value, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdIn(List<Integer> values) {
            addCriterion("eam_ticket.ticket_id in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.ticket_id not in", values, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.ticket_id between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.ticket_id not between", value1, value2, "ticketId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdIsNull() {
            addCriterion("eam_ticket.ticket_type_id is null");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdIsNotNull() {
            addCriterion("eam_ticket.ticket_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_type_id =", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_type_id <>", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.ticket_type_id >", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_type_id >=", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdLessThan(Integer value) {
            addCriterion("eam_ticket.ticket_type_id <", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.ticket_type_id <=", value, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdIn(List<Integer> values) {
            addCriterion("eam_ticket.ticket_type_id in", values, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.ticket_type_id not in", values, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.ticket_type_id between", value1, value2, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andTicketTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.ticket_type_id not between", value1, value2, "ticketTypeId");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("eam_ticket.description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("eam_ticket.description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("eam_ticket.description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("eam_ticket.description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("eam_ticket.description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("eam_ticket.description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("eam_ticket.description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("eam_ticket.description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("eam_ticket.description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("eam_ticket.description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("eam_ticket.description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("eam_ticket.description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andImagePath1IsNull() {
            addCriterion("eam_ticket.image_path_1 is null");
            return (Criteria) this;
        }

        public Criteria andImagePath1IsNotNull() {
            addCriterion("eam_ticket.image_path_1 is not null");
            return (Criteria) this;
        }

        public Criteria andImagePath1EqualTo(String value) {
            addCriterion("eam_ticket.image_path_1 =", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1NotEqualTo(String value) {
            addCriterion("eam_ticket.image_path_1 <>", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1GreaterThan(String value) {
            addCriterion("eam_ticket.image_path_1 >", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1GreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_1 >=", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1LessThan(String value) {
            addCriterion("eam_ticket.image_path_1 <", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1LessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_1 <=", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1Like(String value) {
            addCriterion("eam_ticket.image_path_1 like", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1NotLike(String value) {
            addCriterion("eam_ticket.image_path_1 not like", value, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1In(List<String> values) {
            addCriterion("eam_ticket.image_path_1 in", values, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1NotIn(List<String> values) {
            addCriterion("eam_ticket.image_path_1 not in", values, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1Between(String value1, String value2) {
            addCriterion("eam_ticket.image_path_1 between", value1, value2, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath1NotBetween(String value1, String value2) {
            addCriterion("eam_ticket.image_path_1 not between", value1, value2, "imagePath1");
            return (Criteria) this;
        }

        public Criteria andImagePath2IsNull() {
            addCriterion("eam_ticket.image_path_2 is null");
            return (Criteria) this;
        }

        public Criteria andImagePath2IsNotNull() {
            addCriterion("eam_ticket.image_path_2 is not null");
            return (Criteria) this;
        }

        public Criteria andImagePath2EqualTo(String value) {
            addCriterion("eam_ticket.image_path_2 =", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2NotEqualTo(String value) {
            addCriterion("eam_ticket.image_path_2 <>", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2GreaterThan(String value) {
            addCriterion("eam_ticket.image_path_2 >", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2GreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_2 >=", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2LessThan(String value) {
            addCriterion("eam_ticket.image_path_2 <", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2LessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_2 <=", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2Like(String value) {
            addCriterion("eam_ticket.image_path_2 like", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2NotLike(String value) {
            addCriterion("eam_ticket.image_path_2 not like", value, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2In(List<String> values) {
            addCriterion("eam_ticket.image_path_2 in", values, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2NotIn(List<String> values) {
            addCriterion("eam_ticket.image_path_2 not in", values, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2Between(String value1, String value2) {
            addCriterion("eam_ticket.image_path_2 between", value1, value2, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath2NotBetween(String value1, String value2) {
            addCriterion("eam_ticket.image_path_2 not between", value1, value2, "imagePath2");
            return (Criteria) this;
        }

        public Criteria andImagePath3IsNull() {
            addCriterion("eam_ticket.image_path_3 is null");
            return (Criteria) this;
        }

        public Criteria andImagePath3IsNotNull() {
            addCriterion("eam_ticket.image_path_3 is not null");
            return (Criteria) this;
        }

        public Criteria andImagePath3EqualTo(String value) {
            addCriterion("eam_ticket.image_path_3 =", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3NotEqualTo(String value) {
            addCriterion("eam_ticket.image_path_3 <>", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3GreaterThan(String value) {
            addCriterion("eam_ticket.image_path_3 >", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3GreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_3 >=", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3LessThan(String value) {
            addCriterion("eam_ticket.image_path_3 <", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3LessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_3 <=", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3Like(String value) {
            addCriterion("eam_ticket.image_path_3 like", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3NotLike(String value) {
            addCriterion("eam_ticket.image_path_3 not like", value, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3In(List<String> values) {
            addCriterion("eam_ticket.image_path_3 in", values, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3NotIn(List<String> values) {
            addCriterion("eam_ticket.image_path_3 not in", values, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3Between(String value1, String value2) {
            addCriterion("eam_ticket.image_path_3 between", value1, value2, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath3NotBetween(String value1, String value2) {
            addCriterion("eam_ticket.image_path_3 not between", value1, value2, "imagePath3");
            return (Criteria) this;
        }

        public Criteria andImagePath4IsNull() {
            addCriterion("eam_ticket.image_path_4 is null");
            return (Criteria) this;
        }

        public Criteria andImagePath4IsNotNull() {
            addCriterion("eam_ticket.image_path_4 is not null");
            return (Criteria) this;
        }

        public Criteria andImagePath4EqualTo(String value) {
            addCriterion("eam_ticket.image_path_4 =", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4NotEqualTo(String value) {
            addCriterion("eam_ticket.image_path_4 <>", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4GreaterThan(String value) {
            addCriterion("eam_ticket.image_path_4 >", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4GreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_4 >=", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4LessThan(String value) {
            addCriterion("eam_ticket.image_path_4 <", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4LessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.image_path_4 <=", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4Like(String value) {
            addCriterion("eam_ticket.image_path_4 like", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4NotLike(String value) {
            addCriterion("eam_ticket.image_path_4 not like", value, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4In(List<String> values) {
            addCriterion("eam_ticket.image_path_4 in", values, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4NotIn(List<String> values) {
            addCriterion("eam_ticket.image_path_4 not in", values, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4Between(String value1, String value2) {
            addCriterion("eam_ticket.image_path_4 between", value1, value2, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andImagePath4NotBetween(String value1, String value2) {
            addCriterion("eam_ticket.image_path_4 not between", value1, value2, "imagePath4");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("eam_ticket.priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("eam_ticket.priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(String value) {
            addCriterion("eam_ticket.priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(String value) {
            addCriterion("eam_ticket.priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(String value) {
            addCriterion("eam_ticket.priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(String value) {
            addCriterion("eam_ticket.priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLike(String value) {
            addCriterion("eam_ticket.priority like", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotLike(String value) {
            addCriterion("eam_ticket.priority not like", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<String> values) {
            addCriterion("eam_ticket.priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<String> values) {
            addCriterion("eam_ticket.priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(String value1, String value2) {
            addCriterion("eam_ticket.priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(String value1, String value2) {
            addCriterion("eam_ticket.priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andExecutorIdIsNull() {
            addCriterion("eam_ticket.executor_id is null");
            return (Criteria) this;
        }

        public Criteria andExecutorIdIsNotNull() {
            addCriterion("eam_ticket.executor_id is not null");
            return (Criteria) this;
        }

        public Criteria andExecutorIdEqualTo(Integer value) {
            addCriterion("eam_ticket.executor_id =", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.executor_id <>", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.executor_id >", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.executor_id >=", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdLessThan(Integer value) {
            addCriterion("eam_ticket.executor_id <", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.executor_id <=", value, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdIn(List<Integer> values) {
            addCriterion("eam_ticket.executor_id in", values, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.executor_id not in", values, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.executor_id between", value1, value2, "executorId");
            return (Criteria) this;
        }

        public Criteria andExecutorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.executor_id not between", value1, value2, "executorId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("eam_ticket.status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("eam_ticket.status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("eam_ticket.status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("eam_ticket.status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("eam_ticket.status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("eam_ticket.status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("eam_ticket.status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("eam_ticket.status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("eam_ticket.status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("eam_ticket.status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("eam_ticket.status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("eam_ticket.status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("eam_ticket.status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("eam_ticket.status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("eam_ticket.end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("eam_ticket.end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("eam_ticket.end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("eam_ticket.end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("eam_ticket.end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("eam_ticket.end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("eam_ticket.end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("eam_ticket.end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("eam_ticket.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("eam_ticket.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("eam_ticket.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("eam_ticket.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("eam_ticket.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("eam_ticket.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("eam_ticket.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("eam_ticket.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("eam_ticket.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("eam_ticket.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("eam_ticket.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("eam_ticket.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("eam_ticket.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("eam_ticket.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("eam_ticket.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("eam_ticket.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("eam_ticket.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("eam_ticket.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("eam_ticket.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("eam_ticket.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("eam_ticket.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("eam_ticket.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("eam_ticket.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("eam_ticket.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("eam_ticket.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("eam_ticket.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("eam_ticket.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("eam_ticket.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("eam_ticket.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("eam_ticket.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("eam_ticket.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("eam_ticket.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("eam_ticket.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("eam_ticket.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("eam_ticket.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("eam_ticket.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("eam_ticket.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("eam_ticket.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_ticket.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("eam_ticket.delete_flag not between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("eam_ticket.company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("eam_ticket.company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("eam_ticket.company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("eam_ticket.company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("eam_ticket.company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("eam_ticket.company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("eam_ticket.company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("eam_ticket.company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("eam_ticket.company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("eam_ticket.company_id not between", value1, value2, "companyId");
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