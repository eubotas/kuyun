package com.kuyun.eam.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2018-03-28.
 */
@Component
public class ProductLineShirtStatisticJob extends BaseJob {

    private static Logger _log = LoggerFactory.getLogger(ProductLineShirtStatisticJob.class);


    @Override
    protected void runJob() {
        if (eamApiService != null){
            _log.info("******Start Statistic Job********");
            eamApiService.productLineShirtStatisticJob();
            _log.info("******End Statistic Job**********");
        }
    }
}
