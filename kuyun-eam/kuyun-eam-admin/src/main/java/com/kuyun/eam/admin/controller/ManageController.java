package com.kuyun.eam.admin.controller;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamHomeStatusSummaryVO;
import com.kuyun.eam.vo.EamHomeSummaryVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private EamApiService eamApiService;

    @Autowired
    private BaseEntityUtil baseEntityUtil;

	/**
	 * 后台首页
	 * @return
	 */
	@ApiOperation(value = "后台首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
        List<EamHomeSummaryVO> vos=eamApiService.getSummaryRecord(getCompanyId());
        modelMap.put("summary",vos.size()>0? vos.get(0): new EamHomeSummaryVO());

        List<EamHomeStatusSummaryVO> list=eamApiService.getStatusSummaryRecord(getCompanyId());
        JSONArray arr = new JSONArray();
        JSONObject json;
        for(EamHomeStatusSummaryVO vo: list){
            json = new JSONObject();
            if("0".equals(vo.getStatusName()))
                json.put("name", "离线");
            else
                json.put("name","在线");
            json.put("value",vo.getCount());
            arr.add(json);
        }
        modelMap.put("statusSummary", arr);
	    return "/manage/index.jsp";
	}

	@ApiOperation(value = "Dashboard")
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	@ResponseBody
	public Object  dashboard() {

		return "/manage/index.jsp";
	}

	private Integer getCompanyId(){
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            return company.getCompanyId();
        }
        return null;
    }
}