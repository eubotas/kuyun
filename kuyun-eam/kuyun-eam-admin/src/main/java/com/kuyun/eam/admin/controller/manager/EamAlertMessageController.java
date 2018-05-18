package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamAlertMessageExample;
import com.kuyun.eam.rpc.api.EamAlertMessageService;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.rpc.api.UpmsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 消息控制器
 * Created by kuyun on 2018/1/24.
 */
@Controller
@Api(value = "消息管理", description = "消息管理")
@RequestMapping("/manage/alertMsg")
public class EamAlertMessageController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(EamAlertMessageController.class);

    @Autowired
    private EamAlertMessageService eamAlertMessageService;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    public UpmsApiService upmsApiService;

    @Autowired
    public EamApiService eamApiService;

    @ApiOperation(value = "消息管理首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/alertmsg/index.jsp";
    }

    @ApiOperation(value = "消息列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        EamAlertMessageExample eamAlertMessageExample = new EamAlertMessageExample();
        eamAlertMessageExample.setOffset(offset);
        eamAlertMessageExample.setLimit(limit);
        EamAlertMessageExample.Criteria criteria = eamAlertMessageExample.createCriteria();
        criteria.andReadFlagEqualTo(Boolean.FALSE);
        criteria.andUserIdEqualTo(getCurrUserId());
        criteria.andCompanyIdEqualTo(getCompanyId());

        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            eamAlertMessageExample.setOrderByClause(sort + " " + order);
        }

        List<EamAlertMessage> rows = eamAlertMessageService.selectByExample(eamAlertMessageExample);
        long total = eamAlertMessageService.countByExample(eamAlertMessageExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "消息列表")
    @RequestMapping(value = "/history/list", method = RequestMethod.GET)
    @ResponseBody
    public Object historyList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, value = "startDate") Date startDate,
            @RequestParam(required = false, value = "endDate") Date endDate) {
        EamAlertMessageExample eamAlertMessageExample = new EamAlertMessageExample();
        eamAlertMessageExample.setOffset(offset);
        eamAlertMessageExample.setLimit(limit);
        EamAlertMessageExample.Criteria criteria = eamAlertMessageExample.createCriteria();
        criteria.andUserIdEqualTo(getCurrUserId());
        criteria.andCompanyIdEqualTo(getCompanyId());
        if (startDate != null && endDate != null){
            criteria.andCreateTimeBetween(startDate, endDate);
        }

        if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
            eamAlertMessageExample.setOrderByClause(sort + " " + order);
        }

        List<EamAlertMessage> rows = eamAlertMessageService.selectByExample(eamAlertMessageExample);
        long total = eamAlertMessageService.countByExample(eamAlertMessageExample);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }


    @ApiOperation(value = "修改消息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") String id) {
        EamAlertMessage msg=new EamAlertMessage();
        msg.setId(Integer.parseInt(id));
        msg.setReadFlag(true);
        baseEntityUtil.updateAddtionalValue(msg);

        return new EamResult(SUCCESS, eamAlertMessageService.updateByPrimaryKeySelective(msg));
    }


    public int getCurrUserId(){
        return baseEntityUtil.getCurrentUser().getUserId();
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