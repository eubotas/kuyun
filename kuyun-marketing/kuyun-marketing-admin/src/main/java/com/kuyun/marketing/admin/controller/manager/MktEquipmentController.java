package com.kuyun.marketing.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.dao.model.EamEquipmentExample;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问统计控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "访问统计", description = "访问统计")
@RequestMapping("/manage/marketing/equipment")
public class MktEquipmentController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(MktEquipmentController.class);

	@Autowired
	private UpmsApiService upmsApiService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@ApiOperation(value = "访问统计首页")
	@RequiresPermissions("mkt:marketing:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {

		long companyTotal = upmsApiService.countCompanyEquipments(new UpmsCompanyVo());

		EamEquipmentExample example = new EamEquipmentExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);

		long equipmentTotal = eamEquipmentService.countByExample(example);

		modelMap.put("companyTotal", companyTotal);
		modelMap.put("equipmentTotal", equipmentTotal);
		return "/manage/marketing/equipment/index.jsp";
	}

	@ApiOperation(value = "设备统计列表")
	@RequiresPermissions("mkt:marketing:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		UpmsCompanyVo companyVo = new UpmsCompanyVo();
		companyVo.setOffset(offset);
		companyVo.setLimit(limit);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			companyVo.setOrderByClause(sort + " " + order);
		}else {
			companyVo.setOrderByClause("equipment_number desc");
		}

		List<UpmsCompanyVo> rows = upmsApiService.selectCompanyEquipments(companyVo);
		long total = upmsApiService.countCompanyEquipments(companyVo);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}
}