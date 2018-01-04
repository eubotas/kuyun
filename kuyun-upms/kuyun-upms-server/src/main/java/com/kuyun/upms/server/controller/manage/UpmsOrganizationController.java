package com.kuyun.upms.server.controller.manage;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.upms.common.constant.UpmsResult;
import com.kuyun.upms.common.constant.UpmsResultConstant;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserService;
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
    private UpmsApiService upmsApiService;

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
        if (StringUtils.isNotBlank(search)) {
            upmsOrganizationExample.or()
                    .andNameLike("%" + search + "%");
        }
        List<UpmsOrganization> rows = upmsOrganizationService.selectByExample(upmsOrganizationExample);
        long total = upmsOrganizationService.countByExample(upmsOrganizationExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "分配人员")
    @RequiresPermissions("upms:organization:create")
    @RequestMapping(value = "/assign/{id}", method = RequestMethod.GET)
    public String assign(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("orgId", id);
        return "/manage/organization/assign.jsp";
    }

    @ApiOperation(value = "分配人员")
    @RequiresPermissions("upms:organization:create")
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
            upmsUserOrganizationService.batchInsert(id, list);
//            UpmsUserOrganizationExample example= new UpmsUserOrganizationExample();
//            UpmsUserOrganizationExample.Criteria  criteria =example.createCriteria();
//            criteria.andOrganizationIdEqualTo(id);
//            upmsUserOrganizationService.deleteByExample(example);
//            upmsUserOrganizationService.batchInsert(list);
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "员工列表")
    @RequiresPermissions("upms:organization:read")
    @RequestMapping(value = "/assign/{id}/listStaff", method = RequestMethod.GET)
    @ResponseBody
    public Object staffList(@PathVariable("id") int id,
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        UpmsOrgUserVo vo = new UpmsOrgUserVo();
        vo.setOrgId(id);
        vo.setOffset(offset);
        vo.setLimit(limit);
        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            vo.setOrderByClause(sort + " " + order);
        }

        List<UpmsOrgUserVo> rows = upmsApiService.selectUsersByOrg(vo);
        handleCheckedFlag(id, rows);

        long total = upmsApiService.getUsersCountByOrg(id);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    private void handleCheckedFlag(int orgId, List<UpmsOrgUserVo> rows) {
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
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/organization/create.jsp";
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
        long time = System.currentTimeMillis();
        upmsOrganization.setCtime(time);
        int count = upmsOrganizationService.insertSelective(upmsOrganization);
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
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        UpmsOrganization organization = upmsOrganizationService.selectByPrimaryKey(id);
        modelMap.put("organization", organization);
        return "/manage/organization/update.jsp";
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

}
