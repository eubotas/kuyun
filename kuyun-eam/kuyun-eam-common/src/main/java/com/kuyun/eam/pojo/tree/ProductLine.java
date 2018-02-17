package com.kuyun.eam.pojo.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-02-15.
 */
public class ProductLine implements Serializable {

    private String id;
    private String name;

    private List<Device> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getChildren() {
        return children;
    }

    public void setChildren(List<Device> children) {
        this.children = children;
    }

    public void addChildren(Device device){
        children.add(device);
    }
}
