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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	@ApiOperation(value = "传感器数据列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "100", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "startDate") Date startDate,
			@RequestParam(required = false, value = "endDate") Date endDate) {

		EamSensorVO sensorVO = new EamSensorVO();
		sensorVO.setOffset(offset);
		sensorVO.setLimit(limit);
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			sensorVO.setOrderByClause(sort + " " + order);
		}

		if (startDate != null){
			sensorVO.setStartDate(startDate);
		}

		if (endDate != null){
			sensorVO.setEndDate(endDate);
		}

		UpmsOrganization organization = baseEntityUtil.getCurrentUserParentOrignization();

		if (organization != null){
			sensorVO.setOrganizationId(organization.getOrganizationId());
		}
		List<EamSensorDataVO> rows = eamApiService.selectEamSensorData(sensorVO);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", rows.size());
		return result;
	}

}