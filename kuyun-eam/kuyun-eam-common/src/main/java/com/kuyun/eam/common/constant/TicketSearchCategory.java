package com.kuyun.eam.common.constant;

public enum TicketSearchCategory {
    //我的未处理
    MY_OPEN("myOpen"),
    //我的已处理
    MY_RESOLVED("myResolved"),
    //我的全部
    MY_ALL("myAll"),
    //状态未完成
    OPEN("open"),
    //所有工单
    ALL("all"),
    //待派工
    INIT("init"),
    //维修中
    PROCESSING("processing"),
    //状态未完成
    NOTRESOLVED("notResolved"),
    //已完成
    RESOLVED("resolved"),
    //其他
    OTHER("other");

    private TicketSearchCategory(String name){
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public boolean equalTo (String name) {
        return this.name.equals(name);
    }

    public static TicketSearchCategory getCategroy (String name) {
        switch (name) {
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