package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamTicketTag;
import com.kuyun.eam.dao.model.EamTicketTagExample;
import com.kuyun.eam.rpc.api.EamTicketTagService;
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
 * 设备模型控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "评价标签管理", description = "评价标签管理")
@RequestMapping("/manage/ticket/tag")
public class EamTicketTagController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamTicketTagController.class);
	
//	@Autowired
//	private EamTicketService eamTicketService;

	@Autowired
	private EamTicketTagService eamTicketTagService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "评价标签管理首页")
	@RequiresPermissions("eam:ticketTag:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/ticket/tag/index.jsp";
	}

	@ApiOperation(value = "评价标签列表")
	@RequiresPermissions("eam:ticketTag:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketTagExample eamTicketTagExample = new EamTicketTagExample();
		eamTicketTagExample.setOffset(offset);
		eamTicketTagExample.setLimit(limit);
		EamTicketTagExample.Criteria criteria = eamTicketTagExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketTagExample.setOrderByClause(sort + " " + order);
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}


		List<EamTicketTag> rows = eamTicketTagService.selectByExample(eamTicketTagExample);
		long total = eamTicketTagService.countByExample(eamTicketTagExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增评价标签")
	@RequiresPermissions("eam:TicketTag:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/ticket/tag/create.jsp";
	}

	@ApiOperation(value = "新增评价标签")
	@RequiresPermissions("eam:TicketTag:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamTicketTag ticketTag) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketTag.getName(), new LengthValidator(1, 20, "评价标签名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(ticketTag);
		int count = eamTicketTagService.insertSelective(ticketTag);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除评价标签")
	@RequiresPermissions("eam:ticketTag:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketTagService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改评价标签")
	@RequiresPermissions("eam:ticketTag:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamTicketTag eamTicketTag = eamTicketTagService.selectByPrimaryKey(id);
		modelMap.put("ticketTag", eamTicketTag);
		return "/manage/ticket/tag/update.jsp";
	}

	@ApiOperation(value = "修改评价标签")
	@RequiresPermissions("eam:ticketTag:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamTicketTag ticketTag) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketTag.getName(), new LengthValidator(1, 20, "评价标签名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		ticketTag.setId(id);
		baseEntityUtil.updateAddtionalValue(ticketTag);
		int count = eamTicketTagService.updateByPrimaryKeySelective(ticketTag);
		return new EamResult(SUCCESS, count);
	}



}