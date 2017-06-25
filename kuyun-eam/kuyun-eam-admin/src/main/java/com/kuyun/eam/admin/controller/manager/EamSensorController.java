package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.admin.util.EamUtils;
import com.kuyun.eam.admin.util.ModbusFunctionCode;
import com.kuyun.eam.admin.util.ProtocolEnum;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamEquipmentModelPropertiesService;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.eam.rpc.api.EamSensorService;
import com.kuyun.upms.dao.model.UpmsOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private EamUtils eamUtils;

	@Autowired
	private EamSensorService eamSensorService;

	@ApiOperation(value = "新增设备传感器")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamSensor sensor) {
		ComplexResult result = FluentValidator.checkAll()
				.on(sensor.getSalveId(), new NotNullValidator("从站地址"))
				.on(sensor.getAddress(), new NotNullValidator("地址"))
				.on(sensor.getPeriod(), new NotNullValidator("采集周期"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamUtils.addAddtionalValue(sensor);

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
}