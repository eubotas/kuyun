package com.kuyun.eam.common.constant;

/**
 * Created by user on 2018-01-05.
 */
public enum OrgDepartment {

    MAINTENANCE_DEPARTMENT("MaintenanceDepartment", "维保部"),
    REPAIR_DEPARTMENT("RepairDepartment", "维修部"),
    ALARM_DEPARTMENT("AlarmDepartment", "报警部门");

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
