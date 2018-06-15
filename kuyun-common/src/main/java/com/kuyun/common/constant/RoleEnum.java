package com.kuyun.common.constant;

/**
 * Created by user on 2018-01-05.
 */
public enum RoleEnum {

    SUPER("super", "超级管理员", "拥有所有权限"),
    TICKETCREATE("ticketCreate", "工单提报", "拥有提报工单权限"),
    CUSTOMER_TICKETCREATE("customerTicketCreate", "工单提报", "拥有提报工单权限"),
    TICKETREPAIR("ticketRepair", "工单维修", "拥有维修工单权限"),
    CUSTOMER_TICKETREPAIR("customerTicketRepair", "工单维修", "拥有维修工单权限"),
    TICKETAPPOINT("ticketAppoint", "工单委派", "拥有委派工单权限"),
    CUSTOMER("customer", "客户", "拥有客户所有权限");

    private String name;
    private String title;
    private String description;

    RoleEnum(String name, String title, String description){
        this.name = name;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}