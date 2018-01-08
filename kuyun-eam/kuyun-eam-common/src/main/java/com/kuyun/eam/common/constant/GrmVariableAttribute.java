package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum GrmVariableAttribute {

    READ("R", "只读"),
    WRITE("W", "读写");

    private String code;
    private String name;

    GrmVariableAttribute(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (GrmVariableAttribute c : GrmVariableAttribute.values()) {
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
