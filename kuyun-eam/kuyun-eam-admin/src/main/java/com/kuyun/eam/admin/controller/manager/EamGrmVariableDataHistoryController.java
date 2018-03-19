package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.pojo.CurveData;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamGrmVariableDataHistoryExtVO;
import com.kuyun.eam.vo.EamGrmVariableDataHistoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "智库历史数据管理", description = "智库历史数据管理")
@RequestMapping("/manage/grm/data/history")
public class EamGrmVariableDataHistoryController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataHistoryController.class);

	@Autowired
	private EamApiService eamApiService;


	@ApiOperation(value = "智库历史数据曲线列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list/curve", method = RequestMethod.POST)
	@ResponseBody
	public Object listCurve(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {

		List<EamGrmVariableDataHistoryExtVO> rows = getGrmVariableHistoryData(eamGrmVariableDataHistoryVO);
		return buildCurveData(rows);
	}


	@ApiOperation(value = "智库历史数据列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list/", method = RequestMethod.POST)
	@ResponseBody
	public Object list(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {
		List<EamGrmVariableDataHistoryExtVO> rows = getGrmVariableHistoryData(eamGrmVariableDataHistoryVO);
		return rows;
	}

	private List<EamGrmVariableDataHistoryExtVO> getGrmVariableHistoryData(EamGrmVariableDataHistoryVO eamGrmVariableDataHistoryVO) {

		if (eamGrmVariableDataHistoryVO.getOrderByClause() == null){
			eamGrmVariableDataHistoryVO.setOrderByClause("egvdh.update_time desc");
		}


		List<EamGrmVariableDataHistoryExtVO> result = eamApiService.selectEamGrmVariableDataHistories(eamGrmVariableDataHistoryVO);
		if (result == null){
			result = new ArrayList<>();
		}
		return result;
	}

	private CurveData buildCurveData(List<EamGrmVariableDataHistoryExtVO> rows){
		List<String> value = new ArrayList<>(rows.size());
		List<Date> time = new ArrayList<>(rows.size());
		for (EamGrmVariableDataHistoryExtVO row : rows){
			value.add(row.getValue());
			time.add(row.getUpdateTime());
		}

		CurveData result = new CurveData();
		result.setValue(value);
		result.setTime(time);

		return result;
	}




}