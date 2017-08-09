package com.kuyun.eam.rpc.service.impl;

import com.kuyun.eam.common.constant.DataType;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.sensor.SensorData;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.CityData;
import com.kuyun.eam.pojo.tree.Device;
import com.kuyun.eam.pojo.tree.ProvinceData;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.eam.rpc.api.EamInventoryService;
import com.kuyun.eam.rpc.mapper.EamApiMapper;
import com.kuyun.eam.util.AreaUtil;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsOrganization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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


    @Override
    public List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO) {

        return eamApiMapper.selectMaintenance(maintenanceVO);
    }

    @Override
    public List<EamLocationVO> selectLocation(EamLocationVO locationVO){
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
        if (inventory != null){
            inventory.setQuantity(eamInventory.getQuantity().add(inventory.getQuantity()));
            return inventoryService.updateByPrimaryKeySelective(inventory);
        }else {
            return inventoryService.insertSelective(eamInventory);
        }

    }

    private EamInventory getInventory(EamInventory eamInventory){
        EamInventoryExample example = new EamInventoryExample();
        example.createCriteria().andWarehouseIdEqualTo(eamInventory.getWarehouseId())
                .andLocationIdEqualTo(eamInventory.getLocationId())
                .andPartIdEqualTo(eamInventory.getPartId());
        return inventoryService.selectFirstByExample(example);
    }

    @Override
    public Integer outTask(EamInventory eamInventory) {
        EamInventory inventory = getInventory(eamInventory);
        if (inventory != null){
            inventory.setQuantity(inventory.getQuantity().subtract(eamInventory.getQuantity()));
            return inventoryService.updateByPrimaryKeySelective(inventory);
        }else {
            _log.warn("There is no inventory for " + eamInventory.getPartId());
            return 1;
        }

    }

    @Override
    public List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO){
        return eamApiMapper.selectEamSensorData(sensorVO);
    }

    @Override
    public Tree getCityTree(UpmsOrganization org) {
        Tree tree = new Tree();
        List<EamEquipment> allEquipments = getEquipments(org);

        Map<String, List<EamEquipment>> groupByProvinceMap =
                allEquipments.stream().collect(Collectors.groupingBy(EamEquipment::getProvince));

        for(Map.Entry<String, List<EamEquipment>> entry : groupByProvinceMap.entrySet()){
            ProvinceData provinceData = buildProvinceData(entry);
            tree.addProvice(provinceData);

            Map<String, List<EamEquipment>> groupByCityMap =
                    entry.getValue().stream().collect(Collectors.groupingBy(EamEquipment::getCity));

            for(Map.Entry<String, List<EamEquipment>> cityEntry : groupByCityMap.entrySet()){
                CityData cityData = buildCityData(cityEntry);
                provinceData.addChildren(cityData);

                for(EamEquipment equipment : cityEntry.getValue()){
                    Device device = buildDevice(equipment);
                    cityData.addEquip(device);
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
        cityData.setOnline((int)online);
        cityData.setCode(city);
        cityData.setName(name);
        cityData.setLatitude(latitude);
        cityData.setLongitude(longitude);
        return cityData;
    }

    private BigDecimal getLongitude(List<EamEquipment> equipments) {
        BigDecimal result = null;
        if (!equipments.isEmpty()){
            result = equipments.get(0).getLongitude();
        }
        return result;
    }

    private BigDecimal getLatitude(List<EamEquipment> equipments) {
        BigDecimal result = null;
        if (!equipments.isEmpty()){
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
        provinceData.setOnline((int)online);
        return provinceData;
    }



    private List<EamEquipment> getEquipments(UpmsOrganization org){

        EamEquipmentExample example = new EamEquipmentExample();
        if (org != null){
            example.createCriteria().andOrganizationIdEqualTo(org.getOrganizationId());
        }
        example.setOrderByClause("province, city asc");
        return eamEquipmentService.selectByExample(example);
    }

    public List<SensorGroup> getSensorData(String equipmentId){
        List<SensorGroup> result = new ArrayList<>();
        List<EamSensorVO> sensors = eamApiMapper.selectSensorData(equipmentId);

        Map<String, List<EamSensorVO>> groupByDataTypeMap =
                sensors.stream().collect(Collectors.groupingBy(EamSensorVO::getDataType));

        for(Map.Entry<String, List<EamSensorVO>> entry : groupByDataTypeMap.entrySet()){
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

        for(EamSensorVO sensor : sensorData){
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
}
