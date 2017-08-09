package com.kuyun.eam.rpc.mapper;

import java.util.List;

import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamPartVO;
import com.kuyun.eam.vo.EamSensorDataVO;
import com.kuyun.eam.vo.EamSensorVO;
import com.kuyun.eam.vo.EamTicketVO;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiMapper {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO);

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);

    List<EamSensorDataVO> selectEamSensorData(EamSensorVO sensorVO);
    
    List<EamTicketVO> selectTicket(EamTicketExample example);

    List<EamSensorVO> selectSensorData(String equipmentId);
}
