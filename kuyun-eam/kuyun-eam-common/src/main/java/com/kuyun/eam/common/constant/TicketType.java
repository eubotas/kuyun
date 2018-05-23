package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketType {

    REPAIR(1, "故障报修"),
    MIANTAIN(2, "维保计划"),
    ALARM(3, "报警工单");

    private int code;
    private String name;

    TicketType(int code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(int code) {
        for (TicketType c : TicketType.values()) {
            if (c.getCode() == code ) {
                return c.name;
            }
        }
        return null;
    }

    public boolean match(int code){
        return this.getCode() == code;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
