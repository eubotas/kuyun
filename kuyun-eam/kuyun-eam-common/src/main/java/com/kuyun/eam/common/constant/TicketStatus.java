package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketStatus {

    INIT("init", "待派工"),
    TO_PROCESS("toProcess", "待维修"),
    PROCESSING("processing", "维修中"),
    CLOSED("closed", "维修完成"),
    RESOLVED("resolved", "待评价"),
    COMPLETE("complete", "评价完成");


    private String code;
    private String name;

    TicketStatus(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (TicketStatus c : TicketStatus.values()) {
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
