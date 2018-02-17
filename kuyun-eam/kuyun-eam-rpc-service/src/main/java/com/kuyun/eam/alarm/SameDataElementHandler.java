package com.kuyun.eam.alarm;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamGrmVariableData;

/**
 * Created by user on 2017-09-08.
 */
public class SameDataElementHandler extends AbstractAlarmHandler {
    @Override
    protected String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm) {
        return "";
    }

    @Override
    protected boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm) {
        return variableData.getDataElementId().equals(alarm.getEamDataElementId());
    }
}
