package com.kuyun.upms.dao.vo;


import java.io.Serializable;

public class CompanyInfo implements Serializable {
    private String companyName;
    private String companyLogo;
    private String companySystemName;
    private String companyTel;
    private String companyAddr;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanySystemName() {
        return companySystemName;
    }

    public void setCompanySystemName(String companySystemName) {
        this.companySystemName = companySystemName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

}
