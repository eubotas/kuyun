package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.google.common.base.Splitter;
import com.kuyun.common.constant.RoleEnum;
import com.kuyun.common.util.NumberUtil;
import com.kuyun.common.validator.LengthValidator;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.admin.model.RepairKnowledge;
import com.kuyun.eam.admin.repository.RepairKnowledgeRepository;
import com.kuyun.eam.admin.util.ActionEnum;
import com.kuyun.eam.admin.util.BaseModelUtil;
import com.kuyun.eam.admin.util.TagUtil;
import com.kuyun.eam.common.constant.*;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import com.kuyun.eam.rpc.api.EamTicketRecordService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.util.TicketUtil;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 工单管理控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单管理", description = "工单管理")
@RequestMapping("/manage/ticket")
public class EamTicketController extends EamTicketBaseController {
	
	private static Logger _log = LoggerFactory.getLogger(EamTicketController.class);
	
	@Autowired
	private EamTicketService eamTicketService;

	@Autowired
	private EamEquipmentService eamEquipmentService;

	@Autowired
	private EamApiService eamApiService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;
	
	@Autowired
	private EamTicketRecordService eamTicketRecordService;

	@Autowired
	private com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

	@Resource
	private RepairKnowledgeRepository repairKnowledgeRepository;

	@Autowired
	private TagUtil tagUtil;

	@Autowired
	private BaseModelUtil baseModelUtil;


	@ApiOperation(value = "工单管理首页")
	@RequiresPermissions("eam:ticket:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam(required = false, defaultValue = "myAll", value = "category") String category , ModelMap modelMap) {
		modelMap.put("category", category);
		
		if(category.startsWith("my")){
			return "/manage/ticket/myTicket.jsp";
		}else{
			return "/manage/ticket/index.jsp";
		}

	}

