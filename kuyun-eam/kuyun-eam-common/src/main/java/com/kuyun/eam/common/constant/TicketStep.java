package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum TicketStep {

    CREATE("create", "已提报，等待处理"),
    PROCESSING("processing", "开始处理中"),
    RESOLVED("resolved", "已解决"),
    NO_DEAL_WITH("no_deal_with", "无需处理");

    private String code;
    private String name;

    TicketStep(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (TicketStep c : TicketStep.values()) {
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
