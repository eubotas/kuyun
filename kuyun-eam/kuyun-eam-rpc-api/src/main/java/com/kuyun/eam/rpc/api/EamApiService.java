package com.kuyun.eam.rpc.api;

import java.util.List;

import com.kuyun.eam.common.constant.CollectStatus;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamInventory;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamPartVO;
import com.kuyun.eam.vo.EamSensorDataVO;
import com.kuyun.eam.vo.EamSensorVO;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.dao.model.UpmsOrganization;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiService {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO);

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);
    
    List<EamTicketVO> selectTicket(EamTicketExample example);

    Integer inTask(EamInventory inventory);

    Integer outTask(EamInventory inventory);

    List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO);

    Tree getCityTree(UpmsOrganization org);

    List<SensorGroup> getSensorData(String equipmentId);

    int handleEquimpmentCollect(String jsonString, CollectStatus collectStatus);

    Integer createAlarm(String targetUserId, EamAlarm alarm);

    Integer updateAlarm(String targetUserId, EamAlarm alarm);

}
