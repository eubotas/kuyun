package com.kuyun.eam.job;

import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.eam.rpc.api.EamApiService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by user on 2018-06-10.
 */
public abstract class BaseJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(BaseJob.class);

    @Autowired
    protected EamApiService eamApiService;

    private void buildEamApiService(){
        if (eamApiService == null){
            try {
                eamApiService = SpringContextUtil.getBean(EamApiService.class);
            }catch (Exception e){
                _log.error("build EamApiService Error : " + e.getMessage());
            }
        }
    }
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        buildEamApiService();

        runJob();
    }

    protected abstract void runJob();
}
