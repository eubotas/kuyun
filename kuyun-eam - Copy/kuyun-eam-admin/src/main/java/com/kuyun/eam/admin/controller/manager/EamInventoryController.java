package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamInventoryVO;
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

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 库存控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "库存管理", description = "库存管理")
@RequestMapping("/manage/inventory")
public class EamInventoryController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamInventoryController.class);
	
	@Autowired
	private EamInventoryService eamInventoryService;

	@Autowired
	private EamWarehouseService eamWarehouseService;

	@Autowired
	private EamPartsService eamPartsService;

	@Autowired
	private EamLocationService eamLocationService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "库存首页")
	@RequiresPermissions("eam:inventory:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/inventory/index.jsp";
	}

	@ApiOperation(value = "库存列表")
	@RequiresPermissions("eam:inventory:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamInventoryVO inventoryVO = new EamInventoryVO();
		inventoryVO.setOffset(offset);
		inventoryVO.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			inventoryVO.setOrderByClause(sort + " " + order);
		}
		EamInventoryExample inventoryExample = new EamInventoryExample();
		EamInventoryExample.Criteria criteria = inventoryExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			inventoryVO.setCompanyId(company.getCompanyId());
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}


		List<EamInventoryVO> rows = eamApiService.selectInventory(inventoryVO);
		int total = eamInventoryService.countByExample(inventoryExample);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增库存")
	@RequiresPermissions("eam:inventory:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		handleModelMap(modelMap);


		return "/manage/inventory/create.jsp";
	}

	private void handleModelMap(ModelMap modelMap) {
		EamWarehouseExample warehousesExample = new EamWarehouseExample();
		List<EamWarehouse> warehouseList = eamWarehouseService.selectByExample(warehousesExample);
		modelMap.addAttribute("warehouseList", warehouseList);

		EamLocationExample locationExample = new EamLocationExample();
		List<EamLocation> locationList = eamLocationService.selectByExample(locationExample);
		modelMap.addAttribute("locationList", locationList);

		EamPartsExample partsExample = new EamPartsExample();
		List<EamParts> partList = eamPartsService.selectByExample(partsExample);
		modelMap.addAttribute("partList", partList);
	}

	@ApiOperation(value = "新增库存")
	@RequiresPermissions("eam:inventory:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamInventory inventory) {
//		ComplexResult result = FluentValidator.checkAll()
//				.on(inventory.getQuantity(), new LengthValidator(1, 20, "库存名称"))
//				.doValidate()
//				.result(ResultCollectors.toComplex());
//		if (!result.isSuccess()) {
//			return new EamResult(INVALID_LENGTH, result.getErrors());
//		}
		baseEntityUtil.addAddtionalValue(inventory);
		int count = eamInventoryService.insertSelective(inventory);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除库存")
	@RequiresPermissions("eam:inventory:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamInventoryService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改库存")
	@RequiresPermissions("eam:inventory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamInventory inventory = eamInventoryService.selectByPrimaryKey(id);
		modelMap.put("inventory", inventory);
		handleModelMap(modelMap);
		return "/manage/inventory/update.jsp";
	}

	@ApiOperation(value = "修改库存")
	@RequiresPermissions("eam:inventory:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamInventory inventory) {
//		ComplexResult result = FluentValidator.checkAll()
//				.on(inventory.getName(), new LengthValidator(1, 20, "库存名称"))
//				.doValidate()
//				.result(ResultCollectors.toComplex());
//		if (!result.isSuccess()) {
//			return new EamResult(INVALID_LENGTH, result.getErrors());
//		}
		inventory.setInventoryId(id);
		baseEntityUtil.updateAddtionalValue(inventory);
		int count = eamInventoryService.updateByPrimaryKeySelective(inventory);
		return new EamResult(SUCCESS, count);
	}




}