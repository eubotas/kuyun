package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.upms.client.util.BaseEntityUtil;
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
 * 维修计划控制器
 * Created by kuyun on 2018/1/24.
 */
@Controller
@Api(value = "维修计划管理", description = "维修计划管理")
@RequestMapping("/manage/maintainPlan")
public class EamMaintainPlanController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamMaintainPlanController.class);
	

	@Autowired
	private EamMaintainPlanService eamMaintainPlanService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "维修计划管理首页")
	@RequiresPermissions("eam:maintainPlan:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/maintainplan/index.jsp";
	}

	@ApiOperation(value = "维修计划列表")
	@RequiresPermissions("eam:maintainPlan:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamMaintainPlanExample eamMaintainPlanExample = new EamMaintainPlanExample();
		eamMaintainPlanExample.setOffset(offset);
		eamMaintainPlanExample.setLimit(limit);
		EamMaintainPlanExample.Criteria criteria = eamMaintainPlanExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamMaintainPlanExample.setOrderByClause(sort + " " + order);
		}

		List<EamMaintainPlan> rows = eamMaintainPlanService.selectByExample(eamMaintainPlanExample);
		long total = eamMaintainPlanService.countByExample(eamMaintainPlanExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增维修计划")
	@RequiresPermissions("eam:maintainPlan:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/maintainplan/create.jsp";
	}

	@ApiOperation(value = "新增维修计划")

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
				.on(plan.getEquipmentCategoryId(), new NotNullValidator("设备类别"))
                .on(plan.getEquipmentId(), new NotNullValidator("设备名称"))
                .on(plan.getWorkContent(), new NotNullValidator("工单描述"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(plan);
		int count = eamMaintainPlanService.insertSelective(plan);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除维修计划")
	@RequiresPermissions("eam:maintainPlan:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamMaintainPlanService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamMaintainPlan eamMaintainPlan = eamMaintainPlanService.selectByPrimaryKey(id);
		modelMap.put("ticketType", eamMaintainPlan);
		return "/manage/maintainplan/update.jsp";
	}

	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
				.on(plan.getEquipmentCategoryId(), new NotNullValidator("设备类别"))
				.on(plan.getEquipmentId(), new NotNullValidator("设备名称"))
				.on(plan.getWorkContent(), new NotNullValidator("工单描述"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		plan.setPlanId(id);
		baseEntityUtil.updateAddtionalValue(plan);
		int count = eamMaintainPlanService.updateByPrimaryKeySelective(plan);
		return new EamResult(SUCCESS, count);
	}



}