package com.kuyun.marketing.rpc.service.impl;

import com.kuyun.common.netease.SMSUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 2017-07-26.
 */
public class SendSmsJob implements Job {
    private static Logger _log = LoggerFactory.getLogger(SendSmsUtil.class);

    public static final String APP_KEY = "APP_KEY";
    public static final String APP_SECRET = "APP_SECRET";
    public static final String TEMPLATE_ID = "TEMPLATE_ID";
    public static final String MOBILES = "MOBILES";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String appKey = data.getString(APP_KEY);
        String appSecret = data.getString(APP_SECRET);
        String templateId = data.getString(TEMPLATE_ID);
        String mobiles = data.getString(MOBILES);

        String result = SMSUtil.sendTemplate(appKey, appSecret, templateId, mobiles, "");

        _log.info("Send SMS Result:"+result);
    }
}
