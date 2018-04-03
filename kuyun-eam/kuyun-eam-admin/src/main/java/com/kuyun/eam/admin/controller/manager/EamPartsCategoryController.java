package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.dao.model.EamPartsCategoryExample;
import com.kuyun.eam.rpc.api.EamPartsCategoryService;
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
 * 配件类别控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "配件类别管理", description = "配件类别管理")
@RequestMapping("/manage/part/category")
public class EamPartsCategoryController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamPartsCategoryController.class);

	@Autowired
	private EamPartsCategoryService eamPartsCategoryService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "配件类别首页")
	@RequiresPermissions("eam:partCategory:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/part/category/index.jsp";
	}

	@ApiOperation(value = "配件类别列表")
	@RequiresPermissions("eam:partCategory:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamPartsCategoryExample partsCategoryExample = new EamPartsCategoryExample();
		partsCategoryExample.setOffset(offset);
		partsCategoryExample.setLimit(limit);
		EamPartsCategoryExample.Criteria criteria = partsCategoryExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			partsCategoryExample.setOrderByClause(sort + " " + order);
		}


		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}

		List<EamPartsCategory> rows = eamPartsCategoryService.selectByExample(partsCategoryExample);
		long total = eamPartsCategoryService.countByExample(partsCategoryExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增配件类别")
	@RequiresPermissions("eam:partCategory:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/part/category/create.jsp";
	}

	@ApiOperation(value = "新增配件类别")
	@RequiresPermissions("eam:partCategory:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamPartsCategory eamPartsCategory) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamPartsCategory.getName(), new LengthValidator(1, 20, "配件类别名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamPartsCategory);
		int count = eamPartsCategoryService.insertSelective(eamPartsCategory);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除配件类别")
	@RequiresPermissions("eam:partCategory:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamPartsCategoryService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改配件类别")
	@RequiresPermissions("eam:partCategory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object update(@PathVariable("id") int id) {
		EamPartsCategory partsCategory = eamPartsCategoryService.selectByPrimaryKey(id);
		Map map= new HashMap();
		map.put("partCategory", partsCategory);
		return map;
	}

	@ApiOperation(value = "修改配件类别")
	@RequiresPermissions("eam:partCategory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamPartsCategory partCategory) {
		ComplexResult result = FluentValidator.checkAll()
				.on(partCategory.getName(), new LengthValidator(1, 20, "配件类别名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		partCategory.setCategoryId(id);
		baseEntityUtil.updateAddtionalValue(partCategory);
		int count = eamPartsCategoryService.updateByPrimaryKeySelective(partCategory);
		return new EamResult(SUCCESS, count);
	}



}