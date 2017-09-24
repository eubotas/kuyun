package com.kuyun.marketing.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.marketing.common.constant.MktResult;
import com.kuyun.marketing.dao.model.MktSmsSetting;
import com.kuyun.marketing.dao.model.MktSmsSettingExample;
import com.kuyun.marketing.rpc.api.MktSmsSettingService;
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

import static com.kuyun.marketing.common.constant.MktResultConstant.INVALID_LENGTH;
import static com.kuyun.marketing.common.constant.MktResultConstant.SUCCESS;

/**
 * 短信设置控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "短信设置管理", description = "短信设置管理")
@RequestMapping("/manage/sms/setting")
public class MktSmsSettingController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(MktSmsSettingController.class);

	@Autowired
	private MktSmsSettingService mktSmsSettingService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;


	@ApiOperation(value = "短信设置首页")
	@RequiresPermissions("mkt:smsSetting:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/sms/setting/index.jsp";
	}

	@ApiOperation(value = "短信设置列表")
	@RequiresPermissions("mkt:smsSetting:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		MktSmsSettingExample mktSmsSettingExample = new MktSmsSettingExample();
		mktSmsSettingExample.setOffset(offset);
		mktSmsSettingExample.setLimit(limit);
		MktSmsSettingExample.Criteria criteria = mktSmsSettingExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);
		
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			mktSmsSettingExample.setOrderByClause(sort + " " + order);
		}


		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}

		List<MktSmsSetting> rows = mktSmsSettingService.selectByExample(mktSmsSettingExample);
		long total = mktSmsSettingService.countByExample(mktSmsSettingExample);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增短信设置")
	@RequiresPermissions("mkt:smsSetting:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/sms/setting/create.jsp";
	}

	@ApiOperation(value = "新增短信设置")
	@RequiresPermissions("mkt:smsSetting:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(MktSmsSetting smsSetting) {
		ComplexResult result = FluentValidator.checkAll()
				.on(smsSetting.getAppName(), new LengthValidator(1, 20, "应用名称"))
				.on(smsSetting.getAppKey(), new LengthValidator(1, 50, "appKey"))
				.on(smsSetting.getAppSecret(), new LengthValidator(1, 50, "appSecret"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new MktResult(INVALID_LENGTH, result.getErrors());
		}

		baseEntityUtil.addAddtionalValue(smsSetting);

		int count = mktSmsSettingService.insertSelective(smsSetting);
		return new MktResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除短信设置")
	@RequiresPermissions("mkt:smsSetting:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = mktSmsSettingService.deleteByPrimaryKeys(ids);
		return new MktResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改短信设置")
	@RequiresPermissions("mkt:smsSetting:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		MktSmsSetting smsSetting = mktSmsSettingService.selectByPrimaryKey(id);
		modelMap.put("smsSetting", smsSetting);
		return "/manage/sms/setting/update.jsp";
	}

	@ApiOperation(value = "修改短信设置")
	@RequiresPermissions("mkt:smsSetting:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, MktSmsSetting smsSetting) {
		ComplexResult result = FluentValidator.checkAll()
				.on(smsSetting.getAppName(), new LengthValidator(1, 20, "应用名称"))
				.on(smsSetting.getAppKey(), new LengthValidator(1, 50, "appKey"))
				.on(smsSetting.getAppSecret(), new LengthValidator(1, 50, "appSecret"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new MktResult(INVALID_LENGTH, result.getErrors());
		}
		smsSetting.setId(id);
		baseEntityUtil.updateAddtionalValue(smsSetting);

		int count = mktSmsSettingService.updateByPrimaryKeySelective(smsSetting);
		return new MktResult(SUCCESS, count);
	}



}