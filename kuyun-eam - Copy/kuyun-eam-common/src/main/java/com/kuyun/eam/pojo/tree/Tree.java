package com.kuyun.eam.pojo.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-07.
 */
public class Tree implements Serializable {
    private List<ProvinceData> provices = new ArrayList<>();

    public List<ProvinceData> getProvices() {
        return provices;
    }

    public void setProvices(List<ProvinceData> provices) {
        this.provices = provices;
    }

    public void addProvice(ProvinceData proviceData){
        provices.add(proviceData);
    }
}
