package com.kuyun.eam.admin.controller.manager;

import java.util.*;

import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamEquipmentVO;
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
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
		
//		if(category.startsWith("my"))
//            return "/manage/ticket/myTicket.jsp";
//		else
		    return "/manage/ticket/index.jsp";
	}

    @ApiOperation(value = "工单管理首页")
    @RequiresPermissions("eam:ticket:read")
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String summary(@RequestParam(required = false,defaultValue = "all", value = "category") String category,ModelMap modelMap) {
        modelMap.put("category", category);
        modelMap.put("ticketSummaryVo" ,eamApiService.summaryTicket(getCompanyId()));
        String categoryType="累计报修";
        if("init".equals(category))
            categoryType="未派工";
        else if("processing".equals(category))
            categoryType="维修中";
        else if("notResolved".equals(category))
            categoryType="未完成";
        else if("resolved".equals(category))
            categoryType="已完成";
        modelMap.put("categoryType", categoryType);
        return "/manage/ticket/summary.jsp";
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
		List<String> list;
		switch (TicketSearchCategory.getCategroy(category)) {
		case MY_OPEN:
			criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId()).andStatusNotEqualTo(TicketStatus.RESOLVED.getName()).andStatusNotEqualTo(TicketStatus.COMPLETE.getName());
			break;
		case MY_ALL:
			criteria.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
			break;
		case OPEN:
			criteria.andStatusNotEqualTo(TicketStatus.INIT.getName()).andStatusNotEqualTo(TicketStatus.RESOLVED.getName()).andStatusNotEqualTo(TicketStatus.COMPLETE.getName());
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
             criteria.andStatusNotEqualTo(TicketStatus.RESOLVED.getName()).andStatusNotEqualTo(TicketStatus.COMPLETE.getName());
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
		Map map =new HashMap();
        selectTicketUpdate(map);
		return map;
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
		int count = eamTicketService.insertSelective(ticket);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}

    @ApiOperation(value = "修改工单")
    @RequiresPermissions("eam:ticket:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("id") int id) {
	    Map map =new HashMap();
        EamTicket eamTicket = eamTicketService.selectByPrimaryKey(id);
        map.put("ticket", eamTicket);

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
        map.put("imageList", imageList);

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
        map.put("voiceList", voiceList);

        selectTicketUpdate(map);
        return map;
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
    @RequiresPermissions("eam:ticket:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable("id") int id) {
        EamTicket eamTicket = eamTicketService.selectByPrimaryKey(id);
        String status=eamTicket.getStatus();
        String nextOperateBtn="";
        if(TicketStatus.RESOLVED.getName().equals(status)) {
            nextOperateBtn = "<a class=\"waves-effect waves-button\" href=\"javascript:;\" onclick=\"toaction('TOASSESSMENT');\">评价</a>";
        }else if(TicketStatus.PROCESSING.getName().equals(status)){
            nextOperateBtn="<a class=\"waves-effect waves-button\" href=\"javascript:;\" onclick=\"toaction('COMPLETE');\">完成工单</a>";
        }else if(TicketStatus.TO_PROCESS.getName().equals(status)){
            nextOperateBtn="<a class=\"waves-effect waves-button\" href=\"javascript:;\" onclick=\"toaction('REJECT');\">拒绝工单</a>";
            nextOperateBtn +="<a class=\"waves-effect waves-button\" href=\"javascript:;\" onclick=\"toaction('TORECORD');\">处理工单</a>";
        }else if(TicketStatus.INIT.getName().equals(status)){
            nextOperateBtn="<a class=\"waves-effect waves-button\" href=\"javascript:;\" onclick=\"toaction('TOAPPOINT');\">委派工单</a>";
        }
        Map map =new HashMap();
        map.put("nextOperateBtn", nextOperateBtn);

        setTicketInfo(id, map);
        return map;
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
		int count= eamTicketService.updateByPrimaryKeySelective(ticket);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}

	@ApiOperation(value = "工单拒绝记录")
	@RequiresPermissions("eam:ticket:read")
	@RequestMapping(value = "/rejectRecord/{id}", method = RequestMethod.GET)
	public String rejectRecord(@PathVariable("id") int id, ModelMap modelMap) {
		List records=eamApiService.getTicketRejectRecord(id);
		modelMap.put("records", records);
		return "/manage/ticket/ticketRejectRecord.jsp";
	}

}