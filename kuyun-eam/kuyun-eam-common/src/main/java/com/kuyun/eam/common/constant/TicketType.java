package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketType {

    ALARM("1", "报警工单");

    private String code;
    private String name;

    TicketType(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (TicketType c : TicketType.values()) {
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
