package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.ProtocolEnum;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsUserCompany;
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

import java.util.*;

import static com.kuyun.eam.common.constant.EamResultConstant.FAILED;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * grm控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "智库网关管理", description = "智库网关管理")
@RequestMapping("/manage/grm")
public class EamGrmController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamGrmController.class);
	
	@Autowired
	private EamGrmService eamGrmService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamGrmEquipmentService eamGrmEquipmentService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamProtocolService protocolService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;



	@ApiOperation(value = "智库网关首页")
	@RequiresPermissions("eam:grm:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		EamProtocol protocol = protocolService.selectByPrimaryKey(ProtocolEnum.MODBUS_RTU.getId());
		modelMap.put("protocol", protocol);
		return "/manage/grm/index.jsp";
	}

	@ApiOperation(value = "智库网关列表")
	@RequiresPermissions("eam:grm:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamGrmExample grmExample = new EamGrmExample();
		grmExample.setOffset(offset);
		grmExample.setLimit(limit);
		EamGrmExample.Criteria criteria = grmExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			grmExample.setOrderByClause(sort + " " + order);
		}else {
			grmExample.setOrderByClause("create_time desc");
		}
		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}
		List<EamGrm> rows = eamGrmService.selectByExample(grmExample);
		long total = eamGrmService.countByExample(grmExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增智库网关")
	@RequiresPermissions("eam:grm:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/grm/create.jsp";
	}

	@ApiOperation(value = "新增智库网关")
	@RequiresPermissions("eam:grm:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamGrm grm) {
		baseEntityUtil.addAddtionalValue(grm);
		int count = eamGrmService.insertSelective(grm);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除智库网关")
	@RequiresPermissions("eam:grm:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamGrmService.deleteByPrimaryKeys(ids);
		String[] idArray = ids.split("-");

		EamGrmEquipmentExample example = new EamGrmEquipmentExample();
		example.createCriteria().andGrmIdIn(Arrays.asList(idArray));

		eamGrmEquipmentService.deleteByExample(example);

		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改智库网关")
	@RequiresPermissions("eam:grm:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") String id, ModelMap modelMap) {
	    Map map= new HashMap();
		EamGrm grm = eamGrmService.selectByPrimaryKey(id);
        map.put("grm", grm);
		return map;
	}

	@ApiOperation(value = "修改智库网关")
	@RequiresPermissions("eam:grm:update")
	@RequestMapping(value= "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") String id, EamGrm grm) {
		baseEntityUtil.updateAddtionalValue(grm);
		grm.setGrmId(id);
		int count = eamGrmService.updateByPrimaryKeySelective(grm);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "接入设备列表")
	@RequiresPermissions("eam:grm:read")
	@RequestMapping(value = "/equipment/list", method = RequestMethod.GET)
	@ResponseBody
	public Object equipmentList(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "grmId") String grmId) {

		_log.info("grmId="+grmId);

		EamEquipmentVO equipmentVO = new EamEquipmentVO();
		equipmentVO.setOffset(offset);
		equipmentVO.setLimit(limit);
		equipmentVO.setDeleteFlag(Boolean.FALSE);
		equipmentVO.setGrmId(grmId);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			equipmentVO.setOrderByClause(sort + " " + order);
		}else {
			equipmentVO.setOrderByClause("eam_equipment.create_time desc");
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			equipmentVO.setCompanyId(company.getCompanyId());
		}


		List<EamEquipmentVO> rows = eamApiService.selectUnConnectGrmEquipments(equipmentVO);
		long total = eamApiService.countUnConnectGrmEquipments(equipmentVO);


		handleCheckedFlag(grmId, rows);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	private void handleCheckedFlag(String grmId, List<EamEquipmentVO> rows) {
		EamGrmEquipmentExample example = new EamGrmEquipmentExample();
		example.createCriteria().andGrmIdEqualTo(grmId).andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamGrmEquipment> grmEquipments = eamGrmEquipmentService.selectByExample(example);

		if (grmEquipments != null && rows != null){
			for(EamEquipmentVO eamEquipmentVO : rows){
				for (EamGrmEquipment grmEquipment : grmEquipments){
					if (eamEquipmentVO.getEquipmentId().equals(grmEquipment.getEquipmentId())){
						eamEquipmentVO.setChecked(true);
						break;
					}
				}
			}
		}
	}

	@ApiOperation(value = "接入设备")
	@RequiresPermissions("eam:grm:update")
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ResponseBody
	public Object connect(String eIds, String grmId, ModelMap modelMap) {
		String [] eIdList = eIds.split("::");
		_log.info("eIds="+eIds);
		_log.info("grmId="+ grmId);
		//remove already exist data
		EamGrmEquipmentExample example = new EamGrmEquipmentExample();
		example.createCriteria().andGrmIdEqualTo(grmId);
		eamGrmEquipmentService.deleteByExample(example);

		//add new
		List<EamGrmEquipment> grmEquipments = new ArrayList<>();
		for(String eId : eIdList){
			EamGrmEquipment grmEquipment = buildEamGrmEquipment(grmId, eId);
			grmEquipments.add(grmEquipment);
		}
		eamGrmEquipmentService.batchInsert(grmEquipments);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	@ApiOperation(value = "接入单个设备")
	@RequiresPermissions("eam:grm:update")
	@RequestMapping(value = "/connect/one", method = RequestMethod.POST)
	@ResponseBody
	public Object connectOne(String eId, String grmId) {
		_log.info("eIds="+eId);
		_log.info("grmId="+ grmId);
		EamGrmEquipment grmEquipment = buildEamGrmEquipment(grmId, eId);
		eamGrmEquipmentService.insert(grmEquipment);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	@ApiOperation(value = "移除单个设备")
	@RequiresPermissions("eam:grm:update")
	@RequestMapping(value = "/unconnected/one", method = RequestMethod.POST)
	@ResponseBody
	public Object unconnected(String eId) {
		EamGrmEquipmentExample example = new EamGrmEquipmentExample();
		example.createCriteria().andEquipmentIdEqualTo(eId);
		eamGrmEquipmentService.deleteByExample(example);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	private EamGrmEquipment buildEamGrmEquipment(String grmId, String eId) {
		EamGrmEquipment grmEquipment = new EamGrmEquipment();
		grmEquipment.setEquipmentId(eId);
		grmEquipment.setGrmId(grmId);
		grmEquipment.setDeleteFlag(Boolean.FALSE);
		grmEquipment.setCreateTime(new Date());
		grmEquipment.setCreateUserId(baseEntityUtil.getCurrentUser().getUserId());
		return grmEquipment;
	}

	@ApiOperation(value = "更新采集频率")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/equipment/write", method = RequestMethod.POST)
	@ResponseBody
	public Object equipmentWrite(@RequestBody EamEquipment equipment) {
		_log.info("equipment Id = " + equipment.getEquipmentId());
		int success = eamEquipmentService.updateByPrimaryKeySelective(equipment);
		if (success == 1){
			return new EamResult(SUCCESS, "更新数据成功");
		}else {
			return new EamResult(FAILED, "更新数据失败");
		}
	}

}