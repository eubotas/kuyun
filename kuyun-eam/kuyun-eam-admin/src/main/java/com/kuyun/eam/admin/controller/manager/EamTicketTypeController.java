package com.kuyun.eam.admin.controller.manager;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.util.EamUtils_;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.dao.model.EamTicketTypeExample;
import com.kuyun.eam.rpc.api.EamTicketTypeService;
import com.kuyun.upms.dao.model.UpmsOrganization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 设备模型控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单类型管理", description = "工单类型管理")
@RequestMapping("/manage/ticket/type")
public class EamTicketTypeController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamTicketTypeController.class);
	
//	@Autowired
//	private EamTicketService eamTicketService;

	@Autowired
	private EamTicketTypeService eamTicketTypeService;

	@Autowired
	private EamUtils_ eamUtils_;


	@ApiOperation(value = "工单类型管理首页")
	@RequiresPermissions("eam:ticketType:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/ticket/type/index.jsp";
	}

	@ApiOperation(value = "工单类型列表")
	@RequiresPermissions("eam:ticketType:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketTypeExample eamTicketTypeExample = new EamTicketTypeExample();
		eamTicketTypeExample.setOffset(offset);
		eamTicketTypeExample.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketTypeExample.setOrderByClause(sort + " " + order);
		}

		UpmsOrganization organization = eamUtils_.getCurrentUserParentOrignization();

		if (organization != null){
			eamTicketTypeExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId())
			.andDeleteFlagEqualTo(Boolean.FALSE);
		}


		List<EamTicketType> rows = eamTicketTypeService.selectByExample(eamTicketTypeExample);
		long total = eamTicketTypeService.countByExample(eamTicketTypeExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增工单类型")
	@RequiresPermissions("eam:ticketType:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/ticket/type/create.jsp";
	}

	@ApiOperation(value = "新增工单类型")
	@RequiresPermissions("eam:ticketType:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamTicketType ticketType) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketType.getName(), new LengthValidator(1, 20, "工单类型名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		eamUtils_.addAddtionalValue(ticketType);
		int count = eamTicketTypeService.insertSelective(ticketType);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除工单类型")
	@RequiresPermissions("eam:ticketType:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketTypeService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改工单类型")
	@RequiresPermissions("eam:ticketType:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamTicketType eamTicketType = eamTicketTypeService.selectByPrimaryKey(id);
		modelMap.put("ticketType", eamTicketType);
		return "/manage/ticket/type/update.jsp";
	}

	@ApiOperation(value = "修改工单类型")
	@RequiresPermissions("eam:ticketType:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamTicketType ticketType) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketType.getName(), new LengthValidator(1, 20, "工单类型名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		ticketType.setId(id);
		eamUtils_.updateAddtionalValue(ticketType);
		int count = eamTicketTypeService.updateByPrimaryKeySelective(ticketType);
		return new EamResult(SUCCESS, count);
	}



}