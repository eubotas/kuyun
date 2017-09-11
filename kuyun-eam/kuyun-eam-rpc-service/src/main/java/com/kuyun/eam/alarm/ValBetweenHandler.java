package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

/**
 * Created by user on 2017-09-08.
 */
public class ValBetweenHandler extends XTirYRecHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return AlarmType.VAL_BETWEEN.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("Y", String.valueOf(alarm.getLowerBound()));
    }


}
