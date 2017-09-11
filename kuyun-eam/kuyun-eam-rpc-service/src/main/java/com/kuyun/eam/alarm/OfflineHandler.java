package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentModelProperties;
import com.kuyun.eam.dao.model.EamSensorData;

/**
 * Created by user on 2017-09-08.
 */
public class OfflineHandler extends AbstractAlarmHandler {
    @Override
    protected String buildMessage(EamSensorData sensorData, EamAlarm alarm) {

        StringBuilder stringBuilder = new StringBuilder();
        EamEquipment equipment = getEquipment(sensorData);
        EamEquipmentModelProperties eamEquipmentModelProperties = getEamEquipmentModelProperties(alarm);
        stringBuilder.append("报警内容：");
        stringBuilder.append(equipment.getName());
        stringBuilder.append("(");
        stringBuilder.append(equipment.getNumber());
        stringBuilder.append(")  ");
        stringBuilder.append(eamEquipmentModelProperties.getName() + "  ");
        stringBuilder.append(buildAlarmMessage(sensorData, alarm) + "<br/>");

        stringBuilder.append("报警时间：");

        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    @Override
    protected String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        return AlarmType.OFFLINE.getName();
    }

    @Override
    protected boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm) {
        return false;
    }
}
