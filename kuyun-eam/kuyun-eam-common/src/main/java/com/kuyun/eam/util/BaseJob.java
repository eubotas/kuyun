package com.kuyun.eam.util;

import com.kuyun.common.dao.model.BaseEntity;
import com.kuyun.common.util.SpringContextUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseJob implements Job {
    private static Logger _logger = LoggerFactory.getLogger(BaseJob.class);
    public static String IDKEY="IDKEY";

    public enum ScheduleMethod {
        CRON, SAMPLE
    }

    public String idKey;
    public String jobKeyName;
    public String group;
    public Date startDate;
    public int IntervalHours;
    public String cronSchedule;
    public int scheduleMethod;

    public void setCron(String unit, int num, Date startDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.DAY_OF_WEEK);

        StringBuffer sb=new StringBuffer("0 0 1 ");

        if ("YEAR".equals(unit)) {
            setScheduleMethod(ScheduleMethod.CRON.ordinal());
            sb.append(day).append(" ").append(month).append(" ? */").append( num);
            cronSchedule =sb.toString();
        } else if ("MONTH".equals(unit)) {
            setScheduleMethod(ScheduleMethod.CRON.ordinal());
            sb.append(day).append(" ").append(month).append("/").append(num).append(" ?");
            cronSchedule =sb.toString();
        } else if ("WEEK".equals(unit)) {
            setScheduleMethod(ScheduleMethod.CRON.ordinal());
            sb.append("? * ").append(week).append("/").append(num);
            cronSchedule =sb.toString();
        }
        else if ("DAY".equals(unit)) {
            setIntervalHours(24*num);
            setScheduleMethod(ScheduleMethod.SAMPLE.ordinal());
        }
    }

    public int getScheduleMethod() {
        return scheduleMethod;
    }

    public void setScheduleMethod(int scheduleMethod) {
        this.scheduleMethod = scheduleMethod;
    }

    @Override
    public abstract void execute(JobExecutionContext context) throws JobExecutionException;

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public String getJobKeyName() {
        return jobKeyName;
    }

    public void setJobKeyName(String jobKeyName) {
        this.jobKeyName = jobKeyName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getIntervalHours() {
        return IntervalHours;
    }

    public void setIntervalHours(int intervalHours) {
        IntervalHours = intervalHours;
    }

    public String getCronSchedule() {
        return cronSchedule;
    }

    public void setCronSchedule(String cronSchedule) {
        this.cronSchedule = cronSchedule;
    }

    public String getGroup() {
        if(group !=null)
        return group;
        else
            return jobKeyName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setKeyData(String jobKey){
        jobKeyName =jobKey;
        group =jobKey;
    }

    public void addAddtionalValue(BaseEntity record, int companyId){
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