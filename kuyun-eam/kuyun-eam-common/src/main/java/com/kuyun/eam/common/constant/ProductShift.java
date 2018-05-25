package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum ProductShift {

    MORNING("1", "早班"),
    MIDDLE("2", "中班"),
    NIGHT("3", "晚班");

    private String code;
    private String name;

    ProductShift(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (ProductShift c : ProductShift.values()) {
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

}
