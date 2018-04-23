package com.kuyun.eam.common.constant;

public enum TicketSearchCategory {
    //我的未处理
    MY_OPEN("myOpen", "未处理"),
    //我的已处理
    MY_RESOLVED("myResolved", "已处理"),
    //我的全部
    MY_ALL("myAll", "全部"),
    //状态未完成
    OPEN("open", "未完成"),
    //所有工单
    ALL("all", "全部"),
    //待派工
    INIT("init", "待派工"),
    //维修中
    PROCESSING("processing", "维修中"),
    //状态未完成
    NOTRESOLVED("notResolved", "未完成"),
    //已完成
    RESOLVED("resolved", "已完成"),
    //其他
    OTHER("other", "其他");

    private TicketSearchCategory(String code, String name){
        this.code = code;
        this.name = name;
    }

    private String name;
    private String code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean match(String code){
        return this.getCode().equalsIgnoreCase(code);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static TicketSearchCategory getCategroy (String code) {
        switch (code) {
            case "myAll":
                return MY_ALL;
            case "myOpen":
                return MY_OPEN;
            case "myResolved":
                return MY_RESOLVED;
            case "open":
                return OPEN;
            case "all":
                return ALL;
            case "init":
                return INIT;
            case "processing":
                return PROCESSING;

            case "notResolved":
                return NOTRESOLVED;
            case "resolved":
                return RESOLVED;
            default:
                return OTHER;
        }
    }
}