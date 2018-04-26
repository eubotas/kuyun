package com.kuyun.eam.job;

import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.util.BaseJob;
import com.kuyun.eam.util.EamDateUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;


public class AlertJob extends BaseJob {

    public AlertJob(){}
    public AlertJob(EamMaintainPlan plan){
        int planId= plan.getPlanId();
        idKey = String.valueOf(planId);
        setKeyData("ALERT"+planId);
        setCronData(plan);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String idKey = data.getString(IDKEY); //传值
        MaintainPlanUtil planUtil = SpringContextUtil.getBean(MaintainPlanUtil.class);
        planUtil.createAlert(idKey);
    }

    private void setCronData(EamMaintainPlan plan){
        if(plan != null) {
            Date startMainTainDate = EamDateUtil.getDateBefore(plan.getNextMaintainDate(),plan.getRemindTime());
            setStartDate(startMainTainDate);
            String unit = plan.getMaintainFrequencyUnit();
            int num = plan.getMaintainFrequencyQuantity();

            setCron(unit,  num, startMainTainDate);
        }
    }


}