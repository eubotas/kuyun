package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum AlarmTarget {

//    WEIXIN("WeiXin", "微信"),
    SMS("SMS", "短信"),
    EMAIL("Email", "邮件");

    private String code;
    private String name;

    AlarmTarget(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (AlarmTarget c : AlarmTarget.values()) {
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
