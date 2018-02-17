package com.kuyun.eam.pojo.tree;

import java.io.Serializable;

/**
 * Created by user on 2017-08-07.
 */
public class Device implements Serializable {
    private String id;
    private String productLineId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
