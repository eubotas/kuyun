package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamAlarmModel;
import com.kuyun.eam.dao.model.EamAlarmModelExample;
import com.kuyun.eam.dao.model.EamDataElement;
import com.kuyun.eam.dao.model.EamDataElementExample;
import com.kuyun.eam.rpc.api.EamAlarmModelService;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamDataElementService;
import com.kuyun.eam.vo.EamAlarmModelVO;
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
 * 报警模板控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "报警模板管理", description = "报警模板管理")
@RequestMapping("/manage/alarmModel")
public class EamAlarmModelController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamAlarmModelController.class);
	
	@Autowired
	private EamAlarmModelService alarmModelService;


	@Autowired
	private EamDataElementService dataElementService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;



	@ApiOperation(value = "报警模板首页")
	@RequiresPermissions("eam:alarmModel:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/alarmModel/index.jsp";
	}

	@ApiOperation(value = "报警模板列表")
	@RequiresPermissions("eam:alarmModel:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamAlarmModelVO alarmModelVO = new EamAlarmModelVO();
		alarmModelVO.setOffset(offset);
		alarmModelVO.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			alarmModelVO.setOrderByClause(sort + " " + order);
		}

		EamAlarmModelExample example = new EamAlarmModelExample();
		EamAlarmModelExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		List<EamAlarmModelVO> rows = eamApiService.selectAlarmModels(alarmModelVO);
		int total = alarmModelService.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增报警模板")
	@RequiresPermissions("eam:alarmModel:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		buildModelMap(modelMap);
		return "/manage/alarmModel/create.jsp";
	}

	private void buildModelMap(ModelMap modelMap) {
		EamDataElementExample example = new EamDataElementExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamDataElement> dataElements = dataElementService.selectByExample(example);
		modelMap.addAttribute("eamDataElements", dataElements);
		modelMap.addAttribute("alarmTypes", AlarmType.values());
	}

	@ApiOperation(value = "新增报警模板")
	@RequiresPermissions("eam:alarmModel:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamAlarmModel alarmModel) {
		ComplexResult result = FluentValidator.checkAll()
				.on(alarmModel.getName(), new LengthValidator(1, 20, "报警名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(alarmModel);
		int count = alarmModelService.insertSelective(alarmModel);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除报警模板")
	@RequiresPermissions("eam:alarmModel:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = alarmModelService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改报警模板")
	@RequiresPermissions("eam:alarmModel:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		buildModelMap(modelMap);
		EamAlarmModel alarmModel = alarmModelService.selectByPrimaryKey(id);
		modelMap.put("alarmModel", alarmModel);
		return "/manage/alarmModel/update.jsp";
	}

	@ApiOperation(value = "修改报警模板")
	@RequiresPermissions("eam:alarmModel:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamAlarmModel alarmModel) {
		ComplexResult result = FluentValidator.checkAll()
				.on(alarmModel.getName(), new LengthValidator(1, 20, "报警名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		alarmModel.setAlarmModelId(id);
		baseEntityUtil.updateAddtionalValue(alarmModel);
		int count = alarmModelService.updateByPrimaryKeySelective(alarmModel);
		return new EamResult(SUCCESS, count);
	}



}