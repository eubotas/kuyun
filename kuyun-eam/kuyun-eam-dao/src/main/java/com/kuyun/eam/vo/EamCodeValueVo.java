package com.kuyun.eam.vo;

import java.io.Serializable;

/**
 * Created by user on 2018-03-24.
 */
public class EamCodeValueVo implements Serializable {

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
