package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamPartVO;
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
 * 配件控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "配件管理", description = "配件管理")
@RequestMapping("/manage/part")
public class EamPartsController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamPartsController.class);
	
	@Autowired
	private EamPartsService eamPartsService;

	@Autowired
	private EamPartsCategoryService eamPartsCategoryService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamInventoryService eamInventoryService;

	@Autowired
	private EamWarehouseService eamWarehouseService;
	@Autowired
	private EamLocationService eamLocationService;



	@ApiOperation(value = "配件首页")
	@RequiresPermissions("eam:part:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/part/index.jsp";
	}

	@ApiOperation(value = "配件列表")
	@RequiresPermissions("eam:part:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamPartVO partVO = new EamPartVO();
		partVO.setOffset(offset);
		partVO.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			partVO.setOrderByClause(sort + " " + order);
		}
		EamPartsExample partExample = new EamPartsExample();
		EamPartsExample.Criteria criteria = partExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			partVO.setOrganizationId(organization.getOrganizationId());
			criteria.andOrganizationIdEqualTo(organization.getOrganizationId());
		}

		List<EamPartVO> rows = eamApiService.selectPart(partVO);
		int total = eamPartsService.countByExample(partExample);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增配件")
	@RequiresPermissions("eam:part:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		handleModelMap(modelMap);

		return "/manage/part/create.jsp";
	}

	private void handleModelMap(ModelMap modelMap) {
		EamPartsCategoryExample categoryExample = new EamPartsCategoryExample();
		List<EamPartsCategory> categoryList = eamPartsCategoryService.selectByExample(categoryExample);
		modelMap.addAttribute("categoryList", categoryList);
	}

	@ApiOperation(value = "新增配件")
	@RequiresPermissions("eam:part:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamParts eamPart) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamPart.getName(), new LengthValidator(1, 20, "配件名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamPart);
		int count = eamPartsService.insertSelective(eamPart);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除配件")
	@RequiresPermissions("eam:part:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamPartsService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改配件")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamParts parts = eamPartsService.selectByPrimaryKey(id);
		modelMap.put("part", parts);
		handleModelMap(modelMap);
		return "/manage/part/update.jsp";
	}

	@ApiOperation(value = "修改配件")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamParts part) {
		ComplexResult result = FluentValidator.checkAll()
				.on(part.getName(), new LengthValidator(1, 20, "配件名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		part.setPartId(id);
		baseEntityUtil.updateAddtionalValue(part);
		int count = eamPartsService.updateByPrimaryKeySelective(part);
		return new EamResult(SUCCESS, count);
	}

	private void putWarehouseAndLocationInModelMap(ModelMap modelMap) {
		EamWarehouseExample warehousesExample = new EamWarehouseExample();
		List<EamWarehouse> warehouseList = eamWarehouseService.selectByExample(warehousesExample);
		modelMap.addAttribute("warehouseList", warehouseList);

		EamLocationExample locationExample = new EamLocationExample();
		List<EamLocation> locationList = eamLocationService.selectByExample(locationExample);
		modelMap.addAttribute("locationList", locationList);
	}

	@ApiOperation(value = "配件入库")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/intask/{id}", method = RequestMethod.GET)
	public String intask(@PathVariable("id") int id, ModelMap modelMap) {
		EamParts parts = eamPartsService.selectByPrimaryKey(id);
		modelMap.put("part", parts);
		putWarehouseAndLocationInModelMap(modelMap);
		return "/manage/part/intask/index.jsp";
	}

	@ApiOperation(value = "配件入库")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/intask", method = RequestMethod.POST)
	@ResponseBody
	public Object intask(EamInventory inventory) {
		baseEntityUtil.addAddtionalValue(inventory);
		int count = eamApiService.inTask(inventory);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "配件出库")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/outtask/{id}", method = RequestMethod.GET)
	public String outtask(@PathVariable("id") int id, ModelMap modelMap) {
		EamParts parts = eamPartsService.selectByPrimaryKey(id);
		modelMap.put("part", parts);
		putWarehouseAndLocationInModelMap(modelMap);
		return "/manage/part/outtask/index.jsp";
	}

	@ApiOperation(value = "配件出库")
	@RequiresPermissions("eam:part:update")
	@RequestMapping(value = "/outtask", method = RequestMethod.POST)
	@ResponseBody
	public Object outtask(EamInventory inventory) {
		baseEntityUtil.addAddtionalValue(inventory);
		int count = eamApiService.outTask(inventory);
		return new EamResult(SUCCESS, count);
	}

}