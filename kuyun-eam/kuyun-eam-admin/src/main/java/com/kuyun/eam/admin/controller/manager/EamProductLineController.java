package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamGrmVariableService;
import com.kuyun.eam.rpc.api.EamProductLineCompanyService;
import com.kuyun.eam.rpc.api.EamProductLineService;
import com.kuyun.eam.vo.EamGrmVariableVO;
import com.kuyun.eam.vo.EamProductLineVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 产线控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "产线管理", description = "产线管理")
@RequestMapping("/manage/productLine")
public class EamProductLineController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamProductLineController.class);

	@Autowired
	private EamProductLineService eamProductLineService;

	@Autowired
	private EamProductLineCompanyService eamProductLineCompanyService;

	@Autowired
	private EamApiService eamApiService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamGrmVariableService eamGrmVariableService;


	@ApiOperation(value = "产线首页")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/productLine/index.jsp";
	}

	@ApiOperation(value = "产线列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamProductLineVO productLineVO = new EamProductLineVO();
		productLineVO.setOffset(offset);
		productLineVO.setLimit(limit);
		productLineVO.setDeleteFlag(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			productLineVO.setOrderByClause(sort + " " + order);
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			productLineVO.setCompanyId(company.getCompanyId());
		}

		List<EamProductLineVO> rows = eamApiService.selectProductLines(productLineVO);
		long total = eamApiService.countProductLines(productLineVO);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增产线")
	@RequiresPermissions("eam:productLine:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/productLine/create.jsp";
	}

	@ApiOperation(value = "新增产线")
	@RequiresPermissions("eam:productLine:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamProductLine eamProductLine) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamProductLine.getName(), new LengthValidator(1, 20, "产线名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamProductLine);
		EamProductLineCompany eamProductLineCompany = createProduceLineCompany(eamProductLine);

		int count = eamApiService.persistProductLine(eamProductLine, eamProductLineCompany);
		return new EamResult(SUCCESS, count);
	}

	private EamProductLineCompany createProduceLineCompany(EamProductLine eamProductLine) {
		EamProductLineCompany eamProductLineCompany = new EamProductLineCompany();
		eamProductLineCompany.setCompanyId(baseEntityUtil.getCurrentUserCompany().getCompanyId());
		baseEntityUtil.addAddtionalValue(eamProductLineCompany);
		return eamProductLineCompany;
	}


	@ApiOperation(value = "删除产线")
	@RequiresPermissions("eam:productLine:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamProductLineService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改产线")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, ModelMap modelMap) {
		EamProductLine eamProductLine = eamProductLineService.selectByPrimaryKey(id);
		modelMap.put("eamProductLine", eamProductLine);
		return "/manage/productLine/update.jsp";
	}

	@ApiOperation(value = "修改产线")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") String id, EamProductLine eamProductLine) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamProductLine.getName(), new LengthValidator(1, 20, "产线名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamProductLine.setProductLineId(id);
		baseEntityUtil.updateAddtionalValue(eamProductLine);
		int count = eamProductLineService.updateByPrimaryKeySelective(eamProductLine);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "选择数据点")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/grm/{id}", method = RequestMethod.GET)
	public String grmIndex(@PathVariable("id") String id, ModelMap modelMap) {
		modelMap.put("productLineId", id);
		return "/manage/productLine/grm_variable.jsp";
	}

	@ApiOperation(value = "数据点列表")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/grm/list", method = RequestMethod.GET)
	@ResponseBody
	public Object grmList(@RequestParam(required = false, value = "productLineId") String productLineId) {
		List<EamGrmVariableVO> rows = eamApiService.selectGrmVariables(productLineId);

		handlerCheckedFlag(productLineId, rows);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		return result;
	}

	private void handlerCheckedFlag(String productLineId, List<EamGrmVariableVO> rows) {
		EamGrmVariableExample example = new EamGrmVariableExample();
		example.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);
		if (variables != null && !variables.isEmpty()){
			for(EamGrmVariableVO row : rows){
				for(EamGrmVariable variable : variables){
					if (row.getName().equals(variable.getName())){
						row.setChecked(Boolean.TRUE);
						break;
					}
				}
			}
		}

	}

	@ApiOperation(value = "确认数据点")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/grm/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Object grmConfirm(String productLineId, String names, ModelMap modelMap) {

		List<EamGrmVariable> vos = buildVariables(productLineId, names);

		if (!vos.isEmpty()){
			//remove already exist data
			EamGrmVariableExample example = new EamGrmVariableExample();
			example.createCriteria().andProductLineIdEqualTo(productLineId);
			eamGrmVariableService.deleteByExample(example);

			//add new
			eamGrmVariableService.batchInsert(vos);
		}

		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	private List<EamGrmVariable> buildVariables(String productLineId, String names){
		List<EamGrmVariable> result = new ArrayList<>();
		String [] nameArray = names.split("::");

		List<EamGrmVariableVO> rows = eamApiService.selectGrmVariables(productLineId);
		for (String name : nameArray){
			for (EamGrmVariableVO vo : rows){
				if (name.equals(vo.getName())){
					vo.setProductLineId(productLineId);
					baseEntityUtil.addAddtionalValue(vo);
					result.add(vo);
					break;
				}
			}
		}

		return result;
	}

}