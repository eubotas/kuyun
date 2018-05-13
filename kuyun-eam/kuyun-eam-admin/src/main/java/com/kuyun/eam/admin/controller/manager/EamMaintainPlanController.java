package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.CodeValueType;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.common.constant.OrgDepartment;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamMaintainPlanVO;
import com.kuyun.eam.vo.EamPlanTicketVO;
import com.kuyun.eam.vo.EamProductLineVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsOrganizationExample;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

	@Autowired
	private EamMaintainUserService eamMaintainUserService;

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
		EamMaintainPlanVO vo = new EamMaintainPlanVO();
		vo.setOffset(offset);
		vo.setLimit(limit);
		vo.setCompanyId(getCompanyId());

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			vo.setOrderByClause(sort + " " + order);
		}

		List<EamMaintainPlanVO> rows = eamApiService.listMaintainPlans(vo);
		Long total = eamApiService.countMaintainPlans(vo);
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
	public Object create(HttpServletRequest request, EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
                .on(plan.getEquipmentId(), new NotNullValidator("设备"))
                .on(plan.getWorkContent(), new NotNullValidator("工单描述"))
                .on(plan.getNextMaintainDate(), new NotNullValidator("下个维护日期"))
                .on(plan.getMaintainFrequencyQuantity(), new NotNullValidator("维护频率"))
                .on(plan.getMaintainFrequencyUnit(), new NotNullValidator("维护频率单位"))
                .on(plan.getRemindTime(), new NotNullValidator("维护提前提醒天数"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(plan);

		String[] maintainUserIds = request.getParameterValues("maintainUserId");

        return new EamResult(SUCCESS, eamMaintainPlanService.createMaintainPlan(plan, maintainUserIds));
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
	@ResponseBody
    public Object detail(@PathVariable("id") int id, ModelMap modelMap) {
        EamMaintainPlanVO vo= eamApiService.getMaintainPlan(id);
        modelMap.put("plan", vo);
		setWebSelect(modelMap);
		return new EamResult(SUCCESS, modelMap);
    }

	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object update(@PathVariable("id") int id, ModelMap modelMap) {
		EamMaintainPlan eamMaintainPlan = eamMaintainPlanService.selectByPrimaryKey(id);
		modelMap.put("plan", eamMaintainPlan);
		modelMap.put("maintainUserIds", getMaintainUserIds(id));
		modelMap.put("MaintainDate",EamDateUtil.getDateStr(eamMaintainPlan.getNextMaintainDate()));
		setWebSelect(modelMap);
		return new EamResult(SUCCESS, modelMap);
	}

	private List<EamMaintainUser> getMaintainUsers(int planId){
		EamMaintainUserExample example = new EamMaintainUserExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andPlanIdEqualTo(planId);
		return eamMaintainUserService.selectByExample(example);
	}

	private List<Integer> getMaintainUserIds(int planId){
		List<EamMaintainUser> users = getMaintainUsers(planId);
		List<Integer> result = new ArrayList<>(users != null ? users.size()  : 0);
		for(EamMaintainUser user : users){
			result.add(user.getUserId());
		}
		return result;

	}

	@ApiOperation(value = "修改维修计划")
	@RequiresPermissions("eam:maintainPlan:update")
	@RequestMapping(value = "/update/{planId}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, EamMaintainPlan plan) {
		ComplexResult result = FluentValidator.checkAll()
				.on(plan.getEquipmentId(), new NotNullValidator("设备"))
				.on(plan.getWorkContent(), new NotNullValidator("工单描述"))
                .on(plan.getNextMaintainDate(), new NotNullValidator("下个维护日期"))
                .on(plan.getMaintainFrequencyQuantity(), new NotNullValidator("维护频率"))
                .on(plan.getMaintainFrequencyUnit(), new NotNullValidator("维护频率单位"))
                .on(plan.getRemindTime(), new NotNullValidator("维护提前提醒天数"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.updateAddtionalValue(plan);

		String[] maintainUserIds = request.getParameterValues("maintainUserId");

		return new EamResult(SUCCESS, eamMaintainPlanService.updateMaintainPlan(plan, maintainUserIds));
	}




	private void setWebSelect(ModelMap modelMap){

		EamProductLineVO productLineVO = new EamProductLineVO();
		productLineVO.setDeleteFlag(Boolean.FALSE);
		productLineVO.setCompanyId(getCompanyId());
		List<EamProductLineVO> productLines = eamApiService.selectProductLines(productLineVO);
		modelMap.put("productLines", productLines);

		EamEquipmentCategoryExample example = new EamEquipmentCategoryExample();
		EamEquipmentCategoryExample.Criteria criteria2 = example.createCriteria();
		criteria2.andCompanyIdEqualTo(getCompanyId());
		List<EamEquipmentCategory> cats = eamEquipmentCategoryService.selectByExample( example );
		modelMap.put("equipmentCategorys", cats);

		EamEquipmentVO equipmentVO = new EamEquipmentVO();
		equipmentVO.setCompanyId(getCompanyId());
		List<EamEquipmentVO> rows = eamApiService.selectEquipments(equipmentVO);
		modelMap.put("equipments", rows);

		List<UpmsOrgUserVo> users = getOperatorUsers();
		modelMap.put("users", users);

		EamCodeValueExample eamCodeValueExample = new EamCodeValueExample();
		EamCodeValueExample.Criteria codeCriteria = eamCodeValueExample.createCriteria();
		codeCriteria.andCategoryEqualTo(CodeValueType.MAINTAIN_PLAN_UNIT);
		codeCriteria.andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamCodeValue> units = eamCodeValueService.selectByExample(eamCodeValueExample);
		modelMap.put("units", units);
	}


	@ApiOperation(value = "维保人员列表")
	@RequiresPermissions("eam:maintainPlan:create")
	@RequestMapping(value = "/operator/list", method = RequestMethod.GET)
	@ResponseBody
	public List<UpmsOrgUserVo> getOperatorUsers() {
		UpmsOrgUserVo orgUserVo = new UpmsOrgUserVo();

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
		if (company != null){
			orgUserVo.setCompanyId(company.getCompanyId());
		}
		orgUserVo.setOrgName(OrgDepartment.MAINTENANCE_DEPARTMENT.getName());

		return upmsApiService.selectOrgUsersByOrgNameCompanyId( orgUserVo);
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