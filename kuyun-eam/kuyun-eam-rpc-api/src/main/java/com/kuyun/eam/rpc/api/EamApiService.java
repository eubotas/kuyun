package com.kuyun.eam.rpc.api;

import java.util.HashMap;
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

    List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo);

    List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example);

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

    List<EamEquipmentVO> selectUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);
    List<EamEquipmentVO> selectUnConnectGrmEquipments(EamEquipmentVO eamEquipmentVO);

    Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);
    Long countUnConnectGrmEquipments(EamEquipmentVO eamEquipmentVO);

    int persistEquipment(UpmsUserCompany upmsUserCompany, EamEquipment equipment);

    void processData(String deviceId, Integer sensorId, String data);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(EamEquipmentModelPropertiesVO eamEquipmentModelPropertiesVO);
    Long countEquipmentModelProperties(EamEquipmentModelPropertiesVO eamEquipmentModelPropertiesVO);

    public int createTicketAppoint(EamTicketAppointedRecord record, EamTicket ticket);
    public int rejectTicketAppoint(EamTicketAppointedRecord ticketAppointRecord);
    public int deleteTicketAppoint(EamTicketAppointedRecordExample eamTicketAppointRecordExample, int ticketId);
    public int addTicketRecord(EamTicketRecord ticketRecord);
    public void completeTicket(EamTicketAssessment ticketAssessment, int[] ticketTag);
    public EamSummaryTicketItemVO summaryTicket(Integer companyId);

    List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo);
    List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo);
    EamMaintainPlanVO getMaintainPlan(Integer planId);

    List<EamTicketRejectRecordVO> getTicketRejectRecord(Integer ticketId);

    List<EamHomeStatusSummaryVO> getStatusSummaryRecord(Integer companyId);
    List<EamHomeSummaryVO> getSummaryRecord(Integer companyId);

    List<EamAlarmRemindVO> getUserAlarms(Integer userId);
}
