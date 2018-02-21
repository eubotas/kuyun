package com.kuyun.eam.admin.controller.manager;

import com.google.gson.Gson;
import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.pojo.Position;
import com.kuyun.eam.pojo.Positions;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 仓位控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "产线设备位置管理", description = "产线设备位置管理")
@RequestMapping("/manage/{productLineId}/position")
public class EamPositionController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamPositionController.class);

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;


	@ApiOperation(value = "修改设备位置")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("productLineId") String productLineId,
						 @RequestBody String jsonString) {
		Gson gson = new Gson();
		Positions positions = gson.fromJson(jsonString, Positions.class);
		int count = eamApiService.updatePositions(productLineId, positions);

		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "设备位置列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("productLineId") String productLineId) {

		return eamApiService.getPositions(productLineId);
	}

}