package com.kuyun.eam.admin.util;

/**
 * Created by user on 2017-06-11.
 */
public enum ProtocolEnum {
    MODBUS_RTU(1, "Modbus RTU"),
    MQTT(2, "MQTT");

    private ProtocolEnum(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static String getName(int index) {
        for (ProtocolEnum c : ProtocolEnum.values()) {
            if (c.getId() == index) {
                return c.name;
            }
        }
        return null;
    }
    
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
