package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

import java.math.BigDecimal;

/**
 * Created by user on 2017-09-08.
 */
public class ValBelowHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return AlarmType.VAL_BELOW.getName().replace("Y", String.valueOf(alarm.getLowerBound()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {
        boolean result = false;
        BigDecimal y = alarm.getLowerBound();
        BigDecimal value = covertToBigDecimal(sensorData.getStringValue());
        // value less than Y
        if (value != null) {
            if (value.compareTo(y) == -1) {
                result = true;
            }
        }
        return result;
    }
}
