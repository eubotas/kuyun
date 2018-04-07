package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.excel.ExcelUtils;
import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.admin.initialize.EamCodeValueInitialize;
import com.kuyun.eam.dao.model.EamProductLineCompany;
import com.kuyun.eam.dao.model.EamProductLineCompanyExample;
import com.kuyun.eam.pojo.CompanyBean;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamCodeValueService;
import com.kuyun.eam.rpc.api.EamProductLineCompanyService;
import com.kuyun.eam.vo.EamProductLineVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
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

import java.util.*;

/**
 * 公司controller
 * Created by kuyun on 2017/2/6.
 */
@Controller
@Api(value = "公司管理", description = "公司管理")
@RequestMapping("/manage/company")
public class UpmsCompanyController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsCompanyController.class);

    @Autowired
    private UpmsCompanyService upmsCompanyService;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamApiService eamApiService;

    @Autowired
    private EamProductLineCompanyService eamProductLineCompanyService;

    @Autowired
    private UpmsApiService upmsApiService;

    @Autowired
    private EamCodeValueService eamCodeValueService;

    @ApiOperation(value = "公司首页")
    @RequiresPermissions("eam:company:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/company/index.jsp";
    }

    @ApiOperation(value = "公司列表")
    @RequiresPermissions("eam:company:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(UpmsCompanyVo companyVo) {
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            companyVo.setParentId(company.getCompanyId());
        }
        if (companyVo.getOrderByClause() == null){
            companyVo.setOrderByClause("name desc");
        }
        company.setDeleteFlag(Boolean.FALSE);

        List<UpmsCompany> rows = upmsApiService.selectCompanies(companyVo);
        long total = upmsApiService.countCompanies(companyVo);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增公司")
    @RequiresPermissions("eam:company:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        return "/manage/company/create.jsp";
    }

    @ApiOperation(value = "新增公司")
    @RequiresPermissions("eam:company:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsCompany upmsCompany) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsCompany.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsCompany.setCreateTime(new Date());
        upmsCompany.setDeleteFlag(Boolean.FALSE);

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            upmsCompany.setParentId(company.getCompanyId());
        }

        int count = eamApiService.createCustomer(upmsCompany);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除公司")
    @RequiresPermissions("eam:company:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsCompanyService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改公司")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsCompany company = upmsCompanyService.selectByPrimaryKey(id);
        modelMap.put("company", company);
        return "/manage/company/update.jsp";
    }

    @ApiOperation(value = "修改公司")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsCompany upmsCompany) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsCompany.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsCompany.setCompanyId(id);
        upmsCompany.setUpdateTime(new Date());
        upmsCompany.setDeleteFlag(Boolean.FALSE);
        int count = upmsCompanyService.updateByPrimaryKeySelective(upmsCompany);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "产线授权")
    @RequiresPermissions("eam:productLine:update")
    @RequestMapping(value = "/auth/{id}", method = RequestMethod.GET)
    public String auth(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("companyId", id);
        return "/manage/company/productLine.jsp";
    }

    @ApiOperation(value = "产线授权列表")
    @RequiresPermissions("eam:productLine:read")
    @RequestMapping(value = "/productLine/list", method = RequestMethod.GET)
    @ResponseBody
    public Object productLineList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "companyId") String companyId) {

        _log.info("companyId="+companyId);

        EamProductLineController productLineController = SpringContextUtil.getBean(EamProductLineController.class);


        Map<String, Object> objectMap = (Map<String, Object> )productLineController.list(offset, limit, sort, order);
        List<EamProductLineVO> rows = (List<EamProductLineVO>)objectMap.get("rows");
        long total = (long)objectMap.get("total");


        handleCheckedFlag(companyId, rows);


        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    private void handleCheckedFlag(String companyId, List<EamProductLineVO> rows) {
        EamProductLineCompanyExample example = new EamProductLineCompanyExample();
        example.createCriteria().andCompanyIdEqualTo(Integer.valueOf(companyId)).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamProductLineCompany> productLineCompanies = eamProductLineCompanyService.selectByExample(example);

        if (productLineCompanies != null && rows != null){
            for(EamProductLineVO productLine : rows){
                for (EamProductLineCompany productLineCompany : productLineCompanies){
                    if (productLine.getProductLineId().equals(productLineCompany.getProductLineId())){
                        productLine.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    @ApiOperation(value = "产线授权确认")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Object auth(String pIds, String companyId, ModelMap modelMap) {
        String [] pIdList = pIds.split("::");
        _log.info("pIds="+pIds);
        _log.info("companyId="+companyId);
        //remove already exist data
        EamProductLineCompanyExample example = new EamProductLineCompanyExample();
        example.createCriteria().andCompanyIdEqualTo(Integer.valueOf(companyId));
        eamProductLineCompanyService.deleteByExample(example);

        //add new
        List<EamProductLineCompany> productLineCompanies = new ArrayList<>();
        for(String pId : pIdList){
            EamProductLineCompany productLineCompany = new EamProductLineCompany();
            productLineCompany.setProductLineId(pId);
            productLineCompany.setCompanyId(Integer.valueOf(companyId));
            productLineCompany.setDeleteFlag(Boolean.FALSE);
            productLineCompany.setCreateTime(new Date());
            productLineCompany.setCreateUserId(baseEntityUtil.getCurrentUser().getUserId());
            productLineCompanies.add(productLineCompany);
        }
        eamProductLineCompanyService.batchInsert(productLineCompanies);
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "客户资料上传")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam("uploadFile") MultipartFile file) {
        new EamCodeValueInitialize(eamCodeValueService);

        _log.info("upload file name:{} ", file.getOriginalFilename());
        if(!file.isEmpty()) {
            try {
                Workbook workbook = ExcelUtils.getWorkbook(file);
                List<CompanyBean> list = ExcelUtils.importExcel(workbook, CompanyBean.class);
                _log.info("upload file record size:{}", list.size());

                UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

                eamApiService.importCompanyData(list, company);

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
