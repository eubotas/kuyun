package com.kuyun.eam.rpc.service.impl;

import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamInventoryService;
import com.kuyun.eam.rpc.mapper.EamApiMapper;
import com.kuyun.eam.vo.EamInventoryVO;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import com.kuyun.eam.vo.EamPartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Override
    public List<EamMaintenanceVO> selectMaintenance(EamMaintenanceExample eamMaintenanceExample) {

        return eamApiMapper.selectMaintenance(eamMaintenanceExample);
    }

    @Override
    public List<EamLocationVO> selectLocation(EamLocationExample example){
        return eamApiMapper.selectLocation(example);
    }

    @Override
    public List<EamPartVO> selectPart(EamPartsExample eamPartsExample) {
        return eamApiMapper.selectPart(eamPartsExample);
    }

    @Override
    public List<EamInventoryVO> selectInventory(EamInventoryExample eamInventoryExample) {
        return eamApiMapper.selectInventory(eamInventoryExample);
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

}
