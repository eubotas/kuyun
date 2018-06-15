package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamEquipmentProduct;
import com.kuyun.eam.dao.model.EamEquipmentProductExample;
import com.kuyun.eam.rpc.api.EamEquipmentProductService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 生产品相控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "生产品相管理", description = "生产品相管理")
@RequestMapping("/manage/{equipmentId}/product")
public class EamEquipmentProductController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(EamEquipmentProductController.class);
	
	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamEquipmentProductService eamEquipmentProductService;


	@ApiOperation(value = "生产品相列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("equipmentId") String equipmentId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentProductExample example = new EamEquipmentProductExample();
		example.setOffset(offset);
		example.setLimit(limit);

		EamEquipmentProductExample.Criteria criteria = example.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);
		criteria.andEquipmentIdEqualTo(equipmentId);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			example.setOrderByClause(sort + " " + order);
		}


		List<EamEquipmentProduct> rows = eamEquipmentProductService.selectByExample(example);
		long total = eamEquipmentProductService.countByExample(example);
		Map<String, Object> result = new HashMap<>(2);
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "删除生产品相")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamEquipmentProductService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "新增生产品相")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipmentProduct equipmentProduct) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentProduct.getMaterielNumber(), new LengthValidator(1, 10, "物料ID"))
				.on(equipmentProduct.getMaterielName(), new LengthValidator(1, 20, "物料名称"))
				.on(equipmentProduct.getCapacity(), new NotNullValidator("额定产能"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(equipmentProduct);
		int count = eamEquipmentProductService.insertSelective(equipmentProduct);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改生产品相")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamEquipmentProduct equipmentProduct) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipmentProduct.getMaterielNumber(), new LengthValidator(1, 10, "物料ID"))
				.on(equipmentProduct.getMaterielName(), new LengthValidator(1, 20, "物料名称"))
				.on(equipmentProduct.getCapacity(), new NotNullValidator("额定产能"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipmentProduct.setId(id);
		baseEntityUtil.updateAddtionalValue(equipmentProduct);
		int count = eamEquipmentProductService.updateByPrimaryKeySelective(equipmentProduct);
		return new EamResult(SUCCESS, count);
	}

}