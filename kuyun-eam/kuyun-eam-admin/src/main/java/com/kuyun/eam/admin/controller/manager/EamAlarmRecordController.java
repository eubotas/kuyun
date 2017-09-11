package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamAlarmRecordVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "报警记录管理", description = "报警记录管理")
@RequestMapping("/manage/alarm/record")
public class EamAlarmRecordController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamAlarmRecordController.class);

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@ApiOperation(value = "报警记录列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, value = "eId") String eId,
			@RequestParam(required = true, value = "startDate") Date startDate,
			@RequestParam(required = true, value = "endDate") Date endDate) {

		EamAlarmRecordVO recordVO = new EamAlarmRecordVO();
		recordVO.setEquipmentId(eId);
		recordVO.setStartDate(startDate);
		recordVO.setEndDate(endDate);
		recordVO.setOrderByClause("t.equipment_id, t.create_time desc");

		return eamApiService.selectAlarmRecords(recordVO);
	}
}