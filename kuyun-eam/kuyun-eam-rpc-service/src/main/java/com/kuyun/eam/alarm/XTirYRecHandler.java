package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

import java.math.BigDecimal;

/**
 * Created by user on 2017-09-08.
 */
public class XTirYRecHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {

        return AlarmType.X_TIR_Y_REC.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("Y", String.valueOf(alarm.getLowerBound()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {

        boolean result = false;
        BigDecimal x = alarm.getUpperBound();
        BigDecimal y = alarm.getLowerBound();

        BigDecimal value = covertToBigDecimal(sensorData.getStringValue());
        // value less than X and great than Y
        if (value != null) {
            if (value.compareTo(x) == -1 && value.compareTo(y) == 1) {
                result = true;
            }
        }
        return result;
    }
}
