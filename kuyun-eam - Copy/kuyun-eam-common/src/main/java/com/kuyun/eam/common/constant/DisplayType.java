package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum DisplayType {

    PIE("pie", "饼图"),
    LED("led", "LED"),
    GUAGE("guage", "仪表盘");

    private String code;
    private String name;

    DisplayType(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (DisplayType c : DisplayType.values()) {
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
