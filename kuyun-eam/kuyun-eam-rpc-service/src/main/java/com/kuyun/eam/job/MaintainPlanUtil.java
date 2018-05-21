package com.kuyun.eam.job;

import com.kuyun.common.constant.OrgDepartment;
import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.common.jpush.JpushUtil;
import com.kuyun.common.util.NumberUtil;
import com.kuyun.eam.common.constant.EamConstant;
import com.kuyun.eam.common.constant.TicketPriority;
import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.common.constant.TicketType;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.util.TicketUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserService;
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

    @Autowired
    public EamEquipmentCategoryService eamEquipmentCategoryService;

    @Autowired
    public EamEquipmentService eamEquipmentService;

    @Autowired
    public EamMaintainUserService maintainUserService;

    @Autowired
    public UpmsUserService upmsUserService;

    @Autowired
    private JpushUtil jpushUtil;


    public EamMaintainPlan getEamMaintainPlan(String planId){
        return eamMainTainPlanService.selectByPrimaryKey(Integer.parseInt(planId));
    }

    private List<EamMaintainUser> getEamMaintainUsers(String planId){
        EamMaintainUserExample example = new EamMaintainUserExample();
        example.createCriteria().andPlanIdEqualTo(NumberUtil.toInteger(planId)).andDeleteFlagEqualTo(Boolean.FALSE);

        return maintainUserService.selectByExample(example);
    }

    public void createTicket(String planId){
        EamMaintainPlan plan = getEamMaintainPlan(planId);
        if(plan != null) {

            List<EamMaintainUser> maintainUsers = getEamMaintainUsers(planId);
            for(EamMaintainUser maintainUser : maintainUsers){
                EamTicket ticket = new EamTicket();
                ticket.setStatus(TicketStatus.TO_PROCESS.getName());
                ticket.setDescription(plan.getWorkContent());
                ticket.setTicketTypeId(TicketType.MIANTAIN.getCode());
                ticket.setProductLineId(plan.getProductLineId());
                ticket.setTicketNumber(TicketUtil.generatorTicketNumber());
                ticket.setPriority(TicketPriority.URGENT.getName());
                ticket.setExecutorId(maintainUser.getUserId());

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
    }

    public void createAlert(String planId){
        EamMaintainPlan plan = getEamMaintainPlan(planId);
        if(plan != null) {
            List<EamMaintainUser> maintainUsers = getEamMaintainUsers(planId);

            String equipmentName=eamEquipmentService.selectByPrimaryKey(plan.getEquipmentId()).getName();

            String content= EamConstant.MAINTAIN_PLAN_LABEL + ": "+ EamDateUtil.getDateStr(plan.getNextMaintainDate());
            String title=equipmentName + "的"+ EamConstant.MAINTAIN_PLAN_LABEL + ": "+ EamDateUtil.getDateStr(plan.getNextMaintainDate());

            List<EamAlertMessage> alerts=new ArrayList<EamAlertMessage>();
            EamAlertMessage alert;
            for(EamMaintainUser uo: maintainUsers) {
                alert = new EamAlertMessage();
                alert.setUserId(uo.getUserId());
                alert.setAlertStartDate(EamDateUtil.getDateBefore(plan.getNextMaintainDate(),plan.getRemindTime()));
                alert.setAlertEndDate(plan.getNextMaintainDate());
                alert.setMessageTitle(title);
                alert.setContent(content);
                alert.setReadFlag(Boolean.FALSE);
                addAddtionalValue(alert, plan.getCompanyId());
                alerts.add(alert);

                List<String> phones = getPhones(uo.getUserId());
                if (!phones.isEmpty()){
                    String message = "你有一条维护计划需要处理：" + title;
                    jpushUtil.sendPush(phones, message, OrgDepartment.MAINTENANCE_DEPARTMENT.getCode());
                }

            }
            eamAlertMessageService.batchInsert(alerts);
        }
    }

    private List<String> getPhones(Integer userId){
        List<String> result = new ArrayList<>(1);
        UpmsUser user = getUser(userId);
        if (user != null && user.getPhone() != null){
            result.add(user.getPhone());
        }

        return result;
    }
    private UpmsUser getUser(Integer userId){
        return upmsUserService.selectByPrimaryKey(userId);
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
