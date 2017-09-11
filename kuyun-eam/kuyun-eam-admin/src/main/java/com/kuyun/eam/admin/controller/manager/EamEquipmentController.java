package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.gson.Gson;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.util.ModbusFunctionCode;
import com.kuyun.eam.common.constant.BitOrder;
import com.kuyun.eam.common.constant.DataFormat;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.IDS;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.grm.common.Action;
import com.kuyun.upms.client.util.BaseEntityUtil;
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

import static com.kuyun.eam.common.constant.CollectStatus.NO_START;
import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "设备管理", description = "设备管理")
@RequestMapping("/manage/equipment")
public class EamEquipmentController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentController.class);
	
	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamEquipmentModelService eamEquipmentModelService;

	@Autowired
	private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamProtocolService protocolService;

	@Autowired
	private EamApiService eamApiService;

	@ApiOperation(value = "设备首页")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/equipment/index.jsp";
	}

	@ApiOperation(value = "设备列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentExample eamEquipmentExample = new EamEquipmentExample();
		eamEquipmentExample.setOffset(offset);
		eamEquipmentExample.setLimit(limit);
		EamEquipmentExample.Criteria criteria = eamEquipmentExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamEquipmentExample.setOrderByClause(sort + " " + order);
		}else {
			eamEquipmentExample.setOrderByClause("equipment_model_id desc");
		}

		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			criteria.andOrganizationIdEqualTo(organization.getOrganizationId());
		}

		List<EamEquipment> rows = eamEquipmentService.selectByExample(eamEquipmentExample);
		long total = eamEquipmentService.countByExample(eamEquipmentExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增设备")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create( ModelMap modelMap) {
		EamEquipmentModelExample example = new EamEquipmentModelExample();
		EamEquipmentModelExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			criteria.andOrganizationIdEqualTo(organization.getOrganizationId());
		}

		List<EamEquipmentModel> equipmentModels = eamEquipmentModelService.selectByExample(example);
		modelMap.put("equipmentModels", equipmentModels);

		return "/manage/equipment/create.jsp";
	}

	@ApiOperation(value = "新增设备")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipment eamEquipment) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamEquipment.getName(), new LengthValidator(1, 20, "设备名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamEquipment);
		int count = eamEquipmentService.insertSelective(eamEquipment);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除设备")
	@RequiresPermissions("eam:equipment:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		String jsonString = covertToJson(ids);

		eamApiService.handleEquimpmentCollect(jsonString, NO_START);
		int count = eamEquipmentService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	private String covertToJson(String ids) {
		IDS idsObj = new IDS();
		idsObj.setIds(ids);
		Gson gson = new Gson();
		return gson.toJson(idsObj);
	}

	@ApiOperation(value = "修改设备")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, ModelMap modelMap) {
		EamEquipmentModelExample equipmentModelExample = new EamEquipmentModelExample();
		equipmentModelExample.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamEquipmentModel> equipmentModels = eamEquipmentModelService.selectByExample(equipmentModelExample);
		modelMap.put("equipmentModels", equipmentModels);

		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(id);
		modelMap.put("equipment", equipment);
		return "/manage/equipment/update.jsp";
	}

	@ApiOperation(value = "获取设备信息")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getEamEquipment(@PathVariable("id") String id) {
		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(id);
		return new EamResult(SUCCESS, equipment);
	}

	@ApiOperation(value = "修改设备")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") String id, EamEquipment equipment) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipment.getName(), new LengthValidator(1, 20, "设备名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipment.setEquipmentId(id);
		baseEntityUtil.updateAddtionalValue(equipment);
		//handleSensor(id, equipment);
		int count = eamEquipmentService.updateByPrimaryKeySelective(equipment);
		return new EamResult(SUCCESS, count);
	}


	/*private void handleSensor(String id, EamEquipment equipment){
		EamEquipment equipmentFromDB = eamEquipmentService.selectByPrimaryKey(id);
		if (!equipmentFromDB.getEquipmentModelId().equals(equipment.getEquipmentModelId())){
			EamSensorExample example = new EamSensorExample();
			example.createCriteria().andEquipmentIdEqualTo(id);
			eamSensorService.deleteByExample(example);
		}

	}*/

	@ApiOperation(value = "设备接入")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/connect/{id}", method = RequestMethod.GET)
	public String connect(@PathVariable("id") String id, ModelMap modelMap) {
		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(id);
		EamEquipmentModel equipmentModel = equipment.getEamEquipmentModel();
		EamProtocol protocol = protocolService.selectByPrimaryKey(equipmentModel.getProtocolId());
		modelMap.put("equipment", equipment);
		modelMap.put("equipmentModel", equipmentModel);
		modelMap.put("protocol", protocol);
		return "/manage/equipment/connect.jsp";
	}

	@ApiOperation(value = "设备接入")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/connect/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object connect(@PathVariable("id") String id, EamEquipment equipment) {
		equipment.setEquipmentId(id);
		int count = eamEquipmentService.updateByPrimaryKeySelective(equipment);
		return new EamResult(SUCCESS, count);
	}

