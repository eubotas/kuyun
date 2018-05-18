package com.kuyun.eam.rpc.api;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.*;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

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
    public List<EamPartVO> selectParts(EamPartVO partVO) {
        return null;
    }

    @Override
    public Long countParts(EamPartVO partVO) {
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
    public Integer inTask(EamInventory inventory) {
        return null;
    }

    @Override
    public Integer outTask(EamInventory inventory) {
        return null;
    }



    @Override
    public Tree getCityTree(UpmsUserCompany company) {
        return null;
    }


    @Override
    public int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus) {
        return 0;
    }

    @Override
    public int handleProductLineCollect(String jsonString, CollectStatus collectStatus) {
        return 0;
    }

    @Override
    public Integer createAlarm(String targetUserId, EamAlarm alarm) {
        return null;
    }

    @Override
    public Integer updateAlarm(String[] targetUserIds, EamAlarm alarm) {
        return null;
    }

    @Override
    public void handleAlarm(EamGrmVariableData variableData) {

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
    public Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO) {
        return null;
    }




    @Override
    public void processData(List<Pair<EamGrmVariable, String>> pairs) {

    }

    @Override
    public List<EamGrmVariableVO> selectGrmVariables(String productLineId) {
        return null;
    }

    @Override
    public int persistProductLine(EamProductLine eamProductLine, EamProductLineCompany productLineCompany) {
        return 0;
    }

    @Override
    public List<EamProductLineVO> selectProductLines(EamProductLineVO eamProductLine) {
        return null;
    }

    @Override
    public Long countProductLines(EamProductLineVO eamProductLine) {
        return null;
    }

    @Override
    public List<EamGrmVariable> getGrmVariables(String productLineId) {
        return null;
    }

    @Override
    public List<EamDataElementVO> selectDataElements(EamDataElementVO dataElementVO) {
        return null;
    }

    @Override
    public List<EamAlarmModelVO> selectAlarmModels(EamAlarmModelVO alarmModelVO) {
        return null;
    }

    @Override
    public List<EamEquipmentDataGroupVO> selectEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO) {
        return null;
    }

    @Override
    public long countEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO) {
        return 0;
    }

    @Override
    public List<EamAlarmVO> selectAlarms(EamAlarmVO alarmVO) {
        return null;
    }

    @Override
    public long countAlarms(EamAlarmVO alarmVO) {
        return 0;
    }

    @Override
    public int createAlarms(String productLineId, String ids) {
        return 0;
    }

    @Override
    public int updateAlarm(String ids, String alarmTarget, String[] targetUserId) {
        return 0;
    }

    @Override
    public int handleProductLineDataElements(String productLineId, String dataElementIds) {
        return 0;
    }

    @Override
    public int handleEamEquipmentDataGroupElemets(String equipmentId, String dataGroupId, String equipmentDataGroupId, String ids) {
        return 0;
    }

    @Override
    public List<EamGrmVariableDataExtVO> selectEamGrmVariableData(EamGrmVariableDataVO eamGrmVariableDataVO) {
        return null;
    }

    @Override
    public List<EamGrmVariableDataHistoryExtVO> selectEamGrmVariableDataHistories(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        return null;
    }

    @Override
    public List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo) {
        return null;
    }

    @Override
    public List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example) {
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

    @Override
    public int addTicketRecord(EamTicketRecord ticketRecord) {
        return 0;
    }

    @Override
    public void completeTicket(EamTicketAssessment ticketAssessment, int[] ticketTag) {

    }

    @Override
    public EamSummaryTicketItemVO summaryTicket(Integer companyId) {
        return null;
    }

    @Override
    public int copyProductLine(String productLineId, String name, Integer companyId) {
        return 0;
    }

    @Override
    public int updatePositions(String productLineId, Positions positions) {
        return 0;
    }

    @Override
    public List<Position> getPositions(String productLineId) {
        return null;
    }

    @Override
    public List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo) {
        return null;
    }

    @Override
    public Long countMaintainPlans(EamMaintainPlanVO vo) {
        return null;
    }

    @Override
    public List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo) {
        return null;
    }

    @Override
    public EamMaintainPlanVO getMaintainPlan(Integer planId) {
        return null;
    }

    @Override
    public List<EamTicketRejectRecordVO> getTicketRejectRecord(Integer ticketId) {
        return null;
    }

    @Override
    public int deleteDataElements(String ids) {
        return 0;
    }

    @Override
    public int createCustomer(UpmsCompany companyVo) {
        return 0;
    }

    @Override
    public List<EamGrmVariableDataHistoryExtVO> getGrmVariableHistoryData(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        return null;
    }

    @Override
    public List<GanttData> getGanttData(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        return null;
    }

    @Override
    public List<EamCodeValue> getCodeValues(String category) {
        return null;
    }

    @Override
    public EamCodeValue getCodeValue(String category, String codeValue) {
        return null;
    }

    @Override
    public List<EamCodeValueVo> summaryIndustry() {
        return null;
    }

    @Override
    public List<EamCodeValueVo> summaryProductLineType() {
        return null;
    }

    @Override
    public List<EamCodeValueVo> summaryState() {
        return null;
    }

    @Override
    public List<EamCountryValueVo> summaryCountry() {
        return null;
    }

    @Override
    public HashMap summaryIndustryAndCompanyName() {
        return null;
    }

    @Override
    public void importCompanyData(List<CompanyBean> companyBeanList, UpmsUserCompany currentCompany) {

    }

    @Override
    public void importOrderData(List<OrderBean> orderBeanList) {

    }

    @Override
    public void statisticJob() {

    }

    @Override
    public void importPartData(List<PartBean> list, UpmsUserCompany company, EamEquipment equipment) {

    }

    @Override
    public List<EamOrderVO> selectOrders(EamOrderVO orderVO) {
        return null;
    }

    @Override
    public long countOrders(EamOrderVO orderVO) {
        return 0;
    }

    @Override
    public List<Pair<Integer, List<EamGrmVariable>>> selectGrmVariableGroupByPeriod(String productLineId) {
        return null;
    }

    @Override
    public List<EamGrmVariableVO> selectGrmVariables(EamGrmVariableVO variableVO) {
        return null;
    }

    @Override
    public long countGrmVariables(EamGrmVariableVO variableVO) {
        return 0;
    }

    @Override
    public void importDataElement(List<DataElementBean> list, UpmsUserCompany company) {

    }


}
