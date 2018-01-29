package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.DataType;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamDataElement;
import com.kuyun.eam.dao.model.EamDataElementExample;
import com.kuyun.eam.dao.model.EamEquipmentCategory;
import com.kuyun.eam.dao.model.EamEquipmentCategoryExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamDataElementService;
import com.kuyun.eam.rpc.api.EamEquipmentCategoryService;
import com.kuyun.eam.vo.EamDataElementVO;
import com.kuyun.eam.vo.EamLocationVO;
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
 * 数据点控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "数据点管理", description = "数据点管理")
@RequestMapping("/manage/dataElement")
public class EamDataElementController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamDataElementController.class);
	
	@Autowired
	private EamDataElementService dataElementService;


	@Autowired
	private EamEquipmentCategoryService equipmentCategoryService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;



	@ApiOperation(value = "数据点首页")
	@RequiresPermissions("eam:dataElement:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/dataElement/index.jsp";
	}

	@ApiOperation(value = "数据点列表")
	@RequiresPermissions("eam:dataElement:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "equipmentCategoryId") String equipmentCategoryId) {
		EamDataElementVO dataElementVO = new EamDataElementVO();
		dataElementVO.setOffset(offset);
		dataElementVO.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			dataElementVO.setOrderByClause(sort + " " + order);
		}

		EamDataElementExample example = new EamDataElementExample();
		EamDataElementExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (StringUtils.isNumeric(equipmentCategoryId)){
			dataElementVO.setEquipmentCategoryId(Integer.valueOf(equipmentCategoryId));
			criteria.andEquipmentCategoryIdEqualTo(Integer.valueOf(equipmentCategoryId));
		}



		List<EamDataElementVO> rows = eamApiService.selectDataElements(dataElementVO);
		int total = dataElementService.countByExample(example);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增数据点")
	@RequiresPermissions("eam:dataElement:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		buildModelMap(modelMap);
		return "/manage/dataElement/create.jsp";
	}

	private void buildModelMap(ModelMap modelMap) {
		EamEquipmentCategoryExample example = new EamEquipmentCategoryExample();
		List<EamEquipmentCategory> equipmentCategoryList = equipmentCategoryService.selectByExample(example);
		modelMap.addAttribute("equipmentCategoryList", equipmentCategoryList);
		modelMap.addAttribute("dataTypes", DataType.values());
	}

	@ApiOperation(value = "新增数据点")
	@RequiresPermissions("eam:dataElement:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamDataElement dataElement) {
		ComplexResult result = FluentValidator.checkAll()
				.on(dataElement.getName(), new LengthValidator(1, 20, "数据点编号"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(dataElement);
		int count = dataElementService.insertSelective(dataElement);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除数据点")
	@RequiresPermissions("eam:dataElement:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = dataElementService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改数据点")
	@RequiresPermissions("eam:dataElement:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		buildModelMap(modelMap);
		EamDataElement dataElement = dataElementService.selectByPrimaryKey(id);
		modelMap.put("dataElement", dataElement);
		return "/manage/dataElement/update.jsp";
	}

	@ApiOperation(value = "修改数据点")
	@RequiresPermissions("eam:dataElement:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamDataElement dataElement) {
		ComplexResult result = FluentValidator.checkAll()
				.on(dataElement.getName(), new LengthValidator(1, 20, "数据点编号"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		dataElement.setId(id);
		baseEntityUtil.updateAddtionalValue(dataElement);
		int count = dataElementService.updateByPrimaryKeySelective(dataElement);
		return new EamResult(SUCCESS, count);
	}



}