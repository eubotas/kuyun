package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.util.EamUtils;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamEquipmentModelProperties;
import com.kuyun.eam.dao.model.EamEquipmentModelPropertiesExample;
import com.kuyun.eam.rpc.api.EamEquipmentModelPropertiesService;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备模型参数控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "设备模型参数管理", description = "设备模型参数管理")
@RequestMapping("/manage/equipment/model/property")
public class EamEquipmentModelPropertiesController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentModelPropertiesController.class);
	
	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamEquipmentModelService eamEquipmentModelService;

	@Autowired
	private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

	@Autowired
	private EamUtils eamUtils;




	@ApiOperation(value = "设备模型参数首页")
	@RequiresPermissions("eam:equipmentModelProperty:read")
	@RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
	public String index(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return "/manage/equipment/model/property/index.jsp";
	}

	@ApiOperation(value = "设备模型参数列表")
	@RequiresPermissions("eam:equipmentModelProperty:read")
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("id") int id,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentModelPropertiesExample equipmentModelPropertiesExample = new EamEquipmentModelPropertiesExample();
		equipmentModelPropertiesExample.setOffset(offset);
		equipmentModelPropertiesExample.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			equipmentModelPropertiesExample.setOrderByClause(sort + " " + order);
		}
		equipmentModelPropertiesExample.createCriteria().andEquipmentModelIdEqualTo(id)
		.andDeleteFlagEqualTo(Boolean.FALSE);


//		UpmsOrganization organization = eamUtils.getCurrentUserParentOrignization();
//
//		if (organization != null){
//			equipmentModelPropertiesExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
//		}
		List<EamEquipmentModelProperties> rows = eamEquipmentModelPropertiesService.selectByExample(equipmentModelPropertiesExample);
		long total = eamEquipmentModelPropertiesService.countByExample(equipmentModelPropertiesExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增设备模型参数")
	@RequiresPermissions("eam:equipmentModelProperty:create")
	@RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
	public String create(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("id", id);
		return "/manage/equipment/model/property/create.jsp";
	}

	@ApiOperation(value = "新增设备模型参数")
	@RequiresPermissions("eam:equipmentModelProperty:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipmentModelProperties equipmentModelProperties) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentModelProperties.getName(), new LengthValidator(1, 20, "设备模型参数名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamUtils.addAddtionalValue(equipmentModelProperties);
		int count = eamEquipmentModelPropertiesService.insertSelective(equipmentModelProperties);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除设备模型参数")
	@RequiresPermissions("eam:equipmentModelProperty:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamEquipmentModelPropertiesService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改设备模型参数")
	@RequiresPermissions("eam:equipmentModelProperty:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamEquipmentModelProperties eamEquipmentModelProperties = eamEquipmentModelPropertiesService.selectByPrimaryKey(id);
		modelMap.put("equipmentModelProperties", eamEquipmentModelProperties);
		return "/manage/equipment/model/property/update.jsp";
	}

	@ApiOperation(value = "修改设备模型参数")
	@RequiresPermissions("eam:equipmentModelProperty:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamEquipmentModelProperties equipmentModelProperties) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentModelProperties.getName(), new LengthValidator(1, 20, "设备模型参数名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipmentModelProperties.setEquipmentModelPropertyId(id);
		eamUtils.updateAddtionalValue(equipmentModelProperties);
		int count = eamEquipmentModelPropertiesService.updateByPrimaryKeySelective(equipmentModelProperties);
		return new EamResult(SUCCESS, count);
	}



}