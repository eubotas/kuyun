package com.kuyun.marketing.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.marketing.common.constant.MktResult;
import com.kuyun.marketing.dao.model.MktSmsTemplateExample;
import com.kuyun.marketing.dao.model.MktSmsTemplate;
import com.kuyun.marketing.rpc.api.MktApiService;
import com.kuyun.marketing.rpc.api.MktSmsTemplateService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.vo.UpmsUserVo;
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

import static com.kuyun.marketing.common.constant.MktResultConstant.INVALID_LENGTH;
import static com.kuyun.marketing.common.constant.MktResultConstant.SUCCESS;

/**
 * 短信模板控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "短信模板管理", description = "短信模板管理")
@RequestMapping("/manage/sms/template")
public class MktSmsTemplateController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(MktSmsTemplateController.class);

	@Autowired
	private MktSmsTemplateService mktSmsTemplateService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private MktApiService mktApiService;


	@ApiOperation(value = "短信模板首页")
	@RequiresPermissions("mkt:smsTemplate:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/sms/template/index.jsp";
	}

	@ApiOperation(value = "短信模板列表")
	@RequiresPermissions("mkt:smsTemplate:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		MktSmsTemplateExample smsTemplateExample = new MktSmsTemplateExample();
		smsTemplateExample.setOffset(offset);
		smsTemplateExample.setLimit(limit);
		
		
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			smsTemplateExample.setOrderByClause(sort + " " + order);
		}
		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			smsTemplateExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
		}
		List<MktSmsTemplate> rows = mktSmsTemplateService.selectByExample(smsTemplateExample);
		long total = mktSmsTemplateService.countByExample(smsTemplateExample);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增短信模板")
	@RequiresPermissions("mkt:smsTemplate:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/sms/template/create.jsp";
	}

	@ApiOperation(value = "新增短信模板")
	@RequiresPermissions("mkt:smsTemplate:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(MktSmsTemplate smsTemplate) {
		ComplexResult result = FluentValidator.checkAll()
				.on(smsTemplate.getTemplateId(), new LengthValidator(1, 10, "短信模板ID"))
				.on(smsTemplate.getName(), new LengthValidator(1, 50, "名称"))
				.on(smsTemplate.getContent(), new LengthValidator(1, 500, "短信模板"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new MktResult(INVALID_LENGTH, result.getErrors());
		}

		baseEntityUtil.addAddtionalValue(smsTemplate);

		int count = mktSmsTemplateService.insertSelective(smsTemplate);
		return new MktResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除短信模板")
	@RequiresPermissions("mkt:smsTemplate:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = mktSmsTemplateService.deleteByPrimaryKeys(ids);
		return new MktResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改短信模板")
	@RequiresPermissions("mkt:smsTemplate:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		MktSmsTemplate smsTemplate = mktSmsTemplateService.selectByPrimaryKey(id);
		modelMap.put("smsTemplate", smsTemplate);
		return "/manage/sms/template/update.jsp";
	}

	@ApiOperation(value = "修改短信模板")
	@RequiresPermissions("mkt:smsTemplate:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, MktSmsTemplate smsTemplate) {
		ComplexResult result = FluentValidator.checkAll()
				.on(smsTemplate.getTemplateId(), new LengthValidator(1, 10, "短信模板ID"))
				.on(smsTemplate.getName(), new LengthValidator(1, 50, "名称"))
				.on(smsTemplate.getContent(), new LengthValidator(1, 500, "短信模板"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new MktResult(INVALID_LENGTH, result.getErrors());
		}
		smsTemplate.setId(id);
		baseEntityUtil.updateAddtionalValue(smsTemplate);

		int count = mktSmsTemplateService.updateByPrimaryKeySelective(smsTemplate);
		return new MktResult(SUCCESS, count);
	}

	@RequiresPermissions("mkt:sms:create")
	@RequestMapping(value = "/send/{id}", method = RequestMethod.GET)
	public String send(@PathVariable("id") int id, ModelMap modelMap) {
		MktSmsTemplate smsTemplate = mktSmsTemplateService.selectByPrimaryKey(id);
		modelMap.put("smsTemplate", smsTemplate);
		return "/manage/sms/template/send.jsp";
	}


	@RequiresPermissions("mkt:sms:create")
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	@ResponseBody
	public Object userList() {
		List<UpmsUserVo> users = mktApiService.getUsers();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", users);
		result.put("total", users.size());
		return result;
	}

}