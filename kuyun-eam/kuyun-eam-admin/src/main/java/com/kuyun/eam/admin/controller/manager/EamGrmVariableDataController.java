package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.pojo.CurveData;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamGrmVariableDataVO;
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
@Api(value = "智库数据管理", description = "智库数据管理")
@RequestMapping("/manage/grm/data")
public class EamGrmVariableDataController extends BaseController {
	private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataController.class);

	@Autowired
	private EamApiService eamApiService;


	@ApiOperation(value = "智库数据列表")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/list/", method = RequestMethod.POST)
	@ResponseBody
	public Object list(EamGrmVariableDataVO eamGrmVariableDataVO) {
		List<EamGrmVariableDataVO> rows = getGrmVariableData(eamGrmVariableDataVO);
		return rows;
	}

	private List<EamGrmVariableDataVO> getGrmVariableData(EamGrmVariableDataVO eamGrmVariableDataVO) {

		if (eamGrmVariableDataVO.getOrderByClause() == null){
			eamGrmVariableDataVO.setOrderByClause("eam_grm_variable_data.update_time desc");
		}


		List<EamGrmVariableDataVO> result = eamApiService.selectEamGrmVariableData(eamGrmVariableDataVO);
		if (result == null){
			result = new ArrayList<>();
		}
		return result;
	}

	private CurveData buildCurveData(List<EamGrmVariableDataVO> rows){
		List<String> value = new ArrayList<>(rows.size());
		List<Date> time = new ArrayList<>(rows.size());
		for (EamGrmVariableDataVO row : rows){
			value.add(row.getValue());
			time.add(row.getCreateTime());
		}

		CurveData result = new CurveData();
		result.setValue(value);
		result.setTime(time);

		return result;
	}




}