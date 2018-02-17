package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.*;
import net.sf.json.JSONArray;

/**
 * Created by user on 2017-09-08.
 */
public class OfflineHandler extends AbstractAlarmHandler {
    @Override
    protected String buildEmailMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {

        EamProductLine productLine = getProductLine(variableData);
        EamEquipment equipment = getEquipment(variableData);
        EamDataElement dataElement = getEamDataElement(variableData);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("报警产线：");
        stringBuilder.append(productLine.getName() + "<br/>");

        if (equipment != null){
            stringBuilder.append("报警设备：");
            stringBuilder.append(equipment.getName());
            stringBuilder.append("(");
            stringBuilder.append(equipment.getNumber());
            stringBuilder.append(")  ");
        }

        stringBuilder.append(dataElement.getLableName() + "  ");
        stringBuilder.append(buildAlarmMessage(variableData, alarm) + "<br/>");

        String content = isClearMessage ? "清除时间: " : "报警时间：";
        stringBuilder.append(content);

        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    @Override
    protected String buildSmsMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {

        EamProductLine productLine = getProductLine(variableData);
        EamEquipment equipment = getEquipment(variableData);
        EamDataElement dataElement = getEamDataElement(variableData);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("报警产线：");
        stringBuilder.append(productLine.getName() + "\n");

        if (equipment != null){
            stringBuilder.append("报警设备：");
            stringBuilder.append(equipment.getName());
            stringBuilder.append("(");
            stringBuilder.append(equipment.getNumber());
            stringBuilder.append(")  \n");
        }

        stringBuilder.append("触发条件：");
        stringBuilder.append(dataElement.getLableName() + "  ");
        stringBuilder.append(buildAlarmMessage(variableData, alarm));

        String content = isClearMessage ? "\n报警清除时间：" : "\n报警时间：";
        stringBuilder.append(content);
        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    @Override
    protected String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm) {
        return AlarmType.OFFLINE.getName();
    }

    @Override
    protected boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm) {
        return false;
    }
}
