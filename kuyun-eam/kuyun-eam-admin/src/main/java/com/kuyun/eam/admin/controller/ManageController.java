package com.kuyun.eam.admin.controller;

import com.kuyun.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台controller
 * Created by kuyun on 2017/01/12.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台控制器", description = "后台管理")
public class ManageController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);



	/**
	 * 后台首页
	 * @return
	 */
	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/index.jsp";
	}

	@ApiOperation(value = "Dashboard")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	@ResponseBody
	public Object  dashboard() {

		return "/manage/index.jsp";
	}

}