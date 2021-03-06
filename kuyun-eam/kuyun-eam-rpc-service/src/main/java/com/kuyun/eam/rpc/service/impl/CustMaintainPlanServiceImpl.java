package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.QuartzUtil;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kuyun.eam.job.TicketJob;
import com.kuyun.eam.job.AlertJob;

/**
* EamMaintainPlanService实现
* Created by kuyun on 2018/1/24.
*/
@Service
@Transactional
@BaseService
public class CustMaintainPlanServiceImpl extends BaseServiceImpl<EamMaintainPlanMapper, EamMaintainPlan, EamMaintainPlanExample> implements CustMaintainPlanService {

    private static Logger _log = LoggerFactory.getLogger(CustMaintainPlanServiceImpl.class);

    @Autowired
    EamMaintainPlanMapper eamMaintainPlanMapper;

    @Override
    public int createMaintainPlan(EamMaintainPlan plan){
        plan = insertSelectiveCust(plan);
        try {
            startQuartz(plan);
        }catch(SchedulerException ex){ex.printStackTrace();}
        if(plan.getPlanId() != null)
            return  1;
        else
            return  0;
    }

    @Override
    public int deleteMaintainPlan(String ids) {
        try {
            String[] planIds=ids.split("-");
            for(String id : planIds) {
                EamMaintainPlan plan = this.selectByPrimaryKey(Integer.parseInt(id));
                deleteQuartz(plan);
            }
        }catch(SchedulerException ex){ex.printStackTrace();
            return 0;
        }
        return deleteByPrimaryKeys(ids);
    }

    @Override
    public int updateMaintainPlan(EamMaintainPlan plan) {
        int count = updateByPrimaryKeySelective(plan);
        try {
            deleteQuartz(plan);
            startQuartz(plan);
        }catch(SchedulerException ex){
            ex.printStackTrace();
            return 0;
        }
        return count;
    }


    private void startQuartz(EamMaintainPlan plan) throws SchedulerException{
        TicketJob ticketJob=new TicketJob(plan);
        QuartzUtil ticketQ=new QuartzUtil(ticketJob);
        ticketQ.deleteJob();
        ticketQ.run();

        AlertJob ajob=new AlertJob(plan);
        QuartzUtil aq=new QuartzUtil(ajob);
        aq.deleteJob();
        aq.run();
    }

    private void deleteQuartz(EamMaintainPlan plan) throws SchedulerException{
        TicketJob ticketJob=new TicketJob(plan);
        QuartzUtil ticketQ=new QuartzUtil(ticketJob);
        ticketQ.deleteJob();

        AlertJob ajob=new AlertJob(plan);
        QuartzUtil aq=new QuartzUtil(ajob);
        aq.deleteJob();
    }

}