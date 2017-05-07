package com.kuyun.eam.rpc.api;

import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamPartVO;

import java.util.List;

/**
 * Created by user on 4/24/2017.
 */
public interface EamApiService {

    List<EamMaintenanceVO> selectMaintenance(EamMaintenanceExample example);

    List<EamLocationVO> selectLocation(EamLocationExample example);

    List<EamPartVO> selectPart(EamPartsExample example);

    List<EamInventoryVO> selectInventory(EamInventoryExample example);

    Integer inTask(EamInventory inventory);

    Integer outTask(EamInventory inventory);
}
