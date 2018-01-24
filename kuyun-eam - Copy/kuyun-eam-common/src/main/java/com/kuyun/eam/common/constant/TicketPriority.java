package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketPriority {

    NORMAL("normal", "一般"),
    URGENT("urgent", "紧急"),
    VERY_URGENT("very_urgent", "非常紧急");

    private String code;
    private String name;

    TicketPriority(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (TicketPriority c : TicketPriority.values()) {
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
