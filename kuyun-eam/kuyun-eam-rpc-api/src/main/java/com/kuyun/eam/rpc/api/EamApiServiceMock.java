package com.kuyun.eam.rpc.api;

import java.util.List;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 4/24/2017.
 */
public class EamApiServiceMock implements EamApiService {
    private static Logger _log = LoggerFactory.getLogger(EamApiServiceMock.class);


    @Override
    public List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO) {
        _log.info("EamApiServiceMock => selectMaintenance");
        return null;
    }

    @Override
    public List<EamLocationVO> selectLocation(EamLocationVO locationVO) {
        return null;
    }

    @Override
    public List<EamPartVO> selectPart(EamPartVO partVO) {
        return null;
    }

    @Override
    public List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO) {
        return null;
    }
    
    @Override
    public List<EamTicketVO> selectTicket(EamTicketExample example) {
    		return null;
    }

    @Override
    public List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO example) {
        return null;
    }

    @Override
    public List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example) {
        return null;
    }

    @Override
    public Integer inTask(EamInventory inventory) {
        return null;
    }

    @Override
    public Integer outTask(EamInventory inventory) {
        return null;
    }

    @Override
    public List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO) {
        return null;
    }

    @Override
    public Tree getCityTree(UpmsUserCompany company) {
        return null;
    }

    @Override
    public List<SensorGroup> getSensorData(String equipmentId) {
        return null;
    }

    @Override
    public int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus) {
        return 0;
    }

    @Override
    public Integer createAlarm(String targetUserId, EamAlarm alarm) {
        return null;
    }

    @Override
    public Integer updateAlarm(String targetUserId, EamAlarm alarm) {
        return null;
    }

    @Override
    public void handleAlarm(EamSensorData sensorData) {

    }

    @Override
    public void handleAlarmOffline(String deviceId) {

    }

    @Override
    public List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO) {
        return null;
    }

    @Override
    public Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO) {
        return null;
    }

    @Override
    public List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO) {
        return null;
    }

    @Override
    public Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO) {
        return null;
    }

    @Override
    public List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO) {
        return null;
    }

    @Override
    public Long countEquipments(EamEquipmentVO eamEquipmentVO) {
        return null;
    }

    @Override
    public List<EamEquipmentVO> selectUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO) {
        return null;
    }

    @Override
    public Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO) {
        return null;
    }

    @Override
    public int persistEquipment(UpmsUserCompany upmsUserCompany, EamEquipment equipment) {
        return 0;
    }

    @Override
    public void processData(String deviceId, Integer sensorId, String data) {

    }

    @Override
    public List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId) {
        return null;
    }

    @Override
    public int createTicketAppoint(EamTicketAppointedRecord record, EamTicket ticket) {
        return 0;
    }

    @Override
    public int rejectTicketAppoint(EamTicketAppointedRecord ticketAppointRecord) {
        return 0;
    }

    @Override
    public int deleteTicketAppoint(EamTicketAppointedRecordExample eamTicketAppointRecordExample, int ticketId) {
        return 0;
    }

}
