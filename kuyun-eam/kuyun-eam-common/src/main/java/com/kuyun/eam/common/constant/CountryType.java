package com.kuyun.eam.common.constant;

/**
 * Created by user on 2018-04-09.
 */
public enum CountryType {

    DOMESTIC("domestic", "国内"),
    ABROAD("abroad", "国外");

    private String code;
    private String name;

    CountryType(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (CountryType c : CountryType.values()) {
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

    public boolean match(String name){
        return getName().equalsIgnoreCase(name);
    }

}
