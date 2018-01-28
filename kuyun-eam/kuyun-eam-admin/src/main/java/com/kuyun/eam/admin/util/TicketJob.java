package com.kuyun.eam.admin.util;

import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.dao.model.EamMaintainTicket;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.eam.rpc.api.EamMaintainTicketService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.util.BaseJob;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.upms.client.util.BaseEntityUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TicketJob extends BaseJob {

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamMaintainPlanService eamMainTainPlanService;

    @Autowired
    private EamTicketService eamTicketService;

    @Autowired
    private EamMaintainTicketService eamMaintainTicketService;

    public TicketJob(){}
    public TicketJob(int id){
        idKey = String.valueOf(id);
        setKeyData("TICKET"+id);
        setCronData(id);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String idKey = data.getString(IDKEY); //传值

        System.out.println("do something"+(new Date()));

        //createTicket(eamMainTainPlanService.selectByPrimaryKey(Integer.parseInt(idKey)));
    }

    private EamMaintainPlan getPlan(){
        EamMaintainPlan plan=new EamMaintainPlan();
        plan.setEquipmentCategoryId(2);
        plan.setEquipmentId("94tOF6JTVZsADSti");
        plan.setMaintainFrequencyQuantity(2);
        plan.setMaintainFrequencyUnit("DAY0");
        plan.setNextMaintainDate(EamDateUtil.getDateAfterMinute(new Date(),1));//EamDateUtil.getDate(2018,1,27)
        plan.setRemindTime(2);
        plan.setWorkContent("保养");
        plan.setCompanyId(298);
        plan.setOrgId(1);
        return plan;
    }

    private void setCronData(int id){
        EamMaintainPlan plan= getPlan();//eamMainTainPlanService.selectByPrimaryKey(id);
        if(plan != null) {
            int orgId = plan.getOrgId();
            Date startMainTainDate = plan.getNextMaintainDate();
            setStartDate(startMainTainDate);
            String unit = plan.getMaintainFrequencyUnit();
            int num = plan.getMaintainFrequencyQuantity();

            int day = startMainTainDate.getDay();
            int month = startMainTainDate.getMonth();
            int week = EamDateUtil.getWeekOfDate(startMainTainDate);

            if ("YEAR".equals(unit)) {
                setScheduleMethod(ScheduleMethod.CRON.ordinal());
                cronSchedule = "0 0 1 " + day + " " + month + " ? */" + num;
            } else if ("MONTH".equals(unit)) {
                setScheduleMethod(ScheduleMethod.CRON.ordinal());
                cronSchedule = "0 0 1 " + day + " */" + month + " ? ";
            } else if ("WEEK".equals(unit)) {
                setScheduleMethod(ScheduleMethod.CRON.ordinal());
                cronSchedule = "0 0 1 * * "+week+"/" + num ;
            }else if ("DAY".equals(unit)) {
                setIntervalHours(24*num);
                setScheduleMethod(ScheduleMethod.SAMPLE.ordinal());
            }else{
                setScheduleMethod(ScheduleMethod.CRON.ordinal());
                cronSchedule = "*/10 * * * * ?" ;
            }
        }
    }

    private void createTicket(EamMaintainPlan plan){
        if(plan != null) {
            EamTicket ticket = new EamTicket();
            ticket.setStatus(TicketStatus.INIT.getName());
            ticket.setDescription(plan.getWorkContent());
            ticket.setTicketTypeId(0);
            ticket.setEquipmentCategoryId(plan.getEquipmentCategoryId());
            ticket.setEquipmentId(plan.getEquipmentId());
            ticket.setCompanyId(plan.getCompanyId());
            baseEntityUtil.addAddtionalValue(ticket);

            eamTicketService.insert(ticket);//todo

            EamMaintainTicket mt=new EamMaintainTicket();
            mt.setPlanId(plan.getPlanId());
            mt.setTicketId(ticket.getTicketId());
            baseEntityUtil.addAddtionalValue(mt);
            eamMaintainTicketService.insert(mt);
        }
    }

}
