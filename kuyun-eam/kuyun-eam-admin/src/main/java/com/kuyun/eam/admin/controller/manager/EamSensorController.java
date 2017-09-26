package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.util.StringUtil;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.DataFormat;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamEquipmentModelPropertiesService;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.eam.rpc.api.EamSensorService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "传感器管理", description = "传感器管理")
@RequestMapping("/manage/sensor")
public class EamSensorController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamSensorController.class);

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamSensorService eamSensorService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamEquipmentModelService eamEquipmentModelService;

	@Autowired
	private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

	@ApiOperation(value = "新增设备传感器")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamSensor sensor) {
		Object result = validateSensor(sensor);
		if (result != null) return result;

		return createOrUpate(sensor);
	}

	private Object createOrUpate(EamSensor sensor) {
		baseEntityUtil.addAddtionalValue(sensor);

		updateSensor(sensor);

		int count = 0;
		if (sensor.getSensorId() != null){
			count = eamSensorService.updateByPrimaryKeySelective(sensor);
			_log.info("Update Sensor");
		}else {
			count = eamSensorService.insertSelective(sensor);
			_log.info("Create Sensor");
		}
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "更新设备传感器")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(EamSensor sensor) {
		return createOrUpate(sensor);
	}

	private Object validateSensor(EamSensor sensor) {
		EamEquipmentModelProperties eamEquipmentModelProperties = eamEquipmentModelPropertiesService.selectByPrimaryKey(sensor.getEquipmentModelPropertyId());
		if (eamEquipmentModelProperties != null){
			EamEquipmentModel equipmentModel = eamEquipmentModelService.selectByPrimaryKey(eamEquipmentModelProperties.getEquipmentModelId());

			if (equipmentModel != null){
				//check modbus
				if ("1".equalsIgnoreCase(String.valueOf(equipmentModel.getProtocolId()))){
					ComplexResult result = FluentValidator.checkAll()
							.on(sensor.getSalveId(), new NotNullValidator("从站地址"))
							.on(sensor.getAddress(), new NotNullValidator("地址"))
//							.on(sensor.getPeriod(), new NotNullValidator("采集周期"))
							.doValidate()
							.result(ResultCollectors.toComplex());
					if (!result.isSuccess()) {
						return new EamResult(INVALID_LENGTH, result.getErrors());
					}
				}else if ("4".equalsIgnoreCase(String.valueOf(equipmentModel.getProtocolId()))){
					ComplexResult result = FluentValidator.checkAll()
							.on(sensor.getGrmAction(), new NotNullValidator("巨控 读写指令"))
							.on(sensor.getGrmVariable(), new NotNullValidator("巨控 变量名"))
							.doValidate()
							.result(ResultCollectors.toComplex());
					if (!result.isSuccess()) {
						return new EamResult(INVALID_LENGTH, result.getErrors());
					}
				}


			}

		}

		return null;
	}

	private void updateSensor(EamSensor sensor){
		if (!StringUtils.isEmpty(sensor.getDataFormat())){
			int quantity = DataFormat.getQuantity(sensor.getDataFormat());
			sensor.setQuantity(quantity);
		}
	}
}