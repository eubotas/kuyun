package com.kuyun.eam.admin.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by user on 2017-10-31.
 */
@Document(indexName="knowledge",type="tag")
public class Tag extends BaseModel{
    @Field(type = FieldType.text, fielddata = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String tag;

    private int count;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
