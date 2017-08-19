package com.kuyun.eam.common.constant;

public enum DataFormat {

	SIGNED_16("SIGNED_16", "16位 有符号数", 1),
    UNSIGNED_16("UNSIGNED_16", "16位 无符号数", 1),
    SIGNED_32("SIGNED_32", "32位 有符号数", 2),
    UNSIGNED_32("UNSIGNED_32", "32位 无符号数", 2),
    FLOAT_32("FLOAT_32", "32位 浮点型数", 2);

    private DataFormat(String code, String name, int quantity){
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }
    
    private String code;
    private String name;
    private int quantity;

    public static String getName(String code) {
        for (DataFormat c : DataFormat.values()) {
            if (c.getCode().equals(code) ) {
                return c.name;
            }
        }
        return null;
    }

    public static int getQuantity(String code) {
        for (DataFormat c : DataFormat.values()) {
            if (c.getCode().equals(code) ) {
                return c.quantity;
            }
        }
        return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
