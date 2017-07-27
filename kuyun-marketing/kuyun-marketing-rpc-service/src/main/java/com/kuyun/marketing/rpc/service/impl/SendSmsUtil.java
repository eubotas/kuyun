package com.kuyun.marketing.rpc.service.impl;

import org.quartz.*;
import org.quartz.Calendar;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by user on 2017-07-26.
 */
public class SendSmsUtil {
    private static Logger _logger = LoggerFactory.getLogger(SendSmsUtil.class);

    private static final SchedulerFactory sf = new StdSchedulerFactory();

    private String appKey;
    private String appSecrt;
    private String templateId;
    private String mobiles;
    private Date sendTime;

    public SendSmsUtil(){
        start();
    }

    private Scheduler getScheduler() throws SchedulerException {
        return sf.getScheduler();
    }

    private void start() {
        try {
            if (!getScheduler().isStarted()){
                getScheduler().start();
            }
        } catch (SchedulerException e) {
            _logger.error("Sms Scheduler Error : " + e.getMessage());
        }
    }

    public void sendSMS() {
        try {
            Date runTime = evenMinuteDate(getSendTime());

            JobDetail job = newJob(SendSmsJob.class).withIdentity(runTime.toString(), "group1").build();
            job.getJobDataMap().put(SendSmsJob.APP_KEY, getAppKey());
            job.getJobDataMap().put(SendSmsJob.APP_SECRET, getAppSecrt());
            job.getJobDataMap().put(SendSmsJob.MOBILES, getMobiles());
            job.getJobDataMap().put(SendSmsJob.TEMPLATE_ID, getTemplateId());

            // Trigger the job to sendSMS on the next round minute
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

            // Tell quartz to schedule the job using our trigger
            getScheduler().scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            _logger.error("Send Sms Error:"+ e.getMessage());
        }
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecrt() {
        return appSecrt;
    }

    public void setAppSecrt(String appSecrt) {
        this.appSecrt = appSecrt;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public Date getSendTime() {
        Date now = new Date();
        if (now.after(sendTime)) {
            return addOneMinute(now);
        }
        return sendTime;
    }

    private Date addOneMinute(Date now){
        java.util.Calendar  cal = java.util.Calendar.getInstance();
        cal.setTime(now);
        cal.add(java.util.Calendar.MINUTE,1);
        return cal.getTime();
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
