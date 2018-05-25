package com.kuyun.eam.rpc.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.google.gson.Gson;
import com.kuyun.common.util.NumberUtil;
import com.kuyun.common.util.RedisUtil;
import com.kuyun.eam.alarm.AbstractAlarmHandler;
import com.kuyun.eam.alarm.AlarmTypeFactory;
import com.kuyun.eam.alarm.OfflineHandler;
import com.kuyun.eam.common.constant.*;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.*;
import com.kuyun.eam.pojo.tree.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.rpc.mapper.EamApiMapper;
import com.kuyun.eam.util.AreaUtil;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.*;
import com.kuyun.grm.rpc.api.GrmApiService;
import com.kuyun.modbus.rpc.api.ModbusSlaveRtuApiService;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import javafx.util.Pair;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.kuyun.eam.common.constant.CollectStatus.NO_START;
import static com.kuyun.eam.common.constant.CollectStatus.WORKING;
import static com.kuyun.eam.util.EamDateUtil.getDateStr;
import static com.kuyun.eam.util.EamDateUtil.getShiftStartEndTime;


/**
 * Created by user on 4/24/2017.
 */
@Service
@Transactional
public class EamApiServiceImpl implements EamApiService {
    private static Logger _log = LoggerFactory.getLogger(EamApiServiceImpl.class);


    @Autowired
    EamApiMapper eamApiMapper;

    @Autowired
    EamInventoryService inventoryService;

    @Autowired
    EamEquipmentService eamEquipmentService;

    @Autowired
    AreaUtil areaUtil;

    @Autowired
    private GrmApiService grmApiService;

    @Autowired
    private EamAlarmService eamAlarmService;

    @Autowired
    private EamAlarmTargetUserService eamAlarmTargetUserService;

    @Autowired
    private AlarmTypeFactory alarmTypeFactory;

    @Autowired
    private OfflineHandler offlineHandler;

    @Autowired
    private EamProductLineService eamProductLineService;

    @Autowired
    private EamProductLineCompanyService eamProductLineCompanyService;

    @Autowired
    private EamGrmVariableService eamGrmVariableService;

    @Autowired
    private EamGrmVariableDataService eamGrmVariableDataService;

    @Autowired
    private EamGrmVariableDataHistoryService eamGrmVariableDataHistoryService;

    @Autowired
    private EamAlarmModelService eamAlarmModelService;

    @Autowired
    private EamEquipmentDataGroupElemetsService eamEquipmentDataGroupElemetsService;

    @Autowired
    private EamProductLineDataElementService eamProductLineDataElementService;

    @Autowired
    private EamDataElementService eamDataElementService;

    @Autowired
    private EamTicketService eamTicketService;

    @Autowired
    private EamTicketAppointedRecordService eamTicketAppointRecordService;

    @Autowired
    private EamTicketRecordService eamTicketRecordService;

    @Autowired
    private EamTicketAssessmentService eamTicketAssessmentService;

    @Autowired
    private EamEquipmentDataGroupService eamEquipmentDataGroupService;

    @Autowired
    private EamDataElementService dataElementService;

    @Autowired
    private UpmsCompanyService upmsCompanyService;

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private EamCodeValueService eamCodeValueService;

    @Autowired
    private EamGrmVariableDataByDayService eamGrmVariableDataByDayService;

    @Autowired
    private EamGrmVariableDataByMonthService eamGrmVariableDataByMonthService;

    @Autowired
    private  EamGrmVariableDataByYearService eamGrmVariableDataByYearService;

    @Autowired
    private EamPartsService eamPartsService;

    @Autowired
    private EamPartsCategoryService eamPartsCategoryService;

    @Autowired
    private EamOrderService eamOrderService;

    private HashMap<String, Integer> categoryMap = new HashMap<>();

    private HashMap<String, Integer> equipmentCategoryMap = new HashMap<>();

    @Autowired
    private EamEquipmentCategoryService eamEquipmentCategoryService;

    @Autowired
    private  EamShiftDataElementValueService eamShiftDataElementValueService;

