package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum CollectStatus {

    NO_START("NoStart", "未采集"),
    WORKING("Working", "采集中");

    private String code;
    private String name;

    CollectStatus(String code, String name){
        this.code = code;
        this.name = name;

    }

    public static String getName(String code) {
        for (CollectStatus c : CollectStatus.values()) {
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
