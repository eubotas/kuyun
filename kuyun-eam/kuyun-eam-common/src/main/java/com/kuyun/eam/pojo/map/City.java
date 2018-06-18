package com.kuyun.eam.pojo.map;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018-06-15.
 */
public class City implements Serializable {

    private String name;
    private List<String> value;
    private int symboleSize;
    private ItemStyle itemStyle;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public int getSymboleSize() {
        return symboleSize;
    }

    public void setSymboleSize(int symboleSize) {
        this.symboleSize = symboleSize;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }
}
