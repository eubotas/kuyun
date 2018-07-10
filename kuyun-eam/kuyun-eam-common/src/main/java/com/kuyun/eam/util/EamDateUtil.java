package com.kuyun.eam.util;

import com.kuyun.eam.common.constant.ProductLineShift;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EamDateUtil {

    private static Logger _log = LoggerFactory.getLogger(EamDateUtil.class);

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
        if(format == null ) {
            format = defaultFormat;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(d);
    }

    public static Date convertDate(String date, String format) throws java.text.ParseException {
        if(format == null ) {
            format=defaultFormat;
        }
        return org.apache.commons.lang.time.DateUtils.parseDate(date,  new String[]{format});
    }

    public static int getDayToLast(Date dt){
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int last = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return last - day;
    }

    public static Pair<Date,Date> getShiftStartEndTime(String date) throws java.text.ParseException{
        Pair<Date, Date> pair = null;
        Date startDate = org.apache.commons.lang.time.DateUtils.parseDate(date + " " + "00:00:00", new String[]{"yyyy-MM-dd HH:mm:ss"});
        Date endDate = org.apache.commons.lang.time.DateUtils.parseDate(date + " " + "23:59:59", new String[]{"yyyy-MM-dd HH:mm:ss"});
        pair = new Pair<>(startDate, endDate);
        return pair;
    }

    public static Pair<Date,Date> getShiftStartEndTime(String shiftCode, String date, String startHourMinute, String endHourMinute){
        Pair<Date, Date> pair = null;
        try {
            Date startDate = org.apache.commons.lang.time.DateUtils.parseDate(date + " " + startHourMinute + ":00", new String[]{"yyyy-MM-dd HH:mm:ss"});
            Date endDate = org.apache.commons.lang.time.DateUtils.parseDate(date + " " + endHourMinute + ":00", new String[]{"yyyy-MM-dd HH:mm:ss"});

            if (endDate.before(startDate)) {
                /**
                 * 早班 中班 跨天 减 1 天
                 */
                if (ProductLineShift.MORNING.match(shiftCode) || ProductLineShift.MIDDLE.match(shiftCode)){
                    startDate = getDateAfter(startDate, -1);
                }else if (ProductLineShift.NIGHT.match(shiftCode)){
                    /**
                     * 晚班 跨天 加 1 天
                     */
                    endDate = getDateAfter(endDate, 1);
                }
            }

            pair = new Pair<>(startDate, endDate);
        } catch (ParseException e) {
            _log.error("Error: {}", e.getMessage());
        }
        return pair;
    }

    public static boolean inThisTimes(String shiftCode, String startHourMinute, String endHourMinute){
        if(startHourMinute == null || endHourMinute==null) {
            return false;
        }
        Date now =new Date();
        String strDate = getDateStr(now, "yyyy-MM-dd");
        try {
            Date startDate = org.apache.commons.lang.time.DateUtils.parseDate(strDate + " " + startHourMinute, new String[]{"yyyy-MM-dd HH:mm"});
            Date endDate = org.apache.commons.lang.time.DateUtils.parseDate(strDate + " " + endHourMinute, new String[]{"yyyy-MM-dd HH:mm"});

            if (endDate.before(startDate)) {
                /**
                 * 早班 中班 跨天 减 1 天
                 */
                if (ProductLineShift.MORNING.match(shiftCode) || ProductLineShift.MIDDLE.match(shiftCode)){
                    startDate = getDateAfter(startDate, -1);
                }else if (ProductLineShift.NIGHT.match(shiftCode)){
                    /**
                     * 晚班 跨天 加 1 天
                     */
                    endDate = getDateAfter(endDate, 1);
                }
            }

            if(now.after(startDate) && now.before(endDate)) {
                return true;
            }
        }catch(Exception ex){
            _log.error("Error: {}", ex.getMessage());
        }
        return false;
    }

}