    @Override
    public List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO) {

        return eamApiMapper.selectMaintenance(maintenanceVO);
    }

    @Override
    public List<EamLocationVO> selectLocation(EamLocationVO locationVO) {
        return eamApiMapper.selectLocation(locationVO);
    }

    @Override
    public List<EamPartVO> selectParts(EamPartVO partVO) {
        return eamApiMapper.selectParts(partVO);
    }

    @Override
    public Long countParts(EamPartVO partVO) {
        return eamApiMapper.countParts(partVO);
    }


    @Override
    public List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO) {
        return eamApiMapper.selectInventory(inventoryVO);
    }

    @Override
    public List<EamTicketVO> selectTicket(EamTicketExample example) {
        return eamApiMapper.selectTicket(example);
    }

    @Override
    public Integer inTask(EamInventory eamInventory) {
        EamInventory inventory = getInventory(eamInventory);
        if (inventory != null) {
            inventory.setQuantity(eamInventory.getQuantity().add(inventory.getQuantity()));
            return inventoryService.updateByPrimaryKeySelective(inventory);
        } else {
            return inventoryService.insertSelective(eamInventory);
        }

    }

    private EamInventory getInventory(EamInventory eamInventory) {
        EamInventoryExample example = new EamInventoryExample();
        example.createCriteria().andWarehouseIdEqualTo(eamInventory.getWarehouseId())
                .andLocationIdEqualTo(eamInventory.getLocationId())
                .andPartIdEqualTo(eamInventory.getPartId());
        return inventoryService.selectFirstByExample(example);
    }

    @Override
    public Integer outTask(EamInventory eamInventory) {
        EamInventory inventory = getInventory(eamInventory);
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity().subtract(eamInventory.getQuantity()));
            return inventoryService.updateByPrimaryKeySelective(inventory);
        } else {
            _log.warn("There is no inventory for " + eamInventory.getPartId());
            return 1;
        }

    }

    @Override
    public Tree getCityTree(UpmsUserCompany company) {
        Tree tree = new Tree();
        List<EamProductLineVO> productLines = getProductLines(company);

        Map<String, List<EamProductLine>> groupByProvinceMap =
                productLines.stream().filter(productLine -> productLine.getProvince() != null).collect(Collectors.groupingBy(EamProductLine::getProvince));

        for (Map.Entry<String, List<EamProductLine>> entry : groupByProvinceMap.entrySet()) {
            ProvinceData provinceData = buildProvinceData(entry);
            tree.addProvice(provinceData);

            Map<String, List<EamProductLine>> groupByCityMap =
                    entry.getValue().stream().collect(Collectors.groupingBy(EamProductLine::getCity));

            for (Map.Entry<String, List<EamProductLine>> cityEntry : groupByCityMap.entrySet()) {
                CityData cityData = buildCityData(cityEntry);
                provinceData.addChildren(cityData);

                for (EamProductLine productLine : cityEntry.getValue()) {
                    ProductLine p = buildProductLine(productLine);
                    cityData.addChildren(p);
                }

            }
        }
        return tree;
    }

    private ProductLine buildProductLine(EamProductLine productLine){
        ProductLine result = new ProductLine();
        result.setId(productLine.getProductLineId());
        result.setName(productLine.getName());

        List<EamEquipmentVO> equipments = getEquipments(result);
        for(EamEquipmentVO equipmentVO : equipments){
            Device device = buildDevice(equipmentVO);
            result.addChildren(device);
        }
        return result;
    }

    private Device buildDevice(EamEquipment equipment) {
        Device device = new Device();
        device.setProductLineId(equipment.getProductLineId());
        device.setId(equipment.getEquipmentId());
        device.setName(equipment.getName());
        return device;
    }

    private CityData buildCityData(Map.Entry<String, List<EamProductLine>> entry) {
        List<EamProductLine> productLines = entry.getValue();
        int total = productLines.size();
        long online = productLines.stream().filter(productLine -> productLine.getIsOnline() != null)
                .filter(productLine -> productLine.getIsOnline()).count();
        String city = entry.getKey();
        String name = getCityName(city);

        BigDecimal latitude = getLatitude(productLines);
        BigDecimal longitude = getLongitude(productLines);

        CityData cityData = new CityData();
        cityData.setTotal(total);
        cityData.setOnline((int) online);
        cityData.setCode(city);
        cityData.setName(name);
        cityData.setLatitude(latitude);
        cityData.setLongitude(longitude);
        return cityData;
    }

    private BigDecimal getLongitude(List<EamProductLine> productLines) {
        BigDecimal result = null;
        if (!productLines.isEmpty()) {
            result = productLines.get(0).getLongitude();
        }
        return result;
    }

    private BigDecimal getLatitude(List<EamProductLine> productLines) {
        BigDecimal result = null;
        if (!productLines.isEmpty()) {
            result = productLines.get(0).getLatitude();
        }
        return result;
    }

    private String getCityName(String city) {
        return areaUtil.getName(city);
    }

    private String getProvinceName(String province) {
        return areaUtil.getName(province);
    }

    private ProvinceData buildProvinceData(Map.Entry<String, List<EamProductLine>> entry) {
        String province = entry.getKey();
        List<EamProductLine> productLines = entry.getValue();
        int total = productLines.size();
        long online = productLines.stream().filter(productLine -> productLine.getIsOnline() != null)
                .filter(productLine -> productLine.getIsOnline()).count();
        String name = getProvinceName(province);

        ProvinceData provinceData = new ProvinceData();
        provinceData.setTotal(total);
        provinceData.setCode(province);
        provinceData.setName(name);
        provinceData.setOnline((int) online);
        return provinceData;
    }


    private List<EamEquipmentVO> getEquipments(UpmsUserCompany company) {
        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setDeleteFlag(Boolean.FALSE);
        equipmentVO.setCompanyId(company.getCompanyId());
        equipmentVO.setOrderByClause("province, city asc");
        return selectEquipments(equipmentVO);
    }

    private List<EamEquipmentVO> getEquipments(ProductLine productLine) {
        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setDeleteFlag(Boolean.FALSE);
        equipmentVO.setProductLineId(productLine.getId());
        equipmentVO.setOrderByClause("create_time asc");
        return selectEquipments(equipmentVO);
    }

    private List<EamProductLineVO> getProductLines(UpmsUserCompany company){
        EamProductLineVO productLineVO = new EamProductLineVO();
        productLineVO.setDeleteFlag(Boolean.FALSE);
        productLineVO.setCompanyId(company.getCompanyId());
        productLineVO.setOrderByClause("province, city asc");

        return selectProductLines(productLineVO);

    }

    @Override
    public int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus) {

        _log.info("json=" + jsonString);

        Gson gson = new Gson();
        IDS ids = gson.fromJson(jsonString, IDS.class);

        String[] idArray = ids.getIds().split("-");
        EamEquipment equipment = new EamEquipment();
        equipment.setCollectStatus(collectStatus.getCode());

        if (NO_START.getCode().equalsIgnoreCase(collectStatus.getCode())){
            equipment.setIsOnline(false);
        }

        EamEquipmentExample example = new EamEquipmentExample();
        example.createCriteria().andEquipmentIdIn(Arrays.asList(idArray));

        handleCollectAction(collectStatus, example);

        return eamEquipmentService.updateByExampleSelective(equipment, example);
    }

    @Override
    public int handleProductLineCollect(String jsonString, CollectStatus collectStatus) {

        _log.info("json=" + jsonString);

        Gson gson = new Gson();
        IDS ids = gson.fromJson(jsonString, IDS.class);

        String[] idArray = ids.getIds().split("-");
        EamProductLine productLine = new EamProductLine();
        productLine.setCollectStatus(collectStatus.getCode());

        if (NO_START.getCode().equalsIgnoreCase(collectStatus.getCode())){
            productLine.setIsOnline(false);
        }

        EamProductLineExample example = new EamProductLineExample();
        example.createCriteria().andProductLineIdIn(Arrays.asList(idArray));

        handleCollectAction(collectStatus, example);

        return eamProductLineService.updateByExampleSelective(productLine, example);
    }

    private void handleCollectAction(CollectStatus collectStatus, EamProductLineExample example) {
        List<EamProductLine> productLines = eamProductLineService.selectByExample(example);
        // handle GRM
        handleGRMAction(collectStatus, productLines);

    }


    private void handleCollectAction(CollectStatus collectStatus, EamEquipmentExample example) {
        List<EamEquipment> eamEquipments = eamEquipmentService.selectByExample(example);
        // handle GRM
        handleGRMActionForEquipment(collectStatus, eamEquipments);

    }



    private void handleGRMActionForEquipment(CollectStatus collectStatus, List<EamEquipment> eamEquipments) {
        eamEquipments.forEach(equipment -> {
            try {
                handleGrmAction(collectStatus, equipment);
            } catch (SchedulerException e) {
                _log.error("GRM action error:" + e.getMessage());
            }
        });

    }

    private void handleGRMAction(CollectStatus collectStatus, List<EamProductLine> productLines) {
        productLines.forEach(productLine -> {
            try {
                handleGrmAction(collectStatus, productLine);
            } catch (SchedulerException e) {
                _log.error("GRM action error:" + e.getMessage());
            }
        });

    }

    private void handleGrmAction(CollectStatus collectStatus, EamProductLine productLine) throws SchedulerException {
        if (NO_START.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            grmApiService.pauseJob(productLine.getProductLineId());
        } else if (WORKING.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            grmApiService.startJob(productLine.getProductLineId());
        }
    }

    private void handleGrmAction(CollectStatus collectStatus, EamEquipment equipment) throws SchedulerException {
        if (NO_START.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            grmApiService.pauseJob(equipment.getEquipmentId());
        } else if (WORKING.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            grmApiService.startJob(equipment.getEquipmentId());
        }
    }

    @Override
    public Integer createAlarm(String targetUserId, EamAlarm alarm) {
        eamAlarmService.insertSelective(alarm);
        Integer alarmId = alarm.getAlarmId();
        createEamAlarmTargetUser(alarmId, Integer.valueOf(targetUserId));
        return alarmId;
    }

    private void createEamAlarmTargetUser(int alarmId, int userId) {
        EamAlarmTargetUser result = new EamAlarmTargetUser();
        result.setAlarmId(alarmId);
        result.setUserId(userId);
        result.setDeleteFlag(Boolean.FALSE);
        result.setCreateTime(new Date());
        result.setUpdateTime(new Date());
        eamAlarmTargetUserService.insertSelective(result);
    }

    @Override
    public Integer updateAlarm(String[] targetUserIds, EamAlarm alarm) {
        int result = eamAlarmService.updateByPrimaryKeySelective(alarm);

        EamAlarmTargetUserExample example = new EamAlarmTargetUserExample();
        example.createCriteria().andAlarmIdEqualTo(alarm.getAlarmId());
        eamAlarmTargetUserService.deleteByExample(example);

        if (targetUserIds != null && targetUserIds.length > 0){
            //create new data
            List<EamAlarmTargetUser> targetUsers = createEamAlarmTargetUsers(targetUserIds, alarm);
            eamAlarmTargetUserService.batchInsert(targetUsers);
        }

        return result;
    }

    private void updateEamAlarmTargetUser(int alarmId, int userId) {
        EamAlarmTargetUserExample example = new EamAlarmTargetUserExample();
        example.createCriteria().andAlarmIdEqualTo(alarmId);
        eamAlarmTargetUserService.deleteByExample(example);

        createEamAlarmTargetUser(alarmId, userId);
    }

    @Override
    public void handleAlarm(EamGrmVariableData variableData) {
        List<EamAlarm> alarms = eamApiMapper.selectAlarmsByGrmVariable(variableData);
        if (alarms != null) {
            for(EamAlarm alarm : alarms){
                AbstractAlarmHandler alarmHandler = alarmTypeFactory.buildAlarmHandler(alarm);
                if (alarmHandler != null){
                    alarmHandler.process(variableData, alarm);
                }
            }
        }
    }

    @Override
    public void handleAlarmOffline(String productLineId){
        EamAlarm alarm = getOfflineAlarmType(productLineId);
        if (alarm != null){
            EamGrmVariableData variableData = new EamGrmVariableData();
            variableData.setProductLineId(productLineId);

            offlineHandler.createAlarmRecord(variableData, alarm);
            offlineHandler.sendAlarmMessage(variableData, alarm, false);
        }
    }

    private EamAlarm getOfflineAlarmType(String productLineId){
        EamAlarm result = null;
        EamAlarmVO vo = new EamAlarmVO();
        vo.setProductLineId(productLineId);

        List<EamAlarmVO> alarms = eamApiMapper.selectAlarms(vo);
        if (alarms != null && !alarms.isEmpty()){
            Optional<EamAlarmVO> optional = alarms.stream()
                    .filter(eamAlarm -> AlarmType.OFFLINE.match(eamAlarm.getAlarmType())).findFirst();

            if (optional.isPresent()){
                result = optional.get();
            }
        }

        return result;
    }

    @Override
    public List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO){
        return eamApiMapper.selectAlarmRecords(eamAlarmRecordVO);
    }

    @Override
    public Long countAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO) {
        return eamApiMapper.countAlarmRecords(eamAlarmRecordVO);
    }

    @Override
    public List<EamAlarmRecordVO> selectAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO){
        return eamApiMapper.selectAlarmRecordHistoies(eamAlarmRecordVO);
    }

    @Override
    public Long countAlarmRecordHistoies(EamAlarmRecordVO eamAlarmRecordVO) {
        return eamApiMapper.countAlarmRecordHistoies(eamAlarmRecordVO);
    }

    @Override
    public List<EamEquipmentVO> selectEquipments(EamEquipmentVO eamEquipmentVO) {
        return eamApiMapper.selectEquipments(eamEquipmentVO);
    }

    @Override
    public Long countEquipments(EamEquipmentVO eamEquipmentVO) {
        return eamApiMapper.countEquipments(eamEquipmentVO);
    }



    @Override
    public Long countUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO) {
        return eamApiMapper.countUnConnectDtuEquipments(eamEquipmentVO);
    }

    @Override
    public void processData(List<Pair<EamGrmVariable, String>> pairs) {
        for (Pair<EamGrmVariable, String> pair : pairs){
            EamGrmVariableData variableData =  handleGrmVariableData(pair);
            handleAlarm(variableData);
        }
        handleGrmVariableDataHistory(pairs);
    }

    private void handleGrmVariableDataHistory(List<Pair<EamGrmVariable, String>> pairs) {
        List<EamGrmVariableDataHistory> dataHistoryList = new ArrayList<>();
        for(Pair<EamGrmVariable, String> pair : pairs){
            EamGrmVariableDataHistory dataHistory = createGrmVariableHistoryData(pair);
            dataHistoryList.add(dataHistory);
        }
        if(!dataHistoryList.isEmpty()){
            eamGrmVariableDataHistoryService.batchInsert(dataHistoryList);
            //shift data
            // processShiftData(dataHistoryList);
        }
    }

    private EamGrmVariableData handleGrmVariableData(Pair<EamGrmVariable, String> pair) {
        EamGrmVariable variable = pair.getKey();
        String value = pair.getValue();

        EamGrmVariableDataExample example = new EamGrmVariableDataExample();
        EamGrmVariableDataExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        criteria.andEamGrmVariableIdEqualTo(variable.getId());

        EamGrmVariableData variableData = eamGrmVariableDataService.selectFirstByExample(example);
        if (variableData == null){
            variableData = createGrmVariableData(variable, value);
            eamGrmVariableDataService.insertSelective(variableData);
        }else {
            Date now = new Date();
            variableData.setCreateTime(now);
            variableData.setUpdateTime(now);
            variableData.setValue(value);
            eamGrmVariableDataService.updateByPrimaryKeySelective(variableData);
        }
        return variableData;

    }

    private EamGrmVariableData createGrmVariableData(EamGrmVariable variable, String value) {
        EamGrmVariableData result = new EamGrmVariableData();
        result.setEamGrmVariableId(variable.getId());
        result.setEquipmentId(variable.getEquipmentId());
        result.setProductLineId(variable.getProductLineId());
        result.setDataGroupId(variable.getDataGroupId());
        result.setEquipmentDataGroupId(variable.getEquipmentDataGroupId());
        result.setDataElementId(variable.getDataElementId());
        result.setValue(value);
        Date now = new Date();
        result.setCreateTime(now);
        result.setUpdateTime(now);
        result.setDeleteFlag(Boolean.FALSE);
        return result;
    }

    private EamGrmVariableDataHistory createGrmVariableHistoryData(Pair<EamGrmVariable, String> pair) {
        EamGrmVariableDataHistory result = new EamGrmVariableDataHistory();
        result.setEamGrmVariableId(pair.getKey().getId());
        result.setEquipmentId(pair.getKey().getEquipmentId());
        result.setProductLineId(pair.getKey().getProductLineId());
        result.setDataGroupId(pair.getKey().getDataGroupId());
        result.setEquipmentDataGroupId(pair.getKey().getEquipmentDataGroupId());
        result.setDataElementId(pair.getKey().getDataElementId());
        result.setValue(pair.getValue());
        Date now = new Date();
        result.setCreateTime(now);
        result.setUpdateTime(now);
        result.setDeleteFlag(Boolean.FALSE);
        return result;
    }

    @Override
    public List<EamGrmVariableVO> selectGrmVariables(String productLineId) {
        List<EamGrmVariableVO> result = new ArrayList<>();
        try {
            result = grmApiService.getAllVariable(productLineId);
        } catch (IOException e) {
            _log.error("Select Grm Equipment Variables Error: {}", e.getMessage());
        }
        return result;
    }

    @Override
    public List<EamProductLineVO> selectProductLines(EamProductLineVO eamProductLine) {
        return eamApiMapper.selectProductLines(eamProductLine);
    }

    @Override
    public Long countProductLines(EamProductLineVO eamProductLine) {
        return eamApiMapper.countProductLines(eamProductLine);
    }

    @Override
    public List<EamProductLineVO> selectUnAuthProductLines(EamProductLineVO eamProductLine) {
        return eamApiMapper.selectUnAuthProductLines(eamProductLine);
    }

    @Override
    public Long countUnAuthProductLines(EamProductLineVO eamProductLine) {
        return eamApiMapper.countUnAuthProductLines(eamProductLine);
    }

    @Override
    public List<EamGrmVariable> getGrmVariables(String productLineId) {
        List<EamGrmVariable> result = new ArrayList<>();
        //1. get Product Line variables
        EamGrmVariableExample example = new EamGrmVariableExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);
        if (variables != null && !variables.isEmpty()){
            result.addAll(variables);
        }

        return result;
    }

    @Override
    public List<EamDataElementVO> selectDataElements(EamDataElementVO dataElementVO) {
        return eamApiMapper.selectDataElements(dataElementVO);
    }

    @Override
    public List<EamAlarmModelVO> selectAlarmModels(EamAlarmModelVO alarmModelVO) {
        return eamApiMapper.selectAlarmModels(alarmModelVO);
    }

    @Override
    public List<EamEquipmentDataGroupVO> selectEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO) {
        return eamApiMapper.selectEquipmentDataGroups(dataGroupVO);
    }

    @Override
    public long countEquipmentDataGroups(EamEquipmentDataGroupVO dataGroupVO) {
        return eamApiMapper.countEquipmentDataGroups(dataGroupVO);
    }

    @Override
    public List<EamAlarmVO> selectAlarms(EamAlarmVO alarmVO) {
        return eamApiMapper.selectAlarms(alarmVO);
    }

    @Override
    public long countAlarms(EamAlarmVO alarmVO) {
        return eamApiMapper.countAlarms(alarmVO);
    }

    @Override
    public int createAlarms(String productLineId, String ids) {
        int result = 1;
        String[] idArray = ids.split("::");
        List<Integer> idList = new ArrayList<>();
        for(String idStr : idArray){
            idList.add(Integer.valueOf(idStr));
        }
        if (!idList.isEmpty()){
            //get alarm models
            EamAlarmModelExample example = new EamAlarmModelExample();
            example.createCriteria().andAlarmModelIdIn(idList);
            List<EamAlarmModel> alarmModels = eamAlarmModelService.selectByExample(example);

            List<EamAlarm> alarms = new ArrayList<>();
            for(EamAlarmModel alarmModel : alarmModels){
                alarms.add(createAlarm(alarmModel, productLineId));
            }

            if (!alarms.isEmpty()){
                eamAlarmService.batchInsert(alarms);
            }
        }
        return result;
    }

    @Override
    public int updateAlarm(String ids, String alarmTarget, String[] targetUserIds) {
        if (StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split("::");
            List<Integer> idList = new ArrayList<>();
            for(String id : idArray){
                idList.add(Integer.valueOf(id));
            }

            EamAlarmExample example = new EamAlarmExample();
            example.createCriteria().andAlarmIdIn(idList);

            List<EamAlarm> alarms = eamAlarmService.selectByExample(example);

            for(EamAlarm alarm : alarms){
                alarm.setAlarmTarget(alarmTarget);
                eamAlarmService.updateByPrimaryKey(alarm);

                //delete existing data
                EamAlarmTargetUserExample targetUserExample = new EamAlarmTargetUserExample();
                targetUserExample.createCriteria().andAlarmIdEqualTo(alarm.getAlarmId())
                        .andDeleteFlagEqualTo(Boolean.FALSE);

                eamAlarmTargetUserService.deleteByExample(targetUserExample);

                if (targetUserIds != null && targetUserIds.length > 0){
                    //create new data
                    List<EamAlarmTargetUser> targetUsers = createEamAlarmTargetUsers(targetUserIds, alarm);
                    eamAlarmTargetUserService.batchInsert(targetUsers);
                }

            }

            return 1;
        }
        return 0;
    }

    private List<EamAlarmTargetUser> createEamAlarmTargetUsers(String[] targetUserIds, EamAlarm alarm) {
        List<EamAlarmTargetUser> result = new ArrayList<>();
        for (String targetUserId : targetUserIds){
            EamAlarmTargetUser targetUser = new EamAlarmTargetUser();
            targetUser.setUserId(NumberUtil.toInteger(targetUserId));
            targetUser.setAlarmId(alarm.getAlarmId());
            targetUser.setDeleteFlag(Boolean.FALSE);
            targetUser.setUpdateTime(new Date());
            targetUser.setCreateTime(new Date());
            result.add(targetUser);
        }
        return result;
    }

    private EamAlarm createAlarm(EamAlarmModel alarmModel, String productLineId){
        EamAlarm alarm = new EamAlarm();
        alarm.setProductLineId(productLineId);
        alarm.setAlarmType(alarmModel.getAlarmType());
        alarm.setEamDataElementId(alarmModel.getEamDataElementId());
        alarm.setUpperBound(alarmModel.getUpperBound());
        alarm.setLowerBound(alarmModel.getLowerBound());
        alarm.setDuration(alarmModel.getDuration());
        alarm.setName(alarmModel.getName());
        alarm.setCreateTime(new Date());
        alarm.setUpdateTime(new Date());
        alarm.setDeleteFlag(Boolean.FALSE);
        alarm.setIsCreateTicket(alarmModel.getIsCreateTicket());
        alarm.setEquipmentId(getEquipmentId(productLineId, alarmModel));

        return alarm;
    }

    private String getEquipmentId(String productLineId, EamAlarmModel alarmModel) {
        String result = null;
        EamGrmVariableExample example = new EamGrmVariableExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                .andProductLineIdEqualTo(productLineId)
                .andDataElementIdEqualTo(alarmModel.getEamDataElementId());

        EamGrmVariable variable = eamGrmVariableService.selectFirstByExample(example);

        if (variable != null){
            result = variable.getEquipmentId();
        }

        return result;
    }

    @Override
    public int persistProductLine(EamProductLine eamProductLine, EamProductLineCompany productLineCompany){
        eamProductLineService.insertSelective(eamProductLine);
        productLineCompany.setProductLineId(eamProductLine.getProductLineId());
        return eamProductLineCompanyService.insertSelective(productLineCompany);
    }

    @Override
    public int handleProductLineDataElements(String productLineId, String dataElementIds){
        List<EamProductLineDataElement> vos = buildProductLineDataElements(productLineId, dataElementIds);

        //remove already exist data
        EamProductLineDataElementExample example = new EamProductLineDataElementExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId);
        eamProductLineDataElementService.deleteByExample(example);

        //remove
        EamGrmVariableExample grmVariableExample = new EamGrmVariableExample();
        grmVariableExample.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                .andProductLineIdEqualTo(productLineId)
                .andEquipmentIdIsNull()
                .andDataGroupIdIsNull()
                .andEquipmentDataGroupIdIsNull();
        eamGrmVariableService.deleteByExample(grmVariableExample);

        if (!vos.isEmpty()){
            //add new
            eamProductLineDataElementService.batchInsert(vos);

            List<EamGrmVariable> variables = buildGrmVariables(productLineId, dataElementIds);
            eamGrmVariableService.batchInsert(variables);

        }

        return 1;
    }

    @Override
    public int handleEamEquipmentDataGroupElemets(String equipmentId, String dataGroupId, String equipmentDataGroupId, String ids) {
        EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(equipmentId);
        if (equipment != null){
            //Remove all
            EamEquipmentDataGroupElemetsExample example = new EamEquipmentDataGroupElemetsExample();
            example.createCriteria().andEquipmentIdEqualTo(equipmentId)
                    .andDataGroupIdEqualTo(Integer.valueOf(dataGroupId))
                    .andEquipmentDataGroupIdEqualTo(Integer.valueOf(equipmentDataGroupId))
                    .andDeleteFlagEqualTo(Boolean.FALSE);
            eamEquipmentDataGroupElemetsService.deleteByExample(example);

            //remove
            EamGrmVariableExample grmVariableExample = new EamGrmVariableExample();
            grmVariableExample.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                    .andProductLineIdEqualTo(equipment.getProductLineId())
                    .andEquipmentIdEqualTo(equipmentId)
                    .andDataGroupIdEqualTo(Integer.valueOf(dataGroupId))
                    .andEquipmentDataGroupIdEqualTo(Integer.valueOf(equipmentDataGroupId));
            eamGrmVariableService.deleteByExample(grmVariableExample);

            if(StringUtils.isNotEmpty(ids)){
                //Add new
                List<EamEquipmentDataGroupElemets> elements = buildEamEquipmentDataGroupElements(equipmentId, dataGroupId, equipmentDataGroupId, ids);

                eamEquipmentDataGroupElemetsService.batchInsert(elements);

                List<EamGrmVariable> variables = buildGrmVariables(equipment.getProductLineId(), equipmentId, dataGroupId, equipmentDataGroupId, ids);
                eamGrmVariableService.batchInsert(variables);
            }

        }

        return 1;
    }

    @Override
    public List<EamGrmVariableDataExtVO> selectEamGrmVariableData(EamGrmVariableDataVO eamGrmVariableDataVO) {
        return eamApiMapper.selectEamGrmVariableData(eamGrmVariableDataVO);
    }

    @Override
    public List<EamGrmVariableDataHistoryExtVO> selectEamGrmVariableDataHistories(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        return eamApiMapper.selectEamGrmVariableDataHistories(eamGrmVariableDataHistoryVO);
    }


    private List<EamEquipmentDataGroupElemets> buildEamEquipmentDataGroupElements(String equipmentId, String dataGroupId, String equipmentDataGroupId, String ids) {
        List<EamEquipmentDataGroupElemets> elements = new ArrayList<>();
        String [] idsArray = ids.split("::");

        for (String id : idsArray){
            EamEquipmentDataGroupElemets element = new EamEquipmentDataGroupElemets();
            element.setDataGroupId(Integer.valueOf(dataGroupId));
            element.setEquipmentDataGroupId(Integer.valueOf(equipmentDataGroupId));
            element.setEquipmentId(equipmentId);
            element.setDataElementId(Integer.valueOf(id));
            element.setDeleteFlag(Boolean.FALSE);
            element.setCreateTime(new Date());
            element.setUpdateTime(new Date());
            elements.add(element);
        }

        return elements;
    }



    private List<EamProductLineDataElement> buildProductLineDataElements(String productLineId, String ids){
        List<EamProductLineDataElement> result = new ArrayList<>();
        if (StringUtils.isNotEmpty(ids)){
            String [] idsArray = ids.split("::");

            for (String id : idsArray){
                EamProductLineDataElement element = new EamProductLineDataElement();
                element.setProductLineId(productLineId);
                element.setEamDataElementId(Integer.valueOf(id));
                element.setDeleteFlag(Boolean.FALSE);
                element.setCreateTime(new Date());
                element.setUpdateTime(new Date());
                result.add(element);
            }
        }

        return result;
    }

    private List<EamDataElement> getDataElements(String dataElementIds){
        List<EamDataElement> result = new ArrayList<>();
        String [] idsArray = dataElementIds.split("::");
        List<Integer> ids = coverToInteger(idsArray);

        if (!ids.isEmpty()){
            EamDataElementExample example = new EamDataElementExample();
            example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andIdIn(ids);

            result = eamDataElementService.selectByExample(example);
        }
        return result;
    }


    private List<Integer> coverToInteger(String[] ids){
        List<Integer> result = new ArrayList<>();

        for(String id : ids){
            result.add(Integer.valueOf(id));
        }

        return result;
    }

    private List<EamGrmVariable> buildGrmVariables(String productLineId, String dataElementIds){
        List<EamGrmVariable> result = new ArrayList<>();
        List<EamDataElement> dataElements = getDataElements(dataElementIds);

        for (EamDataElement element : dataElements){
            EamGrmVariable variable = new EamGrmVariable();
            variable.setProductLineId(productLineId);
            variable.setDataElementId(element.getId());
            variable.setName(element.getName());
            variable.setDeleteFlag(Boolean.FALSE);
            variable.setCreateTime(new Date());
            variable.setUpdateTime(new Date());
            result.add(variable);
        }

        return result;
    }

    private List<EamGrmVariable> buildGrmVariables(String productLineId, String equipmentId, String dataGroupId, String equipmentDataGroupId, String dataElementIds){
        List<EamGrmVariable> result = new ArrayList<>();
        List<EamDataElement> dataElements = getDataElements(dataElementIds);

        for (EamDataElement element : dataElements){
            EamGrmVariable variable = new EamGrmVariable();
            variable.setProductLineId(productLineId);
            variable.setEquipmentId(equipmentId);
            variable.setDataGroupId(Integer.valueOf(dataGroupId));
            variable.setEquipmentDataGroupId(Integer.valueOf(equipmentDataGroupId));
            variable.setDataElementId(element.getId());
            variable.setName(element.getName());
            variable.setDeleteFlag(Boolean.FALSE);
            variable.setCreateTime(new Date());
            variable.setUpdateTime(new Date());
            result.add(variable);
        }

        return result;
    }

    @Override
    public List<EamTicketAssessmentTagVO> selectTicketAssessmentTags(EamTicketAssessmentTagVO vo){
        return eamApiMapper.selectTicketAssessmentTags(vo);
    }

    @Override
    public List<EamTicketAppointVO> selectTicketAppointRecord(EamTicketAppointedRecordExample example){
        return eamApiMapper.selectTicketAppointRecord(example);
    }

    @Override
    public int createTicketAppoint(EamTicketAppointedRecord record, EamTicket ticket){
        int i= eamTicketAppointRecordService.insertSelective(record);
        eamTicketService.updateByPrimaryKeySelective(ticket);
        return i;
    }

    @Override
    public int rejectTicketAppoint(EamTicketAppointedRecord ticketAppointRecord){
        EamTicketAppointedRecord lastOne = getLastEamTicketAppointedRecord(ticketAppointRecord.getTicketId());
        lastOne.setAction(EamConstant.TICKET_APPOINT_REJECT);
        lastOne.setRejectCommont(ticketAppointRecord.getRejectCommont());
        int count = eamTicketAppointRecordService.updateByPrimaryKeySelective(lastOne);
        rejectTicketStatus(lastOne.getTicketId(), TicketStatus.INIT.getName());
        return count;
    }

    private EamTicketAppointedRecord getLastEamTicketAppointedRecord(int ticketId){
        EamTicketAppointedRecordExample example = new EamTicketAppointedRecordExample();
        example.createCriteria().andTicketIdEqualTo(ticketId).andDeleteFlagEqualTo(Boolean.FALSE);
        example.setOrderByClause("id desc");
        return eamTicketAppointRecordService.selectFirstByExample(example);
    }

    @Override
    public int deleteTicketAppoint(EamTicketAppointedRecordExample eamTicketAppointRecordExample, int ticketId){
        int i= eamTicketAppointRecordService.deleteByExample(eamTicketAppointRecordExample);
        rejectTicketStatus(ticketId, TicketStatus.INIT.getName());
        return i;
    }

    @Override
    public int addTicketRecord(EamTicketRecord ticketRecord){
        int i= eamTicketRecordService.insertSelective(ticketRecord);
        int ticketId = ticketRecord.getTicketId();
        EamTicket ticket = eamTicketService.selectByPrimaryKey(ticketId);
        if(TicketStatus.TO_PROCESS.getName().equals(ticket.getStatus())){
            updateTicketStatus(ticketId, TicketStatus.PROCESSING.getName());
        }

        return i;
    }

    @Override
    public void completeTicket(EamTicketAssessment ticketAssessment, int[] ticketTag){
        eamTicketAssessmentService.createTicketAssessment(ticketAssessment, ticketTag);
        EamTicket ticket = eamTicketService.selectByPrimaryKey(ticketAssessment.getTicketId());
        if(TicketStatus.RESOLVED.getName().equals(ticket.getStatus())){
            updateTicketStatus(ticketAssessment.getTicketId(), TicketStatus.COMPLETE.getName());
        }
    }

    @Override
    public EamSummaryTicketItemVO summaryTicket(Integer companyId) {
        List<EamSummaryTicketItemVO> items=new ArrayList<EamSummaryTicketItemVO>();
        List<EamSummaryTicketVO> vos= eamApiMapper.summaryTicket(companyId);
        EamSummaryTicketItemVO item = new EamSummaryTicketItemVO();
        int iInit=0, iToProcess=0, iProcessing=0, iResolved=0, iComplete=0, total=0;
        for(EamSummaryTicketVO vo : vos){
            if(TicketStatus.INIT.getName().equals(vo.getStatusName())) {
                iInit = vo.getCount();
                item.setNoAppointStatusName("未委派");
                item.setNoAppointTicketCount(iInit);
            }else if(TicketStatus.TO_PROCESS.getName().equals(vo.getStatusName())){
                iToProcess = vo.getCount();
            }else if(TicketStatus.PROCESSING.getName().equals(vo.getStatusName())){
                iProcessing = vo.getCount();
            }else if(TicketStatus.RESOLVED.getName().equals(vo.getStatusName())) {
                iResolved = vo.getCount();
            }else{
                iComplete = vo.getCount();
            }

        }
        item.setResolvedStatusName("已完成");
        item.setResolvedTicketCount(iResolved + iComplete);

        total = iInit + iToProcess + iProcessing + iResolved + iComplete;
        item.setTotalStatusName("累计报修");
        item.setTotalTicketCount(total);

        item.setProcessingStatusName("维修中");
        item.setProcessingTicketCount(iToProcess + iProcessing);

        item.setNotResolvedStatusName("未完成");
        item.setNotResolvedTicketCount(iInit + iToProcess + iProcessing);

        return item;
    }

    @Override
    public int copyProductLine(String productLineId, String name, Integer companyId) {
        int count = 0;
        EamProductLine productLine = eamProductLineService.selectByPrimaryKey(productLineId);
        if (productLine != null){
            EamProductLine newProductLine = buildEamProductLine(name, productLine);
            count = eamProductLineService.insertSelective(newProductLine);

            copyProduceLineCompany(newProductLine, companyId);
            copyEamProductLineDataElement(newProductLine, productLine);

            copyEquipments(newProductLine, productLine, companyId);

            copyProductLineAlarm(newProductLine, productLineId, companyId);

            copyEamGrmVariable(newProductLine, productLineId);
        }
        return count;
    }

    @Override
    public int updatePositions(String productLineId, Positions positions) {
        int result = 0;
        for(Position position : positions.getPositions()){
            EamEquipment equipment = new EamEquipment();
            equipment.setEquipmentId(position.getEquipmentId());
            equipment.setLeftPosition(position.getLeft());
            equipment.setTopPosition(position.getTop());

            result = eamEquipmentService.updateByPrimaryKeySelective(equipment);
        }

        return result;
    }

    @Override
    public List<Position> getPositions(String productLineId) {
        List<Position> result = new ArrayList<>();


        EamEquipmentExample example = new EamEquipmentExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                .andProductLineIdEqualTo(productLineId);



        List<EamEquipment> equipmentList = eamEquipmentService.selectByExample(example);
        if (equipmentList != null && !equipmentList.isEmpty()){

            for (EamEquipment equipment : equipmentList){
                Position position = new Position();
                position.setLeft(equipment.getLeftPosition());
                position.setTop(equipment.getTopPosition());
                position.setEquipmentId(equipment.getEquipmentId());
                result.add(position);
            }
        }
        return result;
    }

    private void copyEamGrmVariable(EamProductLine newProductLine, String productLineId){
        EamGrmVariableExample example = new EamGrmVariableExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId)
                .andDeleteFlagEqualTo(Boolean.FALSE)
                .andEquipmentIdIsNull();

        List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);
        if (variables != null && !variables.isEmpty()){
            List<EamGrmVariable> newVariables = new ArrayList<>();
            for(EamGrmVariable variable : variables){
                try {
                    EamGrmVariable newVariable = (EamGrmVariable) BeanUtils.cloneBean(variable);
                    newVariable.setId(null);
                    newVariable.setProductLineId(newProductLine.getProductLineId());

                    newVariables.add(newVariable);
                } catch (Exception e) {
                    _log.error("copy EamGrmVariable error:{}", e.getMessage());
                }
            }

            eamGrmVariableService.batchInsert(newVariables);
        }


    }
    private void copyProductLineAlarm(EamProductLine newProductLine, String productLineId, Integer companyId) {
        EamAlarmExample example = new EamAlarmExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId)
                .andEquipmentIdIsNull().andDeleteFlagEqualTo(Boolean.FALSE);

        List<EamAlarm> alarms = eamAlarmService.selectByExample(example);

        if (alarms != null && !alarms.isEmpty()){
            Map<EamAlarm, EamAlarm> map = new HashMap<>();
            List<EamAlarm> newAlarms = new ArrayList<>();
            for (EamAlarm alarm : alarms){

                try {
                    EamAlarm newAlarm = (EamAlarm)BeanUtils.cloneBean(alarm);
                    newAlarm.setProductLineId(newProductLine.getProductLineId());
                    newAlarm.setAlarmId(null);

                    newAlarms.add(newAlarm);
                    map.put(alarm, newAlarm);
                    eamAlarmService.insertSelective(newAlarm);
                } catch (Exception e) {
                    _log.error("copy ProductLineAlarm error: {}", e.getMessage());
                }

            }

            copyAlarmTargetUser(map, alarms, companyId);
        }
    }

    private void copyAlarmTargetUser(Map<EamAlarm, EamAlarm> map, List<EamAlarm> alarms, Integer companyId) {
        List<Integer> alarmIds = alarms.stream().map(x -> x.getAlarmId()).collect(Collectors.toList());
        EamAlarmTargetUserExample example = new EamAlarmTargetUserExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                .andAlarmIdIn(alarmIds);
        List<EamAlarmTargetUser> targetUsers = eamAlarmTargetUserService.selectByExample(example);
        if (targetUsers != null && !targetUsers.isEmpty()){
            List<EamAlarmTargetUser> newTargetUsers = new ArrayList<>();

            for(EamAlarmTargetUser targetUser : targetUsers){
                try {
                    EamAlarmTargetUser newTargetUser = (EamAlarmTargetUser) BeanUtils.cloneBean(targetUser);
                    newTargetUser.setId(null);
                    newTargetUser.setAlarmId(getAlarmId(map, targetUser.getAlarmId()));
                    newTargetUsers.add(newTargetUser);

                } catch (Exception e) {
                    _log.error("copy AlarmTargetUser error: {}", e.getMessage());
                }
            }
            eamAlarmTargetUserService.batchInsert(newTargetUsers);
        }
    }

    private Integer getAlarmId(Map<EamAlarm, EamAlarm> map, Integer alarmId) {
        Integer newAlarmId = null;
        for(Map.Entry<EamAlarm, EamAlarm> entry : map.entrySet()){
            EamAlarm oldAlarm = entry.getKey();
            EamAlarm newAlarm = entry.getValue();

            if (oldAlarm.getAlarmId().equals(alarmId)){
                newAlarmId = newAlarm.getAlarmId();
                break;
            }

        }
        return newAlarmId;
    }

    private void copyEquipments(EamProductLine newProductLine, EamProductLine oldProductLine, Integer companyId){
        EamEquipmentExample example = new EamEquipmentExample();
        example.createCriteria().andProductLineIdEqualTo(oldProductLine.getProductLineId())
                .andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamEquipment> oldEquipments = eamEquipmentService.selectByExample(example);

        if(oldEquipments != null && !oldEquipments.isEmpty()){
            List<EamEquipment> newEquipments = new ArrayList<>();
            Map<EamEquipment, EamEquipment> map = new HashMap<>();
            for(EamEquipment equipment : oldEquipments){

                EamEquipment newEquipment = null;
                try {
                    newEquipment = (EamEquipment) BeanUtils.cloneBean(equipment);
                    newEquipment.setEquipmentId(null);
                    newEquipment.setProductLineId(newProductLine.getProductLineId());
                    newEquipments.add(newEquipment);
                    map.put(equipment, newEquipment);

                    eamEquipmentService.insertSelective(newEquipment);
                } catch (Exception e) {
                    _log.error("clone equipment error: {}", e.getMessage());
                }
            }

            copyEamEquipmentDataGroup(map, oldEquipments);

            copyEquipmentAlarms(newProductLine, map, oldEquipments, companyId);

            copyEquipmentGrmVariables(newProductLine, map, oldEquipments);
        }


    }

    private void copyEquipmentGrmVariables(EamProductLine newProductLine, Map<EamEquipment, EamEquipment> map, List<EamEquipment> eamEquipments) {
        List<String> equipmentIds = eamEquipments.stream().map(x -> x.getEquipmentId()).collect(Collectors.toList());

        EamGrmVariableExample example = new EamGrmVariableExample();
        example.createCriteria().andEquipmentIdIn(equipmentIds).andDeleteFlagEqualTo(Boolean.FALSE);

        List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);

        if (variables != null && !variables.isEmpty()){

            List<EamGrmVariable> newVariables = new ArrayList<>();
            for (EamGrmVariable variable : variables){
                try {
                    EamGrmVariable newVariable = (EamGrmVariable)BeanUtils.cloneBean(variable);
                    newVariable.setId(null);
                    newVariable.setProductLineId(newProductLine.getProductLineId());
                    newVariable.setEquipmentId(getEquipmentId(map, variable));


                    newVariables.add(newVariable);
                } catch (Exception e) {
                    _log.error("copyEquipmentGrmVariables error: {}", e.getMessage());
                }
            }

            eamGrmVariableService.batchInsert(newVariables);
        }
    }

    private String getEquipmentId(Map<EamEquipment, EamEquipment> map, EamGrmVariable variable) {
        String equipmentId = null;
        for(Map.Entry<EamEquipment, EamEquipment> entry : map.entrySet()){
            EamEquipment oldEquipment = entry.getKey();
            EamEquipment newEquipment = entry.getValue();

            if (variable.getEquipmentId().equals(oldEquipment.getEquipmentId())){
                equipmentId = newEquipment.getEquipmentId();
                break;
            }
        }

        return equipmentId;

    }

    private void copyEquipmentAlarms(EamProductLine newProductLine, Map<EamEquipment, EamEquipment> map, List<EamEquipment> eamEquipments, Integer companyId) {
        List<String> equipmentIds = eamEquipments.stream().map(x -> x.getEquipmentId()).collect(Collectors.toList());
        EamAlarmExample example = new EamAlarmExample();
        example.createCriteria().andEquipmentIdIn(equipmentIds).andDeleteFlagEqualTo(Boolean.FALSE);

        List<EamAlarm> alarms = eamAlarmService.selectByExample(example);
        if (alarms != null && !alarms.isEmpty()){
            List<EamAlarm> newAlarms = new ArrayList<>();
            Map<EamAlarm, EamAlarm> alarmMap = new HashMap<>();
            for(EamAlarm alarm : alarms){

                try {
                    EamAlarm newAlarm = (EamAlarm)BeanUtils.cloneBean(alarm);
                    newAlarm.setAlarmId(null);
                    newAlarm.setEquipmentId(getEquipmentId(map, alarm));
                    newAlarm.setProductLineId(newProductLine.getProductLineId());

                    newAlarms.add(newAlarm);
                    alarmMap.put(alarm, newAlarm);

                    eamAlarmService.insertSelective(newAlarm);
                } catch (Exception e) {
                    _log.error("copay EquipmentAlarms error: {}", e.getMessage());
                }


            }

            copyAlarmTargetUser(alarmMap, alarms, companyId);
        }


    }

    private String getEquipmentId(Map<EamEquipment, EamEquipment> map, EamAlarm alarm) {
        String equipmentId = null;
        for(Map.Entry<EamEquipment, EamEquipment> entry : map.entrySet()){
            EamEquipment oldEquipment = entry.getKey();
            EamEquipment newEquipment = entry.getValue();

            if (alarm.getEquipmentId().equals(oldEquipment.getEquipmentId())){
                equipmentId = newEquipment.getEquipmentId();
                break;
            }
        }

        return equipmentId;

    }

    private void copyEamEquipmentDataGroupElemets(Map<EamEquipment, EamEquipment> map, Map<EamEquipmentDataGroup, EamEquipmentDataGroup> dataGroupMap, List<EamEquipment> eamEquipments){
        List<String> equipmentIds = eamEquipments.stream().map(x -> x.getEquipmentId()).collect(Collectors.toList());
        EamEquipmentDataGroupElemetsExample example = new EamEquipmentDataGroupElemetsExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andEquipmentIdIn(equipmentIds);

        List<EamEquipmentDataGroupElemets> elemets = eamEquipmentDataGroupElemetsService.selectByExample(example);

        if (elemets != null && !elemets.isEmpty()){
            List<EamEquipmentDataGroupElemets> newElements = new ArrayList<>();

            for(EamEquipmentDataGroupElemets element : elemets){
                try {
                    EamEquipmentDataGroupElemets newElement = (EamEquipmentDataGroupElemets)BeanUtils.cloneBean(element);
                    newElement.setId(null);
                    newElement.setEquipmentDataGroupId(getEquipmentDataGroupId(dataGroupMap, element));
                    newElement.setEquipmentId(getEquipmentId(map, element));
                    newElements.add(newElement);
                } catch (Exception e) {
                    _log.error("copy EamEquipmentDataGroupElemets error: {}", e.getMessage());
                }
            }

            eamEquipmentDataGroupElemetsService.batchInsert(newElements);
        }

    }

    private Integer getEquipmentDataGroupId(Map<EamEquipmentDataGroup, EamEquipmentDataGroup> dataGroupMap, EamEquipmentDataGroupElemets element) {

        Integer equipmentDataGroupId = null;
        for(Map.Entry<EamEquipmentDataGroup, EamEquipmentDataGroup> entry : dataGroupMap.entrySet()){
            EamEquipmentDataGroup oldDataGroup = entry.getKey();
            EamEquipmentDataGroup newDataGroup = entry.getValue();

            if (element.getEquipmentDataGroupId().equals(oldDataGroup.getId())){
                equipmentDataGroupId = newDataGroup.getId();
                break;

            }
        }

        return equipmentDataGroupId;


    }

    private String getEquipmentId(Map<EamEquipment, EamEquipment> map, EamEquipmentDataGroupElemets element){
        String equipmentId = null;
        for(Map.Entry<EamEquipment, EamEquipment> entry : map.entrySet()){
            EamEquipment oldEquipment = entry.getKey();
            EamEquipment newEquipment = entry.getValue();

            if (element.getEquipmentId().equals(oldEquipment.getEquipmentId())){
                equipmentId = newEquipment.getEquipmentId();
                break;
            }
        }

        return equipmentId;
    }

    private void copyEamEquipmentDataGroup(Map<EamEquipment, EamEquipment> map, List<EamEquipment> eamEquipments) {

        List<String> equipmentIds = eamEquipments.stream().map(x -> x.getEquipmentId()).collect(Collectors.toList());

        EamEquipmentDataGroupExample example = new EamEquipmentDataGroupExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andEquipmentIdIn(equipmentIds);

        List<EamEquipmentDataGroup> equipmentDataGroups = eamEquipmentDataGroupService.selectByExample(example);

        if (equipmentDataGroups != null && !equipmentDataGroups.isEmpty()){
            List<EamEquipmentDataGroup> newEquipmentDataGroups = new ArrayList<>();
            Map<EamEquipmentDataGroup, EamEquipmentDataGroup> dataGroupMap = new HashMap<>();
            for(EamEquipmentDataGroup dataGroup : equipmentDataGroups){
                try {
                    EamEquipmentDataGroup newEquipmentDataGroup = (EamEquipmentDataGroup)BeanUtils.cloneBean(dataGroup);
                    newEquipmentDataGroup.setId(null);
                    newEquipmentDataGroup.setEquipmentId(getEquipmentId(map, dataGroup));
                    newEquipmentDataGroups.add(newEquipmentDataGroup);
                    dataGroupMap.put(dataGroup, newEquipmentDataGroup);

                    eamEquipmentDataGroupService.insertSelective(newEquipmentDataGroup);
                } catch (Exception e) {
                    _log.error("copy EamEquipmentDataGroup error: {}", e.getMessage());
                }
            }

            copyEamEquipmentDataGroupElemets(map, dataGroupMap, eamEquipments);
        }




    }

    private String getEquipmentId(Map<EamEquipment, EamEquipment> map, EamEquipmentDataGroup dataGroup){
        String equipmentId = null;
        for(Map.Entry<EamEquipment, EamEquipment> entry : map.entrySet()){
            EamEquipment oldEquipment = entry.getKey();
            EamEquipment newEquipment = entry.getValue();

            if (dataGroup.getEquipmentId().equals(oldEquipment.getEquipmentId())){
                equipmentId = newEquipment.getEquipmentId();
                break;
            }
        }

        return equipmentId;
    }

    private void copyProduceLineCompany(EamProductLine newProductLine, Integer companyId) {
        EamProductLineCompany eamProductLineCompany = new EamProductLineCompany();
        eamProductLineCompany.setProductLineId(newProductLine.getProductLineId());
        eamProductLineCompany.setCompanyId(companyId);
        eamProductLineCompany.setCreateTime(new Date());
        eamProductLineCompany.setUpdateTime(new Date());
        eamProductLineCompany.setDeleteFlag(Boolean.FALSE);

        eamProductLineCompanyService.insertSelective(eamProductLineCompany);
    }

    private void copyEamProductLineDataElement(EamProductLine newProductLine, EamProductLine productLine){
        EamProductLineDataElementExample example = new EamProductLineDataElementExample();
        example.createCriteria().andProductLineIdEqualTo(productLine.getProductLineId())
                .andDeleteFlagEqualTo(Boolean.FALSE);

        List<EamProductLineDataElement> elements = eamProductLineDataElementService.selectByExample(example);

        List<EamProductLineDataElement> newElements = new ArrayList<>();
        for(EamProductLineDataElement element : elements){
            EamProductLineDataElement newElement = new EamProductLineDataElement();
            newElement.setProductLineId(newProductLine.getProductLineId());
            newElement.setEamDataElementId(element.getEamDataElementId());
            newElement.setDeleteFlag(Boolean.FALSE);
            newElement.setUpdateTime(new Date());
            newElement.setCreateTime(new Date());
            newElements.add(newElement);
        }

        eamProductLineDataElementService.batchInsert(newElements);
    }

    private EamProductLine buildEamProductLine(String name, EamProductLine productLine){
        EamProductLine newProductLine = new EamProductLine();
        newProductLine.setName(name);
        newProductLine.setCity(productLine.getCity());
        newProductLine.setProvince(productLine.getProvince());
        newProductLine.setImagePath(productLine.getImagePath());
        newProductLine.setLongitude(productLine.getLongitude());
        newProductLine.setLatitude(productLine.getLatitude());
        newProductLine.setMorningShiftEndTime(productLine.getMorningShiftEndTime());
        newProductLine.setMorningShiftStartTime(productLine.getMorningShiftStartTime());
        newProductLine.setMiddleShiftStartTime(productLine.getMiddleShiftStartTime());
        newProductLine.setMiddleShiftEndTime(productLine.getMiddleShiftEndTime());
        newProductLine.setNightShiftStartTime(productLine.getNightShiftStartTime());
        newProductLine.setNightShiftEndTime(productLine.getNightShiftEndTime());
        newProductLine.setCreateTime(new Date());
        newProductLine.setUpdateTime(new Date());
        newProductLine.setDeleteFlag(Boolean.FALSE);
        newProductLine.setIsOnline(Boolean.FALSE);


        return newProductLine;

    }


    private int rejectTicketStatus(int ticketId, String status){
        EamTicket ticket=new EamTicket();
        ticket.setTicketId(ticketId);
        ticket.setStatus(status);
        ticket.setExecutorId(-1); //remove executor
        return updateTicketStatus(ticket);
    }
    private int updateTicketStatus(int ticketId, String status){
        EamTicket ticket=new EamTicket();
        ticket.setTicketId(ticketId);
        ticket.setStatus(status);
        return updateTicketStatus(ticket);
    }

    private int updateTicketStatus(EamTicket ticket){
        return eamTicketService.updateByPrimaryKeySelective(ticket);
    }


    @Override
    public List<EamMaintainPlanVO> listMaintainPlans(EamMaintainPlanVO vo) {
        return eamApiMapper.listMaintainPlans(vo);
    }

    @Override
    public Long countMaintainPlans(EamMaintainPlanVO vo) {
        return eamApiMapper.countMaintainPlans(vo);
    }

    @Override
    public List<EamPlanTicketVO> selectTicketByPlan(EamPlanTicketVO vo) {
        return eamApiMapper.selectTicketByPlan(vo);
    }

    @Override
    public EamMaintainPlanVO getMaintainPlan(Integer planId) {
        return eamApiMapper.getMaintainPlan(planId);
    }

    @Override
    public List<EamTicketRejectRecordVO> getTicketRejectRecord(Integer ticketId) {
        return eamApiMapper.getTicketRejectRecord(ticketId);
    }

    @Override
    public int deleteDataElements(String ids) {
        int count = dataElementService.deleteByPrimaryKeys(ids);
        List<Integer> idList = coverToList(ids);
        //1. delete alarm model
        EamAlarmModelExample example = new EamAlarmModelExample();
        example.createCriteria().andEamDataElementIdIn(idList).andDeleteFlagEqualTo(Boolean.FALSE);
        eamAlarmModelService.deleteByExample(example);

        //2. delete product line data element
        EamProductLineDataElementExample pExample = new EamProductLineDataElementExample();
        pExample.createCriteria().andEamDataElementIdIn(idList).andDeleteFlagEqualTo(Boolean.FALSE);

        eamProductLineDataElementService.deleteByExample(pExample);

        //3. delete equipment data group elemets
        EamEquipmentDataGroupElemetsExample eExample = new EamEquipmentDataGroupElemetsExample();
        eExample.createCriteria().andDataElementIdIn(idList).andDeleteFlagEqualTo(Boolean.FALSE);

        eamEquipmentDataGroupElemetsService.deleteByExample(eExample);

        //4. delete alarm
        EamAlarmExample alarmExample = new EamAlarmExample();
        alarmExample.createCriteria().andEamDataElementIdIn(idList).andDeleteFlagEqualTo(Boolean.FALSE);

        eamAlarmService.deleteByExample(alarmExample);

        //5. delete grm variable
        EamGrmVariableExample gExample = new EamGrmVariableExample();
        gExample.createCriteria().andDataElementIdIn(idList).andDeleteFlagEqualTo(Boolean.FALSE);

        eamGrmVariableService.deleteByExample(gExample);

        return count;
    }

    @Override
    public int createCustomer(UpmsCompany company) {
        String adminName = RandomStringUtils.randomAlphabetic(5);
        String adminPassword = RandomStringUtils.randomAlphabetic(6);

        company.setAdminName(adminName);
        company.setAdminPassword(adminPassword);

        int count = upmsCompanyService.insertSelective(company);

        upmsApiService.handleCustomerReg(adminName, adminName, adminPassword, null, null, company.getName());

        return count;
    }

    @Override
    public List<EamGrmVariableDataHistoryExtVO> getGrmVariableHistoryData(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        if (StringUtils.isNotEmpty(eamGrmVariableDataHistoryVO.getProductLineId())){
            EamProductLine productLine = eamProductLineService.selectByPrimaryKey(eamGrmVariableDataHistoryVO.getProductLineId());
            eamGrmVariableDataHistoryVO.setGrmPeriod(productLine.getGrmPeriod());
        }

        List<EamGrmVariableDataHistoryExtVO> result = selectEamGrmVariableDataHistories(eamGrmVariableDataHistoryVO);
        if (result == null){
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<GanttData> getGanttData(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
        if (eamGrmVariableDataHistoryVO.getInterval() == null){
            /*
              间隔为半小时
             */
            eamGrmVariableDataHistoryVO.setInterval(60 * 30);
        }


        List<EamGrmVariableDataHistoryExtVO> historyData = getGrmVariableHistoryData(eamGrmVariableDataHistoryVO);


        List<GanttData> result = new ArrayList<>();

        if (eamGrmVariableDataHistoryVO.getDataElementIds() != null && historyData != null){
            int dataElementIdSize = eamGrmVariableDataHistoryVO.getDataElementIds().size();

            int groupSize = historyData.size() / dataElementIdSize;
            int groupIndex = 0;

            while (groupIndex < groupSize){

                int index = 0;
                while (index < dataElementIdSize){
                    EamGrmVariableDataHistoryExtVO vo = historyData.get(groupIndex * dataElementIdSize + index);
                    String value = vo.getValue();
                    Integer dataElementId = vo.getDataElementId();
                    Date date = vo.getUpdateTime();

                    if ("1".equalsIgnoreCase(value)){
                        if (result.isEmpty()){
                            addNewGantt(result, dataElementId, date);
                        }else {
                            GanttData lastGanttData = result.get(result.size() -1);
                            Integer lastDataElementId = lastGanttData.getDataElementId();

                            lastGanttData.setEndDate(date);

                            if (!lastDataElementId.equals(dataElementId)){
                                addNewGantt(result, dataElementId, date);
                            }
                        }
                    }
                    index++;
                }
                groupIndex++;
            }

        }

        return result;
    }

    @Override
    public List<EamCodeValue> getCodeValues(String category) {
        EamCodeValueExample example = new EamCodeValueExample();
        example.createCriteria().andCategoryEqualTo(category).andDeleteFlagEqualTo(Boolean.FALSE);

        return eamCodeValueService.selectByExample(example);
    }

    @Override
    public EamCodeValue getCodeValue(String category, String codeValue) {
        EamCodeValueExample example = new EamCodeValueExample();
        example.createCriteria().andCategoryEqualTo(category).
                andCodeValueEqualTo(codeValue)
                .andDeleteFlagEqualTo(Boolean.FALSE);

        return eamCodeValueService.selectFirstByExample(example);
    }

    @Override
    public List<EamCodeValueVo> summaryIndustry() {
        return eamApiMapper.summaryIndustry();
    }

    @Override
    public List<EamCodeValueVo> summaryProductLineType() {
        return eamApiMapper.summaryProductLineType();
    }

    @Override
    public List<EamCodeValueVo> summaryState() {
        return eamApiMapper.summaryState();
    }

    @Override
    public List<EamCountryValueVo> summaryCountry() {
        List<EamCountryValueVo> result = new ArrayList<>();
        List<EamCountryValueVo> vos = eamApiMapper.summaryCountry();

        if (vos != null && !vos.isEmpty()){
            EamCountryValueVo firstValue = vos.get(0);
            int firstYear = NumberUtil.toInteger(firstValue.getYear());

            EamCountryValueVo lastValue = vos.get(vos.size() - 1);
            int lastYear = NumberUtil.toInteger(lastValue.getYear());

            int year = firstYear;
            while(year <= lastYear){
                result.add(getEamCountryValueVo(year, CountryType.ABROAD.getName(), vos));
                result.add(getEamCountryValueVo(year, CountryType.DOMESTIC.getName(), vos));
                year++;
            }
        }
        return result;
    }

    EamCountryValueVo getEamCountryValueVo(int argYear, String argCountry, List<EamCountryValueVo> vos){
        EamCountryValueVo result = null;

        for(EamCountryValueVo vo : vos){
            int year = NumberUtil.toInteger(vo.getYear());
            String country = vo.getCountry();

            if (argYear == year && argCountry.equalsIgnoreCase(country)){
                result = vo;
                break;
            }
        }

        if (result == null){
            result = new EamCountryValueVo();
            result.setYear(String.valueOf(argYear));
            result.setCountry(argCountry);
            result.setValue(0);
        }

        return result;
    }


    private String getCodeValueFromRedis(String codeType, String codeName){
        return RedisUtil.get(codeType + "::" + org.apache.commons.lang.StringUtils.trim(codeName));
    }

    private boolean isTrue(String value){
        boolean result = false;
        if ("是".equals(org.apache.commons.lang.StringUtils.trim(value))){
            result = true;
        }
        return result;
    }

    @Override
    public void importCompanyData(List<CompanyBean> companyBeanList, UpmsUserCompany currentCompany) {
        List<UpmsCompany> upmsCompanyList = new ArrayList<>();
        if (companyBeanList != null && !companyBeanList.isEmpty()){
            for (CompanyBean companyBean : companyBeanList){
                UpmsCompany upmsCompany = new UpmsCompany();
                upmsCompany.setParentId(currentCompany.getCompanyId());
                upmsCompany.setName(companyBean.getName());
                upmsCompany.setPhone(companyBean.getPhone());
                upmsCompany.setFax(companyBean.getFax());
                upmsCompany.setWww(companyBean.getWww());
                upmsCompany.setZip(companyBean.getZip());
                upmsCompany.setAddress(companyBean.getAddress());

                upmsCompany.setDeleteFlag(Boolean.FALSE);
                upmsCompany.setUpdateTime(new Date());
                upmsCompany.setCreateTime(new Date());

                String adminName = RandomStringUtils.randomAlphabetic(5);
                String adminPassword = RandomStringUtils.randomAlphabetic(6);

                upmsCompany.setAdminName(adminName);
                upmsCompany.setAdminPassword(adminPassword);

                if (!hasCompany(upmsCompany.getName())){
                    upmsCompanyService.insertSelective(upmsCompany);
                    upmsApiService.handleCustomerReg(adminName, adminName, adminPassword, null, null, upmsCompany.getName());
                    upmsCompanyList.add(upmsCompany);
                }

            }
            _log.info("upload CompanyBean size:{}", upmsCompanyList.size());
        }
    }

    private boolean hasCompany(String companyName){
        UpmsCompanyExample example = new UpmsCompanyExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andNameEqualTo(companyName);
        UpmsCompany company = upmsCompanyService.selectFirstByExample(example);
        boolean result = false;
        if (company != null){
            result = true;
        }
        return result;
    }

    @Override
    public void importOrderData(List<OrderBean> orderBeanList) {
        List<EamOrder> eamOrders = new ArrayList<>();
        if (orderBeanList != null && !orderBeanList.isEmpty()){
            for (OrderBean orderBean : orderBeanList){
                EamOrder eamOrder = new EamOrder();
                eamOrder.setCompanyName(orderBean.getCompanyName());
                eamOrder.setYear(orderBean.getYear());
                eamOrder.setTaskNumber(orderBean.getTaskNumber());

                eamOrder.setState(getCodeValueFromRedis(CodeValueType.STATE, orderBean.getState()));

                eamOrder.setCountry(orderBean.getCountry());
                eamOrder.setProvince(orderBean.getProvince());
                eamOrder.setCity(orderBean.getCity());

                eamOrder.setIndustry(getCodeValueFromRedis(CodeValueType.INDUSTRY, orderBean.getIndustry()));
                eamOrder.setProductLineType(getCodeValueFromRedis(CodeValueType.PRODUCT_LINE_TYPE, orderBean.getProductLineType()));
                eamOrder.setHasCxg(isTrue(orderBean.getHasCxg()));
                eamOrder.setHasZnlk(isTrue(orderBean.getHasZnlk()));
                eamOrder.setProductLineCapacity(getCodeValueFromRedis(CodeValueType.PRODUCT_LINE_CAPACITY, orderBean.getProductLineCapacity()));
                eamOrder.setPackagingMaterial(getCodeValueFromRedis(CodeValueType.PACKAGING_MATERIAL, orderBean.getPackagingMaterial()));
                eamOrder.setProductSpec(getCodeValueFromRedis(CodeValueType.PRODUCT_SPEC, orderBean.getProductSpec()));
                eamOrder.setMajorEquipment(orderBean.getMajorEquipment());
                eamOrder.setComment(orderBean.getComment());
                eamOrder.setDeleteFlag(Boolean.FALSE);
                eamOrder.setUpdateTime(new Date());
                eamOrder.setCreateTime(new Date());

                if (!hasOrder(eamOrder)){
                    eamOrders.add(eamOrder);
                }

            }
            _log.info("upload OrderBean size:{}", eamOrders.size());
            eamOrderService.batchInsert(eamOrders);
        }
    }

    private boolean hasOrder(EamOrder argEamOrder){
        boolean result = false;
        EamOrderExample example = new EamOrderExample();
        example.createCriteria().andTaskNumberEqualTo(argEamOrder.getTaskNumber())
                .andCompanyNameEqualTo(argEamOrder.getCompanyName())
                .andYearEqualTo(argEamOrder.getYear())
                .andDeleteFlagEqualTo(Boolean.FALSE);
        EamOrder eamOrder = eamOrderService.selectFirstByExample(example);
        if (eamOrder != null){
            result = true;
        }
        return result;
    }

    private void addNewGantt(List<GanttData> result, Integer dataElementId, Date date) {
        GanttData ganttData = new GanttData();
        ganttData.setDataElementId(dataElementId);
        ganttData.setStartDate(date);
        result.add(ganttData);
    }

    private List<Integer> coverToList(String ids){
        List<Integer> result = new ArrayList<>();

        String[] idArray = ids.split("-");
        if (idArray != null){
            for(String id : idArray){
                result.add(Integer.valueOf(id));
            }
        }
        return result;
    }

    @Override
    public HashMap summaryIndustryAndCompanyName(){
        int MAX_COUNT = 3;

        List<EamIndustryValueVo> industryValueVos = eamApiMapper.summaryIndustryAndCompanyName();

        List<EamCodeValue> allIndustry = getAllIndustry();

        HashMap<String, List<EamIndustryValueVo>> map = new HashMap<>();

        for (EamCodeValue code : allIndustry){
            String industryValue = code.getCodeValue();
            String industryName = code.getCodeName();

            map.put(industryValue, new ArrayList<>());

            for (EamIndustryValueVo vo : industryValueVos){

                if (industryValue.equalsIgnoreCase(vo.getIndustry())){

                    List<EamIndustryValueVo> list = map.get(industryValue);

                    if (list.size() < MAX_COUNT){
                        list.add(vo);
                    }else {
                        break;
                    }
                }
            }
        }
        return map;
    }

    private List<EamCodeValue> getAllIndustry(){
        EamCodeValueExample example = new EamCodeValueExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andCategoryEqualTo(CodeValueType.INDUSTRY);
        return eamCodeValueService.selectByExample(example);
    }

    @Override
    public void statisticJob(){
        String date = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<EamGrmVariable> grmVariableDataList = eamApiMapper.selectStatisticGrmVariable();
        if (grmVariableDataList != null && !grmVariableDataList.isEmpty()){
            for(EamGrmVariable variable : grmVariableDataList){
                Pair<EamGrmVariableDataHistory, EamGrmVariableDataHistory> pair = getFirstAndLastData(variable, date);
                if (pair != null){
                    EamGrmVariableDataHistory first = pair.getKey();
                    EamGrmVariableDataHistory last = pair.getValue();

                    BigDecimal value = NumberUtil.toBigDecimal(last.getValue()).subtract(NumberUtil.toBigDecimal(first.getValue()));

                    _log.info("ProductLineId:{}", variable.getProductLineId());
                    _log.info("DataElementId:{}", variable.getDataElementId());
                    _log.info("value:{}", value);


                    //1. handle daily data
                    handleGrmVariableDataByDay(variable, value, date);

                    //2. handle month data
                    handleGrmVariableDataByMonth(variable, value);

                    //3. handle year data
                    handleGrmVariableDataByYear(variable, value);

                }
            }
        }

    }

    @Override
    public void importPartData(List<PartBean> partBeanList, UpmsUserCompany company, EamEquipment equipment) {
        List<EamParts> parts = new ArrayList<>();
        if (partBeanList != null && !partBeanList.isEmpty()){
            for (PartBean partBean : partBeanList){
                EamParts part = new EamParts();
                part.setProductLineId(equipment.getProductLineId());
                part.setEquipmentId(equipment.getEquipmentId());
                part.setCategoryId(getPartCategoryId(partBean.getCategoryName(), company));
                part.setSeqId(NumberUtil.toInteger(partBean.getSeqId()));
                part.setName(partBean.getName());
                part.setModel(partBean.getModel());
                part.setSymbol(partBean.getSymbol());
                part.setMaterial(partBean.getMaterial());
                part.setQuantity(NumberUtil.toInteger(partBean.getQuantity()));
                part.setCompanyId(company.getCompanyId());
                part.setCreateTime(new Date());
                part.setUpdateTime(new Date());
                part.setDeleteFlag(Boolean.FALSE);

                if (!hasPart(part)){
                    parts.add(part);
                }

            }
        }

        eamPartsService.batchInsert(parts);
    }

    private boolean hasPart(EamParts argPart){
        boolean result = false;
        EamPartsExample example = new EamPartsExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE)
                .andProductLineIdEqualTo(argPart.getProductLineId())
                .andEquipmentIdEqualTo(argPart.getEquipmentId())
                .andNameEqualTo(argPart.getName())
                .andDeleteFlagEqualTo(Boolean.FALSE);

        EamParts eamPart = eamPartsService.selectFirstByExample(example);
        if (eamPart != null){
            result = true;
        }
        return result;
    }

    @Override
    public List<EamOrderVO> selectOrders(EamOrderVO orderVO) {
        return eamApiMapper.selectOrders(orderVO);
    }

    @Override
    public long countOrders(EamOrderVO orderVO) {
        return eamApiMapper.countOrders(orderVO);
    }

    @Override
    public List<EamAlertMessageVO> selectEamAlertMessages(EamAlertMessageVO alertMessageVO) {
        return eamApiMapper.selectEamAlertMessages(alertMessageVO);
    }

    @Override
    public long countEamAlertMessages(EamAlertMessageVO alertMessageVO) {
        return eamApiMapper.countEamAlertMessages(alertMessageVO);
    }

    @Override
    public List<Pair<Integer, List<EamGrmVariable>>> selectGrmVariableGroupByPeriod(String productLineId) {
        List<Pair<Integer, List<EamGrmVariable>>> result = new ArrayList<>();
        List<EamGrmVariable> variables = eamApiMapper.selectGrmVariableGroupByPeriod(productLineId);
        if (variables != null && !variables.isEmpty()){
            for(EamGrmVariable variable : variables){
                Integer period = variable.getGrmPeriod();
                List<EamGrmVariable> variableList = getGrmVariables(productLineId, period);
                if (period == null){
                    period = getPeriod(productLineId);
                }
                _log.info("ProductLineId:{}, period:{}, variable size:{}", productLineId, period, variableList.size());
                Pair<Integer, List<EamGrmVariable>> pair = new Pair(period, variableList);
                result.add(pair);
            }
        }
        return result;
    }

    @Override
    public List<EamGrmVariableVO> selectGrmVariables(EamGrmVariableVO variableVO) {
        return eamApiMapper.selectGrmVariables(variableVO);
    }

    @Override
    public long countGrmVariables(EamGrmVariableVO variableVO) {
        return eamApiMapper.countGrmVariables(variableVO);
    }

    @Override
    public void importDataElement(List<DataElementBean> list, UpmsUserCompany company) {
        List<EamDataElement> dataElements = new ArrayList<>();
        if (list != null && !list.isEmpty()){
            for (DataElementBean elementBean : list){
                EamDataElement dataElement = new EamDataElement();

                dataElement.setName(elementBean.getName());
                dataElement.setLableName(elementBean.getLabelName());
                dataElement.setUnit(elementBean.getUnit());
                dataElement.setEquipmentCategoryId(getEquipmentCategoryId(elementBean.getEquipmentCategory()));
                dataElement.setDataType(DataType.getCode(elementBean.getName()));
                dataElement.setIsStatisticByDate("是".equals(elementBean.getStatisticByDate()) ? true : false);
                dataElement.setIsStatisticByShift("是".equals(elementBean.getStatisticByShift()) ? true : false);
                dataElement.setIsSummation("是".equals(elementBean.getSummation()) ? true : false);

                if (!hasDataElement(elementBean.getName())){
                    dataElements.add(dataElement);
                }

            }
            eamDataElementService.batchInsert(dataElements);
            _log.info("upload DataElementBean size:{}", dataElements.size());
        }
    }

    private Integer getEquipmentCategoryId(String equipmentCategory) {

        int categoryId = -1;

        if (equipmentCategoryMap.containsKey(equipmentCategory)){
            categoryId = categoryMap.get(equipmentCategory);
        }else {
            EamEquipmentCategoryExample example = new EamEquipmentCategoryExample();
            example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andNameEqualTo(equipmentCategory);
            EamEquipmentCategory category = eamEquipmentCategoryService.selectFirstByExample(example);
            if (category != null){
                categoryId = category.getEquipmentCategoryId();
            }

            categoryMap.put(equipmentCategory, categoryId);
        }

        return categoryId;
    }

    private boolean hasDataElement(String name) {
        boolean result = false;
        EamDataElementExample example = new EamDataElementExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andNameEqualTo(name);

        EamDataElement element = eamDataElementService.selectFirstByExample(example);
        if (element != null){
            result = true;
        }
        return result;
    }

    private List<EamTicketRecord> getTicketRecords(int id){
        EamTicketRecordExample example = new EamTicketRecordExample();
        example.createCriteria().andTicketIdEqualTo(id).andDeleteFlagEqualTo(Boolean.FALSE);
        example.setOrderByClause("eam_ticket_record.create_time asc");
        return eamTicketRecordService.selectByExample(example);
    }

    private Integer getPeriod(String productLineId) {
        Integer result = Integer.valueOf(30);
        EamProductLine productLine = eamProductLineService.selectByPrimaryKey(productLineId);
        if (productLine != null){
            result = productLine.getGrmPeriod();
        }else {
            _log.warn("Please set period for the product line:{}", productLine);
        }
        return result;
    }



    private List<EamGrmVariable> getGrmVariables(String productLineId, Integer period) {
        EamGrmVariableExample example = new EamGrmVariableExample();
        EamGrmVariableExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE).andProductLineIdEqualTo(productLineId);
        if (period == null){
            criteria.andGrmPeriodIsNull();
        }else {
            criteria.andGrmPeriodEqualTo(period);
        }

        return eamGrmVariableService.selectByExample(example);
    }

    private int getPartCategoryId(String name, UpmsUserCompany company){
        int categoryId = -1;

        if (categoryMap.containsKey(name)){
            categoryId = categoryMap.get(name);
        }else {
            EamPartsCategoryExample example = new EamPartsCategoryExample();
            example.createCriteria().andNameEqualTo(name).andDeleteFlagEqualTo(Boolean.FALSE);
            EamPartsCategory category = eamPartsCategoryService.selectFirstByExample(example);
            if (category != null){
                categoryId = category.getCategoryId();
            }else {
                category = new EamPartsCategory();
                category.setName(name);
                category.setCreateTime(new Date());
                category.setUpdateTime(new Date());
                category.setCompanyId(company.getCompanyId());
                category.setDeleteFlag(Boolean.FALSE);

                eamPartsCategoryService.insertSelective(category);
                categoryId = category.getCategoryId();
            }

            categoryMap.put(name, categoryId);
        }


        return categoryId;
    }

    private void handleGrmVariableDataByYear(EamGrmVariable variable, BigDecimal value) {
        EamGrmVariableDataByYear data = getEamGrmVariableDataByYear(variable);
        if (data != null){
            BigDecimal newValue = NumberUtil.toBigDecimal(data.getValue()).add(value);
            data.setValue(newValue.toString());
            data.setUpdateTime(new Date());
            eamGrmVariableDataByYearService.updateByPrimaryKeySelective(data);
        }else {
            data = buildEamGrmVariableDataByYear(variable, value);
            eamGrmVariableDataByYearService.insertSelective(data);
        }
    }

    private EamGrmVariableDataByYear buildEamGrmVariableDataByYear(EamGrmVariable variable, BigDecimal value){
        EamGrmVariableDataByYear data = new EamGrmVariableDataByYear();
        data.setEamGrmVariableId(variable.getId());
        data.setProductLineId(variable.getProductLineId());
        data.setEquipmentId(variable.getEquipmentId());
        data.setDataElementId(variable.getDataElementId());
        data.setValue(value.toString());
        data.setYear(LocalDateTime.now().getYear());
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        data.setDeleteFlag(Boolean.FALSE);
        return data;
    }

    private EamGrmVariableDataByYear getEamGrmVariableDataByYear(EamGrmVariable variable){
        int year = LocalDateTime.now().getYear();
        EamGrmVariableDataByYearExample example = new EamGrmVariableDataByYearExample();
        EamGrmVariableDataByYearExample.Criteria criteria = example.createCriteria();

        criteria.andEamGrmVariableIdEqualTo(variable.getId());
        criteria.andYearEqualTo(year);
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        return eamGrmVariableDataByYearService.selectFirstByExample(example);
    }

    private void handleGrmVariableDataByMonth(EamGrmVariable variable, BigDecimal value) {
        EamGrmVariableDataByMonth data = getEamGrmVariableDataByMoth(variable);
        if (data != null){
            BigDecimal newValue = NumberUtil.toBigDecimal(data.getValue()).add(value);
            data.setValue(newValue.toString());
            data.setUpdateTime(new Date());
            eamGrmVariableDataByMonthService.updateByPrimaryKeySelective(data);
        }else{
            data = buildEamGrmVariableDataByMoth(variable, value);
            eamGrmVariableDataByMonthService.insertSelective(data);
        }

    }

    private EamGrmVariableDataByMonth getEamGrmVariableDataByMoth(EamGrmVariable variable){
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        EamGrmVariableDataByMonthExample example = new EamGrmVariableDataByMonthExample();
        EamGrmVariableDataByMonthExample.Criteria criteria = example.createCriteria();
        criteria.andEamGrmVariableIdEqualTo(variable.getId());
        criteria.andYearEqualTo(year);
        criteria.andMonthEqualTo(month);
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        return eamGrmVariableDataByMonthService.selectFirstByExample(example);
    }

    private EamGrmVariableDataByMonth buildEamGrmVariableDataByMoth(EamGrmVariable variable, BigDecimal value){
        EamGrmVariableDataByMonth data = new EamGrmVariableDataByMonth();
        data.setEamGrmVariableId(variable.getId());
        data.setProductLineId(variable.getProductLineId());
        data.setEquipmentId(variable.getEquipmentId());
        data.setDataElementId(variable.getDataElementId());
        data.setValue(value.toString());
        data.setYear(LocalDateTime.now().getYear());
        data.setMonth(LocalDateTime.now().getMonthValue());
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        data.setDeleteFlag(Boolean.FALSE);
        return data;
    }

    private void handleGrmVariableDataByDay(EamGrmVariable variable, BigDecimal value, String argDate){
        EamGrmVariableDataByDay data = new EamGrmVariableDataByDay();
        data.setEamGrmVariableId(variable.getId());
        data.setProductLineId(variable.getProductLineId());
        data.setEquipmentId(variable.getEquipmentId());
        data.setDataElementId(variable.getDataElementId());
        data.setValue(value.toString());
        Date date = null;
        try {
            date = DateUtils.parseDate(argDate, new String[]{"yyyy-MM-dd"});
        } catch (ParseException e) {

        }
        data.setDate(date);
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        data.setDeleteFlag(Boolean.FALSE);

        eamGrmVariableDataByDayService.insertSelective(data);

    }


    private Pair<EamGrmVariableDataHistory, EamGrmVariableDataHistory> getFirstAndLastData(EamGrmVariable variable, String date){
        Pair<EamGrmVariableDataHistory, EamGrmVariableDataHistory> pair = null;
        try {
            Date startDate = DateUtils.parseDate(date + " 00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"});

            Date endDate = DateUtils.parseDate(date + " 24:59:59", new String[]{"yyyy-MM-dd HH:mm:ss"});


            EamGrmVariableDataHistoryExample example = new EamGrmVariableDataHistoryExample();
            EamGrmVariableDataHistoryExample.Criteria criteria = example.createCriteria();
            criteria.andProductLineIdEqualTo(variable.getProductLineId())
                    .andDataElementIdEqualTo(variable.getDataElementId())
                    .andDeleteFlagEqualTo(Boolean.FALSE)
                    .andUpdateTimeBetween(startDate, endDate);
            example.setOrderByClause("update_time asc");

            if (eamGrmVariableDataHistoryService != null){

                List<EamGrmVariableDataHistory> dataHistoryList = eamGrmVariableDataHistoryService.selectByExample(example);

                if (dataHistoryList != null && !dataHistoryList.isEmpty()){

                    _log.info("productLineId:{}", variable.getProductLineId());
                    _log.info("dataElementId:{}", variable.getDataElementId());
                    _log.info("data size:{}", dataHistoryList.size());

                    EamGrmVariableDataHistory firstData = null;
                    for (EamGrmVariableDataHistory history : dataHistoryList){
                        double value = NumberUtils.toDouble(history.getValue());

                        if (value != 0){
                            firstData = history;
                            break;
                        }
                    }

                    EamGrmVariableDataHistory lastData = null;
                    for (int i = dataHistoryList.size() - 1; i > 0; i--){
                        EamGrmVariableDataHistory history = dataHistoryList.get(i);

                        double value = NumberUtils.toDouble(history.getValue());

                        if (value != 0){
                            lastData = history;
                            break;
                        }
                    }

                    if (firstData != null && lastData != null){
                        pair = new Pair<>(firstData, lastData);
                    }

                }
            }


        } catch (Exception e) {
            _log.error("getFirstAndLastData: {}", e.getMessage());
        }

        return pair;

    }

    private void processShiftData(List<EamGrmVariableDataHistory> dataHistoryList){
        EamGrmVariableVO para = new EamGrmVariableVO();
        para.setGrmVariableIds(getGrmVariableIds(dataHistoryList));
        List<EamProductLineGrmDataElementVO> vos= eamApiMapper.getProductShiftGrmVariable(para); //shift list

        for(EamProductLineGrmDataElementVO vo : vos){
            EamGrmVariableDataHistory h = getEamGrmVariableDataHistory(dataHistoryList, vo.getId());
            para.setProductLineId(h.getProductLineId());
            para.setEquipmentId(h.getEquipmentId());
            para.setDataElementId(h.getDataElementId());
            para.setDataGroupId(h.getDataGroupId());
            para.setEquipmentDataGroupId(h.getEquipmentDataGroupId());
            para.setId(h.getEamGrmVariableId());

            String shiftNum = null, startDate, endDate;
            if(EamDateUtil.inThisTimes(vo.getMorningShiftStartTime(), vo.getMorningShiftEndTime())) {
                shiftNum = ProductShift.MORNING.getCode();
                startDate=vo.getMorningShiftStartTime();
                endDate =vo.getMorningShiftEndTime();
            }else if(EamDateUtil.inThisTimes(vo.getMiddleShiftStartTime(), vo.getMorningShiftEndTime())) {
                shiftNum = ProductShift.MIDDLE.getCode();
                startDate=vo.getMiddleShiftStartTime();
                endDate =vo.getMorningShiftEndTime();
            }else if(EamDateUtil.inThisTimes(vo.getNightShiftStartTime(), vo.getNightShiftEndTime())) {
                shiftNum = ProductShift.NIGHT.getCode();
                startDate=vo.getNightShiftStartTime();
                endDate =vo.getNightShiftEndTime();
            }else
                return;  //execption setting
            String dataType= vo.getDataType(); //analog  digital 开关量
            boolean isShift = vo.getStatisticByShift();
            boolean isSummary = vo.getSummation();
            if(isShift) {
                if("digital".equals(dataType)) {  //开关量
                    String key = "SWITCH-" + h.getProductLineId() + "-" + h.getEquipmentId() + "-" + h.getEquipmentDataGroupId() + "-" + h.getDataGroupId() + "-" + h.getDataElementId() + "-" + h.getEamGrmVariableId();
                    String preVal = RedisUtil.get(key);
                    int changeCount = 0;
                    String offOpen=h.getValue();
                    if (preVal == null && h.getValue() != null)
                        changeCount++;
                    else if (!preVal.equals(h.getValue()))
                        changeCount++;
                    if(changeCount > 0) {
                        RedisUtil.set(key, h.getValue());
                        handleGrmVariableDataByShift(para,String.valueOf(changeCount), offOpen, shiftNum,startDate, endDate);
                    }
                }else{ //模拟量
                    handleGrmVariableDataByShift(para,h.getValue(), isSummary, shiftNum,startDate, endDate);
                }
            }
        }
    }

    private void handleGrmVariableDataByShift(EamGrmVariable variable, String value,boolean isSummary, String shiftNum,String startDate,String endDate) {
        EamShiftDataElementValue data = getEamGrmVariableDataByShift(variable, shiftNum,startDate, endDate);
        if (data != null){
            if(isSummary) {
                BigDecimal newValue = BigDecimal.valueOf(Double.valueOf(value));
                newValue = NumberUtil.toBigDecimal(data.getValue()).add(newValue);
                data.setValue(newValue.toString());
            }else
                data.setValue(value);
            data.setUpdateTime(new Date());
            data.setShift(shiftNum);
            eamShiftDataElementValueService.updateByPrimaryKeySelective(data);
        }else{
            data = buildEamGrmVariableDataByShift(variable, value, "",  shiftNum);
            eamShiftDataElementValueService.insertSelective(data);
        }

    }

    private void handleGrmVariableDataByShift(EamGrmVariable variable, String value,String offOpen, String shiftNum,String startDate,String endDate) {
        EamShiftDataElementValue data = getEamGrmVariableDataByShift(variable, shiftNum,startDate, endDate);
        if (data != null){
            BigDecimal newValue = BigDecimal.valueOf(Double.valueOf(value));
            newValue = NumberUtil.toBigDecimal(data.getValue()).add(newValue);
            data.setValue(newValue.toString());
            data.setUpdateTime(new Date());
            data.setShift(shiftNum);
            eamShiftDataElementValueService.updateByPrimaryKeySelective(data);
        }else{
            data = buildEamGrmVariableDataByShift(variable, value, offOpen,  shiftNum);
            eamShiftDataElementValueService.insertSelective(data);
        }

    }

    private EamShiftDataElementValue getEamGrmVariableDataByShift(EamGrmVariable variable, String shiftNum, String startDate,String endDate){
        Pair<Date,Date> stEnd=getShiftStartEndTime(getDateStr(new Date(), "yyyy-MM-dd"),startDate, endDate);
        EamShiftDataElementValueExample example = new EamShiftDataElementValueExample();
        EamShiftDataElementValueExample.Criteria criteria = example.createCriteria();
        criteria.andEamGrmVariableIdEqualTo(variable.getId())
                .andProductLineIdEqualTo(variable.getProductLineId())
                .andEquipmentIdEqualTo(variable.getEquipmentId())
                .andDataElementIdEqualTo(variable.getDataElementId())
                .andShiftEqualTo(shiftNum)
                .andCreateTimeBetween(stEnd.getKey(),stEnd.getValue())
                .andDeleteFlagEqualTo(Boolean.FALSE);

        return eamShiftDataElementValueService.selectFirstByExample(example);
    }

    private EamShiftDataElementValue buildEamGrmVariableDataByShift(EamGrmVariable variable, String value,String offOpen, String shiftNum){
        EamShiftDataElementValue data = new EamShiftDataElementValue();
        data.setEamGrmVariableId(variable.getId());
        data.setProductLineId(variable.getProductLineId());
        data.setEquipmentId(variable.getEquipmentId());
        data.setDataElementId(variable.getDataElementId());
        data.setValue(value);
        data.setShift(shiftNum);
        data.setCreateTime(new Date());
        data.setUpdateTime(new Date());
        data.setDeleteFlag(Boolean.FALSE);
        return data;
    }

    private List<Integer> getGrmVariableIds(List<EamGrmVariableDataHistory> dataHistoryList){
        List<Integer> list=new ArrayList<Integer>();
        for(EamGrmVariableDataHistory h: dataHistoryList){
            list.add(h.getEamGrmVariableId());
        }
        return list.isEmpty()? null:list;
    }

    private EamGrmVariableDataHistory getEamGrmVariableDataHistory(List<EamGrmVariableDataHistory> dataHistoryList, Integer grmVariableId){
        for(EamGrmVariableDataHistory h: dataHistoryList){
            if(h.getEamGrmVariableId()==grmVariableId)
                return h;
        }
        return null;
    }
}
