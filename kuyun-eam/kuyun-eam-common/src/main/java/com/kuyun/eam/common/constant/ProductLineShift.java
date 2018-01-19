package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum ProductLineShift {

    MORNING("morning", "Shift", "早班"),
    MIDDLE("middle", "Shift", "中班"),
    NIGHT("night", "Shift", "晚班");

    private String code;
    private String type;
    private String name;

    ProductLineShift(String code, String type, String name){
        this.code = code;
        this.type = type;
        this.name = name;

    }

    public static String getName(String code) {
        for (ProductLineShift c : ProductLineShift.values()) {
            if (c.getCode().equals(code) ) {
                return c.name;
            }
        }
        return null;
    }

    public boolean match(String code){
        return this.getCode().equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
