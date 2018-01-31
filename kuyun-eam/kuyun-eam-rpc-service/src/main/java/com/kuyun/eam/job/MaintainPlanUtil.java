package com.kuyun.eam.job;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainTicket;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.rpc.api.EamAlertMessageService;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.eam.rpc.api.EamMaintainTicketService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MaintainPlanUtil {
    private static Logger _log = LoggerFactory.getLogger(MaintainPlanUtil.class);
    @Autowired
    EamMaintainPlanMapper eamMaintainPlanMapper;

    @Autowired
    private EamMaintainPlanService eamMainTainPlanService;
    @Autowired
    private EamTicketService eamTicketService;
    @Autowired
    private EamMaintainTicketService eamMaintainTicketService;
    @Autowired
    private EamAlertMessageService eamAlertMessageService;
    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;


    public EamMaintainPlan getEamMaintainPlan(String planId){
        return eamMainTainPlanService.selectByPrimaryKey(Integer.parseInt(planId));
    }

    public void createTicket(String planId){
        EamMaintainPlan plan = getEamMaintainPlan(planId);
        if(plan != null) {
            EamTicket ticket = new EamTicket();
            ticket.setStatus(TicketStatus.INIT.getName());
            ticket.setDescription(plan.getWorkContent());
            ticket.setTicketTypeId(0);
            ticket.setEquipmentCategoryId(plan.getEquipmentCategoryId());
            ticket.setEquipmentId(plan.getEquipmentId());
            ticket.setCompanyId(plan.getCompanyId());
            addAddtionalValue(ticket, plan.getCompanyId());

            ticket=eamTicketService.insertSelectiveCust(ticket);

            EamMaintainTicket mt=new EamMaintainTicket();
            mt.setPlanId(plan.getPlanId());
            mt.setTicketId(ticket.getTicketId());
            addAddtionalValue(mt, plan.getCompanyId());
            eamMaintainTicketService.insert(mt);
        }
    }

    public void createAlert(String planId){
        EamMaintainPlan plan = getEamMaintainPlan(planId);
        if(plan != null) {
            int orgId=plan.getOrgId();
            UpmsUserOrganizationExample example=new UpmsUserOrganizationExample();
            UpmsUserOrganizationExample.Criteria cr= example.createCriteria();
            cr.andOrganizationIdEqualTo(orgId);

            String content="保养计划: "+ EamDateUtil.getDateStr(plan.getNextMaintainDate(), "yyyy-mm-dd");

            List<UpmsUserOrganization> uos=upmsUserOrganizationService.selectByExample(example);
            List<EamAlertMessage> alerts=new ArrayList<EamAlertMessage>();
            EamAlertMessage alert = null;
            for(UpmsUserOrganization uo: uos) {
                alert = new EamAlertMessage();
                alert.setUserId(uo.getUserId());
                alert.setAlertStartDate(EamDateUtil.getDateBefore(plan.getNextMaintainDate(),plan.getRemindTime()));
                alert.setAlertEndDate(plan.getNextMaintainDate());
                alert.setContent(content);
                addAddtionalValue(alert, plan.getCompanyId());
                alerts.add(alert);
            }
            eamAlertMessageService.batchInsert(alerts);
        }
    }


    private void addAddtionalValue(BaseEntity record, int companyId){
        Date currentDate = new Date();
        if (record.getCreateTime() == null){
            record.setCreateTime(currentDate);
        }
        record.setUpdateTime(currentDate);

        if (record.getCreateUserId() == null){
            record.setCreateUserId(0);
        }
        record.setUpdateUserId(0);
        record.setCompanyId(companyId);
        record.setDeleteFlag(Boolean.FALSE);
    }
}
