package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamAlarmRecord;
import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.rpc.api.EamAlarmRecordService;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

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

	@Autowired
	private EamAlarmRecordService eamAlarmRecordService;

	@ApiOperation(value = "报警记录列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, value = "eId") String eId,
			@RequestParam(required = false, value = "alarmStauts") String alarmStauts,
			@RequestParam(required = true, value = "startDate") Date startDate,
			@RequestParam(required = true, value = "endDate") Date endDate) {

		EamAlarmRecordVO recordVO = new EamAlarmRecordVO();
		recordVO.setEquipmentId(eId);
		recordVO.setStartDate(startDate);
		recordVO.setEndDate(endDate);
		recordVO.setAlarmStatus(alarmStauts);
		recordVO.setOrderByClause("t.equipment_id, t.create_time desc");

		return eamApiService.selectAlarmRecords(recordVO);
	}

//	@ApiOperation(value = "修改报警记录")
//	@RequiresPermissions("eam:equipment:update")
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//	public String update(@PathVariable("id") int id, ModelMap modelMap) {
//		EamAlarmRecord alarmRecord = eamAlarmRecordService.selectByPrimaryKey(id);
//		modelMap.put("alarmRecord", alarmRecord);
//		return "/manage/part/category/update.jsp";
//	}

	@ApiOperation(value = "修改报警记录")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamAlarmRecord alarmRecord) {
		alarmRecord.setId(id);
		baseEntityUtil.updateAddtionalValue(alarmRecord);
		int count = eamAlarmRecordService.updateByPrimaryKeySelective(alarmRecord);
		return new EamResult(SUCCESS, count);
	}
}