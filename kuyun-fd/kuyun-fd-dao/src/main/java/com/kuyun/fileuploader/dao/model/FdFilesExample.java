package com.kuyun.fileuploader.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FdFilesExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public FdFilesExample() {
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
            addCriterion("fd_files.id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("fd_files.id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("fd_files.id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("fd_files.id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("fd_files.id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_files.id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("fd_files.id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("fd_files.id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("fd_files.id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("fd_files.id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("fd_files.file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("fd_files.file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("fd_files.file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("fd_files.file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("fd_files.file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_files.file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("fd_files.file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("fd_files.file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("fd_files.file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("fd_files.file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("fd_files.file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("fd_files.file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("fd_files.file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("fd_files.file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andMimeIsNull() {
            addCriterion("fd_files.mime is null");
            return (Criteria) this;
        }

        public Criteria andMimeIsNotNull() {
            addCriterion("fd_files.mime is not null");
            return (Criteria) this;
        }

        public Criteria andMimeEqualTo(String value) {
            addCriterion("fd_files.mime =", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeNotEqualTo(String value) {
            addCriterion("fd_files.mime <>", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeGreaterThan(String value) {
            addCriterion("fd_files.mime >", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeGreaterThanOrEqualTo(String value) {
            addCriterion("fd_files.mime >=", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeLessThan(String value) {
            addCriterion("fd_files.mime <", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeLessThanOrEqualTo(String value) {
            addCriterion("fd_files.mime <=", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeLike(String value) {
            addCriterion("fd_files.mime like", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeNotLike(String value) {
            addCriterion("fd_files.mime not like", value, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeIn(List<String> values) {
            addCriterion("fd_files.mime in", values, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeNotIn(List<String> values) {
            addCriterion("fd_files.mime not in", values, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeBetween(String value1, String value2) {
            addCriterion("fd_files.mime between", value1, value2, "mime");
            return (Criteria) this;
        }

        public Criteria andMimeNotBetween(String value1, String value2) {
            addCriterion("fd_files.mime not between", value1, value2, "mime");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("fd_files.size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("fd_files.size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Long value) {
            addCriterion("fd_files.size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Long value) {
            addCriterion("fd_files.size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Long value) {
            addCriterion("fd_files.size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("fd_files.size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Long value) {
            addCriterion("fd_files.size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Long value) {
            addCriterion("fd_files.size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Long> values) {
            addCriterion("fd_files.size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Long> values) {
            addCriterion("fd_files.size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Long value1, Long value2) {
            addCriterion("fd_files.size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Long value1, Long value2) {
            addCriterion("fd_files.size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameIsNull() {
            addCriterion("fd_files.saved_file_name is null");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameIsNotNull() {
            addCriterion("fd_files.saved_file_name is not null");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameEqualTo(String value) {
            addCriterion("fd_files.saved_file_name =", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameNotEqualTo(String value) {
            addCriterion("fd_files.saved_file_name <>", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameGreaterThan(String value) {
            addCriterion("fd_files.saved_file_name >", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_files.saved_file_name >=", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameLessThan(String value) {
            addCriterion("fd_files.saved_file_name <", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameLessThanOrEqualTo(String value) {
            addCriterion("fd_files.saved_file_name <=", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameLike(String value) {
            addCriterion("fd_files.saved_file_name like", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameNotLike(String value) {
            addCriterion("fd_files.saved_file_name not like", value, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameIn(List<String> values) {
            addCriterion("fd_files.saved_file_name in", values, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameNotIn(List<String> values) {
            addCriterion("fd_files.saved_file_name not in", values, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameBetween(String value1, String value2) {
            addCriterion("fd_files.saved_file_name between", value1, value2, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andSavedFileNameNotBetween(String value1, String value2) {
            addCriterion("fd_files.saved_file_name not between", value1, value2, "savedFileName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameIsNull() {
            addCriterion("fd_files.moudle_name is null");
            return (Criteria) this;
        }

        public Criteria andMoudleNameIsNotNull() {
            addCriterion("fd_files.moudle_name is not null");
            return (Criteria) this;
        }

        public Criteria andMoudleNameEqualTo(String value) {
            addCriterion("fd_files.moudle_name =", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameNotEqualTo(String value) {
            addCriterion("fd_files.moudle_name <>", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameGreaterThan(String value) {
            addCriterion("fd_files.moudle_name >", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_files.moudle_name >=", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameLessThan(String value) {
            addCriterion("fd_files.moudle_name <", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameLessThanOrEqualTo(String value) {
            addCriterion("fd_files.moudle_name <=", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameLike(String value) {
            addCriterion("fd_files.moudle_name like", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameNotLike(String value) {
            addCriterion("fd_files.moudle_name not like", value, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameIn(List<String> values) {
            addCriterion("fd_files.moudle_name in", values, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameNotIn(List<String> values) {
            addCriterion("fd_files.moudle_name not in", values, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameBetween(String value1, String value2) {
            addCriterion("fd_files.moudle_name between", value1, value2, "moudleName");
            return (Criteria) this;
        }

        public Criteria andMoudleNameNotBetween(String value1, String value2) {
            addCriterion("fd_files.moudle_name not between", value1, value2, "moudleName");
            return (Criteria) this;
        }

        public Criteria andOssIdIsNull() {
            addCriterion("fd_files.oss_id is null");
            return (Criteria) this;
        }

        public Criteria andOssIdIsNotNull() {
            addCriterion("fd_files.oss_id is not null");
            return (Criteria) this;
        }

        public Criteria andOssIdEqualTo(Integer value) {
            addCriterion("fd_files.oss_id =", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdNotEqualTo(Integer value) {
            addCriterion("fd_files.oss_id <>", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdGreaterThan(Integer value) {
            addCriterion("fd_files.oss_id >", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_files.oss_id >=", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdLessThan(Integer value) {
            addCriterion("fd_files.oss_id <", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdLessThanOrEqualTo(Integer value) {
            addCriterion("fd_files.oss_id <=", value, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdIn(List<Integer> values) {
            addCriterion("fd_files.oss_id in", values, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdNotIn(List<Integer> values) {
            addCriterion("fd_files.oss_id not in", values, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.oss_id between", value1, value2, "ossId");
            return (Criteria) this;
        }

        public Criteria andOssIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.oss_id not between", value1, value2, "ossId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("fd_files.organization_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("fd_files.organization_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(Integer value) {
            addCriterion("fd_files.organization_id =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(Integer value) {
            addCriterion("fd_files.organization_id <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(Integer value) {
            addCriterion("fd_files.organization_id >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_files.organization_id >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(Integer value) {
            addCriterion("fd_files.organization_id <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(Integer value) {
            addCriterion("fd_files.organization_id <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<Integer> values) {
            addCriterion("fd_files.organization_id in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<Integer> values) {
            addCriterion("fd_files.organization_id not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.organization_id between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.organization_id not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("fd_files.create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("fd_files.create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("fd_files.create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("fd_files.create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("fd_files.create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_files.create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("fd_files.create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("fd_files.create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("fd_files.create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("fd_files.create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("fd_files.create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("fd_files.create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("fd_files.create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("fd_files.create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("fd_files.create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_files.create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("fd_files.create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_files.create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("fd_files.create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("fd_files.create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("fd_files.create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_files.create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("fd_files.update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("fd_files.update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("fd_files.update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("fd_files.update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("fd_files.update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_files.update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("fd_files.update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("fd_files.update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("fd_files.update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("fd_files.update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_files.update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("fd_files.update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("fd_files.update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("fd_files.update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("fd_files.update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("fd_files.update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_files.update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("fd_files.update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_files.update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("fd_files.update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("fd_files.update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("fd_files.update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_files.update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("fd_files.delete_flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("fd_files.delete_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(Boolean value) {
            addCriterion("fd_files.delete_flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(Boolean value) {
            addCriterion("fd_files.delete_flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(Boolean value) {
            addCriterion("fd_files.delete_flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fd_files.delete_flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(Boolean value) {
            addCriterion("fd_files.delete_flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("fd_files.delete_flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<Boolean> values) {
            addCriterion("fd_files.delete_flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<Boolean> values) {
            addCriterion("fd_files.delete_flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("fd_files.delete_flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fd_files.delete_flag not between", value1, value2, "deleteFlag");
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