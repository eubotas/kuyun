package com.kuyun.marketing.admin.controller.manager;

import com.kuyun.common.base.BaseController;
import com.kuyun.marketing.common.constant.MktResult;
import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.dao.model.MktSmsUser;
import com.kuyun.marketing.dao.model.MktSmsUserExample;
import com.kuyun.marketing.rpc.api.MktApiService;
import com.kuyun.marketing.rpc.api.MktSmsService;
import com.kuyun.marketing.rpc.api.MktSmsUserService;
import com.kuyun.marketing.vo.MktSmsVo;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.vo.UpmsUserVo;
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

import static com.kuyun.marketing.common.constant.MktResultConstant.SUCCESS;

/**
 * 发送短信控制器
 * Created by kuyun on 2017/4/20.
 */
@Controller
@Api(value = "发送短信管理", description = "发送短信管理")
@RequestMapping("/manage/sms")
public class MktSmsController extends BaseController {

	private static Logger _log = LoggerFactory.getLogger(MktSmsController.class);

	@Autowired
	private MktSmsService mktSmsService;

	@Autowired
	private MktSmsUserService mktSmsUserService;


	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private MktApiService mktApiService;


	@ApiOperation(value = "发送短信首页")
	@RequiresPermissions("mkt:sms:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/manage/sms/index.jsp";
	}

	@ApiOperation(value = "发送短信列表")
	@RequiresPermissions("mkt:sms:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		MktSmsVo smsVo = new MktSmsVo();
		smsVo.setOffset(offset);
		smsVo.setLimit(limit);
		
		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			smsVo.setOrderByClause(sort + " " + order);
		}

		List<MktSmsVo> rows = mktApiService.getSmsList(smsVo);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", rows);
		result.put("total", rows.size());
		return result;
	}


	@RequiresPermissions("mkt:sms:create")
	@RequestMapping(value = "/create/{ids}", method = RequestMethod.POST)
	@ResponseBody
	public Object create(MktSms sms, @PathVariable("ids") String ids) {
		baseEntityUtil.addAddtionalValue(sms);
		int count = mktApiService.createSms(sms, ids);
		return new MktResult(SUCCESS, count);
	}

	@RequiresPermissions("mkt:sms:create")
	@RequestMapping(value = "/recreate/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object recreate(@PathVariable("id") int id) {
		int count = mktApiService.recreateSms(id);
		return new MktResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除发送短信")
	@RequiresPermissions("mkt:sms:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = mktSmsService.deleteByPrimaryKeys(ids);
		return new MktResult(SUCCESS, count);
	}

	@RequiresPermissions("mkt:sms:read")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String users(@PathVariable("id") String id, ModelMap modelMap) {
		_log.info("smsId:"+id);
		modelMap.put("smsId", id);
		return "/manage/sms/users.jsp";
	}

	@RequiresPermissions("mkt:sms:read")
	@RequestMapping(value = "/user/list/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object users(@PathVariable("id") String id) {
		_log.info("smsId:"+id);
		List<Integer> userIds = getUserIds(id);

		List<UpmsUserVo> users = mktApiService.getUsers(userIds);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", users);
		result.put("total", users.size());
		return result;
	}

	private List<Integer> getUserIds(String id) {
		MktSmsUserExample smsUserExample = new MktSmsUserExample();
		smsUserExample.createCriteria().andSmsIdEqualTo(Integer.valueOf(id));
		List<MktSmsUser> smsUsers = mktSmsUserService.selectByExample(smsUserExample);
		List<Integer> userIds = new ArrayList<Integer>(smsUsers.size());
		for(MktSmsUser smsUser : smsUsers){
			userIds.add(smsUser.getUserId());
		}
		return userIds;
	}

}