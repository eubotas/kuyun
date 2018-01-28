package com.kuyun.eam.admin.controller.manager;

import com.kuyun.eam.admin.util.TicketJob;
import com.kuyun.eam.util.EamDateUtil;
import com.kuyun.eam.util.QuartzUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.JobBuilder.newJob;

public class TestSchedule implements Job {

    private final static SchedulerFactory sf = new StdSchedulerFactory();

    public static void main(String[] args) throws Exception{
        TicketJob job=new TicketJob(1);
        QuartzUtil q=new QuartzUtil(job);
        q.run();

//        QuartzUtil q2=new QuartzUtil(job);
//        q2.deleteJob();
        //testSch();
    }

    private Scheduler getScheduler() throws SchedulerException {
        return sf.getScheduler();
    }

    private static Date addJob(JobDetail job, Trigger trigger) throws SchedulerException {
         return sf.getScheduler().scheduleJob(job, trigger);
    }

    public static void testSch() throws Exception{
        JobDetail job=newJob()
                .ofType(TestSchedule.class) //引用Job Class
                .withIdentity("job1", "group1") //设置name/group
                .withDescription("this is a test job") //设置描述
                .usingJobData("age", 18) //加入属性到ageJobDataMap
                .build();

        job.getJobDataMap().put("name", "quertz"); //加入属性name到JobDataMap


        //定义一个每秒执行一次的SimpleTrigger
//       Trigger trigger=newTrigger()
//                .startNow()
//                .withIdentity("trigger1")
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(1)
//                        .repeatForever())
//                .build();

       CronTrigger trigger = newTrigger()
                .startAt(EamDateUtil.getDateAfterMinute(new Date(),1))
                //.startAt(EamDateUtil.getDateBefore(new Date(2018,2,1), 2))
                .withIdentity("trigger2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 */2 * * * ?")).build();

        Scheduler sche= sf.getScheduler();
        Date ft = addJob(job, trigger);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        System.out.println("getStartTime:"+(trigger.getStartTime()));
//        System.out.println(job.getKey() + " 已被安排执行于: " + sdf.format(ft) +
//                "，并且以如下重复规则重复执行: "+ trigger.getEndTime());

        sche.start();

        System.in.read();

        sche.shutdown();
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("do nothing"+(new Date()));
    }
}
