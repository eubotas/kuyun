package com.kuyun.upms.common.constant;

/**
 * Created by user on 2018-01-29.
 */
public enum UpmsOrganizationEnum {

    ALARM("报警部门");

    private String name;

    UpmsOrganizationEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
