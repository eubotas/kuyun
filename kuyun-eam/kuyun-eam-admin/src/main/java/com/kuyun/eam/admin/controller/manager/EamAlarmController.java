package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.AlarmTarget;
import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamAlarmVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsOrganizationEnum;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsUserCompanyVO;
import com.kuyun.upms.rpc.api.UpmsApiService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@Api(value = "报警管理", description = "报警管理")
@RequestMapping("/manage/{productLineId}/alarm")
public class EamAlarmController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamAlarmController.class);

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamAlarmService eamAlarmService;

	@Autowired
	private EamAlarmModelService eamAlarmModelService;

	@Autowired
	private EamAlarmModelController eamAlarmModelController;

	@Autowired
	private EamAlarmTargetUserService eamAlarmTargeUserService;

	@Autowired
	private EamDataElementService eamDataElementService;


	@Autowired
	private UpmsApiService upmsApiService;


	@ApiOperation(value = "报警设置首页")
	@RequiresPermissions("eam:alarm:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("productLineId") String productLineId, ModelMap modelMap) {
		modelMap.addAttribute("productLineId", productLineId);
		return "/manage/alarm/index.jsp";
	}

	@ApiOperation(value = "报警设置列表")
	@RequiresPermissions("eam:alarm:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("productLineId") String productLineId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamAlarmVO alarmVO = new EamAlarmVO();
		alarmVO.setOffset(offset);
		alarmVO.setLimit(limit);
		alarmVO.setDeleteFlag(Boolean.FALSE);
		alarmVO.setProductLineId(productLineId);


		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			alarmVO.setOrderByClause(sort + " " + order);
		}


		List<EamAlarmVO> rows = eamApiService.selectAlarms(alarmVO);
		long total = eamApiService.countAlarms(alarmVO);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "删除报警设置")
	@RequiresPermissions("eam:alarm:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		deleteAlarmTargetUser(ids);
		int count = eamAlarmService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	private void deleteAlarmTargetUser(String ids) {
		List<Integer> idList = new ArrayList<>();
		for(String id : ids.split("-")){
			idList.add(Integer.valueOf(id));
		}
		if (!idList.isEmpty()){
			EamAlarmTargetUserExample example = new EamAlarmTargetUserExample();
			example.createCriteria().andAlarmIdIn(idList);
			eamAlarmTargeUserService.deleteByExample(example);
		}
	}


	@RequiresPermissions("eam:alarm:read")
	@RequestMapping(value = "/all/index", method = RequestMethod.GET)
	public String allIndex(@PathVariable("productLineId") String productLineId, ModelMap modelMap) {
		modelMap.addAttribute("productLineId", productLineId);
		return "/manage/alarm/allAlarmModel.jsp";
	}

	@ApiOperation(value = "确认选择报警设置")
	@RequiresPermissions("eam:alarm:create")
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	@ResponseBody
	public Object confirm(@PathVariable("productLineId") String productLineId, String ids, ModelMap modelMap) {
		int result = eamApiService.createAlarms(productLineId, ids);
		return new UpmsResult(UpmsResultConstant.SUCCESS, result);
	}



	@ApiOperation(value = "修改报警设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		handleModelMap(id, modelMap);
		return "/manage/alarm/update.jsp";
	}

	@ApiOperation(value = "修改报警设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/updateIndex/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object updateIndex(@PathVariable("id") int id, ModelMap modelMap) {
		handleModelMap(id, modelMap);
		return new EamResult(SUCCESS, modelMap);
	}

	private void handleModelMap(int id, ModelMap modelMap) {
		EamDataElementExample example = new EamDataElementExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamDataElement> elements = eamDataElementService.selectByExample(example);

		EamAlarmTargetUserExample alarmTargetUserExample = new EamAlarmTargetUserExample();
		alarmTargetUserExample.createCriteria().andAlarmIdEqualTo(Integer.valueOf(id))
				.andDeleteFlagEqualTo(Boolean.FALSE);

		List<EamAlarmTargetUser> alarmTargetUsers = eamAlarmTargeUserService.selectByExample(alarmTargetUserExample);

		List<UpmsUser> users = upmsApiService.selectUsers(createUserCompanyVO());

		EamAlarm alarm = eamAlarmService.selectByPrimaryKey(id);
		modelMap.put("alarm", alarm);
		modelMap.put("elements", elements);
		modelMap.put("users", users);
		modelMap.put("alarmTargetUsers", alarmTargetUsers);
		modelMap.put("alarmTypes", AlarmType.values());
		modelMap.put("alarmTargets", AlarmTarget.values());
	}

	@ApiOperation(value = "修改报警设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request, EamAlarm alarm) {
		String[] targetUserIds = request.getParameterValues("alarmTargetUser");

		baseEntityUtil.updateAddtionalValue(alarm);
		int count = eamApiService.updateAlarm(targetUserIds, alarm);
		return new EamResult(SUCCESS, count);
	}


	@ApiOperation(value = "提醒设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/target/{ids}",method = RequestMethod.GET)
	public Object target(@PathVariable("productLineId") String productLineId,
							  @PathVariable("ids") String ids,
							  ModelMap modelMap) {

		handleModelMap(productLineId, ids, modelMap);
		return "/manage/alarm/target.jsp";
	}

	@ApiOperation(value = "提醒设置")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/targetIndex/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object targetIndex(@PathVariable("productLineId") String productLineId,
							  @PathVariable("ids") String ids,
							  ModelMap modelMap) {

		handleModelMap(productLineId, ids, modelMap);
		return new EamResult(SUCCESS, modelMap);
	}

	private void handleModelMap(String productLineId, String ids, ModelMap modelMap) {
		List<UpmsUser> alarmTargetUsers = upmsApiService.selectUsers(createUserCompanyVO());
		modelMap.put("productLineId", productLineId);
		modelMap.put("ids", ids);
		modelMap.put("alarmTargetUsers", alarmTargetUsers);
		modelMap.put("alarmTargets", AlarmTarget.values());
	}

	private UpmsUserCompanyVO createUserCompanyVO(){
		UpmsUserCompany userCompany = baseEntityUtil.getCurrentUserCompany();
		UpmsUserCompanyVO vo = new UpmsUserCompanyVO();
		vo.setOrgName(UpmsOrganizationEnum.ALARM.getName());
		vo.setCompanyId(userCompany.getCompanyId());
		return vo;
	}

	@ApiOperation(value = "提醒设置提交")
	@RequiresPermissions("eam:alarm:update")
	@RequestMapping(value = "/target/submit",method = RequestMethod.POST)
	@ResponseBody
	public Object targetSubmit(HttpServletRequest request, ModelMap modelMap) {
		String alarmTarget = request.getParameter("alarmTarget");
		String[] targetUserId = request.getParameterValues("alarmTargetUser");
		String ids = request.getParameter("ids");


		int count = eamApiService.updateAlarm(ids, alarmTarget, targetUserId);
		return new EamResult(SUCCESS, count);
	}



}