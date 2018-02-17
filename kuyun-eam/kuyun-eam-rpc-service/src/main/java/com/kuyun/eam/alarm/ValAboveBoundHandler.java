package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamGrmVariableData;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by user on 2017-09-08.
 */
public class ValAboveBoundHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm) {
        return AlarmType.VAL_ABOVE_BOUND.getName().replace("X", String.valueOf(alarm.getUpperBound()))
                .replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm) {

        boolean result = false;
        BigDecimal x = alarm.getUpperBound();

        List<EamGrmVariableDataHistory> dataHistories = getGrmVariableDataHistories(variableData, alarm);
        for(EamGrmVariableDataHistory data : dataHistories){
            BigDecimal value = covertToBigDecimal(data.getValue());
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
