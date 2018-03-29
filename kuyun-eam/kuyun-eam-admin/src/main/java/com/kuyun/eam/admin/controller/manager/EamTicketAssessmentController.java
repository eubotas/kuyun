package com.kuyun.eam.admin.controller.manager;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.validator.NotNullValidator;
import com.kuyun.eam.common.constant.EamResult;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamTicketAssessmentTagVO;
import com.kuyun.eam.vo.EamTicketAssessmentVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.JspUtil;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kuyun.eam.common.constant.EamResultConstant.INVALID_LENGTH;
import static com.kuyun.eam.common.constant.EamResultConstant.SUCCESS;

/**
 * 设备模型控制器
 * Created by kuyun on 2017/4/9.
 */
@Controller
@Api(value = "工单评价管理", description = "工单评价管理")
@RequestMapping("/manage/ticket/{ticketId}/assessment")
public class EamTicketAssessmentController extends EamTicketBaseController {

	private static Logger _log = LoggerFactory.getLogger(EamTicketAssessmentController.class);
	
	@Autowired
	private EamTicketTagService eamTicketTagService;

	@Autowired
	private EamTicketAssessmentService eamTicketAssessmentService;

    @Autowired
    private EamTicketAssessmentTagService eamTicketAssessmentTagService;
    @Autowired
    private EamTicketService eamTicketService;

	@Autowired
	private BaseEntityUtil baseEntityUtil;

	@Autowired
	private EamApiService eamApiService;

    @ApiOperation(value = "评价工单")
    @RequiresPermissions("eam:TicketAssessment:create")
    @RequestMapping(value = "/assess")
    @ResponseBody
    public Object assess(@PathVariable("ticketId") int ticketId) {
        Map map = new HashMap();
        EamTicketAssessment eamTicketAssessment =getTicketAssessment(ticketId);
        if(eamTicketAssessment != null) {  //update
            map.put("ticketAssessment", eamTicketAssessment);

            EamTicketAssessmentTagExample eamTicketAssessmentTagExample = new EamTicketAssessmentTagExample();
            EamTicketAssessmentTagExample.Criteria criteria = eamTicketAssessmentTagExample.createCriteria();
            criteria.andTicketIdEqualTo(ticketId);
            criteria.andDeleteFlagEqualTo(Boolean.FALSE);
            List<EamTicketAssessmentTag> list= eamTicketAssessmentTagService.selectByExample(eamTicketAssessmentTagExample);
            map.put("ticketAssessmentTags", JspUtil.getList(list,"tagId"));
        }
        return map;
    }

