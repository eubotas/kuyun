package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.util.EamUtils;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.eam.rpc.api.EamMaintenanceService;
import com.kuyun.eam.rpc.api.EamPartsService;
import com.kuyun.eam.vo.EamMaintenanceVO;
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
 * 维保控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "维保管理", description = "维保管理")
@RequestMapping("/manage/maintain")
public class EamMaintenanceController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamMaintenanceController.class);
	
	@Autowired
	private EamMaintenanceService eamMaintenanceService;

	@Autowired
	private EamUtils eamUtils;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamPartsService eamPartsService;

	@Autowired
	private UpmsApiService upmsApiService;

	@Autowired
	private EamApiService eamApiService;

	@ApiOperation(value = "维保首页")
	@RequiresPermissions("eam:maintain:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/maintain/index.jsp";
	}

	@ApiOperation(value = "维保列表")
	@RequiresPermissions("eam:maintain:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamMaintenanceExample maintenanceExample = new EamMaintenanceExample();
		maintenanceExample.setOffset(offset);
		maintenanceExample.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			maintenanceExample.setOrderByClause(sort + " " + order);
		}
		UpmsOrganization organization = eamUtils.getCurrentUserParentOrignization();

		if (organization != null){
			maintenanceExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
		}
		List<EamMaintenanceVO> rows = eamApiService.selectMaintenance(maintenanceExample);
		long total = eamMaintenanceService.countByExample(maintenanceExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增维保")
	@RequiresPermissions("eam:maintain:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		handelModelMap(modelMap);

		return "/manage/maintain/create.jsp";
	}

	private void handelModelMap(ModelMap modelMap){
		EamEquipmentExample eamEquipmentExample = new EamEquipmentExample();
		List<EamEquipment> equipmentList = eamEquipmentService.selectByExample(eamEquipmentExample);
		modelMap.addAttribute("equipmentList", equipmentList);

		EamPartsExample partsExample = new EamPartsExample();
		List<EamParts> partList = eamPartsService.selectByExample(partsExample);
		modelMap.addAttribute("partList", partList);

		UpmsUser user = eamUtils.getCurrentUser();
		List<UpmsUser> userList = upmsApiService.selectUsersByUserId(user.getUserId());
		modelMap.addAttribute("userList", userList);

	}

	@ApiOperation(value = "新增维保")
	@RequiresPermissions("eam:maintain:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamMaintenance eamMaintain) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamMaintain.getReason(), new LengthValidator(1, 20, "维保原因"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamUtils.addAddtionalValue(eamMaintain);
		int count = eamMaintenanceService.insertSelective(eamMaintain);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除维保")
	@RequiresPermissions("eam:maintain:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamMaintenanceService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改维保")
	@RequiresPermissions("eam:maintain:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamMaintenance maintain = eamMaintenanceService.selectByPrimaryKey(id);
		modelMap.put("maintain", maintain);
		handelModelMap(modelMap);
		return "/manage/maintain/update.jsp";
	}

	@ApiOperation(value = "修改维保")
	@RequiresPermissions("eam:maintain:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamMaintenance maintain) {
		ComplexResult result = FluentValidator.checkAll()
				.on(maintain.getReason(), new LengthValidator(1, 20, "维保原因"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		maintain.setMaintenanceId(id);
		eamUtils.updateAddtionalValue(maintain);
		int count = eamMaintenanceService.updateByPrimaryKeySelective(maintain);
		return new EamResult(SUCCESS, count);
	}


}