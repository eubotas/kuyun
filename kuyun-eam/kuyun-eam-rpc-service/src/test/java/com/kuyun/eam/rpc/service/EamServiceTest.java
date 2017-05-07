package com.kuyun.eam.rpc.service;

import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.eam.rpc.api.EamWarehouseService;
import com.kuyun.eam.vo.EamLocationVO;
import com.kuyun.eam.vo.EamMaintenanceVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * 单元测试
 * Created by kuyun on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class EamServiceTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EamApiService eamApiService;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private EamEquipmentModelService eamEquipmentModelService;

    @Autowired
    private EamWarehouseService warehouseService;

    @Test
    public void list() {


//        List<EamMaintenanceVO> maintenances = eamApiService.selectMaintenance(new EamMaintenanceExample());
//        System.out.println("maintenance size = " + maintenances.size());

//        EamEquipmentModelExample eamEquipmentModelExample = new EamEquipmentModelExample();
//        eamEquipmentModelExample.setLimit(10);
//        eamEquipmentModelExample.setOffset(0);
//
//        List<EamEquipmentModel> rows = eamEquipmentModelService.selectByExample(eamEquipmentModelExample);
//
//        System.out.println("rows size = " + rows.size());

//        EamWarehouse warehouse = new EamWarehouse();
//        warehouse.setName("dd");
//
//        warehouseService.insertSelective(warehouse);
//        warehouseService.deleteByPrimaryKey(1);
//
//        EamMaintenanceExample eamMaintenanceExample = new EamMaintenanceExample();
//        eamMaintenanceExample.createCriteria().andMaintenanceIdEqualTo(1).andEquipmentIdEqualTo(1);
//        eamApiService.selectMaintenance(eamMaintenanceExample);

        eamApiService.selectLocation(new EamLocationVO());
    }

}
