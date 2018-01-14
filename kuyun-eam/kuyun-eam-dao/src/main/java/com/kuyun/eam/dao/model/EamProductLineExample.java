package com.kuyun.eam.dao.model;

import java.io.Serializable;
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