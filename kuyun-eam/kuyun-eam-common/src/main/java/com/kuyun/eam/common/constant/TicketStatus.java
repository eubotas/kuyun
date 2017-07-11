package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketStatus {

    OPEN("open", "未解决"),
    PROCESSING("processing", "处理中"),
    RESOLVED("resolved", "已解决"),
    NO_DEAL_WITH("no_deal_with", "不需处理");


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
