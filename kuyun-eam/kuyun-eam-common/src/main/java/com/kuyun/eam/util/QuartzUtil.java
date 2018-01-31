package com.kuyun.eam.util;

import javafx.util.Pair;
import net.bytebuddy.implementation.bytecode.Throw;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzUtil {

    private final Logger _logger = LoggerFactory.getLogger(QuartzUtil.class);
    private JobKey jobKey = null;
    private BaseJob jobImpl;
    private final SchedulerFactory sf = new StdSchedulerFactory();

    public QuartzUtil(BaseJob job){
        if(job.getStartDate() ==null)
            throw new RuntimeException("start date or interval hours cannot be null");
        this.jobImpl = job;
        jobKey = new JobKey(job.getJobKeyName(), job.getGroup());
        start();
    }

    public Scheduler getScheduler() throws SchedulerException {
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

    private Pair<JobDetail, Trigger> buildJobAndTrigger(){
        Date startDate = jobImpl.getStartDate();
        if(startDate.getTime() < (new Date().getTime()))
            startDate = new Date();
            //throw new RuntimeException("计划执行日期不能早于现在");
        JobDetail job = newJob(jobImpl.getClass()).withIdentity(jobKey.getName(), jobKey.getGroup()).build();
        job.getJobDataMap().put(BaseJob.IDKEY, jobImpl.getIdKey());

        Trigger trigger = null;
        if(jobImpl.getScheduleMethod() == BaseJob.ScheduleMethod.CRON.ordinal()){
            trigger = newTrigger().withIdentity(jobKey.getName(), jobKey.getGroup())
                    .startAt(startDate)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobImpl.getCronSchedule())).build();
        }else {
            trigger = newTrigger().withIdentity(jobKey.getName(), jobKey.getGroup())
                    .startAt(startDate)
                    .withSchedule(simpleSchedule()
                            .withIntervalInHours(jobImpl.getIntervalHours())
                            .repeatForever()).build();
        }
        return new Pair<JobDetail, Trigger>(job, trigger);
    }



    public void run() throws SchedulerException {
        _logger.info("QuartzUtil Scheduler Starting for keyJob : ", jobKey.getName());
        Pair<JobDetail, Trigger> pair = buildJobAndTrigger();
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
