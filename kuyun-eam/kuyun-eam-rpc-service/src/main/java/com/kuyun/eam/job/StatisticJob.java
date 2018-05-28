package com.kuyun.eam.job;

import com.kuyun.common.util.SpringContextUtil;
import com.kuyun.eam.rpc.api.EamApiService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 2018-03-28.
 */
@Component
public class StatisticJob extends QuartzJobBean {

    private static Logger _log = LoggerFactory.getLogger(StatisticJob.class);

    @Autowired
    private EamApiService eamApiService;

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
        _log.info("******Execute StatisticJob********");
        buildEamApiService();

        if (eamApiService != null){
            _log.info("******Start Statistic Job********");
            String date = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
            eamApiService.statisticJob(date);
            _log.info("******End Statistic Job**********");
        }
    }
}
