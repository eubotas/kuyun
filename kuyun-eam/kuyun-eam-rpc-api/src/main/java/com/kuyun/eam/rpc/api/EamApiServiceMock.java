package com.kuyun.eam.rpc.api;

import com.kuyun.eam.dao.model.EamInventory;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamPartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public Integer inTask(EamInventory inventory) {
        return null;
    }

    @Override
    public Integer outTask(EamInventory inventory) {
        return null;
    }
}
