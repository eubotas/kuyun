package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamProductLineCompany;
import com.kuyun.eam.dao.model.EamProductLineDataElement;
import com.kuyun.eam.dao.model.EamProductLineDataElementExample;
import com.kuyun.eam.pojo.ProductLineInfo;
import com.kuyun.eam.pojo.tree.Tree;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamDataElementVO;
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

	@Autowired
	private EamDataElementController eamDataElementController;

	@Autowired
	private EamProductLineDataElementService eamProductLineDataElementService;


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
		}else {
			productLineVO.setOrderByClause("eam_product_line.create_time desc ");
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null && company.getCompanyId() != 1){
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

	@ApiOperation(value = "获取产线信息")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object get(@PathVariable("id") String id, ModelMap modelMap) {
		EamProductLine eamProductLine = eamProductLineService.selectByPrimaryKey(id);
		return new EamResult(SUCCESS, eamProductLine);
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
	@RequestMapping(value = "/dataElement/{id}", method = RequestMethod.GET)
	public String dataElementIndex(@PathVariable("id") String id, ModelMap modelMap) {
		modelMap.put("productLineId", id);
		return "/manage/productLine/dataElement.jsp";
	}

	@ApiOperation(value = "数据点列表")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/dataElement/list", method = RequestMethod.GET)
	@ResponseBody
	public Object dataElementList(@RequestParam(required = false, value = "productLineId") String productLineId) {

		Map<String, Object> map = (Map<String, Object>)eamDataElementController.list(0, 3000, null, null, null);
		List<EamDataElementVO> rows = (List<EamDataElementVO>)map.get("rows");

		handlerCheckedFlag(productLineId, rows);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		return result;
	}

	private void handlerCheckedFlag(String productLineId, List<EamDataElementVO> rows) {
		EamProductLineDataElementExample example = new EamProductLineDataElementExample();
		example.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamProductLineDataElement> dataElements = eamProductLineDataElementService.selectByExample(example);
		if (dataElements != null && !dataElements.isEmpty()){
			for(EamDataElementVO row : rows){
				for(EamProductLineDataElement dataElement : dataElements){
					if (row.getId().equals(dataElement.getEamDataElementId())){
						row.setChecked(Boolean.TRUE);
						break;
					}
				}
			}
		}

	}

	@ApiOperation(value = "确认数据点")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/dataElement/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Object dataElementConfirm(String productLineId, String ids, ModelMap modelMap) {

		int result = eamApiService.handleProductLineDataElements(productLineId, ids);

		return new UpmsResult(UpmsResultConstant.SUCCESS, result);
	}


	@ApiOperation(value = "CityTree")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/city/tree", method = RequestMethod.GET)
	@ResponseBody
	public Object getCityTree() {
		Tree tree = eamApiService.getCityTree(baseEntityUtil.getCurrentUserCompany());
		return new EamResult(SUCCESS, tree);
	}

	@ApiOperation(value = "复制产线")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
	public String copy(@PathVariable("id") String id, ModelMap modelMap) {
		EamProductLine eamProductLine = eamProductLineService.selectByPrimaryKey(id);
		modelMap.put("eamProductLine", eamProductLine);
		return "/manage/productLine/copy.jsp";
	}

	@ApiOperation(value = "复制产线")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/copy/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object copy(@PathVariable("id") String id, String name) {
		int count = eamApiService.copyProductLine(id, name, baseEntityUtil.getCurrentUserCompany().getCompanyId());
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "产线信息-App使用")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductLineInfo() {

		ProductLineInfo info = eamApiService.getProductLineInfo(baseEntityUtil.getCurrentUserCompany());
		return new EamResult(SUCCESS, info);
	}
}