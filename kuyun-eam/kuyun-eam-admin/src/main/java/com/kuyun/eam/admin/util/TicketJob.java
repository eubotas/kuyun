package com.kuyun.eam.admin.util;

import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.util.BaseJob;
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

    public TicketJob(int id){
        idKey = String.valueOf(id);
        setKeyData("TICKET"+id);
        setCronData(id);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String idKey = data.getString(IDKEY); //传值

        createTicket(eamMainTainPlanService.selectByPrimaryKey(Integer.parseInt(idKey)));
    }

    private void setCronData(int id){
        EamMaintainPlan plan= eamMainTainPlanService.selectByPrimaryKey(id);
        if(plan != null) {
            int orgId = plan.getOrgId();
            Date startMainTainDate = plan.getNextMaintainDate();
            String unit = plan.getMaintainFrequencyUnit();
            int num = plan.getMaintainFrequencyQuantity();

            int day = startMainTainDate.getDay();
            int month = startMainTainDate.getMonth();

            if ("YEAR".equals(unit)) {
                //setScheduleMethod(ScheduleMethod.CRON.);
                cronSchedule = "0 0 1 " + day + " " + month + " ? */" + num;
            } else if ("MONTH".equals(unit)) {
                cronSchedule = "0 0 1 " + day + " */" + month + " ? ";
            } else if ("DAY".equals(unit)) {
                cronSchedule = "0 0 1 */" + num + " * ? ";
            }
        }else {
            cronSchedule = "0 */2 * * * ? ";  //test
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

            eamTicketService.insert(ticket);
        }
        System.out.println("Start execute..."+(new Date()));
    }

}
