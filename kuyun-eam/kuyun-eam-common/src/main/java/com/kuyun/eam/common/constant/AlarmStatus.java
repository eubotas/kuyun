package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum AlarmStatus {

    ANU("ANU", "Active and Unacked"),
//    ANA("ANA", "Active and Acked"),
    CNU("CNU", "Clear and Unacked");
//    CNA("CNA", "Clear and Acked");

    private String code;
    private String name;

    AlarmStatus(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (AlarmStatus c : AlarmStatus.values()) {
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
