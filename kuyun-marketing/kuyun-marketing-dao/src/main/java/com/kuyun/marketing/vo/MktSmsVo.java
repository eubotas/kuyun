package com.kuyun.marketing.vo;

import com.kuyun.marketing.dao.model.MktSms;

/**
 * Created by user on 2017-07-25.
 */
public class MktSmsVo extends MktSms {
    private String templateName;

    private String templateContent;

    private Integer limit;

    private Integer offset;

    private String orderByClause;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

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
}
