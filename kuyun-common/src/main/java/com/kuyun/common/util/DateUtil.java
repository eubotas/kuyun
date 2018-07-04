package com.kuyun.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
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

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String getCurrentDate(){
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        return formatter.format(LocalDate.now());
    }

    public static Date getFirstDateOfMonth(int year, int month){
        YearMonth yearMonth = YearMonth.of( year, month );
        LocalDate firstOfMonth = yearMonth.atDay( 1 );
        return asDate(firstOfMonth);
    }

    public static Date getLastDateOfMonth(int year, int month){
        YearMonth yearMonth = YearMonth.of( year, month );
        LocalDate lastOfMonth = yearMonth.atEndOfMonth();
        return asDate(lastOfMonth);
    }
}
