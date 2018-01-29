package com.kuyun.eam.rpc.mapper;

import java.util.List;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.vo.*;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiMapper {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO);

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);

    List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO);
    
    List<EamTicketVO> selectTicket(EamTicketExample example);

    List<EamSensorVO> selectSensorData(String equipmentId);

    EamAlarm selectAlarm(EamSensorData sensorData);

    //List<EamAlarm> selectAlarms(String deviceId);

    List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO);

    Long countEquipments(EamEquipmentVO eamEquipmentVO);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId);

    Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);

    List<EamProductLineVO> selectProductLines(EamProductLine eamProductLine);

    Long countProductLines(EamProductLine eamProductLine);

    List<EamDataElementVO> selectDataElements(EamDataElementVO dataElementVO);

    List<EamAlarmModelVO> selectAlarmModels(EamAlarmModelVO alarmModelVO);

    List<EamEquipmentDataGroupVO> selectEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO);

    long countEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO);

    List<EamAlarmVO> selectAlarms(EamAlarmVO alarmVO);

    long countAlarms(EamAlarmVO alarmVO);
}
