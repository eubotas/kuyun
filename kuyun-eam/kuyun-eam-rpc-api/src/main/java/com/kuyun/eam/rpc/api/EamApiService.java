package com.kuyun.eam.rpc.api;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.Position;
import com.kuyun.eam.pojo.Positions;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import javafx.util.Pair;

import java.util.List;

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

    int handleProductLineCollect(String jsonString, CollectStatus collectStatus);

    Integer createAlarm(String targetUserId, EamAlarm alarm);

    Integer updateAlarm(String targetUserId, EamAlarm alarm);

    void handleAlarm(EamGrmVariableData variableData);

    void handleAlarmOffline(String deviceId);

    List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO);

    Long countEquipments(EamEquipmentVO eamEquipmentVO);

    Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);

    int persistEquipment(UpmsUserCompany upmsUserCompany, EamEquipment equipment);

    void processData(String deviceId, Integer sensorId, String data);

    void processData(List<Pair<EamGrmVariable, String>> pairs);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId);


    List<EamGrmVariableVO> selectGrmVariables(String productLineId);


    int persistProductLine(EamProductLine eamProductLine, EamProductLineCompany productLineCompany);

    List<EamProductLineVO> selectProductLines(EamProductLineVO eamProductLine);

    Long countProductLines(EamProductLineVO eamProductLine);

    List<EamGrmVariable> getGrmVariables(String productLineId);

    List<EamDataElementVO> selectDataElements(EamDataElementVO dataElementVO);

    List<EamAlarmModelVO> selectAlarmModels(EamAlarmModelVO alarmModelVO);

    List<EamEquipmentDataGroupVO> selectEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO);

    long countEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO);

    List<EamAlarmVO> selectAlarms(EamAlarmVO alarmVO);

    long countAlarms(EamAlarmVO alarmVO);

    int createAlarms(String productLineId, String ids);

    int updateAlarm(String ids, String alarmTarget, String[] targetUserId);

    int handleProductLineDataElements(String productLineId, String dataElementIds);

    int handleEamEquipmentDataGroupElemets(String equipmentId, String dataGroupId, String equipmentDataGroupId, String ids);

    List<EamGrmVariableDataVO> selectEamGrmVariableData(EamGrmVariableDataVO eamGrmVariableDataVO);

    List<EamGrmVariableDataHistoryVO> selectEamGrmVariableDataHistories(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO);

    List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo);

    List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example);

    public int createTicketAppoint(EamTicketAppointedRecord record, EamTicket ticket);
    public int rejectTicketAppoint(EamTicketAppointedRecord ticketAppointRecord);
    public int deleteTicketAppoint(EamTicketAppointedRecordExample eamTicketAppointRecordExample, int ticketId);
    public int addTicketRecord(EamTicketRecord ticketRecord);
    public void completeTicket(EamTicketAssessment ticketAssessment, int[] ticketTag);
    public EamSummaryTicketItemVO summaryTicket(Integer companyId);

    int copyProductLine(String productLineId, String name, Integer companyId);

    int updatePositions(String productLineId, Positions positions);

    List<Position> getPositions(String productLineId);

    List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo);
    List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo);
    EamMaintainPlanVO getMaintainPlan(Integer planId);
}
