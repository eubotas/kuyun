package com.kuyun.eam.rpc.service.impl;

import com.google.gson.Gson;
import com.kuyun.common.mail.service.EmailService;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.eam.alarm.AbstractAlarmHandler;
import com.kuyun.eam.alarm.AlarmTypeFactory;
import com.kuyun.eam.alarm.OfflineHandler;
import com.kuyun.eam.common.constant.AlarmTarget;
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
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.model.UpmsUserExample;
import com.kuyun.upms.rpc.api.UpmsUserService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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
        List<EamEquipment> allEquipments = getEquipments(company);

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


    private List<EamEquipment> getEquipments(UpmsUserCompany company) {

        EamEquipmentExample example = new EamEquipmentExample();
        EamEquipmentExample.Criteria criteria = example.createCriteria();
        if (company != null){
            criteria.andCompanyIdEqualTo(company.getCompanyId());
        }
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        example.setOrderByClause("province, city asc");
        return eamEquipmentService.selectByExample(example);
    }

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


    public int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus) {

        _log.info("json=" + jsonString);

        Gson gson = new Gson();
        IDS ids = gson.fromJson(jsonString, IDS.class);

        String[] idArray = ids.getIds().split("-");
        EamEquipment equipment = new EamEquipment();
        equipment.setCollectStatus(collectStatus.getCode());

        EamEquipmentExample example = new EamEquipmentExample();
        example.createCriteria().andEquipmentIdIn(Arrays.asList(idArray));

        handleCollectAction(collectStatus, example);

        return eamEquipmentService.updateByExampleSelective(equipment, example);
    }


    private void handleCollectAction(CollectStatus collectStatus, EamEquipmentExample example) {
        List<EamEquipment> eamEquipments = eamEquipmentService.selectByExample(example);
        // handle GRM
        handleGRMAction(collectStatus, eamEquipments);

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

    private void handleGRMAction(CollectStatus collectStatus, List<EamEquipment> eamEquipments) {
        List<EamEquipment> grmEquipments = eamEquipments.stream().filter(equipment -> equipment.getEamEquipmentModel() != null)
                .filter(equipment -> ProtocolEnum.GRM.getId() == equipment.getEamEquipmentModel().getProtocolId().intValue()).collect(Collectors.toList());

        grmEquipments.forEach(equipment -> {
            try {
                handleGrmAction(collectStatus, equipment);
            } catch (SchedulerException e) {
                _log.error("GRM action error:" + e.getMessage());
            }
        });

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
        int alarmId = eamAlarmService.insertSelective(alarm);
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

    public void handleAlarm(EamSensorData sensorData) {
        eamSensorDataService.insertSelective(sensorData);

        EamAlarm alarm = eamApiMapper.selectAlarm(sensorData);
        if (alarm != null) {
            AbstractAlarmHandler alarmHandler = alarmTypeFactory.buildAlarmHandler(alarm);

            alarmHandler.process(sensorData, alarm);
        }
    }

    public void handleAlarmOffline(String deviceId){
        EamAlarm alarm = getOfflineAlarmType(deviceId);
        if (alarm != null){
            EamSensorData sensorData = new EamSensorData();
            sensorData.setEquipmentId(deviceId);
            offlineHandler.createAlarmRecord(sensorData, alarm);
            offlineHandler.sendAlarmMessage(sensorData, alarm);
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

    public List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO){
        return eamApiMapper.selectAlarmRecords(eamAlarmRecordVO);
    }

}
