package com.kuyun.eam.job;

import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.util.BaseJob;
import com.kuyun.eam.util.EamDateUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class TicketJob extends BaseJob {

    public TicketJob(){}
    public TicketJob(EamMaintainPlan plan){
        int id= plan.getPlanId();
        idKey = String.valueOf(id);
        setKeyData("TICKET"+id);
        setCronData(plan);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String idKey = data.getString(IDKEY); //传值

        MaintainPlanUtil planUtil = SpringContextUtil.getBean(MaintainPlanUtil.class);
        planUtil.createTicket(idKey);
    }


    private void setCronData(EamMaintainPlan plan){
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
            }
//            else if ("DAY".equals(unit)) {
//                setIntervalHours(24*num);
//                setScheduleMethod(ScheduleMethod.SAMPLE.ordinal());
//            }
            else{
                setScheduleMethod(ScheduleMethod.CRON.ordinal());
                cronSchedule = "*/20 * * * * ?" ;
            }
        }
    }



}
