package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamSensorDataVO;
import com.kuyun.eam.vo.EamSensorVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.*;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "传感器数据管理", description = "传感器数据管理")
@RequestMapping("/manage/sensor/data")
public class EamSensorDataController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamSensorDataController.class);

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;


	@ApiOperation(value = "传感器数据曲线列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/curve", method = RequestMethod.GET)
	@ResponseBody
	public Object listCurve(
			@RequestParam(required = true, value = "eId") String eId,
			@RequestParam(required = true, value = "sensorId") String sensorId,
			@RequestParam(required = true, value = "startDate") Date startDate,
			@RequestParam(required = true, value = "endDate") Date endDate) {

		List<EamSensorDataVO> rows = getEamSensorData(eId, sensorId,  startDate, endDate, "t.create_time asc");
		return buildCurveData(rows);
	}


	@ApiOperation(value = "传感器数据列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = true, value = "eId") String eId,
			@RequestParam(required = true, value = "sensorId") String sensorId,
			@RequestParam(required = true, value = "startDate") Date startDate,
			@RequestParam(required = true, value = "endDate") Date endDate) {
		List<EamSensorDataVO> rows = getEamSensorData(eId, sensorId, startDate, endDate, "t.create_time desc");
		return rows;
	}

	private List<EamSensorDataVO> getEamSensorData(String eId, String sensorId,  Date startDate, Date endDate, String order) {
		EamSensorVO sensorVO = new EamSensorVO();
		sensorVO.setEquipmentId(eId);
		sensorVO.setSensorId(Integer.valueOf(sensorId));
		sensorVO.setOrderByClause(order);

		if (startDate != null){
			sensorVO.setStartDate(startDate);
		}

		if (endDate != null){
			sensorVO.setEndDate(endDate);
		}
		return eamApiService.selectEamSensorData(sensorVO);
	}


	private CurveData buildCurveData(List<EamSensorDataVO> rows){
		List<String> value = new ArrayList<>(rows.size());
		List<Date> time = new ArrayList<>(rows.size());
		for (EamSensorDataVO row : rows){
			value.add(row.getStringValue());
			time.add(row.getCreateTime());
		}

		CurveData result = new CurveData();
		result.setValue(value);
		result.setTime(time);

		return result;
	}

	class CurveData implements Serializable{
		List<String> value = new ArrayList<>();
		List<Date> time = new ArrayList<>();

		public List<String> getValue() {
			return value;
		}

		public void setValue(List<String> value) {
			this.value = value;
		}

		public List<Date> getTime() {
			return time;
		}

		public void setTime(List<Date> time) {
			this.time = time;
		}
	}

}