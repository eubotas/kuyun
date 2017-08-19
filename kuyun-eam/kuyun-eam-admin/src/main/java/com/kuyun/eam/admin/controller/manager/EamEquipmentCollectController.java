package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.kuyun.eam.common.constant.CollectStatus.NO_START;
import static com.kuyun.eam.common.constant.CollectStatus.WORKING;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "数据采集", description = "数据采集")
@RequestMapping("/manage/equipment/collect")
public class EamEquipmentCollectController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentCollectController.class);
	
	@Autowired
	private EamApiService eamApiService;



	@ApiOperation(value = "数据采集首页")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/equipment/collect/index.jsp";
	}


	@ApiOperation(value = "采集启动")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/start/",method = RequestMethod.POST)
	@ResponseBody
	public Object start(@RequestBody String jsonString) {
		int count = eamApiService.handleEquimpmentCollect(jsonString, WORKING);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "采集停止")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/stop/",method = RequestMethod.POST)
	@ResponseBody
	public Object stop(@RequestBody String jsonString) {
		int count = eamApiService.handleEquimpmentCollect(jsonString, NO_START);
		return new EamResult(SUCCESS, count);
	}



}