package com.kuyun.eam.rpc.service;


import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamAlarmRecordVO;
import com.kuyun.eam.vo.EamTicketVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.List;

/**
 * 单元测试
 * Created by kuyun on 2017/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:META-INF/spring/applicationContext-dubbo-provider.xml",
        "classpath:META-INF/spring/applicationContext-dubbo-consumer.xml",
        "classpath:META-INF/spring/applicationContext.xml",
        "classpath:applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class EamServiceTest {

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamApiService eamApiService;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamEquipmentModelService eamEquipmentModelService;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamWarehouseService warehouseService;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamTicketService eamTicketService;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamTicketRecordService eamTicketRecordService;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private EamTicketTypeService eamTicketTypeService;
//    @Autowired
//    private EamSensorDataService eamSensorDataService;
    

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

//        eamApiService.selectLocation(new EamLocationVO());
//        eamEquipmentModelService.deleteByPrimaryKey(3);
//    		EamTicketType tt = new EamTicketType();
//    		tt.setName("手工工单");
//    		int pk = eamTicketTypeService.insert(tt);
//    		System.out.println("create ticket Type : "+ tt.getName()+" has PK :"+ pk);
//    		eamTicketTypeService.deleteByPrimaryKey(pk);
//    		System.out.println("delete PK :"+ pk);
//    		
//    		int offset = 0;
//		int limit = 10;
//		String sort = "";
//		String order = "";
//		
//    		EamTicketTypeExample eamTicketTypeExample = new EamTicketTypeExample();
//    		eamTicketTypeExample.setOffset(offset);
//    		eamTicketTypeExample.setLimit(limit);
//    		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
//    			eamTicketTypeExample.setOrderByClause(sort + ", " + order);
//    		}

//    		UpmsOrganization organization = eamUtils.getCurrentUserParentOrignization();
//
//    		if (organization != null){
//    			eamTicketTypeExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId())
//    			.andDeleteFlagEqualTo(Boolean.FALSE);
//    		}

//
//    		List<EamTicketType> rows = eamTicketTypeService.selectByExample(eamTicketTypeExample);
//    		long total = eamTicketTypeService.countByExample(eamTicketTypeExample);
//    		Map<String, Object> result = new HashMap<>();
//    		result.put("rows", rows);
//    		result.put("total", total);
//    		
//    		EamTicketExample eamTicketExample = new EamTicketExample();
//    		eamTicketExample.setOffset(0);
//    		eamTicketExample.setLimit(10);
//    		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
//    			eamTicketExample.setOrderByClause(sort + " " + order);
//    		}

//    		List<EamTicketVO> voRows = eamApiService.selectTicket(eamTicketExample);
//    		int total = eamTicketService.countByExample(eamTicketExample);
//    		System.out.println(total);
    		
    		
    		 
    }

//    @Test
//    public void testAlarm() {
//
//        EamSensorData sensorData = new EamSensorData();
////        sensorData.setSensorDataId(1);
//        sensorData.setEquipmentId("qMrJ5KqVwFUj1bkz");
//        sensorData.setStringValue("130");
//        sensorData.setSensorId(1);
//        sensorData.setDeleteFlag(Boolean.FALSE);
//        sensorData.setCreateTime(new Date());
//
////        eamSensorDataService.insertSelective(sensorData);
//
//
//
//        eamApiService.handleAlarm(sensorData);
//
//    }
//
//    @Test
//    public void testSelectAlarmRecords() {
//        EamAlarmRecordVO recordVO = new EamAlarmRecordVO();
//        recordVO.setStartDate(new Date("207/09/01"));
//        recordVO.setEndDate(new Date("207/09/11"));
//        List<EamAlarmRecordVO> result = eamApiService.selectAlarmRecords(recordVO);
//
//    }

}
