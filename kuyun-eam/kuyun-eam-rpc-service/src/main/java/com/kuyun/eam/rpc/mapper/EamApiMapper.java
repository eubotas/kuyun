package com.kuyun.eam.rpc.mapper;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamTicketAppointedRecordExample;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.vo.*;

import java.util.List;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiMapper {

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);

    List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO);
    
    List<EamTicketVO> selectTicket(EamTicketExample example);

    List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo);

    List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example);

    List<EamSensorVO> selectSensorData(String equipmentId);

    EamAlarm selectAlarm(EamSensorData sensorData);

    List<EamAlarm> selectAlarms(String deviceId);

    List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO);

    List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO);

    Long countEquipments(EamEquipmentVO eamEquipmentVO);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId);

    List<EamEquipmentVO> selectUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);

    Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO);

    List<EamEquipmentVO> selectUnConnectGrmEquipments(EamEquipmentVO eamEquipmentVO);

    Long countUnConnectGrmEquipments(EamEquipmentVO eamEquipmentVO);

    List<EamSummaryTicketVO> summaryTicket(Integer companyId);

    List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo);
    long getMaintainPlanCount(EamMaintainPlanVO vo);

    List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo);

    EamMaintainPlanVO getMaintainPlan(Integer planId);

    List<EamTicketRejectRecordVO> getTicketRejectRecord(Integer ticketId);

    List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(EamEquipmentModelPropertiesVO eamEquipmentModelPropertiesVO);
    Long countEquipmentModelProperties(EamEquipmentModelPropertiesVO eamEquipmentModelPropertiesVO);

    List<EamHomeStatusSummaryVO> getStatusSummaryRecord(Integer companyId);
    List<EamHomeSummaryVO> getSummaryRecord(Integer companyId);
    List<EamAlarmRemindVO> getUserAlarms(EamAlarmRecordVO eamAlarmRecordVO);
    Long getUserAlarmsCount(EamAlarmRecordVO eamAlarmRecordVO);
}
