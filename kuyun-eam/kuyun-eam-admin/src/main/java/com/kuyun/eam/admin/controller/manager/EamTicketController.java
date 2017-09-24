package com.kuyun.eam.admin.controller.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kuyun.upms.dao.model.UpmsUserCompany;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.common.base.Splitter;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.common.constant.EamResultConstant;
import com.kuyun.eam.common.constant.TicketSearchCategory;
import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.dao.model.EamTicketRecord;
import com.kuyun.eam.dao.model.EamTicketRecordExample;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.dao.model.EamTicketTypeExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamTicketRecordService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.rpc.api.EamTicketTypeService;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 设备模型控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单管理", description = "工单管理")
@RequestMapping("/manage/ticket")
public class EamTicketController extends BaseController {
	
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
//		List<EamTicketType> types = eamTicketTypeService.selectByExample(new EamTicketTypeExample());
//		Map<Integer,String> typeKeyValue = new HashMap<Integer, String>();
//		for (EamTicketType type: types) {
//			typeKeyValue.put(type.getId(), type.getName());
//		}
//		modelMap.put("typeKeyValue", typeKeyValue);
		modelMap.put("category", category);
		
		
		return "/manage/ticket/index.jsp";
	}

	@ApiOperation(value = "工单列表")
	@RequiresPermissions("eam:ticket:read")
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
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketExample.setOrderByClause(sort + " " + order);
		}

		switch (TicketSearchCategory.getCategroy(category)) {
		case MY_OPEN:
			criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId()).andStatusNotEqualTo(TicketStatus.NO_DEAL_WITH.getName()).andStatusNotEqualTo(TicketStatus.RESOLVED.getName());
			break;
		case MY_ALL:
			criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
			break;
		case OPEN:
			criteria.andStatusNotEqualTo(TicketStatus.NO_DEAL_WITH.getName()).andStatusNotEqualTo(TicketStatus.RESOLVED.getName());
			break;
		case ALL:
		default:
			break;
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}
		


		List<EamTicketVO> rows = eamApiService.selectTicket(eamTicketExample);

		long total = eamTicketService.countByExample(eamTicketExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", rows);
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增工单类型")
	@RequiresPermissions("eam:ticket:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(ModelMap modelMap ) {
		
		
		List<UpmsUser> users = upmsApiService.selectUsersByUserId(baseEntityUtil.getCurrentUser().getUserId());
	
		
		modelMap.put("users", users);
		List<EamTicketType> types = eamTicketTypeService.selectByExample(new EamTicketTypeExample());
		modelMap.put("ticketTypes", types);
		modelMap.put("uploadServer", fileUploaderService.getServerInfo());
		
		
		return "/manage/ticket/create.jsp";
	}

	@ApiOperation(value = "新增工单类型")
	@RequiresPermissions("eam:ticket:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(EamTicket ticket) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticket.getDescription(), new LengthValidator(1, 200, "工单描述"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(EamResultConstant.INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(ticket);
		ticket.setStatus(TicketStatus.OPEN.getName());
		int count = eamTicketService.insertSelective(ticket);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}
/*
	@ApiOperation(value = "删除工单类型")
	@RequiresPermissions("eam:ticketType:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketTypeService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}

*/

	@ApiOperation(value = "处理工单")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, ModelMap modelMap) {
		EamTicketExample ete = new EamTicketExample();
		ete.createCriteria().andTicketIdEqualTo(id);
		EamTicketVO eamTicket = eamApiService.selectTicket(ete).get(0);
		modelMap.put("ticket", eamTicket);
		
		//retrieve the image list
		List<String> imageList =  new ArrayList<String>();
		for ( String uuid : Splitter.on(',')
			    .trimResults()
			    .omitEmptyStrings()
			    .split(eamTicket.getImagePath1()) ) {
			imageList.add(fileUploaderService.getServerInfo().getEndpoint_show()+"/"+uuid);
		}
		
		modelMap.put("imageList", imageList);
		
		EamTicketRecordExample etre = new EamTicketRecordExample();
		etre.createCriteria().andTicketIdEqualTo(id);
		etre.setOrderByClause("eam_ticket_record_create_time desc");
		
		List<EamTicketRecord> records = eamTicketRecordService.selectByExample(etre);
		modelMap.put("records", records);
		return "/manage/ticket/update.jsp";
	}

	@ApiOperation(value = "处理工单")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("id") int id, EamTicketRecord ticketRecord) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketRecord.getComments(), new LengthValidator(1, 200, "工单处理注释"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(EamResultConstant.INVALID_LENGTH, result.getErrors());
		}
		ticketRecord.setTicketId(id);
		ticketRecord.setId(null);
		baseEntityUtil.addAddtionalValue(ticketRecord);
		int count = eamTicketRecordService.insertSelective(ticketRecord);
		
		// change the ticket status according to the record step value
		EamTicket ticket = eamTicketService.selectByPrimaryKey(id);
		ticket.setStatus(ticketRecord.getStep());
		eamTicketService.updateByPrimaryKeySelective(ticket);
		
		return new EamResult(EamResultConstant.SUCCESS, count);
	}



}