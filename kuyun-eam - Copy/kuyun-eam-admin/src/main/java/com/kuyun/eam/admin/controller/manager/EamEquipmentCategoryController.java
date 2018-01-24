package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamEquipmentCategory;
import com.kuyun.eam.dao.model.EamEquipmentCategoryExample;
import com.kuyun.eam.rpc.api.EamEquipmentCategoryService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
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
 * 设备类别控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "设备类别管理", description = "设备类别管理")
@RequestMapping("/manage/equipment/category")
public class EamEquipmentCategoryController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentCategoryController.class);
	
	@Autowired
	private EamEquipmentCategoryService eamEquipmentCategoryService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "设备类别首页")
	@RequiresPermissions("eam:equipmentCategory:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/equipment/category/index.jsp";
	}

	@ApiOperation(value = "设备类别列表")
	@RequiresPermissions("eam:equipmentCategory:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		
		EamEquipmentCategoryExample eamEquipmentCategoryExample = new EamEquipmentCategoryExample();
		eamEquipmentCategoryExample.setOffset(offset);
		eamEquipmentCategoryExample.setLimit(limit);

		EamEquipmentCategoryExample.Criteria criteria = eamEquipmentCategoryExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamEquipmentCategoryExample.setOrderByClause(sort + " " + order);
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}


		List<EamEquipmentCategory> rows = eamEquipmentCategoryService.selectByExample(eamEquipmentCategoryExample);
		long total = eamEquipmentCategoryService.countByExample(eamEquipmentCategoryExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增设备类别")
	@RequiresPermissions("eam:equipmentCategory:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		return "/manage/equipment/category/create.jsp";
	}

	@ApiOperation(value = "新增设备类别")
	@RequiresPermissions("eam:equipmentCategory:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipmentCategory equipmentCategory) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentCategory.getName(), new LengthValidator(1, 20, "设备类别名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(equipmentCategory);
		int count = eamEquipmentCategoryService.insertSelective(equipmentCategory);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除设备类别")
	@RequiresPermissions("eam:equipmentCategory:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamEquipmentCategoryService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改设备类别")
	@RequiresPermissions("eam:equipmentCategory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamEquipmentCategory eamEquipmentCategory = eamEquipmentCategoryService.selectByPrimaryKey(id);
		modelMap.put("equipmentCategory", eamEquipmentCategory);
		return "/manage/equipment/category/update.jsp";
	}

	@ApiOperation(value = "修改设备类别")
	@RequiresPermissions("eam:equipmentCategory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamEquipmentCategory equipmentCategory) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentCategory.getName(), new LengthValidator(1, 20, "设备类别名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipmentCategory.setEquipmentCategoryId(id);
		baseEntityUtil.updateAddtionalValue(equipmentCategory);
		int count = eamEquipmentCategoryService.updateByPrimaryKeySelective(equipmentCategory);
		return new EamResult(SUCCESS, count);
	}

}