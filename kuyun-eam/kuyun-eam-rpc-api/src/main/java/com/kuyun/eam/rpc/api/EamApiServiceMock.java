package com.kuyun.eam.rpc.api;

import java.util.List;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.*;
import com.kuyun.upms.dao.model.UpmsOrganization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuyun.eam.dao.model.EamInventory;
import com.kuyun.eam.dao.model.EamTicketExample;

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
    public List<EamPartVO> selectPart(EamPartVO partVO) {
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
    public List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO) {
        return null;
    }

    @Override
    public Tree getCityTree(UpmsOrganization org) {
        return null;
    }

    @Override
    public List<SensorGroup> getSensorData(String equipmentId) {
        return null;
    }

    @Override
    public int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus) {
        return 0;
    }

    @Override
    public Integer createAlarm(String targetUserId, EamAlarm alarm) {
        return null;
    }

    @Override
    public Integer updateAlarm(String targetUserId, EamAlarm alarm) {
        return null;
    }

    @Override
    public void handleAlarm(EamSensorData sensorData) {

    }

    @Override
    public void handleAlarmOffline(String deviceId) {

    }

    @Override
    public List<EamAlarmRecordVO> selectAlarmRecords(EamAlarmRecordVO eamAlarmRecordVO) {
        return null;
    }
}
