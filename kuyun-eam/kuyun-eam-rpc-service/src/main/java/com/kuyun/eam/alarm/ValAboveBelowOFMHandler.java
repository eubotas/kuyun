package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamSensorDataHistory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2017-09-08.
 */
public class ValAboveBelowOFMHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {

        return AlarmType.VAL_ABOVE_BELOW_OFM.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("Y", String.valueOf(alarm.getLowerBound()))
                .replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {

        boolean result = false;
        BigDecimal x = alarm.getUpperBound();
        BigDecimal y = alarm.getLowerBound();

        List<EamSensorDataHistory> sensorDataHistoryList = getSensorDataHistories(sensorData, alarm);
        for(EamSensorDataHistory data : sensorDataHistoryList){
            BigDecimal value = covertToBigDecimal(data.getStringValue());
            // value great than X and less than Y
            if (value != null) {
                if ((value.compareTo(x) == 1 && value.compareTo(y) == -1)) {
                    result = true;
                }else {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }



}
