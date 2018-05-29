package com.kuyun.eam.admin.controller;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistory;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamGrmVariableDataHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 后台controller
 * Created by kuyun on 2017/01/12.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "后台控制器", description = "后台管理")
public class ManageController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(ManageController.class);



	/**
	 * 后台首页
	 * @return
	 */
	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/index.jsp";
	}

	@ApiOperation(value = "Dashboard")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	@ResponseBody
	public Object  dashboard() {

		return "/manage/index.jsp";
	}

    @Autowired
    static EamApiService eamApiService;
    @Autowired
    static EamGrmVariableDataHistoryService eamGrmVariableDataHistoryService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value="date", required = true) String date) {
        eamApiService.statisticJob(date);
        //eamApiService.processShiftData(getHistoryDayData("2018-5-9"));
        return "/manage/index.jsp";
    }


    private static List<EamGrmVariableDataHistory> getHistoryDayData(String date){
        List<EamGrmVariableDataHistory>  dataHistoryList = new ArrayList<EamGrmVariableDataHistory>();
        try {
            Date startDate = DateUtils.parseDate(date + " 00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"});
            Date endDate = DateUtils.parseDate(date + " 23:59:59", new String[]{"yyyy-MM-dd HH:mm:ss"});

            EamGrmVariableDataHistoryExample example = new EamGrmVariableDataHistoryExample();
            EamGrmVariableDataHistoryExample.Criteria criteria = example.createCriteria();
            criteria.andDeleteFlagEqualTo(Boolean.FALSE)
                    .andUpdateTimeBetween(startDate, endDate);
            example.setOrderByClause("update_time asc");

            if (eamGrmVariableDataHistoryService != null){
                dataHistoryList = eamGrmVariableDataHistoryService.selectByExample(example);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataHistoryList;
    }
}