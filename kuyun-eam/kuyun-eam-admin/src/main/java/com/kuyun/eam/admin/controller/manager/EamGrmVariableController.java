package com.kuyun.eam.admin.controller.manager;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamGrmVariable;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamGrmVariableService;
import com.kuyun.eam.vo.EamGrmVariableVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
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

import static com.kuyun.eam.common.constant.EamResultConstant.FAILED;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "产线数据点管理", description = "产线数据点管理")
@RequestMapping("/manage/{productLineId}/variable")
public class EamGrmVariableController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamGrmVariableController.class);

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamGrmVariableService eamGrmVariableService;

	@ApiOperation(value = "产线数据点设置首页")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("productLineId") String productLineId, ModelMap modelMap) {
		modelMap.addAttribute("productLineId", productLineId);
		return "/manage/variable/index.jsp";
	}

	@ApiOperation(value = "产线数据点设置列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("productLineId") String productLineId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamGrmVariableVO variableVO = new EamGrmVariableVO();
		variableVO.setOffset(offset);
		variableVO.setLimit(limit);
		variableVO.setDeleteFlag(Boolean.FALSE);
		variableVO.setProductLineId(productLineId);


		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			variableVO.setOrderByClause(sort + " " + order);
		}else{
			variableVO.setOrderByClause("equipment_id asc");
		}


		List<EamGrmVariableVO> rows = eamApiService.selectGrmVariables(variableVO);
		long total = eamApiService.countGrmVariables(variableVO);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}


	@ApiOperation(value = "设置采集频率")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestBody EamGrmVariableVO vo) {
		_log.info("period = " + vo.getGrmPeriod());
		EamGrmVariable variable = buildEamGrmVariable(vo.getId(), vo.getGrmPeriod());
		int count = eamGrmVariableService.updateByPrimaryKeySelective(variable);
		if (count == 1){
			return new EamResult(SUCCESS, "设置采集频率成功");
		}else {
			return new EamResult(FAILED, "设置采集频率失败");
		}
	}

	private EamGrmVariable buildEamGrmVariable(Integer id, Integer grmPeriod) {
		EamGrmVariable variable = new EamGrmVariable();
		variable.setId(id);
		variable.setGrmPeriod(grmPeriod);
		return variable;
	}


	@ApiOperation(value = "批量设置采集频率")
	@RequiresPermissions("eam:productLine:update")
	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object batchUpdate(String ids, Integer period) {
		_log.info("ids = " + ids);
		_log.info("period = " + period);
		int count = 0;
		JSONArray idArray = JSONArray.parseArray(ids);
		for (int i = 0; i < idArray.size(); i ++) {
			Integer id = idArray.getInteger(i);
			EamGrmVariable variable = buildEamGrmVariable(id, period);
			count = eamGrmVariableService.updateByPrimaryKeySelective(variable);
		}

		return new UpmsResult(UpmsResultConstant.SUCCESS, count);
	}

}