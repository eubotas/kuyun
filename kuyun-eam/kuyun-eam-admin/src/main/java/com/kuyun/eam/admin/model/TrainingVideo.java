package com.kuyun.eam.admin.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


/**
 * Created by user on 2017-10-27.
 */
@Document(indexName="knowledge",type="training-video")
public class TrainingVideo extends BaseModel{
    @Field(type = FieldType.text, analyzer = "keyword")
    private String tag;

    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;

    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    private String path;

    private Integer order;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
