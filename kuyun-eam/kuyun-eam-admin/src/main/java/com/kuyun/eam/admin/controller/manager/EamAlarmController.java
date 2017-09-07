package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.rpc.api.EamAlarmService;
import com.kuyun.eam.rpc.api.EamAlarmTargetUserService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "报警管理", description = "传感器管理")
@RequestMapping("/manage/alarm")
public class EamAlarmController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamAlarmController.class);

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamAlarmService eamAlarmService;

	@Autowired
	private EamAlarmTargetUserService eamAlarmTargetUserService;


	@ApiOperation(value = "新增报警设置")
	@RequiresPermissions("eam:alarm:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamAlarm alarm) {
//		Object result = validateSensor(sensor);
//		if (result != null) return result;

		return createOrUpate(alarm);
	}

	private Object createOrUpate(EamAlarm alarm) {
		baseEntityUtil.addAddtionalValue(alarm);

		int count = 0;
		if (alarm.getAlarmId() != null){
			count = eamAlarmService.updateByPrimaryKeySelective(alarm);
			_log.info("Update Alarm");
		}else {
			count = eamAlarmService.insertSelective(alarm);
			_log.info("Create Alarm");
		}
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "更新报警设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(EamAlarm alarm) {
		return createOrUpate(alarm);
	}

//	private Object validateSensor(EamSensor sensor) {
//		EamEquipmentModel equipmentModel = eamEquipmentModelService.selectByPrimaryKey(sensor.getEquipmentModelPropertyId());
//
//		if (equipmentModel != null){
//			//check modbus
//			if ("1".equalsIgnoreCase(String.valueOf(equipmentModel.getProtocolId()))){
//				ComplexResult result = FluentValidator.checkAll()
//						.on(sensor.getSalveId(), new NotNullValidator("从站地址"))
//						.on(sensor.getAddress(), new NotNullValidator("地址"))
//						.on(sensor.getPeriod(), new NotNullValidator("采集周期"))
//						.doValidate()
//						.result(ResultCollectors.toComplex());
//				if (!result.isSuccess()) {
//					return new EamResult(INVALID_LENGTH, result.getErrors());
//				}
//			}else if ("4".equalsIgnoreCase(String.valueOf(equipmentModel.getProtocolId()))){
//				ComplexResult result = FluentValidator.checkAll()
//						.on(sensor.getGrmAction(), new NotNullValidator("巨控 读写指令"))
//						.on(sensor.getGrmVariable(), new NotNullValidator("巨控 变量名"))
//						.on(sensor.getGrmVariableOrder(), new NotNullValidator("巨控 读写变量顺序"))
//						.doValidate()
//						.result(ResultCollectors.toComplex());
//				if (!result.isSuccess()) {
//					return new EamResult(INVALID_LENGTH, result.getErrors());
//				}
//			}
//
//
//		}
//
//
//		return null;
//	}


}