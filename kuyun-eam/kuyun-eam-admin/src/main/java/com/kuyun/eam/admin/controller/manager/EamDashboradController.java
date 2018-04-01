package com.kuyun.eam.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.eam.dao.model.EamProductLineExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamProductLineService;
import com.kuyun.eam.vo.EamCodeValueVo;
import com.kuyun.eam.vo.EamCountryValueVo;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 模板控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "Dashboard管理", description = "Dashboard管理")
@RequestMapping("/manage/dashboard")
public class EamDashboradController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(EamDashboradController.class);
	
	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private EamProductLineService eamProductLineService;

	@Autowired
	private UpmsCompanyService upmsCompanyService;


	@ApiOperation(value = "大屏首页")
	@RequiresPermissions("eam:productLine:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public Object index() {

		HashMap map = new HashMap();

		//1. 客户行业
		List<EamCodeValueVo> industry = eamApiService.summaryIndustry();
		map.put("industry", industry);

		//2. 采购排行榜
		HashMap buyOrder = eamApiService.summaryIndustryAndCompanyName();
		map.put("buyOrder", buyOrder);

		//3. 产线状态统计
		//TODO:

		//4. 产线数量（按州）
		List<EamCodeValueVo> state = eamApiService.summaryState();
		map.put("state", state);

		//5. 产线类别
		List<EamCodeValueVo> productLineType = eamApiService.summaryProductLineType();
		map.put("productLineType", productLineType);

		//6. 客户发展趋势
		List<EamCountryValueVo> country = eamApiService.summaryCountry();
		map.put("country", country);

		//7. 客户产线统计
		int taskNumber = getTaskNumber();
		map.put("taskNumber", taskNumber);

		//8. 接入产线数量
		int productLineCount = getProductLineCount();
		map.put("productLineCount", productLineCount);

		//9. 生产总瓶数
		//TODO:

		return map;
	}


	private int getProductLineCount(){
		EamProductLineExample example = new EamProductLineExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
		return eamProductLineService.countByExample(example);
	}

	private int getTaskNumber(){
		UpmsCompanyExample example = new UpmsCompanyExample();
		example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE).andParentIdIsNotNull();
		return upmsCompanyService.countByExample(example);
	}


}