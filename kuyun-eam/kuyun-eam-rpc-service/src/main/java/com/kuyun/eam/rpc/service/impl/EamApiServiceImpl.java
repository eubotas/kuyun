package com.kuyun.eam.rpc.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.google.gson.Gson;
import com.kuyun.eam.alarm.AbstractAlarmHandler;
import com.kuyun.eam.alarm.AlarmTypeFactory;
import com.kuyun.eam.alarm.OfflineHandler;
import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.common.constant.DataType;
import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.IDS;
import com.kuyun.eam.pojo.Position;
import com.kuyun.eam.pojo.Positions;
import com.kuyun.eam.pojo.sensor.SensorData;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.rpc.mapper.EamApiMapper;
import com.kuyun.eam.util.AreaUtil;
import com.kuyun.eam.vo.*;
import com.kuyun.grm.rpc.api.GrmApiService;
import com.kuyun.modbus.rpc.api.ModbusSlaveRtuApiService;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import javafx.util.Pair;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.kuyun.eam.common.constant.CollectStatus.NO_START;
import static com.kuyun.eam.common.constant.CollectStatus.WORKING;


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
    EamEquipmentModelService equipmentModelService;

    @Autowired
    EamSensorService eamSensorService;

    @Autowired
    AreaUtil areaUtil;

    @Autowired
    private GrmApiService grmApiService;

    @Autowired
    private ModbusSlaveRtuApiService modbusSlaveRtuApiService;

    @Autowired
    private EamAlarmService eamAlarmService;

    @Autowired
    private EamAlarmTargetUserService eamAlarmTargetUserService;

    @Autowired
    private AlarmTypeFactory alarmTypeFactory;

    @Autowired
    private OfflineHandler offlineHandler;

    @Autowired
    private EamSensorDataService eamSensorDataService;

    @Autowired
    private EamSensorDataHistoryServiceImpl eamSensorDataHistoryService;

    @Autowired
    private EamEquipmentCompanyService eamEquipmentCompanyService;

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

    @Override
    public List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO) {

        return eamApiMapper.selectMaintenance(maintenanceVO);
    }

    @Override
    public List<EamLocationVO> selectLocation(EamLocationVO locationVO) {
        return eamApiMapper.selectLocation(locationVO);
    }

    @Override
    public List<EamPartVO> selectPart(EamPartVO partVO) {
        return eamApiMapper.selectPart(partVO);
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
    public List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO) {
        return eamApiMapper.selectEamSensorData(sensorVO);
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
    public List<SensorGroup> getSensorData(String equipmentId) {
        List<SensorGroup> result = new ArrayList<>();
        List<EamSensorVO> sensors = eamApiMapper.selectSensorData(equipmentId);

        Map<String, List<EamSensorVO>> groupByDataTypeMap =
                sensors.stream().collect(Collectors.groupingBy(EamSensorVO::getDataType));

        for (Map.Entry<String, List<EamSensorVO>> entry : groupByDataTypeMap.entrySet()) {
            result.add(buildSensorGroup(entry));
        }

        return result;
    }

    private SensorGroup buildSensorGroup(Map.Entry<String, List<EamSensorVO>> entry) {
        SensorGroup group = new SensorGroup();
        String dataType = entry.getKey();
        List<EamSensorVO> sensorData = entry.getValue();
        group.setType(dataType);
        group.setGroupName(DataType.getLabel(dataType));

        for (EamSensorVO sensor : sensorData) {
            SensorData data = buildSensorData(dataType, sensor);
            group.addVars(data);
        }
        return group;
    }

    private SensorData buildSensorData(String dataType, EamSensorVO sensor) {
        SensorData data = new SensorData();
        data.setName(sensor.getName());
        data.setUnit(sensor.getUnit());
        data.setShowchart(DataType.hasHistoryData(dataType));
        data.setVarid(String.valueOf(sensor.getSensorId()));
        data.setValue(sensor.getStringValue());
        data.setShowtype(sensor.getDisplayType());
        data.setShoworder(sensor.getSensorId());
        return data;
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

        //handle Modbus RTU
//        handleModbusRtuAction(collectStatus, eamEquipments);

    }

//    private void handleModbusRtuAction(CollectStatus collectStatus, List<EamEquipment> eamEquipments) {
//        List<EamEquipment> modbusEquipments = eamEquipments.stream().filter(equipment -> equipment.getEamEquipmentModel() != null)
//                .filter(equipment -> ProtocolEnum.MODBUS_RTU.getId() == equipment.getEamEquipmentModel().getProtocolId().intValue()).collect(Collectors.toList());
//
//        modbusEquipments.forEach(equipment -> {
//            handleModbusRtuAction(collectStatus, equipment);
//
//        });
//    }

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

    private void handleModbusRtuAction(CollectStatus collectStatus, EamEquipment equipment) {
        if (NO_START.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            modbusSlaveRtuApiService.pauseJob(equipment.getEquipmentId());
        } else if (WORKING.getCode().equalsIgnoreCase(collectStatus.getCode())) {
            modbusSlaveRtuApiService.startJob(equipment.getEquipmentId());
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
    public Integer updateAlarm(String targetUserId, EamAlarm alarm) {
        int result = eamAlarmService.updateByPrimaryKeySelective(alarm);
        updateEamAlarmTargetUser(alarm.getAlarmId(), Integer.valueOf(targetUserId));
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
    public int persistEquipment(UpmsUserCompany upmsUserCompany, EamEquipment equipment){
        eamEquipmentService.insertSelective(equipment);

        EamEquipmentCompany equipmentCompany = new EamEquipmentCompany();
        equipmentCompany.setCreateTime(new Date());
        equipmentCompany.setDeleteFlag(Boolean.FALSE);
        equipmentCompany.setCompanyId(upmsUserCompany.getCompanyId());
        equipmentCompany.setEquipmentId(equipment.getEquipmentId());

        return eamEquipmentCompanyService.insertSelective(equipmentCompany);

    }

    @Override
    public void processData(String deviceId, Integer sensorId, String data){
        EamSensorData sensorData = handleSensorData(deviceId, sensorId, data);
        handleSensorDataHistory(deviceId, sensorId, data);

        //handleAlarm(sensorData);
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
        }
    }

    private EamGrmVariableData handleGrmVariableData(Pair<EamGrmVariable, String> pair) {
        EamGrmVariable variable = pair.getKey();
        String value = pair.getValue();

        EamGrmVariableDataExample example = new EamGrmVariableDataExample();
        EamGrmVariableDataExample.Criteria criteria = example.createCriteria();
        criteria.andProductLineIdEqualTo(variable.getProductLineId());
        criteria.andDataElementIdEqualTo(variable.getDataElementId());
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        if (variable.getEquipmentId() == null){
            criteria.andEquipmentIdIsNull();
        }else {
            criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
        }

        if (variable.getDataGroupId() == null){
            criteria.andDataGroupIdIsNull();
        }else {
            criteria.andDataGroupIdEqualTo(variable.getDataGroupId());
        }

        if (variable.getEquipmentDataGroupId() == null){
            criteria.andEquipmentDataGroupIdIsNull();
        }else {
            criteria.andEquipmentDataGroupIdEqualTo(variable.getEquipmentDataGroupId());
        }

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
    public List<EamEquipmentModelPropertiesVO> selectEquipmentModelProperties(String equipmentId) {
        return eamApiMapper.selectEquipmentModelProperties(equipmentId);
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
        if (StringUtils.isNotEmpty(ids) && StringUtils.isNotEmpty(alarmTarget) && targetUserIds != null && targetUserIds.length > 0){
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
                eamAlarmService.updateByPrimaryKeySelective(alarm);

                //delete existing data
                EamAlarmTargetUserExample targetUserExample = new EamAlarmTargetUserExample();
                targetUserExample.createCriteria().andAlarmIdEqualTo(alarm.getAlarmId())
                        .andDeleteFlagEqualTo(Boolean.FALSE);

                eamAlarmTargetUserService.deleteByExample(targetUserExample);

                //create new data
                List<EamAlarmTargetUser> targetUsers = createEamAlarmTargetUsers(targetUserIds, alarm);
                eamAlarmTargetUserService.batchInsert(targetUsers);
            }

            return 1;
        }
        return 0;
    }

    private List<EamAlarmTargetUser> createEamAlarmTargetUsers(String[] targetUserIds, EamAlarm alarm) {
        List<EamAlarmTargetUser> result = new ArrayList<>();
        for (String targetUserId : targetUserIds){
            EamAlarmTargetUser targetUser = new EamAlarmTargetUser();
            targetUser.setUserId(Integer.valueOf(targetUserId));
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
        alarm.setEquipmentId(getEquipmentId(alarmModel));

        return alarm;
    }

    private String getEquipmentId(EamAlarmModel alarmModel) {
        String result = null;
        EamEquipmentDataGroupElemetsExample example = new EamEquipmentDataGroupElemetsExample();
        example.createCriteria().andDataElementIdEqualTo(alarmModel.getEamDataElementId())
        .andDeleteFlagEqualTo(Boolean.FALSE);

        EamEquipmentDataGroupElemets element = eamEquipmentDataGroupElemetsService.selectFirstByExample(example);

        if (element != null){
            result = element.getEquipmentId();
        }

        return result;
    }

    private EamSensorData handleSensorData(String deviceId, Integer sensorId, String data){
        EamSensorDataExample example = new EamSensorDataExample();
        example.createCriteria().andEquipmentIdEqualTo(deviceId)
                .andSensorIdEqualTo(sensorId)
                .andDeleteFlagEqualTo(Boolean.FALSE);

        EamSensorData sensorData = eamSensorDataService.selectFirstByExample(example);
        if (sensorData == null){
            sensorData = createSensorData(deviceId, sensorId, data);
            eamSensorDataService.insertSelective(sensorData);
        }else {
            Date now = new Date();
            sensorData.setCreateTime(now);
            sensorData.setUpdateTime(now);
            sensorData.setStringValue(data);
            eamSensorDataService.updateByPrimaryKeySelective(sensorData);
        }
        return sensorData;
    }

    private void
    handleSensorDataHistory(String deviceId, Integer sensorId, String data){
        EamSensorDataHistory dataHistory = createSensorDataHistory(deviceId, sensorId, data);
        eamSensorDataHistoryService.insertSelective(dataHistory);
    }

    private EamSensorDataHistory createSensorDataHistory(String deviceId, Integer sensorId, String data) {
        EamSensorDataHistory result = new EamSensorDataHistory();
        result.setEquipmentId(deviceId);
        result.setSensorId(sensorId);
        result.setStringValue(data);
        Date now = new Date();
        result.setCreateTime(now);
        result.setUpdateTime(now);
        result.setDeleteFlag(Boolean.FALSE);
        return result;
    }

    private EamSensorData createSensorData(String deviceId, Integer sensorId, String data) {
        EamSensorData result = new EamSensorData();
        result.setEquipmentId(deviceId);
        result.setSensorId(sensorId);
        result.setStringValue(data);
        Date now = new Date();
        result.setCreateTime(now);
        result.setUpdateTime(now);
        result.setDeleteFlag(Boolean.FALSE);
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
        int count = eamTicketAppointRecordService.insertSelective(ticketAppointRecord);
        rejectTicketStatus(ticketAppointRecord.getTicketId(), TicketStatus.INIT.getName());
        return count;
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
        if(TicketStatus.TO_PROCESS.getName().equals(ticket.getStatus()))
            updateTicketStatus(ticketId, TicketStatus.PROCESSING.getName());
        return i;
    }

    @Override
    public void completeTicket(EamTicketAssessment ticketAssessment, int[] ticketTag){
        eamTicketAssessmentService.createTicketAssessment(ticketAssessment, ticketTag);
        EamTicket ticket = eamTicketService.selectByPrimaryKey(ticketAssessment.getTicketId());
        if(TicketStatus.RESOLVED.getName().equals(ticket.getStatus()))
            updateTicketStatus(ticketAssessment.getTicketId(), TicketStatus.COMPLETE.getName());
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

}
