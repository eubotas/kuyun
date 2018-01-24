package com.kuyun.common.util;

import java.util.Date;

/**
 * Created by user on 2018-01-21.
 */
public class DateUtil {

    public static boolean isDateBetween(Date argDate, Date startDate, Date endDate){
        boolean result = false;
        if (argDate != null && startDate != null && endDate != null){
            result =  argDate.after(startDate) && argDate.before(endDate);
        }
        return result;
    }
}
