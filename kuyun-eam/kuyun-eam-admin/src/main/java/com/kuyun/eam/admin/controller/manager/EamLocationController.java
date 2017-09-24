package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamLocation;
import com.kuyun.eam.dao.model.EamLocationExample;
import com.kuyun.eam.dao.model.EamWarehouse;
import com.kuyun.eam.dao.model.EamWarehouseExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamLocationService;
import com.kuyun.eam.rpc.api.EamWarehouseService;
import com.kuyun.eam.vo.EamLocationVO;
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
 * 仓位控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "仓位管理", description = "仓位管理")
@RequestMapping("/manage/location")
public class EamLocationController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamLocationController.class);
	
	@Autowired
	private EamLocationService eamLocationService;


	@Autowired
	private EamWarehouseService eamWarehouseService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;



	@ApiOperation(value = "仓位首页")
	@RequiresPermissions("eam:location:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/location/index.jsp";
	}

	@ApiOperation(value = "仓位列表")
	@RequiresPermissions("eam:location:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "warehouseId") String warehouseId) {
		EamLocationVO locationVO = new EamLocationVO();
		locationVO.setOffset(offset);
		locationVO.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			locationVO.setOrderByClause(sort + " " + order);
		}

		EamLocationExample example = new EamLocationExample();
		EamLocationExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (StringUtils.isNumeric(warehouseId)){
			locationVO.setWarehouseId(Integer.valueOf(warehouseId));
			criteria.andWarehouseIdEqualTo(Integer.valueOf(warehouseId));
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			locationVO.setCompanyId(company.getCompanyId());
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}

		List<EamLocationVO> rows = eamApiService.selectLocation(locationVO);
		int total = eamLocationService.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增仓位")
	@RequiresPermissions("eam:location:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		EamWarehouseExample warehousesExample = new EamWarehouseExample();
		List<EamWarehouse> warehouseList = eamWarehouseService.selectByExample(warehousesExample);
		modelMap.addAttribute("warehouseList", warehouseList);
		return "/manage/location/create.jsp";
	}

	@ApiOperation(value = "新增仓位")
	@RequiresPermissions("eam:location:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamLocation eamLocation) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamLocation.getNumber(), new LengthValidator(1, 20, "仓位编号"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamLocation);
		int count = eamLocationService.insertSelective(eamLocation);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除仓位")
	@RequiresPermissions("eam:location:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamLocationService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改仓位")
	@RequiresPermissions("eam:location:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamLocation locations = eamLocationService.selectByPrimaryKey(id);
		modelMap.put("location", locations);
		return "/manage/location/update.jsp";
	}

	@ApiOperation(value = "修改仓位")
	@RequiresPermissions("eam:location:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamLocation location) {
		ComplexResult result = FluentValidator.checkAll()
				.on(location.getNumber(), new LengthValidator(1, 20, "仓位编号"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		location.setLocationId(id);
		baseEntityUtil.updateAddtionalValue(location);
		int count = eamLocationService.updateByPrimaryKeySelective(location);
		return new EamResult(SUCCESS, count);
	}



}