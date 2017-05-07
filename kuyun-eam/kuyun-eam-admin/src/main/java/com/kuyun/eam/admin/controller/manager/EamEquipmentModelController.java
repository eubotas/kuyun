package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.util.EamUtils;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamEquipmentModel;
import com.kuyun.eam.dao.model.EamEquipmentModelExample;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
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
 * 设备模型控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "设备模型管理", description = "设备模型管理")
@RequestMapping("/manage/equipment/model")
public class EamEquipmentModelController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentModelController.class);
	
	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamEquipmentModelService eamEquipmentModelService;

	@Autowired
	private EamUtils eamUtils;


	@ApiOperation(value = "设备模型首页")
	@RequiresPermissions("eam:equipmentModel:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/equipment/model/index.jsp";
	}

	@ApiOperation(value = "设备模型列表")
	@RequiresPermissions("eam:equipmentModel:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentModelExample eamEquipmentModelExample = new EamEquipmentModelExample();
		eamEquipmentModelExample.setOffset(offset);
		eamEquipmentModelExample.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamEquipmentModelExample.setOrderByClause(sort + " " + order);
		}

		UpmsOrganization organization = eamUtils.getCurrentUserParentOrignization();

		if (organization != null){
			eamEquipmentModelExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
		}

		List<EamEquipmentModel> rows = eamEquipmentModelService.selectByExample(eamEquipmentModelExample);
		long total = eamEquipmentModelService.countByExample(eamEquipmentModelExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增设备模型")
	@RequiresPermissions("eam:equipmentModel:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/equipment/model/create.jsp";
	}

	@ApiOperation(value = "新增设备模型")
	@RequiresPermissions("eam:equipmentModel:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipmentModel equipmentModel) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentModel.getName(), new LengthValidator(1, 20, "设备模型名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamUtils.addAddtionalValue(equipmentModel);
		int count = eamEquipmentModelService.insertSelective(equipmentModel);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除设备模型")
	@RequiresPermissions("eam:equipmentModel:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamEquipmentModelService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改设备模型")
	@RequiresPermissions("eam:equipmentModel:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamEquipmentModel eamEquipmentModel = eamEquipmentModelService.selectByPrimaryKey(id);
		modelMap.put("equipmentModel", eamEquipmentModel);
		return "/manage/equipment/model/update.jsp";
	}

	@ApiOperation(value = "修改设备模型")
	@RequiresPermissions("eam:equipmentModel:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamEquipmentModel equipmentModel) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentModel.getName(), new LengthValidator(1, 20, "设备模型名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipmentModel.setEquipmentModelId(id);
		eamUtils.updateAddtionalValue(equipmentModel);
		int count = eamEquipmentModelService.updateByPrimaryKeySelective(equipmentModel);
		return new EamResult(SUCCESS, count);
	}



}