package com.kuyun.marketing.admin.controller.manager;

import com.kuyun.common.base.BaseController;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问统计控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "访问统计", description = "访问统计")
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