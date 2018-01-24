package com.kuyun.eam.rpc.service.impl;

import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.ProtocolEnum;
import com.kuyun.eam.vo.EamEquipmentModelPropertiesVO;
import com.kuyun.grm.rpc.api.GrmApiService;
import com.kuyun.modbus.rpc.api.ModbusSlaveRtuApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * Created by user on 2017-11-02.
 */
@Service
@Transactional
public class EamWriteDataServiceImpl implements EamWriteDataService {
    private static Logger _log = LoggerFactory.getLogger(EamWriteDataServiceImpl.class);

    @Autowired
    private EamEquipmentModelService equipmentModelService;

    @Autowired
    private GrmApiService grmApiService;

    @Autowired
    private ModbusSlaveRtuApiService modbusSlaveRtuApiService;

    @Autowired
    private EamSensorService eamSensorService;

    @Autowired
    private EamSensorWriteDataService eamSensorWriteDataService;

    @Autowired
    private EamSensorWriteDataHistoryService eamSensorWriteDataHistoryService;

    @Override
    public boolean sensorWrite(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO) {
        boolean result = false;
        EamEquipmentModel equipmentModel = equipmentModelService.selectByPrimaryKey(equipmentModelPropertiesVO.getEquipmentModelId());
        EamSensor sensor = eamSensorService.selectByPrimaryKey(equipmentModelPropertiesVO.getSensorId());

        if (ProtocolEnum.GRM.getId() == equipmentModel.getProtocolId()){
            //1. handle grm write
            result = handleGrmWrite(equipmentModelPropertiesVO, equipmentModel, sensor);
        }else if (ProtocolEnum.MODBUS_RTU.getId() == equipmentModel.getProtocolId()){
            //2. handle modbus write
            result = handleModbusRtuWrite(equipmentModelPropertiesVO, equipmentModel, sensor);
        }

        if (result){
            persistSensorWriteData(equipmentModelPropertiesVO, equipmentModel, sensor);

            persistSensorWriteDataHistory(equipmentModelPropertiesVO, equipmentModel, sensor);
        }

        return result;
    }

    private void persistSensorWriteData(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO, EamEquipmentModel equipmentModel, EamSensor sensor){
        EamSensorWriteDataExample example = new EamSensorWriteDataExample();
        example.createCriteria().andEquipmentIdEqualTo(equipmentModelPropertiesVO.getEquipmentId())
                .andSensorIdEqualTo(sensor.getSensorId())
                .andEquipmentModelIdEqualTo(equipmentModelPropertiesVO.getEquipmentModelId())
                .andEquipmentModelPropertyIdEqualTo(equipmentModelPropertiesVO.getEquipmentModelPropertyId());

        EamSensorWriteData data = eamSensorWriteDataService.selectFirstByExample(example);
        if (data == null){
            data = new EamSensorWriteData();
            data.setEquipmentId(equipmentModelPropertiesVO.getEquipmentId());
            data.setSensorId(sensor.getSensorId());
            data.setEquipmentModelId(equipmentModelPropertiesVO.getEquipmentModelId());
            data.setEquipmentModelPropertyId(equipmentModelPropertiesVO.getEquipmentModelPropertyId());
            data.setWriteValue(equipmentModelPropertiesVO.getWriteValue());
            data.setCreateTime(new Date());
            data.setDeleteFlag(Boolean.FALSE);
            eamSensorWriteDataService.insertSelective(data);

        }else {
            data.setWriteValue(equipmentModelPropertiesVO.getWriteValue());
            data.setUpdateTime(new Date());
            eamSensorWriteDataService.updateByPrimaryKeySelective(data);
        }

    }

    private void persistSensorWriteDataHistory(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO, EamEquipmentModel equipmentModel, EamSensor sensor){
        EamSensorWriteDataHistory history = new EamSensorWriteDataHistory();
        history.setEquipmentId(equipmentModelPropertiesVO.getEquipmentId());
        history.setSensorId(sensor.getSensorId());
        history.setEquipmentModelId(equipmentModelPropertiesVO.getEquipmentModelId());
        history.setEquipmentModelPropertyId(equipmentModelPropertiesVO.getEquipmentModelPropertyId());
        history.setWriteValue(equipmentModelPropertiesVO.getWriteValue());
        history.setCreateTime(new Date());
        history.setDeleteFlag(Boolean.FALSE);
        eamSensorWriteDataHistoryService.insertSelective(history);
    }

    private boolean handleModbusRtuWrite(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO, EamEquipmentModel equipmentModel, EamSensor sensor) {
        boolean result = false;
        String deviceId = equipmentModelPropertiesVO.getEquipmentId();
        int value = getWriteValue(equipmentModelPropertiesVO);

        //TODO: change me!
        sensor.setWriteNumber(value);

        _log.info("deviceId [{}], write data [{}]", deviceId, sensor);
        result = modbusSlaveRtuApiService.writeData(deviceId, sensor);

        return result;
    }

    private int getWriteValue(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO) {
        String writeValue = equipmentModelPropertiesVO.getWriteValue();
        int value = 0;
        try {
            value = Integer.parseInt(writeValue);
        }catch (Exception e){
            _log.error("Write Value Parse To Int Error, write value is {}", writeValue);
        }
        return value;
    }

    private boolean handleGrmWrite(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO, EamEquipmentModel equipmentModel, EamSensor sensor) {
        boolean result = false;
        String deviceId = equipmentModelPropertiesVO.getEquipmentId();
        String sensorName = sensor.getGrmVariable();
        String writeValue = equipmentModelPropertiesVO.getWriteValue();
        StringBuilder requestData = new StringBuilder();
        requestData.append(1).append("\r");
        requestData.append(sensorName).append(" ").append(writeValue);

        try {
            String[] rows = grmApiService.writeData(deviceId, requestData.toString());
            if (rows != null && rows.length == 3
                    && "OK".equalsIgnoreCase(rows[0])
                    && "1".equalsIgnoreCase(rows[1])
                    && "0".equalsIgnoreCase(rows[2])){
                result = true;
            }
        } catch (IOException e) {
            _log.error("GRM Write Error:" + e.getMessage());
        }

        return result;
    }
}
