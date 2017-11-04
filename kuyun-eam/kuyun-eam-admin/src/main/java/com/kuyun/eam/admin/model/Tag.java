package com.kuyun.eam.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by user on 2017-10-31.
 */
@Document(indexName="knowledge",type="tag")
public class Tag extends BaseModel{

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
