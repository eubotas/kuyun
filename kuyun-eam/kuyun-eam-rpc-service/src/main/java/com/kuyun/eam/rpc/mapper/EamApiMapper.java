package com.kuyun.eam.rpc.mapper;

import java.util.List;

import com.kuyun.eam.dao.model.*;
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

    List<EamAlarm> selectAlarmsByGrmVariable(EamGrmVariableData variableData);

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

    List<EamGrmVariableDataExtVO> selectEamGrmVariableData(EamGrmVariableDataVO eamGrmVariableDataVO);

    List<EamGrmVariableDataHistoryExtVO> selectEamGrmVariableDataHistories(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO);


    List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo);

    List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example);

    List<EamSummaryTicketVO> summaryTicket(Integer companyId);

    List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo);
    Long countMaintainPlans(EamMaintainPlanVO vo);

    List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo);

    EamMaintainPlanVO getMaintainPlan(Integer planId);

    List<EamTicketRejectRecordVO> getTicketRejectRecord(Integer ticketId);

    List<EamCodeValueVo> summaryIndustry();

    List<EamCodeValueVo> summaryProductLineType();

    List<EamCodeValueVo> summaryState();

    List<EamCountryValueVo> summaryCountry();

    List<EamIndustryValueVo> summaryIndustryAndCompanyName();

    List<EamGrmVariable> selectStatisticGrmVariable();

    List<EamOrderVO> selectOrders(EamOrderVO orderVO);

    long countOrders(EamOrderVO orderVO);

    List<EamGrmVariable> selectGrmVariableGroupByPeriod(String productLineId);

    List<EamGrmVariableVO> selectGrmVariables(EamGrmVariableVO variableVO);

    long countGrmVariables(EamGrmVariableVO variableVO);


}
