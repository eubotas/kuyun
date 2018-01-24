package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.ProtocolEnum;
import com.kuyun.eam.vo.EamEquipmentModelPropertiesVO;
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
 * DTU控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "DTU管理", description = "DTU管理")
@RequestMapping("/manage/dtu")
public class EamDtuController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamDtuController.class);
	
	@Autowired
	private EamDtuService eamDtuService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamDtuEquipmentService eamDtuEquipmentService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamProtocolService protocolService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;



	@ApiOperation(value = "DTU首页")
	@RequiresPermissions("eam:dtu:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		EamProtocol protocol = protocolService.selectByPrimaryKey(ProtocolEnum.MODBUS_RTU.getId());
		modelMap.put("protocol", protocol);
		return "/manage/dtu/index.jsp";
	}

	@ApiOperation(value = "DTU列表")
	@RequiresPermissions("eam:dtu:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamDtuExample dtuExample = new EamDtuExample();
		dtuExample.setOffset(offset);
		dtuExample.setLimit(limit);
		EamDtuExample.Criteria criteria = dtuExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			dtuExample.setOrderByClause(sort + " " + order);
		}else {
			dtuExample.setOrderByClause("create_time desc");
		}
		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}
		List<EamDtu> rows = eamDtuService.selectByExample(dtuExample);
		long total = eamDtuService.countByExample(dtuExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增DTU")
	@RequiresPermissions("eam:dtu:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "/manage/dtu/create.jsp";
	}

	@ApiOperation(value = "新增DTU")
	@RequiresPermissions("eam:dtu:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamDtu dtu) {
		baseEntityUtil.addAddtionalValue(dtu);
		int count = eamDtuService.insertSelective(dtu);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除DTU")
	@RequiresPermissions("eam:dtu:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamDtuService.deleteByPrimaryKeys(ids);
		String[] idArray = ids.split("-");

		EamDtuEquipmentExample example = new EamDtuEquipmentExample();
		example.createCriteria().andDtuIdIn(Arrays.asList(idArray));

		eamDtuEquipmentService.deleteByExample(example);

		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "修改DTU")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") String id, ModelMap modelMap) {
		EamDtu dtu = eamDtuService.selectByPrimaryKey(id);
		modelMap.put("dtu", dtu);
		return "/manage/dtu/update.jsp";
	}

	@ApiOperation(value = "修改DTU")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value= "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") String id, EamDtu dtu) {
		baseEntityUtil.updateAddtionalValue(dtu);
		dtu.setDtuId(id);
		int count = eamDtuService.updateByPrimaryKeySelective(dtu);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "接入设备")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value = "/connect/{id}", method = RequestMethod.GET)
	public String auth(@PathVariable("id") String id, ModelMap modelMap) {
		modelMap.put("dtuId", id);
		return "/manage/dtu/equipment.jsp";
	}

	@ApiOperation(value = "接入设备列表")
	@RequiresPermissions("eam:dtu:read")
	@RequestMapping(value = "/equipment/list", method = RequestMethod.GET)
	@ResponseBody
	public Object equipmentList(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order,
			@RequestParam(required = false, value = "dtuId") String dtuId) {

		_log.info("dtuId="+dtuId);

		EamEquipmentVO equipmentVO = new EamEquipmentVO();
		equipmentVO.setOffset(offset);
		equipmentVO.setLimit(limit);
		equipmentVO.setDeleteFlag(Boolean.FALSE);
		equipmentVO.setDtuId(dtuId);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			equipmentVO.setOrderByClause(sort + " " + order);
		}else {
			equipmentVO.setOrderByClause("t.equipment_id, t.create_time desc");
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			equipmentVO.setCompanyId(company.getCompanyId());
		}


		List<EamEquipmentVO> rows = eamApiService.selectUnConnectDtuEquipments(equipmentVO);
		long total = eamApiService.countUnConnectDtuEquipments(equipmentVO);


		handleCheckedFlag(dtuId, rows);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	private void handleCheckedFlag(String dtuId, List<EamEquipmentVO> rows) {
		EamDtuEquipmentExample example = new EamDtuEquipmentExample();
		example.createCriteria().andDtuIdEqualTo(dtuId).andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamDtuEquipment> dtuEquipments = eamDtuEquipmentService.selectByExample(example);

		if (dtuEquipments != null && rows != null){
			for(EamEquipmentVO eamEquipmentVO : rows){
				for (EamDtuEquipment dtuEquipment : dtuEquipments){
					if (eamEquipmentVO.getEquipmentId().equals(dtuEquipment.getEquipmentId())){
						eamEquipmentVO.setChecked(true);
						break;
					}
				}
			}
		}
	}

	@ApiOperation(value = "接入设备")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value = "/connect", method = RequestMethod.POST)
	@ResponseBody
	public Object connect(String eIds, String dtuId, ModelMap modelMap) {
		String [] eIdList = eIds.split("::");
		_log.info("eIds="+eIds);
		_log.info("dtuId="+ dtuId);
		//remove already exist data
		EamDtuEquipmentExample example = new EamDtuEquipmentExample();
		example.createCriteria().andDtuIdEqualTo(dtuId);
		eamDtuEquipmentService.deleteByExample(example);

		//add new
		List<EamDtuEquipment> dtuEquipments = new ArrayList<>();
		for(String eId : eIdList){
			EamDtuEquipment dtuEquipment = buildEamDtuEquipment(dtuId, eId);
			dtuEquipments.add(dtuEquipment);
		}
		eamDtuEquipmentService.batchInsert(dtuEquipments);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	@ApiOperation(value = "接入单个设备")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value = "/connect/one", method = RequestMethod.POST)
	@ResponseBody
	public Object connectOne(String eId, String dtuId) {
		_log.info("eIds="+eId);
		_log.info("dtuId="+ dtuId);
		EamDtuEquipment dtuEquipment = buildEamDtuEquipment(dtuId, eId);
		eamDtuEquipmentService.insert(dtuEquipment);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	@ApiOperation(value = "移除单个设备")
	@RequiresPermissions("eam:dtu:update")
	@RequestMapping(value = "/unconnected/one", method = RequestMethod.POST)
	@ResponseBody
	public Object unconnected(String eId) {
		EamDtuEquipmentExample example = new EamDtuEquipmentExample();
		example.createCriteria().andEquipmentIdEqualTo(eId);
		eamDtuEquipmentService.deleteByExample(example);
		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	private EamDtuEquipment buildEamDtuEquipment(String dtuId, String eId) {
		EamDtuEquipment dtuEquipment = new EamDtuEquipment();
		dtuEquipment.setEquipmentId(eId);
		dtuEquipment.setDtuId(dtuId);
		dtuEquipment.setDeleteFlag(Boolean.FALSE);
		dtuEquipment.setCreateTime(new Date());
		dtuEquipment.setCreateUserId(baseEntityUtil.getCurrentUser().getUserId());
		return dtuEquipment;
	}

	@ApiOperation(value = "写入从站地址")
	@RequiresPermissions("eam:equipment:update")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/equipment/write", method = RequestMethod.POST)
	@ResponseBody
	public Object equipmentWrite(@RequestBody EamEquipment equipment) {
		_log.info("equipment Id = " + equipment.getEquipmentId());
		_log.info("Slave Id = " + equipment.getSalveId());
		int success = eamEquipmentService.updateByPrimaryKeySelective(equipment);
		if (success == 1){
			return new EamResult(SUCCESS, "写入数据成功");
		}else {
			return new EamResult(FAILED, "写入数据失败");
		}
	}

}