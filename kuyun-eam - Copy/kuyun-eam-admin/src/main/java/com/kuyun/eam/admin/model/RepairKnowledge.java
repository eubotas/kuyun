package com.kuyun.eam.admin.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * Created by user on 2017-10-27.
 */
@Document(indexName="knowledge",type="repair-knowledge")
public class RepairKnowledge extends BaseModel{
    @Field(type = FieldType.text, analyzer = "keyword")
    private String tag;

    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String codes;

    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String method;

    private Integer order;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
