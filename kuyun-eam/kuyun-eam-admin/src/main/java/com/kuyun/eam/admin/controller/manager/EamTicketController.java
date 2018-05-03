package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.common.base.Splitter;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.*;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.TicketUtil;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.kuyun.eam.common.constant.EamConstant.TICKET_APPOINT;
import static com.kuyun.eam.common.constant.EamConstant.TICKET_CREATE;
import static com.kuyun.eam.common.constant.EamConstant.TICKET_REPAIR;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 工单管理控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单管理", description = "工单管理")
@RequestMapping("/manage/ticket")
public class EamTicketController extends EamTicketBaseController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
	}

	private static Logger _log = LoggerFactory.getLogger(EamTicketController.class);

	@Autowired
	private EamTicketService eamTicketService;

	@Autowired
	private EamTicketTypeService eamTicketTypeService;

	@Autowired
	private EamEquipmentCategoryService eamEquipmentCategoryService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private UpmsApiService upmsApiService;

	@Autowired
	private EamTicketRecordService eamTicketRecordService;

	@Autowired
	private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

	@ApiOperation(value = "工单管理首页")
	@RequiresPermissions("eam:ticket:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam(required = false, defaultValue = "myAll", value = "category") String category , ModelMap modelMap) {
		modelMap.put("category", category);
		modelMap.put("uploadServer", fileUploaderService.getServerInfo());

		Subject subject = SecurityUtils.getSubject();
		String categoryType = TicketSearchCategory.MY_OPEN.getName();

		if(subject.hasRole(TICKET_REPAIR) || subject.hasRole(TICKET_CREATE)) {
			categoryType = TicketSearchCategory.MY_OPEN.getName();
		} else if(subject.hasRole(TICKET_APPOINT)) {
			categoryType = TicketSearchCategory.INIT.getName();
		}

		if(TicketSearchCategory.MY_OPEN.match(category)){
			categoryType = TicketSearchCategory.MY_OPEN.getName();
		}else if(TicketSearchCategory.MY_RESOLVED.match(category)){
			categoryType = TicketSearchCategory.MY_RESOLVED.getName();
		}else if(TicketSearchCategory.MY_ALL.match(category)){
			categoryType = TicketSearchCategory.MY_ALL.getName();
		}else if(TicketSearchCategory.INIT.match(category)){
			categoryType = TicketSearchCategory.INIT.getName();
		}else if(TicketSearchCategory.RESOLVED.match(category)){
			categoryType = TicketSearchCategory.RESOLVED.getName();
		}else if(TicketSearchCategory.ALL.match(category)){
			categoryType = TicketSearchCategory.ALL.getName();
		}

		modelMap.put("categoryType", categoryType);



		return "/manage/ticket/index.jsp";
	}

	@ApiOperation(value = "工单管理首页")
	@RequiresPermissions("eam:ticket:read")
	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public String summary(@RequestParam(required = false,defaultValue = "all", value = "category") String category,ModelMap modelMap) {
		modelMap.put("category", category);
		modelMap.put("ticketSummaryVo" ,eamApiService.summaryTicket(getCompanyId()));
		String categoryType="累计报修";

		if(TicketSearchCategory.INIT.match(category)){
			categoryType = TicketSearchCategory.INIT.getName();
		}else if(TicketSearchCategory.PROCESSING.match(category)){
			categoryType = TicketSearchCategory.PROCESSING.getName();
		}else if(TicketSearchCategory.NOTRESOLVED.match(category)){
			categoryType = TicketSearchCategory.NOTRESOLVED.getName();
		}else if(TicketSearchCategory.RESOLVED.match(category)){
			categoryType = TicketSearchCategory.RESOLVED.getName();
		}

		modelMap.put("categoryType", categoryType);
		return "/manage/ticket/summary.jsp";
	}

	@ApiOperation(value = "工单列表")
	@RequiresPermissions(value = {"eam:ticket:read", "eam:myOpenTicket:read","eam:myAllTicket:read","eam:initTicket:read"}, logical = Logical.OR)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, defaultValue = "myAll", value = "category") String category,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketExample eamTicketExample = new EamTicketExample();
		eamTicketExample.setOffset(offset);
		eamTicketExample.setLimit(limit);
		EamTicketExample.Criteria criteria = eamTicketExample.createCriteria();
		EamTicketExample.Criteria criteria2 = eamTicketExample.createCriteria();
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);
		criteria2.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketExample.setOrderByClause(sort + " " + order);
		}else{
			eamTicketExample.setOrderByClause("ABS(ticket_number) desc");
		}

		Subject subject = SecurityUtils.getSubject();

		switch (TicketSearchCategory.getCategroy(category)) {
			case MY_OPEN:
				if(subject.hasRole(TICKET_CREATE)) {
					//工单提报人 有权限
					criteria.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
							.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

				}else if(subject.hasRole(TICKET_REPAIR)) {
					//工单维修人 有权限
					criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
							.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

				}
				break;
			case MY_RESOLVED:
				if(subject.hasRole(TICKET_CREATE)) {
					//工单提报人 有权限
					criteria.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusEqualTo(TicketStatus.RESOLVED.getName());
					criteria2.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusEqualTo(TicketStatus.COMPLETE.getName());
					eamTicketExample.or(criteria2);

				}else if(subject.hasRole(TICKET_REPAIR)) {
					//工单维修人 有权限
					criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusEqualTo(TicketStatus.RESOLVED.getName());
					criteria2.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
							.andStatusEqualTo(TicketStatus.COMPLETE.getName());
					eamTicketExample.or(criteria2);
				}
				break;
			case MY_ALL:
				if(subject.hasRole(TICKET_CREATE)) {
					//工单提报人 有权限
					criteria.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());

				}else if(subject.hasRole(TICKET_REPAIR)) {
					//工单维修人 有权限
					criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				}
				break;
			case OPEN:
				List<String> list=new ArrayList();
				list.add(TicketStatus.INIT.getName());
				list.add(TicketStatus.RESOLVED.getName());
				list.add(TicketStatus.COMPLETE.getName());
				criteria.andStatusNotIn(list);
				break;
			case INIT:
				criteria.andStatusEqualTo(TicketStatus.INIT.getName());
				break;
			case PROCESSING:
				list=new ArrayList();
				list.add(TicketStatus.TO_PROCESS.getName());
				list.add(TicketStatus.PROCESSING.getName());
				criteria.andStatusIn(list);
				break;
			case NOTRESOLVED:
				list=new ArrayList();
				list.add(TicketStatus.RESOLVED.getName());
				list.add(TicketStatus.COMPLETE.getName());
				criteria.andStatusNotIn(list);
				break;
			case RESOLVED:
				list=new ArrayList();
				list.add(TicketStatus.RESOLVED.getName());
				list.add(TicketStatus.COMPLETE.getName());
				criteria.andStatusIn(list);
				break;
			case ALL:
			default:
				break;
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
			criteria2.andCompanyIdEqualTo(company.getCompanyId());
		}

		List<EamTicketVO> rows = eamApiService.selectTicket(eamTicketExample);

		long total = eamTicketService.countByExample(eamTicketExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增工单")
	@RequiresPermissions("eam:ticket:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public Object create(ModelMap modelMap ) {
		selectTicketUpdate(modelMap);
		return new EamResult(EamResultConstant.SUCCESS, modelMap);
	}

	@ApiOperation(value = "新增工单")
	@RequiresPermissions("eam:ticket:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamTicket ticket) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticket.getDescription(), new LengthValidator(1, 2000, "工单问题描述"))
				.on(ticket.getTicketTypeId(), new NotNullValidator("工单类型"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(EamResultConstant.INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(ticket);
		ticket.setStatus(TicketStatus.INIT.getName());
		ticket.setTicketNumber(TicketUtil.generatorTicketNumber());
		int count = eamTicketService.insertSelective(ticket);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "修改工单")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object update(@PathVariable("id") int id, ModelMap modelMap) {
		EamTicket eamTicket = eamTicketService.selectByPrimaryKey(id);
		modelMap.put("ticket", eamTicket);

		//retrieve the image list
		List<String> imageList =  new ArrayList<String>();
		if(eamTicket.getImagePath() != null) {
			for (String uuid : Splitter.on(',')
					.trimResults()
					.omitEmptyStrings()
					.split(eamTicket.getImagePath())) {
				imageList.add(fileUploaderService.getServerInfo().getEndpoint_show() + "/" + uuid);
			}
		}
		modelMap.put("imageList", imageList);

		//retrieve the voice list
		List<String> voiceList =  new ArrayList<String>();
		if(eamTicket.getVoicePath() != null) {
			for (String uuid : Splitter.on(',')
					.trimResults()
					.omitEmptyStrings()
					.split(eamTicket.getVoicePath())) {
				voiceList.add(fileUploaderService.getServerInfo().getEndpoint_show() + "/" + uuid);
			}
		}
		modelMap.put("voiceList", voiceList);

		selectTicketUpdate(modelMap);
		return new EamResult(EamResultConstant.SUCCESS, modelMap);
	}

	@ApiOperation(value = "修改工单")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/update/{ticketId}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(EamTicket eamTicket) {
		ComplexResult result = FluentValidator.checkAll()
				.on(eamTicket.getDescription(), new LengthValidator(1, 2000, "工单问题描述"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(EamResultConstant.INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(eamTicket);
		int count = eamTicketService.updateByPrimaryKeySelective(eamTicket);

		return new EamResult(EamResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "工单详细")
	@RequiresPermissions(value = {"eam:ticket:read", "eam:myOpenTicket:read","eam:myAllTicket:read","eam:initTicket:read"}, logical = Logical.OR)
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") int id, ModelMap modelMap) {
		setTicketInfo(id, modelMap);
		return "/manage/ticket/detail.jsp";
	}

	@ApiOperation(value = "删除工单")
	@RequiresPermissions("eam:ticket:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}


	@ApiOperation(value = "完成工单")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/complete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object complete(@PathVariable("id") int id) {
		EamTicket ticket=new EamTicket();
		ticket.setTicketId(id);
		ticket.setStatus(TicketStatus.RESOLVED.getName());
		ticket.setUpdateTime(new Date());
		int count= eamTicketService.updateByPrimaryKeySelective(ticket);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "工单拒绝记录")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/rejectRecord/{id}", method = RequestMethod.GET)
	public String rejectRecord(@PathVariable("id") int id, ModelMap modelMap) {
		List records=eamApiService.getTicketRejectRecord(id);
		modelMap.put("records", records);
		return "/manage/ticket/ticketRejectRecord.jsp";
	}

	@ApiOperation(value = "维修人员列表")
	@RequiresPermissions("eam:ticket:create")
	@RequestMapping(value = "/operator/list", method = RequestMethod.GET)
	@ResponseBody
	public Object operatorList() {
		HashMap<String, List<UpmsOrgUserVo>> map = new HashMap();
		map.put("users", getOperatorUsers());
		return map;
	}

}