    @ApiOperation(value = "工单管理首页")
    @RequiresPermissions("eam:ticket:read")
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public String summary(@RequestParam(required = false,defaultValue = "all", value = "category") String category,ModelMap modelMap) {
        modelMap.put("category", category);
        modelMap.put("ticketSummaryVo" ,eamApiService.summaryTicket(getCompanyId()));
        String categoryType="累计报修";
        if("init".equals(category)){
			categoryType="未派工";
		}
        else if("processing".equals(category)){
			categoryType="维修中";
		}
        else if("notResolved".equals(category)){
			categoryType="未完成";
		}
        else if("resolved".equals(category)){
			categoryType="已完成";
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
			@RequestParam(required = false) String ticketTypeId,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketExample eamTicketExample = new EamTicketExample();
		eamTicketExample.setOffset(offset);
		eamTicketExample.setLimit(limit);
		EamTicketExample.Criteria criteria1 = eamTicketExample.createCriteria();
		EamTicketExample.Criteria criteria2 = eamTicketExample.createCriteria();
		criteria1.andDeleteFlagEqualTo(Boolean.FALSE);
		criteria2.andDeleteFlagEqualTo(Boolean.FALSE);

		if (StringUtils.isNotEmpty(ticketTypeId)){
			criteria1.andTicketTypeIdEqualTo(NumberUtil.toInteger(ticketTypeId));
			criteria2.andTicketTypeIdEqualTo(NumberUtil.toInteger(ticketTypeId));
		}


		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketExample.setOrderByClause(sort + " " + order);
		}else{
			eamTicketExample.setOrderByClause("eam_ticket.ticket_id desc");
		}


		Subject subject = SecurityUtils.getSubject();

		switch (TicketSearchCategory.getCategroy(category)) {
		case MY_OPEN:
			if(subject.hasRole(RoleEnum.TICKETCREATE.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETCREATE.getName())) {
				//工单提报人 有权限
				criteria1.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
						.andStatusNotEqualTo(TicketStatus.CLOSED.getName())
						.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
						.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

				criteria2.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
						.andStatusNotEqualTo(TicketStatus.CLOSED.getName())
						.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
						.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

			}else if(subject.hasRole(RoleEnum.TICKETREPAIR.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETREPAIR.getName())) {
				//工单维修人 有权限
				criteria1.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
						.andStatusNotEqualTo(TicketStatus.CLOSED.getName())
						.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
						.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

				criteria2.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId())
						.andStatusNotEqualTo(TicketStatus.CLOSED.getName())
						.andStatusNotEqualTo(TicketStatus.RESOLVED.getName())
						.andStatusNotEqualTo(TicketStatus.COMPLETE.getName());

			}
			break;
		case MY_RESOLVED:
			if(subject.hasRole(RoleEnum.TICKETCREATE.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETCREATE.getName())) {
				//工单提报人 有权限
				criteria1.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				criteria2.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				List<String> list=new ArrayList();
				list.add(TicketStatus.CLOSED.getName());
				list.add(TicketStatus.RESOLVED.getName());
				list.add(TicketStatus.COMPLETE.getName());
				criteria1.andStatusIn(list);
				criteria2.andStatusIn(list);

			}else if(subject.hasRole(RoleEnum.TICKETREPAIR.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETREPAIR.getName())) {
				//工单维修人 有权限
				criteria1.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				criteria2.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				List<String> list=new ArrayList();
				list.add(TicketStatus.CLOSED.getName());
				list.add(TicketStatus.RESOLVED.getName());
				list.add(TicketStatus.COMPLETE.getName());
				criteria1.andStatusIn(list);
				criteria2.andStatusIn(list);
			}
			break;
		case MY_ALL:
			if(subject.hasRole(RoleEnum.TICKETCREATE.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETCREATE.getName())) {
				//工单提报人 有权限
				criteria1.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				criteria2.andCreateUserIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());

			}else if(subject.hasRole(RoleEnum.TICKETREPAIR.getName()) || subject.hasRole(RoleEnum.CUSTOMER_TICKETREPAIR.getName())) {
				//工单维修人 有权限
				criteria1.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
				criteria2.andExecutorIdEqualTo(baseEntityUtil.getCurrentUser().getUserId());
			}
			break;
		case OPEN:
			List<String> list=new ArrayList();
			list.add(TicketStatus.INIT.getName());
			list.add(TicketStatus.CLOSED.getName());
			list.add(TicketStatus.RESOLVED.getName());
			list.add(TicketStatus.COMPLETE.getName());
			criteria1.andStatusNotIn(list);
			criteria2.andStatusNotIn(list);
			break;
		case INIT:
			criteria1.andStatusEqualTo(TicketStatus.INIT.getName());
			criteria2.andStatusEqualTo(TicketStatus.INIT.getName());
			break;
		case PROCESSING:
			list=new ArrayList();
            list.add(TicketStatus.TO_PROCESS.getName());
            list.add(TicketStatus.PROCESSING.getName());
			criteria1.andStatusIn(list);
			criteria2.andStatusIn(list);
             break;
        case NOTRESOLVED:
			list=new ArrayList();
			list.add(TicketStatus.CLOSED.getName());
			list.add(TicketStatus.RESOLVED.getName());
			list.add(TicketStatus.COMPLETE.getName());
			criteria1.andStatusNotIn(list);
			criteria2.andStatusNotIn(list);
             break;
        case RESOLVED:
            list=new ArrayList();
            list.add(TicketStatus.CLOSED.getName());
            list.add(TicketStatus.RESOLVED.getName());
            list.add(TicketStatus.COMPLETE.getName());
            criteria1.andStatusIn(list);
            criteria2.andStatusIn(list);
             break;
         case ALL:
		default:
			break;
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
		if (company != null){
			List companys=new ArrayList();
			companys.add(company.getCompanyId());
			List<Integer> childCompanyIds =baseEntityUtil.getChildCompanys(company.getCompanyId());
			if(childCompanyIds != null) {
				companys.addAll(childCompanyIds);
				criteria1.andCompanyIdIn(companys);
				criteria1.andTicketTypeIdEqualTo(TicketType.REPAIR.getCode());

				criteria2.andCompanyIdEqualTo(company.getCompanyId());

				eamTicketExample.or(criteria2);
			}else{
				criteria1.andCompanyIdEqualTo(company.getCompanyId());

			}

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
	public String create(ModelMap modelMap ) {
        selectTicketUpdate(modelMap);
		return "/manage/ticket/create.jsp";
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
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
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
        return "/manage/ticket/update.jsp";
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
	@ResponseBody
    public Object detail(@PathVariable("id") int id, ModelMap modelMap) {
		HashMap<String, Object> map = new HashMap();
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
		EamTicket ticket = eamTicketService.selectByPrimaryKey(id);
		//维修工单
		if (TicketType.REPAIR.match(ticket.getTicketTypeId())){
			ticket.setStatus(TicketStatus.RESOLVED.getName());
		}else {
			ticket.setStatus(TicketStatus.CLOSED.getName());
		}
		ticket.setUpdateTime(new Date());
		ticket.setUpdateUserId(getCurrUserId());
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
	@RequiresRoles("ticketAppoint")
	@RequestMapping(value = "/operator/list", method = RequestMethod.GET)
	@ResponseBody
	public Object operatorList() {
		HashMap<String, List<UpmsOrgUserVo>> map = new HashMap();
		map.put("users", getOperatorUsers());
		return map;
	}

	@ApiOperation(value = "生成维修知识")
	@RequiresPermissions("eam:ticket:update")
	@RequestMapping(value = "/create/knowledge/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object createKnowledge(@PathVariable("id") int id) {
		int count = createKnowledgeImpl(id);
		return new EamResult(EamResultConstant.SUCCESS, count);
	}

	private int createKnowledgeImpl(int id) {
		int result =  0;
		EamTicket ticket = eamTicketService.selectByPrimaryKey(id);
		if (ticket != null){
			RepairKnowledge repairKnowledge = new RepairKnowledge();
			repairKnowledge.setDescription(ticket.getDescription());
			repairKnowledge.setMethod(buildMethods(id));
			repairKnowledge.setTag(getEquipmentName(ticket));

			baseModelUtil.addAddtionalValue(repairKnowledge);

			tagUtil.handleTag(ActionEnum.CREATE.getName(), null, repairKnowledge.getTag());

			repairKnowledgeRepository.save(repairKnowledge);
		}
		return result;
	}

	private String getEquipmentName(EamTicket ticket) {
		String result = null;
		EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(ticket.getEquipmentId());
		if (equipment != null){
			result = equipment.getName();
		}
		return result;
	}

	private String buildMethods(int id){
		StringBuilder method = new StringBuilder();
		List<EamTicketRecord> records = getTicketRecords(id);
		for(EamTicketRecord record : records){
			method.append(record.getComments()).append("\r\n");
		}
		return method.toString();
	}
	private List<EamTicketRecord> getTicketRecords(int id){
		EamTicketRecordExample example = new EamTicketRecordExample();
		example.createCriteria().andTicketIdEqualTo(id).andDeleteFlagEqualTo(Boolean.FALSE);
		example.setOrderByClause("eam_ticket_record.create_time asc");
		return eamTicketRecordService.selectByExample(example);
	}

}