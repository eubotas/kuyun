package com.kuyun.eam.rpc.api;

import java.util.List;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUserCompany;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiService {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO);

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);
    
    List<EamTicketVO> selectTicket(EamTicketExample example);

    Integer inTask(EamInventory inventory);

    Integer outTask(EamInventory inventory);

    List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO);

    Tree getCityTree(UpmsUserCompany company);

    List<SensorGroup> getSensorData(String equipmentId);

    int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus);

    Integer createAlarm(String targetUserId, EamAlarm alarm);

    Integer updateAlarm(String targetUserId, EamAlarm alarm);

    void handleAlarm(EamSensorData sensorData);

    void handleAlarmOffline(String deviceId);

    List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO);

    Long countEquipments(EamEquipmentVO eamEquipmentVO);

    int persistEquipment(UpmsUserCompany upmsUserCompany, EamEquipment equipment);

    void processData(String deviceId, Integer sensorId, String data);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId);



}
