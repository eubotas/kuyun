package com.kuyun.eam.job;

import com.kuyun.common.util.NumberUtil;
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
        planUtil.createTicket(NumberUtil.toInteger(idKey));
    }


    private void setCronData(EamMaintainPlan plan){
        if(plan != null) {
            Date startMainTainDate = plan.getNextMaintainDate();
            setStartDate(startMainTainDate);
            String unit = plan.getMaintainFrequencyUnit();
            int num = plan.getMaintainFrequencyQuantity();

            setCron(unit,  num, startMainTainDate);
        }
    }



}
