package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
//import com.kuyun.common.util.DateUtil;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.EamCodeValueVo;
import com.kuyun.eam.vo.EamCountryValueVo;
import com.kuyun.eam.vo.EamShiftDataElementValueVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Pair;
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

	@Autowired
	private  EamShiftDataElementValueService eamShiftDataElementValueService;

	@ApiOperation(value = "按班次统计数据点")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/shift", method = RequestMethod.POST)
	public Object shift(EamShiftDataElementValueVO variable) {
		EamShiftDataElementValueExample example = new EamShiftDataElementValueExample();
		EamShiftDataElementValueExample.Criteria criteria = example.createCriteria();

		if (variable.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(variable.getProductLineId());
		}

		if (variable.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
		}
		if (variable.getSwitchValue() != null){
			criteria.andSwitchValueEqualTo(variable.getSwitchValue());
		}
		if (variable.getShift() != null){
			criteria.andShiftEqualTo(variable.getShift());
		}
		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}

		try {
            Date date = variable.getData();
            if (date != null){
				Pair<Date, Date> startEnd = EamDateUtil.getShiftStartEndTime(EamDateUtil.getDateStr(date, "yyyy-MM-dd"));
				criteria.andUpdateTimeBetween(startEnd.getKey(), startEnd.getValue());
			}
        }catch(Exception ex){ex.printStackTrace();}
		example.setOrderByClause("date asc");

		List<EamShiftDataElementValue> data = eamShiftDataElementValueService.selectByExample(example);
		return data;
	}

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
		if (variable.getSwitchValue() != null){
			criteria.andSwitchValueEqualTo(variable.getSwitchValue());
		}
		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}
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
		if (variable.getSwitchValue() != null){
			criteria.andSwitchValueEqualTo(variable.getSwitchValue());
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


		if (variable.getDataElementId() != null){
			criteria.andDataElementIdEqualTo(variable.getDataElementId());
		}
		if (variable.getSwitchValue() != null){
			criteria.andSwitchValueEqualTo(variable.getSwitchValue());
		}
		int year = LocalDate.now().getYear();

		criteria.andYearEqualTo(year);
		example.setOrderByClause("update_time asc");

		List<EamGrmVariableDataByYear> data = eamGrmVariableDataByYearService.selectByExample(example);
		return data;
	}


}