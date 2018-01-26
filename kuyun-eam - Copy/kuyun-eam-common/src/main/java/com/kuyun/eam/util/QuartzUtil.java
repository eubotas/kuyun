package com.kuyun.eam.util;

import javafx.util.Pair;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzUtil {

    private final Logger _logger = LoggerFactory.getLogger(QuartzUtil.class);
    private JobKey jobKey = null;
    private BaseJob jobImpl;
    private final SchedulerFactory sf = new StdSchedulerFactory();

    public QuartzUtil(BaseJob job){
        if(job.getStartDate() ==null || job.getIntervalHours() ==0)
            throw new RuntimeException("start date or interval hours cannot be null");
        this.jobImpl = job;
        jobKey = new JobKey(job.getJobKey(), job.getGroup());
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
            _logger.error("QuartzUtil Scheduler Error : " + e.getMessage());
        }
    }

    private Pair<JobDetail, SimpleTrigger> buildJobAndTrigger(){
        JobDetail job = newJob(jobImpl.getClass()).withIdentity(jobKey.getName(), jobKey.getGroup()).build();
        //job.getJobDataMap().put(ReadDataJob.DEVICE_ID, deviceId);

        SimpleTrigger trigger = newTrigger().withIdentity(jobKey.getName(), jobKey.getGroup())
                .startAt(jobImpl.getStartDate())
                .withSchedule(simpleSchedule()
               // .withIntervalInHours(jobImpl.getIntervalHours())
                .withIntervalInSeconds(jobImpl.getIntervalHours())
                 .repeatForever()).build();

        return new Pair<JobDetail, SimpleTrigger>(job, trigger);
    }

    public void run() throws SchedulerException {
        _logger.info("QuartzUtil Scheduler Starting for keyJob : ", jobKey.getName());
        Pair<JobDetail, SimpleTrigger> pair = buildJobAndTrigger();
        getScheduler().scheduleJob(pair.getKey(), pair.getValue());
    }

    public void pauseJob() throws SchedulerException {
        _logger.info("QuartzUtil Scheduler Stopping for keyJob : ", jobKey.getName());
        if (getScheduler().checkExists(jobKey)){
            getScheduler().unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            _logger.info("QuartzUtil Scheduler Stopped for keyJob : ", jobKey.getName());
        }
    }

    public void resumeJob() throws SchedulerException {
        getScheduler().resumeJob(jobKey);
    }

    public void deleteJob() throws SchedulerException{
        getScheduler().deleteJob(jobKey);
    }

}