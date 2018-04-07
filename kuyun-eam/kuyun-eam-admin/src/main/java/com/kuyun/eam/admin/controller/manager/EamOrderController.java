package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.excel.ExcelUtils;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.initialize.EamCodeValueInitialize;
import com.kuyun.eam.common.constant.CodeValueType;
import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamOrder;
import com.kuyun.eam.pojo.OrderBean;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamCodeValueService;
import com.kuyun.eam.rpc.api.EamOrderService;
import com.kuyun.eam.vo.EamOrderVO;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务单controller
 * Created by kuyun on 2017/2/6.
 */
@Controller
@Api(value = "任务单管理", description = "任务单管理")
@RequestMapping("/manage/order")
public class EamOrderController extends BaseController {
    private static Logger _log = LoggerFactory.getLogger(EamOrderController.class);

    @Autowired
    private EamApiService eamApiService;

    @Autowired
    private EamCodeValueService eamCodeValueService;

    @Autowired
    private EamOrderService eamOrderService;

    @ApiOperation(value = "任务单首页")
    @RequiresPermissions("eam:order:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/order/index.jsp";
    }

    @ApiOperation(value = "任务单列表")
    @RequiresPermissions("eam:order:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(EamOrderVO orderVO) {
        if (orderVO.getOrderByClause() == null){
            orderVO.setOrderByClause("year, task_number desc");
        }
        orderVO.setDeleteFlag(Boolean.FALSE);

        List<EamOrderVO> rows = eamApiService.selectOrders(orderVO);
        long total = eamApiService.countOrders(orderVO);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "选择项")
    @RequiresPermissions("eam:order:read")
    @RequestMapping(value = "/selectOptions", method = RequestMethod.GET)
    @ResponseBody
    public Object selectOptions() {
        HashMap map = new HashMap();

        setSelectOptions(new ModelMap(), map);

        return map;
    }

    @ApiOperation(value = "新增任务单")
    @RequiresPermissions("eam:order:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        setSelectOptions(modelMap, new HashMap());
        return "/manage/order/create.jsp";
    }

    public void setSelectOptions(ModelMap modelMap, HashMap map) {
        List<EamCodeValue> states = eamApiService.getCodeValues(CodeValueType.STATE);
        List<EamCodeValue> industries = eamApiService.getCodeValues(CodeValueType.INDUSTRY);
        List<EamCodeValue> productLineTypes = eamApiService.getCodeValues(CodeValueType.PRODUCT_LINE_TYPE);
        List<EamCodeValue> productLineCapacities = eamApiService.getCodeValues(CodeValueType.PRODUCT_LINE_CAPACITY);
        List<EamCodeValue> packagingMaterials = eamApiService.getCodeValues(CodeValueType.PACKAGING_MATERIAL);
        List<EamCodeValue> productSpecs = eamApiService.getCodeValues(CodeValueType.PRODUCT_SPEC);

        modelMap.put("states", states);
        modelMap.put("industries", industries);
        modelMap.put("productLineTypes", productLineTypes);
        modelMap.put("productLineCapacities", productLineCapacities);
        modelMap.put("packagingMaterials", packagingMaterials);
        modelMap.put("productSpecs", productSpecs);

        map.put("states", states);
        map.put("industries", industries);
        map.put("productLineTypes", productLineTypes);
        map.put("productLineCapacities", productLineCapacities);
        map.put("packagingMaterials", packagingMaterials);
        map.put("productSpecs", productSpecs);

    }

    @ApiOperation(value = "新增任务单")
    @RequiresPermissions("eam:order:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(EamOrder eamOrder) {
        ComplexResult result = FluentValidator.checkAll()
                .on(eamOrder.getCompanyName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        eamOrder.setCreateTime(new Date());
        eamOrder.setDeleteFlag(Boolean.FALSE);

        int count = eamOrderService.insertSelective(eamOrder);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除任务单")
    @RequiresPermissions("eam:order:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = eamOrderService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改任务单")
    @RequiresPermissions("eam:order:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        EamOrder order = eamOrderService.selectByPrimaryKey(id);
        modelMap.put("order", order);
        setSelectOptions(modelMap, new HashMap());
        return "/manage/order/update.jsp";
    }

    @ApiOperation(value = "修改任务单")
    @RequiresPermissions("eam:order:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, EamOrder EamOrder) {
        ComplexResult result = FluentValidator.checkAll()
                .on(EamOrder.getCompanyName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        EamOrder.setCompanyId(id);
        EamOrder.setUpdateTime(new Date());
        EamOrder.setDeleteFlag(Boolean.FALSE);
        int count = eamOrderService.updateByPrimaryKeySelective(EamOrder);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "任务单上传")
    @RequiresPermissions("eam:order:update")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam("uploadFile") MultipartFile file) {
        new EamCodeValueInitialize(eamCodeValueService);

        _log.info("upload file name:{} ", file.getOriginalFilename());
        if(!file.isEmpty()) {
            try {
                Workbook workbook = ExcelUtils.getWorkbook(file);
                List<OrderBean> list = ExcelUtils.importExcel(workbook, OrderBean.class);
                _log.info("upload file record size:{}", list.size());

                eamApiService.importOrderData(list);

            } catch (Exception e) {
                _log.error("导入Excel失败:", e.getMessage());
                return new UpmsResult(UpmsResultConstant.FAILED, e.getMessage());
            }
            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
        } else {
            return new UpmsResult(UpmsResultConstant.FAILED, "导入失败");
        }

    }

}
