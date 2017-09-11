package com.kuyun.eam.alarm;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

/**
 * Created by user on 2017-09-08.
 */
public class OfflineForMinutesHandler extends OfflineHandler {

    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return null;
//        return AlarmType.OFFLINE_FOR_MINUTES.getName().replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {
        return false;
    }
}
