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
@Api(value = "报警历史记录管理", description = "报警历史记录管理")
@RequestMapping("/manage/alarm/record/history")
public class EamAlarmRecordHistoryController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamAlarmRecordHistoryController.class);

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamUtil eamUtil;

	@ApiOperation(value = "报警历史记录列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list/", method = RequestMethod.POST)
	@ResponseBody
	public Object list(EamAlarmRecordVO recordVO) {
		if (StringUtils.isEmpty(recordVO.getOrderByClause())){
			recordVO.setOrderByClause("t.create_time desc");
		}

		if (recordVO.getEquipmentIds() == null){
			List<String> equipmentIds = eamUtil.getEquipmentIds();
			if (!equipmentIds.isEmpty()){
				recordVO.setEquipmentIds(equipmentIds);
			}
		}

		List<EamAlarmRecordVO> rows = eamApiService.selectAlarmRecordHistoies(recordVO);
		Long total = eamApiService.countAlarmRecordHistoies(recordVO);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;

	}

}