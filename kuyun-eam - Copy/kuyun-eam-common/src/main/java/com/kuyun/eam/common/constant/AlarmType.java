package com.kuyun.eam.common.constant;

/**
 * Created by user on 2017-06-30.
 */
public enum AlarmType {

    VAL_ABOVE("val_above", "数值高于X"),
    VAL_BELOW("val_below", "数值低于Y"),
    VAL_BETWEEN("val_between", "数值在X和Y之间"),
    VAL_ABOVE_BELOW_OFM("val_above_below_ofm", "数值在X和Y之间超过M分钟"),
    VAL_ABOVE_BOUND("val_above_bound", "数值超过M分钟高于X"),
    VAL_BELOW_BOUND("val_below_bound", "数值超过M分钟低于Y"),
    OFFLINE("offline", "传感器断开"),
    SWITCH_ON("switch_on", "开关开启"),
    SWITCH_OFF("switch_off", "开关关闭");

//    OFFLINE_FOR_MINUTES("offline_for_minutes", "超过M分钟断开"),
//    X_TIR_Y_REC("x_tir_y_rec", "数值高于X报警低于Y恢复"),
//    VAL_ABOVE_BELOW("val_above_below", "数值高于X低于Y"),


    private String code;
    private String name;

    AlarmType(String code, String name){
        this.code = code;
        this.name = name;
    }



    public static String getName(String code) {
        for (AlarmType c : AlarmType.values()) {
            if (c.getCode().equals(code) ) {
                return c.name;
            }
        }
        return null;
    }

    public boolean match(String code){
        return this.getCode().equalsIgnoreCase(code);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
