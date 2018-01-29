package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamDataElementGroupService;
import com.kuyun.eam.rpc.api.EamEquipmentDataGroupElemetsService;
import com.kuyun.eam.rpc.api.EamEquipmentDataGroupService;
import com.kuyun.eam.vo.EamDataElementVO;
import com.kuyun.eam.vo.EamEquipmentDataGroupVO;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 数据点分组控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "数据点分组管理", description = "数据点分组管理")
@RequestMapping("/manage/{equipmentId}/dataGroup")
public class EamEquipmentDataGroupController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentDataGroupController.class);
	
	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamDataElementGroupService eamDataElementGroupService;

	@Autowired
	private EamEquipmentDataGroupService eamEquipmentDataGroupService;

	@Autowired
	private EamEquipmentDataGroupElemetsService eamEquipmentDataGroupElemetsService;

	@Autowired
	private EamDataElementController dataElementController;


	@ApiOperation(value = "数据点分组首页")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("equipmentId") String equipmentId, ModelMap modelMap) {
		modelMap.addAttribute("equipmentId", equipmentId);
		return "/manage/equipment/dataGroup/index.jsp";
	}

	@ApiOperation(value = "数据点分组列表")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("equipmentId") String equipmentId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentDataGroupVO dataGroupVO = new EamEquipmentDataGroupVO();
		dataGroupVO.setOffset(offset);
		dataGroupVO.setLimit(limit);
		dataGroupVO.setDeleteFlag(Boolean.FALSE);
		dataGroupVO.setEquipmentId(equipmentId);


		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			dataGroupVO.setOrderByClause(sort + " " + order);
		}


		List<EamEquipmentDataGroupVO> rows = eamApiService.selectEquipmentDataGroups(dataGroupVO);
		long total = eamApiService.countEquipmentDataGroups(dataGroupVO);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "删除数据点分组")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamEquipmentDataGroupService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}


	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/all/index", method = RequestMethod.GET)
	public String allIndex(@PathVariable("equipmentId") String equipmentId, ModelMap modelMap) {
		modelMap.addAttribute("equipmentId", equipmentId);
		return "/manage/equipment/dataGroup/allDataGroup.jsp";
	}

	@ApiOperation(value = "未分配数据点分组列表")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Object all(@PathVariable("equipmentId") String equipmentId) {

		EamDataElementGroupExample example = new EamDataElementGroupExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamDataElementGroup> groups = eamDataElementGroupService.selectByExample(example);

		EamEquipmentDataGroupVO dataGroupVO = new EamEquipmentDataGroupVO();
		dataGroupVO.setEquipmentId(equipmentId);
		List<EamEquipmentDataGroupVO> rows = eamApiService.selectEquipmentDataGroups(dataGroupVO);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", filterDataElementGroups(groups, rows));
		return result;
	}

	private List<EamDataElementGroup> filterDataElementGroups(List<EamDataElementGroup> groups, List<EamEquipmentDataGroupVO> rows){
		List<EamDataElementGroup> result = new ArrayList<>();

		for(EamDataElementGroup group : groups){
			boolean found = false;
			for(EamEquipmentDataGroupVO row : rows){
				if (group.getId().equals(row.getDataGroupId())){
					found = true;
					break;
				}
			}
			if (!found){
				result.add(group);
			}
		}
		return result;
	}

	@ApiOperation(value = "选择数据点分组")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	@ResponseBody
	public Object confirm(@PathVariable("equipmentId") String equipmentId, String ids, ModelMap modelMap) {

		List<EamEquipmentDataGroup> groups = buildEamEquipmentDataGroups(equipmentId, ids);

		eamEquipmentDataGroupService.batchInsert(groups);

		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}


	private List<EamEquipmentDataGroup> buildEamEquipmentDataGroups(String equipmentId, String ids){
		List<EamEquipmentDataGroup> result = new ArrayList<>();
		String [] idsArray = ids.split("::");

		for (String id : idsArray){
			EamEquipmentDataGroup group = new EamEquipmentDataGroup();
			group.setDataGroupId(Integer.valueOf(id));
			group.setEquipmentId(equipmentId);
			baseEntityUtil.addAddtionalValue(group);
			result.add(group);
		}
		return result;
	}

	@ApiOperation(value = "设备数据点列表首页")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/{dataGroupId}/{equipmentDataGroupId}/index",method = RequestMethod.GET)
	public Object elementIndex(@PathVariable("equipmentId") String equipmentId,
							   @PathVariable("dataGroupId") String dataGroupId,
							   @PathVariable("equipmentDataGroupId") String equipmentDataGroupId,
								 ModelMap modelMap) {
		modelMap.addAttribute("equipmentId", equipmentId);
		modelMap.addAttribute("dataGroupId", dataGroupId);
		modelMap.addAttribute("equipmentDataGroupId", equipmentDataGroupId);
		return "/manage/equipment/dataGroup/element.jsp";
	}

	@ApiOperation(value = "设备数据点列表")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/{dataGroupId}/{equipmentDataGroupId}/list", method = RequestMethod.GET)
	@ResponseBody
	public Object elementList(@PathVariable("equipmentId") String equipmentId,
								 @PathVariable("dataGroupId") String dataGroupId,
								 @PathVariable("equipmentDataGroupId") String equipmentDataGroupId,
								 String ids, ModelMap modelMap) {
		Map<String, Object> map = (Map<String, Object>)dataElementController.list(0, 3000, null, null, null);
		List<EamDataElementVO> rows = (List<EamDataElementVO>)map.get("rows");


		handlerCheckedFlag(equipmentId, dataGroupId, equipmentDataGroupId, rows);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		return result;
	}

	private void handlerCheckedFlag(String equipmentId, String dataGroupId, String equipmentDataGroupId, List<EamDataElementVO> rows) {
		EamEquipmentDataGroupElemetsExample example = new EamEquipmentDataGroupElemetsExample();
		example.createCriteria().andEquipmentIdEqualTo(equipmentId)
				.andDataGroupIdEqualTo(Integer.valueOf(dataGroupId))
				.andEquipmentDataGroupIdEqualTo(Integer.valueOf(equipmentDataGroupId))
				.andDeleteFlagEqualTo(Boolean.FALSE);

		List<EamEquipmentDataGroupElemets> dataElements = eamEquipmentDataGroupElemetsService.selectByExample(example);
		if (dataElements != null && !dataElements.isEmpty()){
			for(EamDataElementVO row : rows){
				for(EamEquipmentDataGroupElemets dataElement : dataElements){
					if (row.getId().equals(dataElement.getDataElementId())){
						row.setChecked(Boolean.TRUE);
						break;
					}
				}
			}
		}

	}


	@ApiOperation(value = "选择数据点")
	@RequiresPermissions("eam:dataGroup:read")
	@RequestMapping(value = "/{dataGroupId}/{equipmentDataGroupId}/confirm", method = RequestMethod.GET)
	@ResponseBody
	public Object elementConfirm(@PathVariable("equipmentId") String equipmentId,
								 @PathVariable("dataGroupId") String dataGroupId,
								 @PathVariable("equipmentDataGroupId") String equipmentDataGroupId,
								 String ids, ModelMap modelMap) {
		_log.info("dataGroupId=" + dataGroupId);
		_log.info("equipmentDataGroupId=" + equipmentDataGroupId);
		_log.info("ids=" + ids);
		//Remove all
		EamEquipmentDataGroupElemetsExample example = new EamEquipmentDataGroupElemetsExample();
		example.createCriteria().andEquipmentIdEqualTo(equipmentId)
				.andDataGroupIdEqualTo(Integer.valueOf(dataGroupId))
				.andEquipmentDataGroupIdEqualTo(Integer.valueOf(equipmentDataGroupId))
				.andDeleteFlagEqualTo(Boolean.FALSE);
		eamEquipmentDataGroupElemetsService.deleteByExample(example);

		//Add new
		List<EamEquipmentDataGroupElemets> elements = buildEamEquipmentDataGroupElements(equipmentId, dataGroupId, equipmentDataGroupId, ids);

		eamEquipmentDataGroupElemetsService.batchInsert(elements);

		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	private List<EamEquipmentDataGroupElemets> buildEamEquipmentDataGroupElements(String equipmentId, String dataGroupId, String equipmentDataGroupId, String ids) {
		List<EamEquipmentDataGroupElemets> elements = new ArrayList<>();
		String [] idsArray = ids.split("::");

		for (String id : idsArray){
			EamEquipmentDataGroupElemets element = new EamEquipmentDataGroupElemets();
			element.setDataGroupId(Integer.valueOf(dataGroupId));
			element.setEquipmentDataGroupId(Integer.valueOf(equipmentDataGroupId));
			element.setEquipmentId(equipmentId);
			element.setDataElementId(Integer.valueOf(id));
			baseEntityUtil.addAddtionalValue(element);
			elements.add(element);
		}

		return elements;
	}


}