package com.kuyun.eam.util;

import com.kuyun.common.util.DateUtil;
import com.kuyun.common.util.NumberUtil;
import com.kuyun.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by user on 2018-04-13.
 */
public class TicketUtil {

    private static Logger _log = LoggerFactory.getLogger(TicketUtil.class);

    /**
     *
     * @return YYYYMMDD+Number ext: 2018040110
     */
    public static String generatorTicketNumber(){
        String result = null;

        String currentDate = DateUtil.getCurrentDate();
        String ticketNumber = RedisUtil.get(currentDate);
        if (StringUtils.isEmpty(ticketNumber)){
            ticketNumber = currentDate + "1";
            result = ticketNumber;
            RedisUtil.set(currentDate, ticketNumber);
        }else {
            String strNumber = ticketNumber.substring(8);
            Integer number = NumberUtil.toInteger(strNumber);
            result = currentDate + (++number);
            RedisUtil.set(currentDate, result);

        }

        return result;
    }
}
