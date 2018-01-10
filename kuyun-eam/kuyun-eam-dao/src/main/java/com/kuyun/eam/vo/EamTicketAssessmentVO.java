package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentTag;

/**
 * Created by user on 5/1/2017.
 */
public class EamTicketAssessmentVO extends EamTicketAssessment {
    private String tagNames;

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

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }
}
