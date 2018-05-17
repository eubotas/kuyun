package com.kuyun.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.util.StringUtil;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.JspUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.dao.model.UpmsCompanyOption;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsCompanyOptionService;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
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
    private UpmsCompanyOptionService upmsCompanyOptionService;

    @Autowired
    private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @ApiOperation(value = "公司首页")
    @RequiresPermissions("upms:company:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        modelMap.put("pid", getCompanyId());
        return "/manage/company/index.jsp";
    }

    @ApiOperation(value = "公司列表")
    @RequiresPermissions("upms:company:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsCompanyExample companyExample = new UpmsCompanyExample();
        companyExample.setOffset(offset);
        companyExample.setLimit(limit);
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            companyExample.setOrderByClause(sort + " " + order);
        }
        if (StringUtils.isNotBlank(search)) {
            companyExample.or()
                    .andNameLike("%" + search + "%");
        }

        UpmsCompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            criteria.andCompanyIdEqualTo(company.getCompanyId());
        }

        List<UpmsCompany> rows = upmsCompanyService.selectByExample(companyExample);
        long total = upmsCompanyService.countByExample(companyExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增公司")
    @RequiresPermissions("upms:company:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/company/create.jsp";
    }

    @ApiOperation(value = "新增公司")
    @RequiresPermissions("upms:company:create")
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

        int count = upmsCompanyService.insertSelective(upmsCompany);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除公司")
    @RequiresPermissions("upms:company:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsCompanyService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改公司")
    @RequiresPermissions("upms:company:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsCompany company = upmsCompanyService.selectByPrimaryKey(id);
        Map map=new HashMap();
        map.put("company", company);
        return map;
    }

    @ApiOperation(value = "修改公司")
    @RequiresPermissions("upms:company:update")
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
        upmsCompany.setUpdateTime(new Date());
        upmsCompany.setDeleteFlag(Boolean.FALSE);
        upmsCompany.setCompanyId(id);
        int count = upmsCompanyService.updateByPrimaryKeySelective(upmsCompany);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "添加/修改公司logo")
    @RequiresPermissions("upms:company:update")
    @RequestMapping(value = "/updateOption", method = RequestMethod.GET)
    public String updateOption(ModelMap modelMap) {
        UpmsCompanyOption opt = upmsCompanyOptionService.selectByPrimaryKey(getCompanyId());
        modelMap.put("companyOpt", opt);
        modelMap.put("uploadServer", fileUploaderService.getServerInfo());
        return "/manage/company/companyOption.jsp";
    }

    @ApiOperation(value = "修改公司")
    @RequiresPermissions("upms:company:update")
    @RequestMapping(value = "/updateOption", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOption(UpmsCompanyOption upmsCompanyOption) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsCompanyOption.getSystemName(), new LengthValidator(1, 60, "系统名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsCompanyOption.setUpdateTime(new Date());
        upmsCompanyOption.setDeleteFlag(Boolean.FALSE);
        upmsCompanyOption.setLogoPath(StringUtil.removeSuffix(upmsCompanyOption.getLogoPath(), "::"));
        int count =0;

        if(null == upmsCompanyOption.getCompanyId()){ //insert
            upmsCompanyOption.setCompanyId(getCompanyId());
            count = upmsCompanyOptionService.insert(upmsCompanyOption);
        }else {
            count = upmsCompanyOptionService.updateByPrimaryKeySelective(upmsCompanyOption);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    public int getCompanyId(){
        int cId=-1;
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            cId = company.getCompanyId();
        }
        return cId;
    }
}
