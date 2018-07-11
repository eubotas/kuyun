package com.kuyun.common.util;

public class EquipmentSensorDataUtil {
    static String EQ_SENSOR="EQ_SENSOR";

    public static void saveData(String eqid,Integer sensorId,  String value){
        RedisUtil.set(EQ_SENSOR+"_"+eqid+"_"+sensorId, value);
    }

    public static String getData(String eqid,Integer sensorId){
        return RedisUtil.get(EQ_SENSOR+"_"+eqid+"_"+sensorId);
    }

    public static boolean isChangedData(String eqid,Integer sensorId, String val) {
        boolean isChnaged=false;
        String oldVal=getData(eqid, sensorId);
        if(oldVal == null && val !=null){
            saveData(eqid, sensorId, val);
            isChnaged=true;
        }else if (!oldVal.equals(val)){
            saveData(eqid, sensorId, val);
            isChnaged=true;
        }
        return isChnaged;
    }
}
