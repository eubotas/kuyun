package com.kuyun.eam.admin.util;

import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import com.kuyun.eam.util.BaseJob;
import com.kuyun.upms.client.util.BaseEntityUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.quartz.DateBuilder.evenHourDate;

public class TicketJob extends BaseJob {

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamMaintainPlanService eamMainTainPlanService;

    public TicketJob(int id){

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobDataMap data = context.getJobDetail().getJobDataMap();
//        String deviceId = data.getString(DEVICE_ID); //传值
    }

    private void getData(int id){
        EamMaintainPlan plan= eamMainTainPlanService.selectByPrimaryKey(id);
        int orgId= plan.getOrgId();
        Date nextDate = plan.getNextMaintainDate();
        String unit= plan.getMaintainFrequencyUnit();
        int day= plan.getMaintainFrequencyQuantity();

        startDate = evenHourDate(nextDate);
        if("YEAR".equals(unit)){

        }else if("MONTH".equals(unit)){
            IntervalHours = 24 * day * ;
        }else if("DAY".equals(unit)){
            IntervalHours = 24 * day;
        }

    }
}
