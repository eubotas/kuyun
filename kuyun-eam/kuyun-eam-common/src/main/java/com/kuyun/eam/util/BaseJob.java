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

    public String jobKey;
    public String group;
    public Date startDate;
    public int IntervalHours;


    @Override
    public abstract void execute(JobExecutionContext context) throws JobExecutionException;

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

    public String getGroup() {
        if(group !=null)
        return group;
        else
            return jobKey;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}