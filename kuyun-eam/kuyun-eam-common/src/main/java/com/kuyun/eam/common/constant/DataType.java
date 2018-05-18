package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum DataType {

    ANALOG("analog", "模拟量", "运行数据", true),
    DIGITAL("digital", "开关量", "机组启停", false);

    private String code;
    private String name;
    private String label;
    private boolean hasHistoryData;

    DataType(String code, String name, String label, boolean hasHistoryData){
        this.code = code;
        this.name = name;
        this.label = label;
        this.hasHistoryData = hasHistoryData;
    }

    public static String getName(String code) {
        for (DataType c : DataType.values()) {
            if (c.getCode().equals(code) ) {
                return c.name;
            }
        }
        return null;
    }

    public static String getCode(String name) {
        for (DataType c : DataType.values()) {
            if (c.getName().equals(name) ) {
                return c.code;
            }
        }
        return null;
    }

    public static String getLabel(String code) {
        for (DataType c : DataType.values()) {
            if (c.getCode().equals(code) ) {
                return c.label;
            }
        }
        return null;
    }

    public static boolean hasHistoryData(String code) {
        for (DataType c : DataType.values()) {
            if (c.getCode().equals(code) ) {
                return c.hasHistoryData;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    public String getLabel() {
        return label;
    }
}
