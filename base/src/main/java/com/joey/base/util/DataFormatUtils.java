package com.joey.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataFormatUtils
 *
 * @author Joey 2013-8-24
 */
public class DataFormatUtils {

    /**
     * 几种日期formater
     **/
    public static final String FORMATTER_DATE_AND_TIME0 = "MM/dd/yyyy HH:mm:ss";
    public static final String FORMATTER_DATE_AND_TIME1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_DATE_AND_TIME2 = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATTER_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_DATE = "yyyy-MM-dd";
    public static final String FORMATTER_TIME_ = "HH-mm-ss";
    public static final String FORMATTER_TIME = "HH:mm:ss";
    public static final String FORMATTER_DATE_AND_TIME_CH = "yyyy年MM月dd日 HH:mm:ss EEEE";
    public static final String FORMATTER_YEAR_AND_MONTH_CH = "yyyy年MM月";
    public static final String FORMATTER_YEAR_AND_MONTH_DAY = "yyyy年MM月dd日";

    private DataFormatUtils() {
        throw new AssertionError();
    }


    /**
     * long time to string, format
     *
     * @param timeInMillis
     * @param format
     * @return
     */
    public static String getTime(long timeInMillis, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        return formatDate.format(new Date(timeInMillis));
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format
     *
     * @param
     * @return
     */
    public static String getCurrentTimeInString(String format) {
        return getTime(getCurrentTimeInLong(), format);
    }

    /**
     * 由日期的String格式得到毫秒
     * @param dateStr
     * @param format
     * @return
     */
    public static long convertDateStrToMillis(String dateStr, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        try {
            Date date = formatDate.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 把毫秒转化为时间
     * @param time
     * @param timerFormat
     * @return
     */
    public static String convertMillisToHHmmss(long time, String timerFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(timerFormat);
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 日期转化为String
     * @param date
     * @param format
     * @return
     */
    public static String convertDateToStr(Date date, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        return formatDate.format(date);
    }

    /**
     * String转化为日期
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convertStrToDate(String dateStr, String format) {
        SimpleDateFormat formatDate = new SimpleDateFormat(format);
        try {
            Date date = formatDate.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
