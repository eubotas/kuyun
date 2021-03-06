package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamParts;

/**
 * Created by user on 5/1/2017.
 */
public class EamPartVO extends EamParts {
    private String categoryName;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
