package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.admin.util.EamUtil;
import com.kuyun.eam.common.constant.AlarmStatus;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamAlarmRecord;
import com.kuyun.eam.rpc.api.EamAlarmRecordService;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamAlarmRecordVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private EamUtil eamUtil;

    @ApiOperation(value = "报警中心")
    //@RequiresPermissions("eam:alarm:read")
    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        return "/manage/alarm/alarmCenter.jsp";
    }

	@ApiOperation(value = "报警记录列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/")
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "startDate") Date startDate,
			@RequestParam(required = false, value = "endDate") Date endDate) {
		EamAlarmRecordVO recordVO = new EamAlarmRecordVO();
		recordVO.setOffset(offset);
		recordVO.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			recordVO.setOrderByClause(sort + " " + order);
		}else {
			recordVO.setOrderByClause("t.equipment_id, t.create_time desc");
		}
		if (startDate != null){
			recordVO.setStartDate(startDate);
		}
		if (endDate != null){
			recordVO.setEndDate(endDate);
		}
		recordVO.setAlarmStatus(AlarmStatus.ANU.getCode());

		List<String> equipmentIds = eamUtil.getEquipmentIds();
		if (!equipmentIds.isEmpty()){
			recordVO.setEquipmentIds(equipmentIds);
		}else{
			//current company have not equipment
			recordVO.setEquipmentId("-1");
		}
		List<EamAlarmRecordVO> rows = eamApiService.selectAlarmRecords(recordVO);
		Long total = eamApiService.countAlarmRecords(recordVO);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}



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