package com.kuyun.eam.pojo;

import com.kuyun.common.excel.BaseImportBean;

/**
 * Created by user on 2018-04-02.
 */
public class CompanyBean extends BaseImportBean {
    private String seqId;
    private String name;

    private String phone;

    private String fax;

    private String zip;

    private String www;

    private String address;

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 返回sheet对应的第一行的每一列的中文名字 例如{"第一列名字","第二列名字","第三列名字"}
     */
    @Override
    public String[] getHeaderName() {
        return new String[]{"序号", "客户名称", "电话", "传真", "网址", "邮编", "地址"};
    }

    /**
     * 返回dto中定义的每个Field按指定顺序返回 例如{"firstColumn","secondColumn","thirdColumn"}
     * 如果只需要导出dto中的一部分字段，只需要添加需要导出的Field
     */
    @Override
    public String[] getSortedProperty() {
        return new String[]{"seqId", "name", "phone", "fax", "www", "zip", "address"};
    }
}
