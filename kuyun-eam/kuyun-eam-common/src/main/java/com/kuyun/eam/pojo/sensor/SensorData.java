package com.kuyun.eam.pojo.sensor;

import java.io.Serializable;

/**
 * Created by user on 2017-08-08.
 */
public class SensorData implements Serializable{
    private String name;
    private String unit;
    private boolean showchart;
    private String varid;
    private String value;
    private String showtype;
    private int showorder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean getShowchart() {
        return showchart;
    }

    public void setShowchart(boolean showchart) {
        this.showchart = showchart;
    }

    public String getVarid() {
        return varid;
    }

    public void setVarid(String varid) {
        this.varid = varid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getShowtype() {
        return showtype;
    }

    public void setShowtype(String showtype) {
        this.showtype = showtype;
    }

    public int getShoworder() {
        return showorder;
    }

    public void setShoworder(int showorder) {
        this.showorder = showorder;
    }
}
