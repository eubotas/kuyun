package com.kuyun.eam.rpc.api;

import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamPartVO;
import com.kuyun.eam.vo.EamTicketVO;

import java.util.List;

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

}
