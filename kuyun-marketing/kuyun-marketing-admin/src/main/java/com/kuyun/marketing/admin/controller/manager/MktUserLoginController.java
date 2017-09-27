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
import com.kuyun.upms.dao.model.UpmsUserExample;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsUserService;
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
 * 访问统计控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "访问统计管理", description = "访问统计管理")
@RequestMapping("/manage/marketing/user")
public class MktUserLoginController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(MktUserLoginController.class);

	@Autowired
	private UpmsApiService upmsApiService;


	@Autowired
	private UpmsUserService upmsUserService;


	@ApiOperation(value = "访问统计首页")
	@RequiresPermissions("mkt:marketing:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/marketing/user/index.jsp";
	}

	@ApiOperation(value = "访问统计列表")
	@RequiresPermissions("mkt:marketing:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		UpmsUserVo userVo = new UpmsUserVo();
		userVo.setOffset(offset);
		userVo.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			userVo.setOrderByClause(sort + " " + order);
		}else {
			userVo.setOrderByClause("login_number desc");
		}

		UpmsUserExample example = new UpmsUserExample();
		List<UpmsUserVo> rows = upmsApiService.selectLoginUsers(userVo);
		long total = upmsUserService.countByExample(example);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}
}