	@ApiOperation(value = "工单评价管理首页")
	@RequiresPermissions("eam:ticketAssessment:read")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@PathVariable("ticketId") int ticketId, ModelMap modelMap) {
		modelMap.addAttribute("ticketId", ticketId);
        modelMap.put("ticketTags", getTicketTag());
		return "/manage/ticket/assessment/index.jsp";
	}

	@ApiOperation(value = "工单评价列表")
	@RequiresPermissions("eam:ticketAssessment:read")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object list(@PathVariable("ticketId") int ticketId,
			@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
			@RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
			@RequestParam(required = false, value = "sort") String sort,
			@RequestParam(required = false, value = "order") String order) {
		EamTicketAssessmentExample eamTicketAssessmentExample = new EamTicketAssessmentExample();
		eamTicketAssessmentExample.setOffset(offset);
		eamTicketAssessmentExample.setLimit(limit);
		EamTicketAssessmentExample.Criteria criteria = eamTicketAssessmentExample.createCriteria();
		criteria.andTicketIdEqualTo(ticketId);
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);

		if (!StringUtils.isBlank(sort) && !StringUtils.isBlank(order)) {
			eamTicketAssessmentExample.setOrderByClause(sort + " " + order);
		}

		UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

		if (company != null){
			criteria.andCompanyIdEqualTo(company.getCompanyId());
		}

		List<EamTicketAssessment> rows = eamTicketAssessmentService.selectByExample(eamTicketAssessmentExample);
		long total = eamTicketAssessmentService.countByExample(eamTicketAssessmentExample);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", getAssessmentTicket(rows));
		result.put("total", total);
		return result;
	}

	@ApiOperation(value = "新增工单评价")
	@RequiresPermissions("eam:TicketAssessment:create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@PathVariable("ticketId") int ticketId, ModelMap modelMap) {
        setTicketInfo(  ticketId,  modelMap);
        EamTicketAssessment eamTicketAssessment =getTicketAssessment(ticketId);
        if(eamTicketAssessment != null) {
            modelMap.put("ticketAssessment", eamTicketAssessment);
            modelMap.addAttribute("ticketTags", getTicketTag());

            EamTicketAssessmentTagExample eamTicketAssessmentTagExample = new EamTicketAssessmentTagExample();
            EamTicketAssessmentTagExample.Criteria criteria = eamTicketAssessmentTagExample.createCriteria();
            criteria.andTicketIdEqualTo(ticketId);
            criteria.andDeleteFlagEqualTo(Boolean.FALSE);
            List<EamTicketAssessmentTag> list= eamTicketAssessmentTagService.selectByExample(eamTicketAssessmentTagExample);
            modelMap.put("ticketAssessmentTags", list);

            return "/manage/ticket/assessment/update.jsp";
        }else {
            modelMap.addAttribute("ticketId", ticketId);
            modelMap.addAttribute("ticketTags", getTicketTag());
            return "/manage/ticket/assessment/create.jsp";
        }
	}

	@ApiOperation(value = "新增工单评价")
	@RequiresPermissions("eam:TicketAssessment:create")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Object create(@PathVariable("ticketId") int ticketId, EamTicketAssessment ticketAssessment, int[] ticketTag) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketAssessment.getAssessmentLevel(), new NotNullValidator( "工单评价星级不能为空"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		baseEntityUtil.addAddtionalValue(ticketAssessment);
        EamTicket eamTicket = eamTicketService.selectByPrimaryKey(ticketId);
        ticketAssessment.setAssessmentUserId(eamTicket.getExecutorId());
        eamApiService.completeTicket(ticketAssessment, ticketTag);
		return new EamResult(SUCCESS, 1);
	}

	@ApiOperation(value = "删除工单评价")
	@RequiresPermissions("eam:ticketAssessment:delete")
	@RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
	@ResponseBody
	public Object delete(@PathVariable("ids") String ids) {
		int count = eamTicketAssessmentService.deleteByPrimaryKeys(ids);
		return new EamResult(SUCCESS, count);
	}



	@ApiOperation(value = "修改工单评价")
	@RequiresPermissions("eam:ticketAssessment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object update(@PathVariable("ticketId") int ticketId, @PathVariable("id") int id) {
        Map map =new HashMap();
		EamTicketAssessment eamTicketAssessment = eamTicketAssessmentService.selectByPrimaryKey(id);
        map.put("ticketAssessment", eamTicketAssessment);

        EamTicketAssessmentTagExample eamTicketAssessmentTagExample = new EamTicketAssessmentTagExample();
        EamTicketAssessmentTagExample.Criteria criteria = eamTicketAssessmentTagExample.createCriteria();
        criteria.andTicketIdEqualTo(ticketId);
		criteria.andAssessmentIdEqualTo(id);
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamTicketAssessmentTag> list= eamTicketAssessmentTagService.selectByExample(eamTicketAssessmentTagExample);
        map.put("ticketAssessmentTags", list);
		return map;
	}

	@ApiOperation(value = "修改工单评价")
	@RequiresPermissions("eam:ticketAssessment:update")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object update(@PathVariable("ticketId") int ticketId, @PathVariable("id") int id,int[] ticketTag, EamTicketAssessment ticketAssessment) {
		ComplexResult result = FluentValidator.checkAll()
				.on(ticketAssessment.getAssessmentLevel(), new NotNullValidator( "工单评价星级不能为空"))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if (!result.isSuccess()) {
			return new EamResult(INVALID_LENGTH, result.getErrors());
		}
		ticketAssessment.setId(id);
		baseEntityUtil.updateAddtionalValue(ticketAssessment);
		int count = eamTicketAssessmentService.updateTicketAssessment(ticketAssessment, ticketTag);
		return new EamResult(SUCCESS, count);
	}

    @ApiOperation(value = "工单评价详细")
    @RequiresPermissions("eam:ticketAssessment:read")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public EamTicketAssessmentVO getAssessmentTicket(@PathVariable("ticketId") int ticketId){
        EamTicketAssessment ass =getTicketAssessment(ticketId);
        EamTicketAssessmentVO vo = new EamTicketAssessmentVO();
        vo.setId(ass.getId());
        vo.setAssessmentLevel(ass.getAssessmentLevel());
        vo.setTagNames(getAssessmentTicketTag(ass.getId()));
        vo.setDescription(ass.getDescription());
        return vo;
    }

	private List<EamTicketAssessmentVO> getAssessmentTicket(List<EamTicketAssessment> rows){
		List<EamTicketAssessmentVO> vos=new ArrayList<EamTicketAssessmentVO> ();
		EamTicketAssessmentVO vo =null;
		for(EamTicketAssessment ass : rows){
			vo = new EamTicketAssessmentVO();
			vo.setId(ass.getId());
			vo.setAssessmentLevel(ass.getAssessmentLevel());
			vo.setTagNames(getAssessmentTicketTag(ass.getId()));
			vo.setDescription(ass.getDescription());
			vos.add(vo);
		}
		return vos;
	}


	private void insertTag(int pid,int ticketId, int[] ticketTags){
		//insert tag
		List<EamTicketAssessmentTag> items=new ArrayList<EamTicketAssessmentTag>();
		EamTicketAssessmentTag tag=null;
		for(int i : ticketTags){
			tag=new EamTicketAssessmentTag();
			tag.setTagId(i);
            tag.setTicketId(ticketId);
			tag.setAssessmentId(pid);
			baseEntityUtil.addAddtionalValue(tag);
			items.add(tag);
		}
		eamTicketAssessmentTagService.batchInsert(items);
	}

	private int deleteTag(int assessmentId) {
		//delete tag
		EamTicketAssessmentTagExample eamTicketAssessmentTagExample = new EamTicketAssessmentTagExample();
		EamTicketAssessmentTagExample.Criteria criteria = eamTicketAssessmentTagExample.createCriteria();
		criteria.andAssessmentIdEqualTo(assessmentId);
		criteria.andDeleteFlagEqualTo(Boolean.FALSE);
		return eamTicketAssessmentTagService.deleteByExample(eamTicketAssessmentTagExample);
	}

    private EamTicketAssessment getTicketAssessment(int ticketId){
        EamTicketAssessmentExample eamTicketAssessmentExample = new EamTicketAssessmentExample();
        EamTicketAssessmentExample.Criteria criteria = eamTicketAssessmentExample.createCriteria();
        criteria.andTicketIdEqualTo(ticketId);
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            criteria.andCompanyIdEqualTo(company.getCompanyId());
        }

        List<EamTicketAssessment> rows = eamTicketAssessmentService.selectByExample(eamTicketAssessmentExample);
        if(rows.isEmpty())
            return null;
        else
            return rows.get(0);
    }

}