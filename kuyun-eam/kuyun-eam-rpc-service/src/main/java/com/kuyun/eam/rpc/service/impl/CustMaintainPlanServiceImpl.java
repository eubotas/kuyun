package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.util.NumberUtil;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.mapper.EamMaintainUserMapper;
import com.kuyun.eam.dao.model.*;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    EamMaintainUserService maintainUserService;

    @Override
    public int createMaintainPlan(EamMaintainPlan plan, String[] maintainUserIds){
        plan = insertSelectiveCust(plan);

        insertMaintainUsers(plan, maintainUserIds);

        try {
            startQuartz(plan);
        }catch(SchedulerException ex){ex.printStackTrace();}
        if(plan.getPlanId() != null){
            return  1;
        }else {
            return  0;
        }
    }

    private void insertMaintainUsers(EamMaintainPlan plan, String[] maintainUserIds) {
        List<EamMaintainUser> maintainUsers = createEamMaintainUsers(maintainUserIds, plan);
        maintainUserService.batchInsert(maintainUsers);
    }


    private List<EamMaintainUser> createEamMaintainUsers(String[] maintainUserIds, EamMaintainPlan plan){
        List<EamMaintainUser> result = new ArrayList<>(maintainUserIds != null ? maintainUserIds.length : 0);
        for(String userId : maintainUserIds){
            EamMaintainUser maintainUser = new EamMaintainUser();
            maintainUser.setPlanId(plan.getPlanId());
            maintainUser.setUserId(NumberUtil.toInteger(userId));
            maintainUser.setUpdateTime(new Date());
            maintainUser.setCreateTime(new Date());
            maintainUser.setDeleteFlag(Boolean.FALSE);
            result.add(maintainUser);
        }
        return result;
    }

    private void updateMaintainUsers(String[] maintainUserIds, EamMaintainPlan plan){
        //delete existing data
        EamMaintainUserExample example = new EamMaintainUserExample();
        example.createCriteria().andPlanIdEqualTo(plan.getPlanId())
                .andDeleteFlagEqualTo(Boolean.FALSE);

        maintainUserService.deleteByExample(example);

        insertMaintainUsers(plan, maintainUserIds);
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
    public int updateMaintainPlan(EamMaintainPlan plan, String[] maintainUserIds) {
        int count = updateByPrimaryKeySelective(plan);

        updateMaintainUsers(maintainUserIds, plan);

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