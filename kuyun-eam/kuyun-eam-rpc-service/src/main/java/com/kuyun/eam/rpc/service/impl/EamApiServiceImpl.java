package com.kuyun.eam.rpc.service.impl;

import com.google.gson.Gson;
import com.kuyun.eam.alarm.AbstractAlarmHandler;
import com.kuyun.eam.alarm.AlarmTypeFactory;
import com.kuyun.eam.alarm.OfflineHandler;
import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.common.constant.DataType;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.IDS;
import com.kuyun.eam.pojo.sensor.SensorData;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.CityData;
import com.kuyun.eam.pojo.tree.Device;
import com.kuyun.eam.pojo.tree.ProvinceData;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.rpc.mapper.EamApiMapper;
import com.kuyun.eam.util.AreaUtil;
import com.kuyun.eam.util.ProtocolEnum;
import com.kuyun.eam.vo.*;
import com.kuyun.grm.rpc.api.GrmApiService;
import com.kuyun.modbus.rpc.api.ModbusSlaveRtuApiService;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import javafx.util.Pair;
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
        List<EamEquipmentVO> allEquipments = getEquipments(company);

        Map<String, List<EamEquipment>> groupByProvinceMap =
                allEquipments.stream().filter(equipment -> equipment.getProvince() != null).collect(Collectors.groupingBy(EamEquipment::getProvince));

        for (Map.Entry<String, List<EamEquipment>> entry : groupByProvinceMap.entrySet()) {
            ProvinceData provinceData = buildProvinceData(entry);
            tree.addProvice(provinceData);

            Map<String, List<EamEquipment>> groupByCityMap =
                    entry.getValue().stream().collect(Collectors.groupingBy(EamEquipment::getCity));

            for (Map.Entry<String, List<EamEquipment>> cityEntry : groupByCityMap.entrySet()) {
                CityData cityData = buildCityData(cityEntry);
                provinceData.addChildren(cityData);

                for (EamEquipment equipment : cityEntry.getValue()) {
                    Device device = buildDevice(equipment);
                    cityData.addChildren(device);
                }

            }
        }
        return tree;
    }

    private Device buildDevice(EamEquipment equipment) {
        Device device = new Device();
        device.setId(equipment.getEquipmentId());
        device.setName(equipment.getName());
        return device;
    }

    private CityData buildCityData(Map.Entry<String, List<EamEquipment>> entry) {
        List<EamEquipment> equipments = entry.getValue();
        int total = equipments.size();
        long online = equipments.stream().filter(equipment -> equipment.getIsOnline() != null)
                .filter(equipment -> equipment.getIsOnline()).count();
        String city = entry.getKey();
        String name = getCityName(city);

        BigDecimal latitude = getLatitude(equipments);
        BigDecimal longitude = getLongitude(equipments);

        CityData cityData = new CityData();
        cityData.setTotal(total);
        cityData.setOnline((int) online);
        cityData.setCode(city);
        cityData.setName(name);
        cityData.setLatitude(latitude);
        cityData.setLongitude(longitude);
        return cityData;
    }

    private BigDecimal getLongitude(List<EamEquipment> equipments) {
        BigDecimal result = null;
        if (!equipments.isEmpty()) {
            result = equipments.get(0).getLongitude();
        }
        return result;
    }

    private BigDecimal getLatitude(List<EamEquipment> equipments) {
        BigDecimal result = null;
        if (!equipments.isEmpty()) {
            result = equipments.get(0).getLatitude();
        }
        return result;
    }

    private String getCityName(String city) {
        return areaUtil.getName(city);
    }

    private String getProvinceName(String province) {
        return areaUtil.getName(province);
    }

    private ProvinceData buildProvinceData(Map.Entry<String, List<EamEquipment>> entry) {
        String province = entry.getKey();
        List<EamEquipment> equipments = entry.getValue();
        int total = equipments.size();
        long online = equipments.stream().filter(equipment -> equipment.getIsOnline() != null)
                .filter(equipment -> equipment.getIsOnline()).count();
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
        handleModbusRtuAction(collectStatus, eamEquipments);

    }

    private void handleModbusRtuAction(CollectStatus collectStatus, List<EamEquipment> eamEquipments) {
        List<EamEquipment> modbusEquipments = eamEquipments.stream().filter(equipment -> equipment.getEamEquipmentModel() != null)
                .filter(equipment -> ProtocolEnum.MODBUS_RTU.getId() == equipment.getEamEquipmentModel().getProtocolId().intValue()).collect(Collectors.toList());

        modbusEquipments.forEach(equipment -> {
            handleModbusRtuAction(collectStatus, equipment);

        });
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
    public void handleAlarm(EamSensorData sensorData) {
        EamAlarm alarm = eamApiMapper.selectAlarm(sensorData);
        if (alarm != null) {
            AbstractAlarmHandler alarmHandler = alarmTypeFactory.buildAlarmHandler(alarm);
            if (alarmHandler != null){
                alarmHandler.process(sensorData, alarm);
            }
        }
    }

    @Override
    public void handleAlarmOffline(String deviceId){
        EamAlarm alarm = getOfflineAlarmType(deviceId);
        if (alarm != null){
            EamSensorData sensorData = new EamSensorData();
            sensorData.setEquipmentId(deviceId);

            offlineHandler.createAlarmRecord(sensorData, alarm);
            offlineHandler.sendAlarmMessage(sensorData, alarm, false);
        }
    }

    private EamAlarm getOfflineAlarmType(String deviceId){
        EamAlarm result = null;

        List<EamAlarm> alarms = eamApiMapper.selectAlarms(deviceId);
        if (alarms != null && !alarms.isEmpty()){
            Optional<EamAlarm> optional = alarms.stream()
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
    public List<EamEquipmentVO> selectUnConnectDtuEquipments(EamEquipmentVO eamEquipmentVO) {
        return eamApiMapper.selectUnConnectDtuEquipments(eamEquipmentVO);
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

        handleAlarm(sensorData);
    }

    @Override
    public void processData(List<Pair<EamGrmVariable, String>> pairs) {
        for (Pair<EamGrmVariable, String> pair : pairs){
            handleGrmVariableData(pair);


            //TODO: handleAlarm(variableData);

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
        example.createCriteria().andGrmVariableIdEqualTo(variable.getId())
                .andDeleteFlagEqualTo(Boolean.FALSE);

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
        result.setGrmVariableId(variable.getId());
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
        result.setGrmVariableId(pair.getKey().getId());
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
        //1. get Product Line variable
        EamGrmVariableExample example = new EamGrmVariableExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);
        if (variables != null && !variables.isEmpty()){
            result.addAll(variables);
        }


        //2. get Equipment variable
        EamEquipmentExample equipmentExample = new EamEquipmentExample();
        equipmentExample.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamEquipment> eamEquipments = eamEquipmentService.selectByExample(equipmentExample);
        if (eamEquipments != null && !eamEquipments.isEmpty()){
            List<String> equipmentIds = eamEquipments.stream().map(x -> x.getEquipmentId()).collect(Collectors.toList());
            example = new EamGrmVariableExample();
            example.createCriteria().andEquipmentIdIn(equipmentIds).andDeleteFlagEqualTo(Boolean.FALSE);
            variables = eamGrmVariableService.selectByExample(example);
            if (variables != null && !variables.isEmpty()){
                result.addAll(variables);
            }

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
}
