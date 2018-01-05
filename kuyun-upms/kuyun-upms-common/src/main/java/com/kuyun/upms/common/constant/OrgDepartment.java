package com.kuyun.upms.common.constant;

/**
 * Created by user on 2018-01-05.
 */
public enum OrgDepartment {

    MAINTENANCE_DEPARTMENT("MaintenanceDepartment", "维修部"),
    TICKET_MANAGE("TicketManage", "工单管理部");

    private String code;
    private String name;

    OrgDepartment(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (OrgDepartment c : OrgDepartment.values()) {
            if (c.getCode().equals(code) ) {
                return c.name;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
