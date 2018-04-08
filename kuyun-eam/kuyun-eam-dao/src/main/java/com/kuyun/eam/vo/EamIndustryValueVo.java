package com.kuyun.eam.vo;

import java.io.Serializable;

/**
 * Created by user on 2018-03-24.
 */
public class EamIndustryValueVo implements Serializable {

    private String industry;
    private String companyName;
    private Integer count;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
