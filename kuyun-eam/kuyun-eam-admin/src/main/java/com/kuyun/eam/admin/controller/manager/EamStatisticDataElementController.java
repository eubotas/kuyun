package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.common.util.DateUtil;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamCodeValueVo;
import com.kuyun.eam.vo.EamCountryValueVo;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 模板控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "统计数据点管理", description = "统计数据点管理")
@RequestMapping("/manage/statistic")
public class EamStatisticDataElementController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamStatisticDataElementController.class);
	
	@Autowired
	private EamGrmVariableDataByYearService eamGrmVariableDataByYearService;

	@Autowired
	private EamGrmVariableDataByMonthService eamGrmVariableDataByMonthService;

	@Autowired
	private EamGrmVariableDataByDayService eamGrmVariableDataByDayService;


	@ApiOperation(value = "按日统计数据点")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/day", method = RequestMethod.POST)
	public Object day(EamGrmVariableDataByDay variable) {
		EamGrmVariableDataByDayExample example = new EamGrmVariableDataByDayExample();
		EamGrmVariableDataByDayExample.Criteria criteria = example.createCriteria();

		if (variable.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(variable.getProductLineId());
		}

		if (variable.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
		}

		if (variable.getDataGroupId() != null){
			criteria.andDataGroupIdEqualTo(variable.getDataGroupId());
		}

		if (variable.getEquipmentDataGroupId() != null){
			criteria.andEquipmentDataGroupIdEqualTo(variable.getEquipmentDataGroupId());
		}

		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}
		LocalDate now = LocalDate.now();
		Date startDate = DateUtil.asDate(now.withDayOfMonth(1));
		Date endDate = DateUtil.asDate(now.withDayOfMonth(now.lengthOfMonth()));

		criteria.andDateBetween(startDate, endDate);
		example.setOrderByClause("date asc");

		List<EamGrmVariableDataByDay> data = eamGrmVariableDataByDayService.selectByExample(example);
		return data;
	}


	@ApiOperation(value = "按月统计数据点")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/month", method = RequestMethod.POST)
	public Object month(EamGrmVariableDataByMonth variable) {
		EamGrmVariableDataByMonthExample example = new EamGrmVariableDataByMonthExample();
		EamGrmVariableDataByMonthExample.Criteria criteria = example.createCriteria();

		if (variable.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(variable.getProductLineId());
		}

		if (variable.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
		}

		if (variable.getDataGroupId() != null){
			criteria.andDataGroupIdEqualTo(variable.getDataGroupId());
		}

		if (variable.getEquipmentDataGroupId() != null){
			criteria.andEquipmentDataGroupIdEqualTo(variable.getEquipmentDataGroupId());
		}

		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}
		int year = LocalDate.now().getYear();

		criteria.andYearEqualTo(year);
		example.setOrderByClause("update_time asc");

		List<EamGrmVariableDataByMonth> data = eamGrmVariableDataByMonthService.selectByExample(example);
		return data;
	}


	@ApiOperation(value = "按年统计数据点")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/year", method = RequestMethod.POST)
	public Object year(EamGrmVariableDataByYear variable) {
		EamGrmVariableDataByYearExample example = new EamGrmVariableDataByYearExample();
		EamGrmVariableDataByYearExample.Criteria criteria = example.createCriteria();

		if (variable.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(variable.getProductLineId());
		}

		if (variable.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
		}

		if (variable.getDataGroupId() != null){
			criteria.andDataGroupIdEqualTo(variable.getDataGroupId());
		}

		if (variable.getEquipmentDataGroupId() != null){
			criteria.andEquipmentDataGroupIdEqualTo(variable.getEquipmentDataGroupId());
		}

		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}
		int year = LocalDate.now().getYear();

		criteria.andYearEqualTo(year);
		example.setOrderByClause("update_time asc");

		EamGrmVariableDataByYear data = eamGrmVariableDataByYearService.selectFirstByExample(example);
		return data;
	}


}