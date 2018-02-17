package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018-02-17.
 */
public class CurveData implements Serializable {
    List<String> value = new ArrayList<>();
    List<Date> time = new ArrayList<>();

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public List<Date> getTime() {
        return time;
    }

    public void setTime(List<Date> time) {
        this.time = time;
    }
}
