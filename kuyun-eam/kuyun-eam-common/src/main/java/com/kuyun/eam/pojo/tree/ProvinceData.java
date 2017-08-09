package com.kuyun.eam.pojo.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-07.
 */
public class ProvinceData implements Serializable {
    private int total = 0;
    private String code = "";
    private String name = "";
    private int online = 0;

    private List<CityData> children = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<CityData> getChildren() {
        return children;
    }

    public void setChildren(List<CityData> children) {
        this.children = children;
    }

    public void addChildren(CityData cityData){
        children.add(cityData);
    }
}
