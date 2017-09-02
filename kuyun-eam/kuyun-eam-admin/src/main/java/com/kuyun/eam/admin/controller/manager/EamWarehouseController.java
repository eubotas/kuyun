package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamWarehouse;
import com.kuyun.eam.dao.model.EamWarehouseExample;
import com.kuyun.eam.rpc.api.EamWarehouseService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
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
 * 仓库控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "仓库管理", description = "仓库管理")
@RequestMapping("/manage/warehouse")
public class EamWarehouseController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamWarehouseController.class);
	
	@Autowired
	private EamWarehouseService eamWarehouseService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;



	@ApiOperation(value = "仓库首页")
	@RequiresPermissions("eam:warehouse:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/warehouse/index.jsp";
	}

	@ApiOperation(value = "仓库列表")
	@RequiresPermissions("eam:warehouse:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamWarehouseExample warehousesExample = new EamWarehouseExample();
		warehousesExample.setOffset(offset);
		warehousesExample.setLimit(limit);
		warehousesExample.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			warehousesExample.setOrderByClause(sort + " " + order);
		}
		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			warehousesExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
		}
		List<EamWarehouse> rows = eamWarehouseService.selectByExample(warehousesExample);
		long total = eamWarehouseService.countByExample(warehousesExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增仓库")
	@RequiresPermissions("eam:warehouse:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/warehouse/create.jsp";
	}

	@ApiOperation(value = "新增仓库")
	@RequiresPermissions("eam:warehouse:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamWarehouse warehouse) {
		ComplexResult result = FluentValidator.checkAll()
				.on(warehouse.getName(), new LengthValidator(1, 20, "仓库名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(warehouse);
		int count = eamWarehouseService.insertSelective(warehouse);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除仓库")
	@RequiresPermissions("eam:warehouse:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamWarehouseService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改仓库")
	@RequiresPermissions("eam:warehouse:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamWarehouse warehouses = eamWarehouseService.selectByPrimaryKey(id);
		modelMap.put("warehouse", warehouses);
		return "/manage/warehouse/update.jsp";
	}

	@ApiOperation(value = "修改仓库")
	@RequiresPermissions("eam:warehouse:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamWarehouse warehouse) {
		ComplexResult result = FluentValidator.checkAll()
				.on(warehouse.getName(), new LengthValidator(1, 20, "仓库名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		warehouse.setWarehouseId(id);
		baseEntityUtil.updateAddtionalValue(warehouse);
		int count = eamWarehouseService.updateByPrimaryKeySelective(warehouse);
		return new EamResult(SUCCESS, count);
	}



}