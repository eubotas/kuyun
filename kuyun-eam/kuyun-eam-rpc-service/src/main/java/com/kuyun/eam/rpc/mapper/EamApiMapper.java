package com.kuyun.eam.rpc.mapper;

import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamPartVO;

import java.util.List;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiMapper {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceVO maintenanceVO);

    List<EamLocationVO> selectLocation(EamLocationVO locationVO);

    List<EamPartVO> selectPart(EamPartVO partVO);

    List<EamInventoryVO> selectInventory(EamInventoryVO inventoryVO);
}
