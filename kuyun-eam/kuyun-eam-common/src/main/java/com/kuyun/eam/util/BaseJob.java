package com.kuyun.eam.util;

import com.kuyun.common.util.SpringContextUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public abstract class BaseJob implements Job {
    private static Logger _logger = LoggerFactory.getLogger(BaseJob.class);
    public static String IDKEY="IDKEY";

    public enum ScheduleMethod {
        CRON, SAMPLE
    }

    public String idKey;
    public String jobKey;
    public String group;
    public Date startDate;
    public int IntervalHours;
    public String cronSchedule;
    public int scheduleMethod;


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

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
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
            return jobKey;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setKeyData(String jobKey){
        jobKey =jobKey;
        group =jobKey;
    }
}