//	private void buildModelMap(@PathVariable("eId") String eId, @PathVariable("pId") int pId, ModelMap modelMap) {
//		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(eId);
//		EamEquipmentModelProperties eamEquipmentModelProperties = eamEquipmentModelPropertiesService.selectByPrimaryKey(pId);
//		//EamSensor sensor = getSensor(equipment, eamEquipmentModelProperties);
//
//		modelMap.put("equipment", equipment);
//		modelMap.put("equipmentModelProperties", eamEquipmentModelProperties);
////		if (sensor != null){
////			modelMap.put("sensor", sensor);
////		}
//	}

//	@RequiresPermissions("eam:equipment:update")
//	@RequestMapping(value = "/grm/{eId}/{pId}", method = RequestMethod.GET)
//	public Object grm(@PathVariable("eId") String eId, @PathVariable("pId") int pId, ModelMap modelMap) {
//		buildModelMap(eId, pId, modelMap);
//		modelMap.put("grmActions", Action.values());
//		modelMap.put("modbusFunctionCodes", ModbusFunctionCode.values());
//		modelMap.put("dataFormats", DataFormat.values());
//		modelMap.put("bitOrders", BitOrder.values());
//		return modelMap;
//	}
//
//
//
//    @ApiOperation(value = "巨控传感器参数")
//    @RequiresPermissions("eam:equipment:update")
//    @RequestMapping(value = "/sensor/grm/{eId}/{pId}", method = RequestMethod.GET)
//    @ResponseBody
//    public Object sensorGrm(@PathVariable("eId") String eId, @PathVariable("pId") int pId) {
//        Map<String, Object> result = buildHashMap(eId, pId);
//
//        result.put("grmActions", Action.values());
//        return result;
//    }

//    private Map<String, Object> buildHashMap(@PathVariable("eId") String eId, @PathVariable("pId") int pId) {
//        EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(eId);
//        EamEquipmentModelProperties eamEquipmentModelProperties = eamEquipmentModelPropertiesService.selectByPrimaryKey(pId);
////        EamSensor sensor = getSensor(equipment, eamEquipmentModelProperties);
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("equipment", equipment);
//        result.put("equipmentModelProperties", eamEquipmentModelProperties);
////        result.put("sensor", sensor);
//        return result;
//    }


//    public EamSensor getSensor(EamEquipment equipment, EamEquipmentModelProperties eamEquipmentModelProperties){
//		EamSensorExample example = new EamSensorExample();
//		example.createCriteria().andEquipmentIdEqualTo(equipment.getEquipmentId())
//				.andEquipmentModelPropertyIdEqualTo(eamEquipmentModelProperties.getEquipmentModelPropertyId()).andDeleteFlagEqualTo(Boolean.FALSE);
//
//
//		return eamSensorService.selectFirstByExample(example);
//	}

	@ApiOperation(value = "CityTree")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/city/tree", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityTree() {
		Tree tree = eamApiService.getCityTree(baseEntityUtil.getCurrentUserParentOrignization());
		return new EamResult(SUCCESS, tree);
	}

	@ApiOperation(value = "设备当前采集数据")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/sensor/data/{eId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getSensorData(@PathVariable("eId") String eId) {
		List<SensorGroup> sensorGroups = eamApiService.getSensorData(eId);
		return new EamResult(SUCCESS, sensorGroups);
	}
}