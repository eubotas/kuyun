package com.kuyun.eam.alarm;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamGrmVariableData;

/**
 * Created by user on 2017-09-08.
 */
public class OfflineForMinutesHandler extends OfflineHandler {

    @Override
    protected String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm) {
        return null;
//        return AlarmType.OFFLINE_FOR_MINUTES.getName().replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm) {
        return false;
    }
}
