package com.kuyun.eam.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 2018-03-28.
 */
@Component
public class StatisticJob extends BaseJob {

    private static Logger _log = LoggerFactory.getLogger(StatisticJob.class);

    @Override
    protected void runJob() {
        if (eamApiService != null){
            _log.info("******Start Statistic Job********");
            String date = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
            eamApiService.statisticJob(date);
            _log.info("******End Statistic Job**********");
        }
    }
}
