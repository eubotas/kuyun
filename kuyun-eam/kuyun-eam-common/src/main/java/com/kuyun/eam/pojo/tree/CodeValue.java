package com.kuyun.eam.pojo.tree;

import java.io.Serializable;

/**
 * Created by user on 2017-08-08.
 */
public class CodeValue implements Serializable {

    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
