package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.util.MD5Util;
import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.dao.model.EamEquipmentCompany;
import com.kuyun.eam.dao.model.EamEquipmentCompanyExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentCompanyService;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsApiService;
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
    private UpmsApiService upmsApiService;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamEquipmentCompanyService eamEquipmentCompanyService;

    @Autowired
    private EamApiService eamApiService;

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
        }else {
            companyExample.setOrderByClause("create_time desc ");
        }

        UpmsCompanyExample.Criteria criteria = companyExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike("%" + search + "%");
        }

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            criteria.andParentIdEqualTo(company.getCompanyId());
        }

        List<UpmsCompany> rows = upmsCompanyService.selectByExample(companyExample);
        long total = upmsCompanyService.countByExample(companyExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增公司")
    @RequiresPermissions("eam:company:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
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
    @ResponseBody
    public Object update(@PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        UpmsCompany company = upmsCompanyService.selectByPrimaryKey(id);
        map.put("company", company);
        return map;
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

    @ApiOperation(value = "设备授权")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/auth/{id}", method = RequestMethod.GET)
    public String auth(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("companyId", id);
        return "/manage/company/equipment.jsp";
    }

    @ApiOperation(value = "设备授权列表")
    @RequiresPermissions("eam:equipment:read")
    @RequestMapping(value = "/equipment/list", method = RequestMethod.GET)
    @ResponseBody
    public Object equipmentList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "companyId") String companyId) {

        _log.info("companyId="+companyId);

        EamEquipmentController equipmentController = SpringContextUtil.getBean(EamEquipmentController.class);


        Map<String, Object> objectMap = (Map<String, Object> )equipmentController.list(offset, limit, null,null, sort, order);
        List<EamEquipmentVO> rows = (List<EamEquipmentVO>)objectMap.get("rows");
        long total = (long)objectMap.get("total");


        handleCheckedFlag(companyId, rows);


        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    private void handleCheckedFlag(String companyId, List<EamEquipmentVO> rows) {
        EamEquipmentCompanyExample example = new EamEquipmentCompanyExample();
        example.createCriteria().andCompanyIdEqualTo(Integer.valueOf(companyId)).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamEquipmentCompany> equipmentCompanies = eamEquipmentCompanyService.selectByExample(example);

        if (equipmentCompanies != null && rows != null){
            for(EamEquipmentVO eamEquipmentVO : rows){
                for (EamEquipmentCompany equipmentCompany : equipmentCompanies){
                    if (eamEquipmentVO.getEquipmentId().equals(equipmentCompany.getEquipmentId())){
                        eamEquipmentVO.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    @ApiOperation(value = "设备授权")
    @RequiresPermissions("eam:company:update")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Object auth(String eIds, String companyId, ModelMap modelMap) {
        String [] eIdList = eIds.split("::");
        _log.info("eIds="+eIds);
        _log.info("companyId="+companyId);
        //remove already exist data
        EamEquipmentCompanyExample example = new EamEquipmentCompanyExample();
        example.createCriteria().andCompanyIdEqualTo(Integer.valueOf(companyId));
        eamEquipmentCompanyService.deleteByExample(example);

        //add new
        List<EamEquipmentCompany> equipmentCompanies = new ArrayList<>();
        for(String eId : eIdList){
            EamEquipmentCompany equipmentCompany = new EamEquipmentCompany();
            equipmentCompany.setEquipmentId(eId);
            equipmentCompany.setCompanyId(Integer.valueOf(companyId));
            equipmentCompany.setDeleteFlag(Boolean.FALSE);
            equipmentCompany.setCreateTime(new Date());
            equipmentCompany.setCreateUserId(baseEntityUtil.getCurrentUser().getUserId());
            equipmentCompanies.add(equipmentCompany);
        }
        eamEquipmentCompanyService.batchInsert(equipmentCompanies);
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "新增简单用户")
    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser() {
        return "/manage/company/createUser.jsp";
    }

    @ApiOperation(value = "新增简单用户")
    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Object createUser(UpmsUser upmsUser) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsUser.getUsername(), new LengthValidator(1, 20, "帐号"))
                .on(upmsUser.getPassword(), new LengthValidator(5, 32, "密码"))
                .on(upmsUser.getRealname(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        int count = upmsApiService.handleSimpleUser(upmsUser, company.getCompanyId());
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }
}
