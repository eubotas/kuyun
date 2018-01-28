package com.kuyun.eam.admin.util;

import com.kuyun.eam.common.constant.TicketStatus;
import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.rpc.api.EamAlertMessageService;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.eam.rpc.api.EamTicketService;
import com.kuyun.eam.util.BaseJob;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;
import com.kuyun.upms.rpc.api.UpmsOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlertJob extends BaseJob {

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamMaintainPlanService eamMainTainPlanService;

    @Autowired
    private EamAlertMessageService eamAlertMessageService;

    @Autowired
    private UpmsUserOrganizationService upmsUserOrganizationService;

    public AlertJob(){}
    public AlertJob(int id){
        idKey = String.valueOf(id);
        setKeyData("ALERT"+id);
        setCronData(id);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String idKey = data.getString(IDKEY); //传值
        createAlert(eamMainTainPlanService.selectByPrimaryKey(Integer.parseInt(idKey)));
    }

    private void setCronData(int id){
        EamMaintainPlan plan= eamMainTainPlanService.selectByPrimaryKey(id);
        if(plan != null) {
            int orgId = plan.getOrgId();
            Date startMainTainDate = EamDateUtil.getDateBefore(plan.getNextMaintainDate(),plan.getRemindTime());
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
            }
        }
    }

    private void createAlert(EamMaintainPlan plan){
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
                baseEntityUtil.addAddtionalValue(alert);
                alerts.add(alert);
            }
            eamAlertMessageService.batchInsert(alerts);
        }
    }

}
