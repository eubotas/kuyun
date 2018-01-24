package com.kuyun.eam.pojo.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-08.
 */
public class Areas {

    public List<CodeValue> province = new ArrayList<>();

    public List<CodeValue> city = new ArrayList<>();

    public List<CodeValue> district = new ArrayList<>();

    public List<CodeValue> getProvince() {
        return province;
    }

    public void setProvince(List<CodeValue> province) {
        this.province = province;
    }

    public List<CodeValue> getCity() {
        return city;
    }

    public void setCity(List<CodeValue> city) {
        this.city = city;
    }

    public List<CodeValue> getDistrict() {
        return district;
    }

    public void setDistrict(List<CodeValue> district) {
        this.district = district;
    }
}
