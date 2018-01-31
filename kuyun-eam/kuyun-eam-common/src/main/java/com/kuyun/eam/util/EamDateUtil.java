package com.kuyun.eam.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EamDateUtil {
    private static String defaultFormat="yyyy/MM/dd";

    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static Date getDateAfterMinute(Date d, int minu) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + minu);
        return now.getTime();
    }

    public static Date getDate(int year, int month, int day) {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DATE, day);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }


    public static String getDateStr(Date d) {
        return getDateStr(d, null);
    }

    public static String getDateStr(Date d, String format) {
        if(format == null )
            format=defaultFormat;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }
}
