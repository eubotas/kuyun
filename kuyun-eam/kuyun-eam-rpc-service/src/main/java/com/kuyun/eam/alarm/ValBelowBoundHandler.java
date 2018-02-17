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
public class ValBelowBoundHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm) {
        return AlarmType.VAL_BELOW_BOUND.getName().replace("Y", String.valueOf(alarm.getLowerBound()))
                .replace("M", String.valueOf(alarm.getDuration()));
    }

    @Override
    protected boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm) {
        boolean result = false;
        BigDecimal x = alarm.getUpperBound();
        BigDecimal y = alarm.getLowerBound();

        List<EamGrmVariableDataHistory> dataHistoryList = getGrmVariableDataHistories(variableData, alarm);
        for(EamGrmVariableDataHistory data : dataHistoryList){
            BigDecimal value = covertToBigDecimal(data.getValue());
            // value less than Y
            if (value != null) {
                if (value.compareTo(y) == -1) {
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
