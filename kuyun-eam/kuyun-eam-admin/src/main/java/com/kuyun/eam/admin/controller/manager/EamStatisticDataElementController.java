package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.EamShiftDataElementValueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Pair;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//import com.kuyun.common.util.DateUtil;

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

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamProductLineShiftDataService eamProductLineShiftDataService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

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
            Date date = variable.getDate();
            if (date != null){
				Pair<Date, Date> startEnd = EamDateUtil.getShiftStartEndTime(EamDateUtil.getDateStr(date, "yyyy-MM-dd"));
				criteria.andUpdateTimeBetween(startEnd.getKey(), startEnd.getValue());
			}
        }catch(Exception ex){ex.printStackTrace();}
		example.setOrderByClause("update_time asc");

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

	@ApiOperation(value = "产线当前班次数据")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/productLine", method = RequestMethod.POST)
	public Object getProductLineShiftInfo(String productLineId) {
		EamProductLineShiftDataExample example = new EamProductLineShiftDataExample();
		example.createCriteria().andProductLineIdEqualTo(productLineId).andDeleteFlagEqualTo(Boolean.FALSE);
		example.setOrderByClause("update_time desc");
		return eamProductLineShiftDataService.selectFirstByExample(example);
	}

	@ApiOperation(value = "产线历史班次数据")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/productLine/history", method = RequestMethod.POST)
	public Object getProductLineShiftHistoryInfo(EamProductLineShiftData productLineShiftData) {
		EamProductLineShiftDataExample example = new EamProductLineShiftDataExample();
		EamProductLineShiftDataExample.Criteria criteria = example.createCriteria();
		criteria.andProductLineIdEqualTo(productLineShiftData.getProductLineId()).andDeleteFlagEqualTo(Boolean.FALSE);

		if (productLineShiftData.getStartDate() != null && productLineShiftData.getEndDate() != null){
			criteria.andStartDateBetween(productLineShiftData.getStartDate(), productLineShiftData.getEndDate());
		}

		if (productLineShiftData.getShiftName() != null){
			criteria.andShiftNameEqualTo(productLineShiftData.getShiftName());
		}

		example.setOrderByClause("update_time desc");
		return eamProductLineShiftDataService.selectByExample(example);
	}

}