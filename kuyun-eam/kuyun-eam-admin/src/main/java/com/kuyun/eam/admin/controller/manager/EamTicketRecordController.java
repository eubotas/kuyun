package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.EamTicketRecord;
import com.kuyun.eam.dao.model.EamTicketRecordExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamTicketRecordService;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 工单记录控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单记录管理", description = "工单记录管理")
@RequestMapping("/manage/ticket/{ticketId}/record")
public class EamTicketRecordController extends EamTicketBaseController {

	private static Logger _log = LoggerFactory.getLogger(EamTicketRecordController.class);

	@Autowired
	private EamTicketRecordService eamTicketRecordService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamApiService eamApiService;

	@ApiOperation(value = "工单记录管理首页")
	@RequiresPermissions("eam:ticketRecord:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("ticketId") int ticketId, ModelMap modelMap) {
		modelMap.addAttribute("ticketId", ticketId);
		return "/manage/ticket/record/index.jsp";
	}

	@ApiOperation(value = "工单记录列表")
	@RequiresPermissions("eam:ticketRecord:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@PathVariable("ticketId") int ticketId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketRecordExample eamTicketRecordExample = new EamTicketRecordExample();
		eamTicketRecordExample.setOffset(offset);
		eamTicketRecordExample.setLimit(limit);
		EamTicketRecordExample.Criteria criteria = eamTicketRecordExample.createCriteria();
		criteria.andTicketIdEqualTo(ticketId);
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketRecordExample.setOrderByClause(sort + " " + order);
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}


		List<EamTicketRecord> rows = eamTicketRecordService.selectByExample(eamTicketRecordExample);
		long total = eamTicketRecordService.countByExample(eamTicketRecordExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增工单记录")
	@RequiresPermissions("eam:TicketRecord:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@PathVariable("ticketId") int ticketId, ModelMap modelMap) {
        modelMap.addAttribute("ticketId", ticketId);
        setTicketInfo(  ticketId,  modelMap);
		return "/manage/ticket/record/create.jsp";
	}

	@ApiOperation(value = "新增工单记录")
	@RequiresPermissions("eam:TicketRecord:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamTicketRecord ticketRecord) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketRecord.getComments(), new LengthValidator(1, 20, "工单记录不能为空"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(ticketRecord);
		int count = eamApiService.addTicketRecord(ticketRecord);
		return new EamResult(SUCCESS, count);
	}

	@ApiOperation(value = "删除工单记录")
	@RequiresPermissions("eam:ticketRecord:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketRecordService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改工单记录")
	@RequiresPermissions("eam:ticketRecord:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") int id, ModelMap modelMap) {
		EamTicketRecord eamTicketRecord = eamTicketRecordService.selectByPrimaryKey(id);
        Map map=new HashMap();
        setTicketInfo(eamTicketRecord.getTicketId(),  modelMap);
        map.put("ticketRecord", eamTicketRecord);
        return map;
	}

	@ApiOperation(value = "修改工单记录")
	@RequiresPermissions("eam:ticketRecord:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamTicketRecord ticketRecord) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketRecord.getComments(), new LengthValidator(1, 20, "工单记录不能为空"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		ticketRecord.setId(id);
		baseEntityUtil.updateAddtionalValue(ticketRecord);
		int count = eamTicketRecordService.updateByPrimaryKeySelective(ticketRecord);
		return new EamResult(SUCCESS, count);
	}



}