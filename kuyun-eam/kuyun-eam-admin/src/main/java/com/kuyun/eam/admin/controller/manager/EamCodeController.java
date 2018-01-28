package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamCodeValueExample;
import com.kuyun.eam.rpc.api.EamCodeValueService;
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
 * 数据字典控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "数据字典管理", description = "数据字典管理")
@RequestMapping("/manage/codeValue")
public class EamCodeController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamCodeController.class);
	

	@Autowired
	private EamCodeValueService eamCodeValueService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "数据字典管理首页")
	@RequiresPermissions("eam:codeValue:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/common/code/index.jsp";
	}

	@ApiOperation(value = "数据字典列表")
	@RequiresPermissions("eam:codeValue:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamCodeValueExample eamCodeValueExample = new EamCodeValueExample();
		eamCodeValueExample.setOffset(offset);
		eamCodeValueExample.setLimit(limit);
		EamCodeValueExample.Criteria criteria = eamCodeValueExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamCodeValueExample.setOrderByClause(sort + " " + order);
		}

		List<EamCodeValue> rows = eamCodeValueService.selectByExample(eamCodeValueExample);
		long total = eamCodeValueService.countByExample(eamCodeValueExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增数据字典")
	@RequiresPermissions("eam:codeValue:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/common/code/create.jsp";
	}

	@ApiOperation(value = "新增数据字典")
	@RequiresPermissions("eam:codeValue:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamCodeValue code) {
		ComplexResult result = FluentValidator.checkAll()
				.on(code.getCategory(), new NotNullValidator("类别"))
                .on(code.getCodeValue(), new NotNullValidator("Code"))
                .on(code.getCodeName(), new NotNullValidator("Code名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(code);
		int count = eamCodeValueService.insertSelective(code);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除数据字典")
	@RequiresPermissions("eam:codeValue:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamCodeValueService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改数据字典")
	@RequiresPermissions("eam:codeValue:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamCodeValue eamCodeValue = eamCodeValueService.selectByPrimaryKey(id);
		modelMap.put("code", eamCodeValue);
		return "/manage/common/code/update.jsp";
	}

	@ApiOperation(value = "修改数据字典")
	@RequiresPermissions("eam:codeValue:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamCodeValue code) {
		ComplexResult result = FluentValidator.checkAll()
                .on(code.getCategory(), new NotNullValidator("类别"))
                .on(code.getCodeValue(), new NotNullValidator("Code"))
                .on(code.getCodeName(), new NotNullValidator("Code名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		code.setId(id);
		baseEntityUtil.updateAddtionalValue(code);
		int count = eamCodeValueService.updateByPrimaryKeySelective(code);
		return new EamResult(SUCCESS, count);
	}



}