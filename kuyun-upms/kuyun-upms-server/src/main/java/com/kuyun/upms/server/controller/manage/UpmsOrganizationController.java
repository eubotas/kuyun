package com.kuyun.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsOrgRoleVo;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import com.kuyun.upms.rpc.api.UpmsOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织controller
 * Created by kuyun on 2017/2/6.
 */
@Controller
@Api(value = "组织管理", description = "组织管理")
@RequestMapping("/manage/organization")
public class UpmsOrganizationController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationController.class);

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;

    @Autowired
    private UpmsCompanyService upmsCompanyService;

    @Autowired
    private UpmsApiService upmsApiService;
    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @ApiOperation(value = "组织首页")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/organization/index.jsp";
    }

    @ApiOperation(value = "组织列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();
        upmsOrganizationExample.setOffset(offset);
        upmsOrganizationExample.setLimit(limit);
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            upmsOrganizationExample.setOrderByClause(sort + " " + order);
        }

        UpmsOrganizationExample.Criteria criteria = upmsOrganizationExample.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        List<String> fixedOrgs= new ArrayList<String>();
        fixedOrgs.add("维修部门");
        fixedOrgs.add("维保部门");
        fixedOrgs.add("报警部门");
        criteria.andNameNotIn(fixedOrgs);

        if (StringUtils.isNotBlank(search)) {
            criteria.andNameLike("%" + search + "%");
        }

        List<UpmsOrganization> rows = upmsOrganizationService.selectByExample(upmsOrganizationExample);
        long total = upmsOrganizationService.countByExample(upmsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    /*************  assign role start **********************/
    @ApiOperation(value = "分配角色")
    @RequiresPermissions("upms:organization:create")
    @RequestMapping(value = "/assignRole/{id}", method = RequestMethod.GET)
    public String assignRole(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("orgId", id);
        return "/manage/organization/role.jsp";
    }

    @ApiOperation(value = "分配角色")
    @RequiresPermissions("upms:organization:create")
    @ResponseBody
    @RequestMapping(value = "/assignRole/{id}", method = RequestMethod.POST)
    public Object assignRole(@PathVariable("id") int id, String eIds, ModelMap modelMap) {
        UpmsOrganizationRole uor=null;
        List<UpmsOrganizationRole> list=new ArrayList<UpmsOrganizationRole>();
        if(eIds != null) {
            String[] roleIds = eIds.split("::");
            for (String rid : roleIds) {
                uor = new UpmsOrganizationRole();
                uor.setOrganizationId(id);
                uor.setRoleId(Integer.parseInt(rid));
                list.add(uor);
            }
            upmsApiService.createOrgRole(id, list);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "角色列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/assignRole/listRole", method = RequestMethod.GET)
    @ResponseBody
    public Object roleList(@RequestParam("orgId") int id,
                            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                            @RequestParam(required = false, defaultValue = "r.role_id", value = "sort") String sort,
                            @RequestParam(required = false, defaultValue = "asc", value = "order") String order) {

        UpmsOrgRoleVo vo = new UpmsOrgRoleVo();
        vo.setOrgId(id);
        vo.setOffset(offset);
        vo.setLimit(limit);
        vo.setDeleteFlag(Boolean.FALSE);
        vo.setCompanyId(getCompanyId());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            vo.setOrderByClause(sort + " " + order);
        }

        List<UpmsOrgRoleVo> rows = upmsApiService.selectRolesByOrg(vo);
        long total = upmsApiService.getRoleCountByOrg(vo);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }
    /*************  assign role end **********************/

    @ApiOperation(value = "分配人员")
    @RequiresPermissions("upms:organization:create")
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public String assign(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("orgId", id);
        return "/manage/organization/assign.jsp";
    }

    @ApiOperation(value = "分配人员")
    @RequiresPermissions("upms:organization:create")
    @ResponseBody
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.POST)
    public Object assign(@PathVariable("id") int id, String eIds, ModelMap modelMap) {
        UpmsUserOrganization uo=null;
        List<UpmsUserOrganization> list=new ArrayList<UpmsUserOrganization>();
        if(eIds != null) {
            String[] userId = eIds.split("::");
            for (String uid : userId) {
                uo = new UpmsUserOrganization();
                uo.setOrganizationId(id);
                uo.setUserId(Integer.parseInt(uid));
                list.add(uo);
            }
            upmsApiService.createOrgUser(id, list);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "员工列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/assign/listStaff", method = RequestMethod.GET)
    @ResponseBody
    public Object staffList(@RequestParam("orgId") int id,
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        UpmsOrgUserVo vo = new UpmsOrgUserVo();
        vo.setOrgId(id);
        vo.setOffset(offset);
        vo.setLimit(limit);
        vo.setDeleteFlag(false);
        vo.setCompanyId(getCompanyId());
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            vo.setOrderByClause(sort + " " + order);
        }

        List<UpmsOrgUserVo> rows = upmsApiService.selectUsersByOrg(vo);
        handleUserCheckedFlag(id, rows);

        long total = upmsApiService.getUsersCountByOrg(vo);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    private void handleUserCheckedFlag(int orgId, List<UpmsOrgUserVo> rows) {
        UpmsUserOrganizationExample example = new UpmsUserOrganizationExample();
        example.createCriteria().andOrganizationIdEqualTo(orgId);
        List<UpmsUserOrganization> userOrgs = upmsUserOrganizationService.selectByExample(example);

        if (userOrgs != null && rows != null){
            for(UpmsOrgUserVo vo : rows){
                for (UpmsUserOrganization uo : userOrgs){
                    if (uo.getUserId().equals(vo.getUserId())){
                        vo.setChecked(true);
                        break;
                    }
                }
            }
        }
    }


    @ApiOperation(value = "新增组织")
    @RequiresPermissions("upms:organization:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(UpmsOrganization upmsOrganization) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        upmsOrganization.setDeleteFlag(Boolean.FALSE);

        Integer id =upmsOrganization.getOrganizationId();
        int count = 0;
        if(id == null) {
            long time = System.currentTimeMillis();
            upmsOrganization.setCtime(time);
            upmsOrganization.setCompanyId(getCompanyId());
            count = upmsOrganizationService.insertSelective(upmsOrganization);

        }else {
            upmsOrganization.setOrganizationId(id);
            count = upmsOrganizationService.updateByPrimaryKeySelective(upmsOrganization);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除组织")
    @RequiresPermissions("upms:organization:delete")
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = upmsOrganizationService.deleteByPrimaryKeys(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("upms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") int id, ModelMap modelMap) {
        Map map=new HashMap();
        UpmsOrganization organization = upmsOrganizationService.selectByPrimaryKey(id);
        map.put("org", organization);
        return map;
    }

    @ApiOperation(value = "修改组织")
    @RequiresPermissions("upms:organization:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, UpmsOrganization upmsOrganization) {
        ComplexResult result = FluentValidator.checkAll()
                .on(upmsOrganization.getName(), new LengthValidator(1, 20, "名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        upmsOrganization.setOrganizationId(id);
        int count = upmsOrganizationService.updateByPrimaryKeySelective(upmsOrganization);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    private int getCompanyId(){
        int cId=-1;
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            cId = company.getCompanyId();
        }
        return cId;
    }
}
