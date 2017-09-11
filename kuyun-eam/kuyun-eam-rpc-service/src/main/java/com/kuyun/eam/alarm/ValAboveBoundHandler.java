package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2017-09-08.
 */
public class ValAboveBoundHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return AlarmType.VAL_ABOVE_BOUND.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {

        boolean result = false;
        BigDecimal x = alarm.getUpperBound();

        List<EamSensorData> sensorDatas = getSensorDates(sensorData, alarm);
        for(EamSensorData data : sensorDatas){
            BigDecimal value = covertToBigDecimal(data.getStringValue());
            // value great than X
            if (value != null) {
                if ((value.compareTo(x) == 1)) {
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
