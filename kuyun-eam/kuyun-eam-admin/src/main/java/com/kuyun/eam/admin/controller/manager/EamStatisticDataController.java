package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.common.util.DateUtil;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamShiftStatisticDataService;
import com.kuyun.eam.rpc.api.EamStatisticDataByDayService;
import com.kuyun.eam.rpc.api.EamStatisticDataByMonthService;
import com.kuyun.eam.rpc.api.EamStatisticDataByYearService;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.vo.EamShiftStatisticDataVO;
import com.kuyun.eam.vo.EamStatisticDataByDayVO;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


/**
 * 模板控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "统计数据管理", description = "统计数据管理")
@RequestMapping("/manage/statistic/data")
public class EamStatisticDataController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamStatisticDataController.class);
	
	@Autowired
	private EamStatisticDataByYearService eamStatisticDataByYearService;

	@Autowired
	private EamStatisticDataByMonthService eamStatisticDataByMonthService;

	@Autowired
	private EamStatisticDataByDayService eamStatisticDataByDayService;

	@Autowired
	private  EamShiftStatisticDataService eamShiftStatisticDataService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}



	@ApiOperation(value = "按日统计数据")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/day", method = RequestMethod.POST)
	public Object day(EamStatisticDataByDayVO dayVO) {
		EamStatisticDataByDayExample example = new EamStatisticDataByDayExample();
		EamStatisticDataByDayExample.Criteria criteria = example.createCriteria();

		if (dayVO.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(dayVO.getProductLineId());
		}

		if (dayVO.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(dayVO.getEquipmentId());
		}

		if (dayVO.getCode() != null){
			criteria.andCodeEqualTo(dayVO.getCode());
		}
		if (dayVO.getDate() != null){
			criteria.andDateEqualTo(dayVO.getDate());
		}
		if (dayVO.getYear() != null && dayVO.getMonth() != null){
			Date firstDateOfMonth = DateUtil.getFirstDateOfMonth(dayVO.getYear(), dayVO.getMonth());
			Date lastDateOfMonth = DateUtil.getLastDateOfMonth(dayVO.getYear(), dayVO.getMonth());
			criteria.andDateBetween(firstDateOfMonth, lastDateOfMonth);
		}

		example.setOrderByClause("date asc");

		List<EamStatisticDataByDay> data = eamStatisticDataByDayService.selectByExample(example);
		return data;
	}


	@ApiOperation(value = "按月统计数据点")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/month", method = RequestMethod.POST)
	public Object month(EamStatisticDataByMonth dataByMonth) {
		EamStatisticDataByMonthExample example = new EamStatisticDataByMonthExample();
		EamStatisticDataByMonthExample.Criteria criteria = example.createCriteria();

		if (dataByMonth.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(dataByMonth.getProductLineId());
		}

		if (dataByMonth.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(dataByMonth.getEquipmentId());
		}

		if (dataByMonth.getCode() != null){
			criteria.andCodeEqualTo(dataByMonth.getCode());
		}
		if (dataByMonth.getYear() == null){
			int year = LocalDate.now().getYear();
			criteria.andYearEqualTo(year);
		}else {
			criteria.andYearEqualTo(dataByMonth.getYear());
		}

		example.setOrderByClause("update_time asc");

		List<EamStatisticDataByMonth> data = eamStatisticDataByMonthService.selectByExample(example);
		return data;
	}


	@ApiOperation(value = "按年统计数据")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/year", method = RequestMethod.POST)
	public Object year(EamStatisticDataByYear dataByYear) {
		EamStatisticDataByYearExample example = new EamStatisticDataByYearExample();
		EamStatisticDataByYearExample.Criteria criteria = example.createCriteria();

		if (dataByYear.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(dataByYear.getProductLineId());
		}

		if (dataByYear.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(dataByYear.getEquipmentId());
		}


		if (dataByYear.getCode() != null){
			criteria.andCodeEqualTo(dataByYear.getCode());
		}

		if (dataByYear.getYear() == null){
			int year = LocalDate.now().getYear();

			criteria.andYearEqualTo(year);
		}else {
			criteria.andYearEqualTo(dataByYear.getYear());
		}

		example.setOrderByClause("update_time asc");

		List<EamStatisticDataByYear> data = eamStatisticDataByYearService.selectByExample(example);
		return data;
	}

	@ApiOperation(value = "按班次统计数据")
	@RequiresPermissions("eam:productLine:read")
	@ResponseBody
	@RequestMapping(value = "/shift", method = RequestMethod.POST)
	public Object shift(EamShiftStatisticDataVO dataByShift) {
		EamShiftStatisticDataExample example = new EamShiftStatisticDataExample();
		EamShiftStatisticDataExample.Criteria criteria = example.createCriteria();

		if (dataByShift.getProductLineId() != null){
			criteria.andProductLineIdEqualTo(dataByShift.getProductLineId());
		}

		if (dataByShift.getEquipmentId() != null){
			criteria.andEquipmentIdEqualTo(dataByShift.getEquipmentId());
		}


		if (dataByShift.getCode() != null){
			criteria.andCodeEqualTo(dataByShift.getCode());
		}

		if (dataByShift.getShift() != null){
			criteria.andShiftEqualTo(dataByShift.getShift());
		}

		if (dataByShift.getDate() != null){
			try {
				Pair<Date,Date> pair = EamDateUtil.getShiftStartEndTime(dataByShift.getDate());
				criteria.andCreateTimeBetween(pair.getKey(), pair.getValue());
			} catch (ParseException e) {
				_log.error("Error:{}", e.getMessage());
			}

		}



		example.setOrderByClause("update_time asc");

		List<EamShiftStatisticData> data = eamShiftStatisticDataService.selectByExample(example);
		return data;
	}

}