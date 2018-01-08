package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum GrmVariableType {

    BOOLEAN("B", "开关量"),
    INTEGER("I", "整数"),
    FLOAT("F", "浮点数");

    private String code;
    private String name;

    GrmVariableType(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (GrmVariableType c : GrmVariableType.values()) {
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
