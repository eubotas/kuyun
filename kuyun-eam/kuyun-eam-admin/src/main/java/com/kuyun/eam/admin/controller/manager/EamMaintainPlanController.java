package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.SizeValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamMaintainPlanVO;
import com.kuyun.eam.vo.EamPlanTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.JspUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsOrganizationExample;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
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
	private EamMaintainTicketService eamMaintainTicketService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	public EamEquipmentCategoryService eamEquipmentCategoryService;

	@Autowired
	public EamEquipmentService eamEquipmentService;

	@Autowired
	private UpmsOrganizationService upmsOrganizationService;

	@Autowired
	private EamCodeValueService eamCodeValueService;

	@Autowired
	public UpmsApiService upmsApiService;

	@Autowired
	public EamApiService eamApiService;

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
		criteria.andCompanyIdEqualTo(getCompanyId());

		EamMaintainPlanVO vo = new EamMaintainPlanVO();
		vo.setOffset(offset);
		vo.setLimit(limit);
		vo.setCompanyId(getCompanyId());

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamMaintainPlanExample.setOrderByClause(sort + " " + order);
			vo.setOrderByClause(sort + " " + order);
		}

		List<EamMaintainPlanVO> rows = eamApiService.listMaintainPlans(vo);
		long total = eamMaintainPlanService.countByExample(eamMaintainPlanExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "维修计划工单列表")
	@RequiresPermissions("eam:maintainPlan:read")
	@RequestMapping(value = "/{planId}/tickets", method = RequestMethod.GET)
	@ResponseBody
	public Object tickets( @PathVariable("planId") int planId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamMaintainTicketExample example = new EamMaintainTicketExample();
		example.setOffset(offset);
		example.setLimit(limit);
		EamMaintainTicketExample.Criteria criteria = example.createCriteria();
		criteria.andPlanIdEqualTo(planId);

		EamPlanTicketVO vo = new EamPlanTicketVO();
		vo.setOffset(offset);
		vo.setLimit(limit);
		vo.setPlanId(planId);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			example.setOrderByClause(sort + " " + order);
			vo.setOrderByClause(sort + " " + order);
		}

		List<EamPlanTicketVO> rows = eamApiService.selectTicketByPlan(vo);
		long total = eamMaintainTicketService.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增维修计划")
	@RequiresPermissions("eam:maintainPlan:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		setWebSelect(modelMap);
		return "/manage/maintainplan/create.jsp";
	}

	@ApiOperation(value = "新增维修计划")
	@RequiresPermissions("eam:maintainPlan:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
				.on(plan.getEquipmentCategoryId(), new NotNullValidator("设备类别"))
                .on(plan.getEquipmentId(), new NotNullValidator("设备名称"))
                .on(plan.getWorkContent(), new LengthValidator(1, 2000,"工单描述"))
                .on(plan.getNextMaintainDate(), new NotNullValidator("下个维护日期"))
                .on(plan.getMaintainFrequencyQuantity(), new SizeValidator(1, 10,"维护频率"))
                .on(plan.getMaintainFrequencyUnit(), new NotNullValidator("维护频率单位"))
                .on(plan.getRemindTime(), new SizeValidator(1,10,"维护提前提醒天数"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(plan);

        return new EamResult(SUCCESS, eamMaintainPlanService.createMaintainPlan(plan));
	}

	@ApiOperation(value = "删除维修计划")
	@RequiresPermissions("eam:maintainPlan:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		return new EamResult(SUCCESS, eamMaintainPlanService.deleteMaintainPlan(ids));
	}

    @ApiOperation(value = "维修计划详细")
    @RequiresPermissions("eam:maintainPlan:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int id, ModelMap modelMap) {

        EamMaintainPlanVO vo= eamApiService.getMaintainPlan(id);
        modelMap.put("plan", vo);
        return "/manage/maintainplan/detail.jsp";
    }

	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object update(@PathVariable("id") int id, ModelMap modelMap) {
		EamMaintainPlan eamMaintainPlan = eamMaintainPlanService.selectByPrimaryKey(id);
		Map map=new HashMap();
		setWebSelect(map);
		map.put("plan", eamMaintainPlan);
		map.put("MaintainDate",EamDateUtil.getDateStr(eamMaintainPlan.getNextMaintainDate()));
		return map;
	}

	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{planId}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
				.on(plan.getEquipmentCategoryId(), new NotNullValidator("设备类别"))
				.on(plan.getEquipmentId(), new NotNullValidator("设备名称"))
				.on(plan.getWorkContent(), new LengthValidator(1, 2000,"工单描述"))
				.on(plan.getNextMaintainDate(), new NotNullValidator("下个维护日期"))
				.on(plan.getMaintainFrequencyQuantity(), new SizeValidator(1, 10,"维护频率"))
				.on(plan.getMaintainFrequencyUnit(), new NotNullValidator("维护频率单位"))
				.on(plan.getRemindTime(), new SizeValidator(1,10,"维护提前提醒天数"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.updateAddtionalValue(plan);

		return new EamResult(SUCCESS, eamMaintainPlanService.updateMaintainPlan(plan));
	}


	private void setWebSelect(Map map){
		EamEquipmentCategoryExample example = new EamEquipmentCategoryExample();
		EamEquipmentCategoryExample.Criteria criteria2 = example.createCriteria();
		criteria2.andCompanyIdEqualTo(getCompanyId());
		List<EamEquipmentCategory> cats = eamEquipmentCategoryService.selectByExample( example );
		map.put("equipmentCategorys", JspUtil.getMapList(cats,"equipmentCategoryId","name"));

		EamEquipmentVO equipmentVO = new EamEquipmentVO();
		equipmentVO.setCompanyId(getCompanyId());
		List<EamEquipmentVO> rows = eamApiService.selectEquipments(equipmentVO);
		map.put("equipments", JspUtil.getMapList(rows,"equipmentId","name"));

		UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
		UpmsOrganizationExample.Criteria criteria = upmsOrganizationExample.createCriteria();
		criteria.andCompanyIdEqualTo(getCompanyId());
		List<UpmsOrganization> orgs = upmsOrganizationService.selectByExample(upmsOrganizationExample);
		map.put("orgs", JspUtil.getMapList(orgs,"organizationId","name"));

		EamCodeValueExample eamCodeValueExample = new EamCodeValueExample();
		EamCodeValueExample.Criteria codeCriteria = eamCodeValueExample.createCriteria();
		codeCriteria.andCategoryEqualTo("MAINTAIN_PLAN_UNIT");
		codeCriteria.andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamCodeValue> units = eamCodeValueService.selectByExample(eamCodeValueExample);
		map.put("units", JspUtil.getMapList(units,"codeValue","codeName"));
	}

	public int getCurrUserId(){
		return baseEntityUtil.getCurrentUser().getUserId();
	}

	public int getCompanyId(){
		int cId=-1;
		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
		if (company != null){
			cId = company.getCompanyId();
		}
		return cId;
	}
}