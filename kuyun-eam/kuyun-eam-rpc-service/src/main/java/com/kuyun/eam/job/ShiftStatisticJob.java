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
public class ShiftStatisticJob extends BaseJob {

    private static Logger _log = LoggerFactory.getLogger(ShiftStatisticJob.class);

    @Override
    protected void runJob() {
        if (eamApiService != null){
            _log.info("******Start Shift Statistic Job********");
            eamApiService.shiftStatisticJob();
            _log.info("******End Shift Statistic Job**********");
        }
    }
}
