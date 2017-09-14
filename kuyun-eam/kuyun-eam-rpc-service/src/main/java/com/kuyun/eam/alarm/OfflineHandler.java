package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentModelProperties;
import com.kuyun.eam.dao.model.EamSensorData;
import net.sf.json.JSONArray;

/**
 * Created by user on 2017-09-08.
 */
public class OfflineHandler extends AbstractAlarmHandler {
    @Override
    protected String buildEmailMessage(EamSensorData sensorData, EamAlarm alarm) {

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

    protected String buildSmsMessage(EamSensorData sensorData, EamAlarm alarm) {

        EamEquipment equipment = getEquipment(sensorData);
        EamEquipmentModelProperties eamEquipmentModelProperties = getEamEquipmentModelProperties(alarm);

        JSONArray msg = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(equipment.getName());
        stringBuilder.append("(");
        stringBuilder.append(equipment.getNumber());
        stringBuilder.append(")  ");
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(eamEquipmentModelProperties.getName() + "  ");
        stringBuilder.append(buildAlarmMessage(sensorData, alarm));
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(getCurrentTimeStamp());
        msg.add(stringBuilder.toString());

        return msg.toString();
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
