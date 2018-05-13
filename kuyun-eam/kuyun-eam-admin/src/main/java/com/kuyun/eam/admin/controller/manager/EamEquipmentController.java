package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.gson.Gson;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.excel.ExcelUtils;
import com.kuyun.common.util.StringUtil;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.pojo.IDS;
import com.kuyun.eam.pojo.PartBean;
import com.kuyun.eam.pojo.sensor.SensorGroup;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamEquipmentModelPropertiesVO;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamGrmVariableVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.CollectStatus.NO_START;
import static com.kuyun.eam.common.constant.EamResultConstant.*;

/**
 * 设备控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "设备管理", description = "设备管理")
@RequestMapping("/manage/{productLineId}/equipment")
public class EamEquipmentController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamEquipmentController.class);

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamEquipmentModelService eamEquipmentModelService;

	@Autowired
	private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

	@Autowired
	private EamEquipmentPropertyService eamEquipmentPropertyService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamProtocolService protocolService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamWriteDataService eamWriteDataService;

	@Autowired
	private EamEquipmentCategoryService eamEquipmentCategoryService;

	@Autowired
	private EamGrmVariableService eamGrmVariableService;

	@Autowired
	private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

	@ApiOperation(value = "设备首页")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("productLineId") String productLineId, ModelMap modelMap) {
		modelMap.addAttribute("productLineId", productLineId);
		return "/manage/equipment/index.jsp";
	}

	@ApiOperation(value = "设备列表")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@PathVariable("productLineId") String productLineId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "categoryId") String categoryId,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamEquipmentVO equipmentVO = new EamEquipmentVO();
		equipmentVO.setOffset(offset);
		equipmentVO.setLimit(limit);
		equipmentVO.setDeleteFlag(Boolean.FALSE);
		equipmentVO.setProductLineId(productLineId);

		if (StringUtils.isNotEmpty(categoryId)) {
			equipmentVO.setEquipmentCategoryId(Integer.valueOf(categoryId));
		}

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			equipmentVO.setOrderByClause(sort + " " + order);
		} else {
			equipmentVO.setOrderByClause("eam_equipment.create_time desc");
		}


		List<EamEquipmentVO> rows = eamApiService.selectEquipments(equipmentVO);
		long total = eamApiService.countEquipments(equipmentVO);


		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增设备")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@PathVariable("productLineId") String productLineId, ModelMap modelMap) {
		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		modelMap.put("productLineId", productLineId);
		modelMap.put("equipmentCategories", getEamEquipmentCategories(company));
		modelMap.put("uploadServer", fileUploaderService.getServerInfo());

		return "/manage/equipment/create.jsp";
	}

	private List<EamEquipmentCategory> getEamEquipmentCategories(UpmsUserCompany company) {
		EamEquipmentCategoryExample categoryExample = new EamEquipmentCategoryExample();
		EamEquipmentCategoryExample.Criteria categoryCriteria = categoryExample.createCriteria();
		categoryCriteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (company != null) {
			categoryCriteria.andCompanyIdEqualTo(company.getCompanyId());
		}

		return eamEquipmentCategoryService.selectByExample(categoryExample);
	}


	@ApiOperation(value = "新增设备")
	@RequiresPermissions("eam:equipment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamEquipment eamEquipment) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamEquipment.getName(), new LengthValidator(1, 20, "设备名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamEquipment);
		eamEquipment.setIsOnline(Boolean.FALSE);
		UpmsUserCompany upmsUserCompany = baseEntityUtil.getCurrentUserCompany();
		int count = eamApiService.persistEquipment(upmsUserCompany, eamEquipment);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除设备")
	@RequiresPermissions("eam:equipment:delete")
	@RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		String jsonString = covertToJson(ids);

		eamApiService.handleEquimpmentCollect(jsonString, NO_START);
		int count = eamEquipmentService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

	private String covertToJson(String ids) {
		IDS idsObj = new IDS();
		idsObj.setIds(ids);
		Gson gson = new Gson();
		return gson.toJson(idsObj);
	}

	@ApiOperation(value = "修改设备")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("productLineId") String productLineId, @PathVariable("id") String id, ModelMap modelMap) {
		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		modelMap.put("productLineId", productLineId);
		modelMap.put("equipmentCategories", getEamEquipmentCategories(company));

		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(id);
		modelMap.put("equipment", equipment);
		return "/manage/equipment/update.jsp";
	}

	@ApiOperation(value = "获取设备信息")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getEamEquipment(@PathVariable("id") String id) {

		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(id);
		return new EamResult(SUCCESS, equipment);
	}

	@ApiOperation(value = "修改设备")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") String id, EamEquipment equipment) {
		ComplexResult result = FluentValidator.checkAll()
				.on(equipment.getName(), new LengthValidator(1, 20, "设备名称"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		equipment.setEquipmentId(id);
		baseEntityUtil.updateAddtionalValue(equipment);
		int count = eamEquipmentService.updateByPrimaryKeySelective(equipment);
		return new EamResult(SUCCESS, count);
	}


	@ApiOperation(value = "设备当前采集数据")
	@RequiresPermissions("eam:equipment:read")
	@RequestMapping(value = "/sensor/data/{eId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getSensorData(@PathVariable("eId") String eId) {
		List<SensorGroup> sensorGroups = eamApiService.getSensorData(eId);
		return new EamResult(SUCCESS, sensorGroups);
	}

	@RequiresPermissions("eam:equipmentSensor:write")
	@RequestMapping(value = "/sensor/{eId}", method = RequestMethod.GET)
	public String sensor(@PathVariable("eId") String eId, ModelMap modelMap) {
		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(eId);

		modelMap.put("equipment", equipment);
		return "/manage/equipment/sensor/index.jsp";
	}

	@ApiOperation(value = "设备参数列表")
	@RequiresPermissions("eam:equipmentSensor:write")
	@RequestMapping(value = "/sensor/list/{eId}", method = RequestMethod.GET)
	@ResponseBody
	public Object sensorList(@PathVariable("eId") String eId) {
		Map<String, Object> result = new HashMap<>();
		result.put("rows", eamApiService.selectEquipmentModelProperties(eId));
		return result;
	}

	@ApiOperation(value = "设备写入数据")
	@RequiresPermissions("eam:equipmentSensor:write")
	@RequestMapping(value = "/sensor/write", method = RequestMethod.POST)
	@ResponseBody
	public Object sensorWrite(@RequestBody EamEquipmentModelPropertiesVO vo) {
		_log.info("Equipment Model Id = " + vo.getEquipmentModelId());
		boolean success = eamWriteDataService.sensorWrite(vo);
		if (success) {
			return new EamResult(SUCCESS, "写入数据成功");
		} else {
			return new EamResult(FAILED, "写入数据失败");
		}
	}

	@ApiOperation(value = "选择数据点")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/grm/{id}", method = RequestMethod.GET)
	public String grmIndex(
			@PathVariable("productLineId") String productLineId,
			@PathVariable("id") String id, ModelMap modelMap) {
		modelMap.put("productLineId", productLineId);
		modelMap.put("equipmentId", id);
		return "/manage/equipment/grm_variable.jsp";
	}


	@ApiOperation(value = "数据点列表")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/grm/list", method = RequestMethod.GET)
	@ResponseBody
	public Object grmList(
			@PathVariable("productLineId") String productLineId,
			@RequestParam(required = false, value = "equipmentId") String equipmentId) {
		List<EamGrmVariableVO> rows = eamApiService.selectGrmVariables(productLineId);

		handlerCheckedFlag(equipmentId, rows);

		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		return result;
	}

	private void handlerCheckedFlag(String equipmentId, List<EamGrmVariableVO> rows) {
		EamGrmVariableExample example = new EamGrmVariableExample();
		example.createCriteria().andEquipmentIdEqualTo(equipmentId).andDeleteFlagEqualTo(Boolean.FALSE);
		List<EamGrmVariable> variables = eamGrmVariableService.selectByExample(example);
		if (variables != null && !variables.isEmpty()) {
			for (EamGrmVariableVO row : rows) {
				for (EamGrmVariable variable : variables) {
					if (row.getName().equals(variable.getName())) {
						row.setChecked(Boolean.TRUE);
						break;
					}
				}
			}
		}

	}

	@ApiOperation(value = "确认数据点")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/grm/confirm", method = RequestMethod.POST)
	@ResponseBody
	public Object grmConfirm(@PathVariable("productLineId") String productLineId,
							 String equipmentId, String names, ModelMap modelMap) {

		List<EamGrmVariable> vos = buildVariables(productLineId, equipmentId, names);

		if (!vos.isEmpty()) {
			//remove already exist data
			EamGrmVariableExample example = new EamGrmVariableExample();
			example.createCriteria().andEquipmentIdEqualTo(equipmentId);
			eamGrmVariableService.deleteByExample(example);

			//add new
			eamGrmVariableService.batchInsert(vos);
		}

		return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
	}

	private List<EamGrmVariable> buildVariables(String productLineId, String equipmentId, String names) {
		List<EamGrmVariable> result = new ArrayList<>();
		String[] nameArray = names.split("::");

		List<EamGrmVariableVO> rows = eamApiService.selectGrmVariables(productLineId);
		for (String name : nameArray) {
			for (EamGrmVariableVO vo : rows) {
				if (name.equals(vo.getName())) {
					vo.setEquipmentId(equipmentId);
					baseEntityUtil.addAddtionalValue(vo);
					result.add(vo);
					break;
				}
			}
		}

		return result;
	}


	@ApiOperation(value = "单机备件资料上传")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadFile(@RequestParam("uploadFile") MultipartFile file, EamEquipment equipment) {
		_log.info("upload file name:{} ", file.getOriginalFilename());
		if (!file.isEmpty()) {
			try {
				Workbook workbook = ExcelUtils.getWorkbook(file);
				List<PartBean> list = ExcelUtils.importExcel(workbook, PartBean.class);
				_log.info("upload file record size:{}", list.size());

				UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

				eamApiService.importPartData(list, company, equipment);

			} catch (Exception e) {
				_log.error("导入Excel失败:", e.getMessage());
				return new UpmsResult(UpmsResultConstant.FAILED, e.getMessage());
			}
			return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
		} else {
			return new UpmsResult(UpmsResultConstant.FAILED, "导入失败");
		}

	}

	@ApiOperation(value = "创建设备属性")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/{eid}/property/create", method = RequestMethod.GET)
	public String createProperty(@PathVariable("productLineId") String productLineId,
								 @PathVariable("eid") String equipmentId, ModelMap modelMap) {
		EamEquipmentPropertyExample ex=new EamEquipmentPropertyExample();
		ex.createCriteria().andEquipmentIdEqualTo(equipmentId).andDeleteFlagEqualTo(false);
		List<EamEquipmentProperty> props = eamEquipmentPropertyService.selectByExample(ex);
		modelMap.put("props", props);
		modelMap.put("productLineId", productLineId);
		modelMap.put("equipmentId", equipmentId);
		return "/manage/equipment/createProperty.jsp";
	}

	@ApiOperation(value = "创建设备属性")
	@RequiresPermissions("eam:equipment:update")
	@RequestMapping(value = "/{eid}/property/create", method = RequestMethod.POST)
	@ResponseBody
	public Object createProperty(@PathVariable("eid") String equipmentId, @RequestParam(value="propertyLabel", required = true) List<String> propertyLabels,
								 @RequestParam(value="propertyValue", required = true) List<String> propertyValues) {
		List<EamEquipmentProperty> props=new ArrayList<EamEquipmentProperty>();
		EamEquipmentProperty prop=null;
		String label, val;
		for(int i=0; i < propertyLabels.size(); i++ ){
			label=propertyLabels.get(i);
			val=propertyValues.get(i);
			if("".equals(label) || "".equals(val))
				continue;
			prop=new EamEquipmentProperty();
			prop.setEquipmentId(equipmentId);
			prop.setPropertyLabel(label);
			prop.setPropertyValue(val);
			baseEntityUtil.addAddtionalValue(prop);
			props.add(prop);
		}
		if(props.size() < 1)
			return new UpmsResult(UpmsResultConstant.SUCCESS, 0);

		EamEquipmentPropertyExample ex=new EamEquipmentPropertyExample();
		ex.createCriteria().andEquipmentIdEqualTo(equipmentId).andDeleteFlagEqualTo(false);
		int result = eamEquipmentPropertyService.deleteByExample(ex);

		eamEquipmentPropertyService.batchInsert(props);

		return new UpmsResult(UpmsResultConstant.SUCCESS, result);
	}
}
