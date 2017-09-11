package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

import java.math.BigDecimal;

/**
 * Created by user on 2017-09-08.
 */
public class ValAboveBelowHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {

        return AlarmType.VAL_ABOVE_BELOW.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("Y", String.valueOf(alarm.getLowerBound()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {
        boolean result = false;
        BigDecimal x = alarm.getUpperBound();
        BigDecimal y = alarm.getLowerBound();
        BigDecimal value = covertToBigDecimal(sensorData.getStringValue());
        // value great than X and less than Y
        if (value != null) {
            if (value.compareTo(x) == 1 && value.compareTo(y) == -1) {
                result = true;
            }
        }
        return result;
    }
}
