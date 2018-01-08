package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum GrmVariableNtworkPermisstion {

    LOW("0", "低"),
    NORMAL("1", "中"),
    HIGHT("2", "高");

    private String code;
    private String name;

    GrmVariableNtworkPermisstion(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (GrmVariableNtworkPermisstion c : GrmVariableNtworkPermisstion.values()) {
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
