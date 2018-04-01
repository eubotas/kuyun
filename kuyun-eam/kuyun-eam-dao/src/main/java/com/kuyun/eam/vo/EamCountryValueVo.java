package com.kuyun.eam.vo;

import java.io.Serializable;

/**
 * Created by user on 2018-03-24.
 */
public class EamCountryValueVo implements Serializable {

    private String year;
    private String country;
    private Integer value;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
