package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamDataElementGroup;
import com.kuyun.eam.dao.model.EamDataElementGroupExample;
import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.dao.model.EamDataElementGroupExample;
import com.kuyun.eam.rpc.api.EamDataElementGroupService;
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
 * 数据点分组控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "数据点分组管理", description = "数据点分组管理")
@RequestMapping("/manage/dataElementGroup")
public class EamDataElementGroupController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamDataElementGroupController.class);

	@Autowired
	private EamDataElementGroupService dataElementGroupService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "数据点分组首页")
	@RequiresPermissions("eam:dataElementGroup:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/dataElementGroup/index.jsp";
	}

	@ApiOperation(value = "数据点分组列表")
	@RequiresPermissions("eam:dataElementGroup:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamDataElementGroupExample example = new EamDataElementGroupExample();
		example.setOffset(offset);
		example.setLimit(limit);

		EamDataElementGroupExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			example.setOrderByClause(sort + " " + order);
		}


		List<EamDataElementGroup> rows = dataElementGroupService.selectByExample(example);
		long total = dataElementGroupService.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增数据点分组")
	@RequiresPermissions("eam:dataElementGroup:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/dataElementGroup/create.jsp";
	}

	@ApiOperation(value = "新增数据点分组")
	@RequiresPermissions("eam:dataElementGroup:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamDataElementGroup dataElementGroup) {
		ComplexResult result = FluentValidator.checkAll()
				.on(dataElementGroup.getName(), new LengthValidator(1, 20, "数据点分组名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(dataElementGroup);
		int count = dataElementGroupService.insertSelective(dataElementGroup);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除数据点分组")
	@RequiresPermissions("eam:dataElementGroup:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = dataElementGroupService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改数据点分组")
	@RequiresPermissions("eam:dataElementGroup:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamDataElementGroup dataElementGroup = dataElementGroupService.selectByPrimaryKey(id);
		modelMap.put("dataElementGroup", dataElementGroup);
		return "/manage/dataElementGroup/update.jsp";
	}

	@ApiOperation(value = "修改数据点分组")
	@RequiresPermissions("eam:dataElementGroup:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamDataElementGroup dataElementGroup) {
		ComplexResult result = FluentValidator.checkAll()
				.on(dataElementGroup.getName(), new LengthValidator(1, 20, "数据点分组名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		dataElementGroup.setId(id);
		baseEntityUtil.updateAddtionalValue(dataElementGroup);
		int count = dataElementGroupService.updateByPrimaryKeySelective(dataElementGroup);
		return new EamResult(SUCCESS, count);
	}



}