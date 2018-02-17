package com.kuyun.eam.pojo.tree;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-07.
 */
public class CityData implements Serializable {
    private int total = 0;
    private String code = "";
    private String name = "";

    private int online = 0;

    private BigDecimal latitude;
    private BigDecimal longitude;

    private List<ProductLine> children = new ArrayList<>();

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

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<ProductLine> getChildren() {
        return children;
    }

    public void setChildren(List<ProductLine> children) {
        this.children = children;
    }

    public void addChildren(ProductLine device){
        children.add(device);
    }
}
