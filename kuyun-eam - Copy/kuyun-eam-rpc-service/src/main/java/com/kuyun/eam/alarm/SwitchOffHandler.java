package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

/**
 * Created by user on 2017-09-08.
 */
public class SwitchOffHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return AlarmType.SWITCH_OFF.getName();
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {
        boolean result = false;
        Boolean value = covertToBoolean(sensorData.getStringValue());
        if (value != null && !value.booleanValue()) {
            result = true;
        }
        return result;
    }
}
