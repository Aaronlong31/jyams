package com.jyams.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期操作辅助类
 * 
 * @author zhanglong
 * 
 */
public final class DateTimeUtils {

    /** * 每秒钟的毫秒数 */
    public static final int SECOND = 1000;
    /** * 每分钟的毫秒数 */
    public static final int MINUTES = SECOND * 60;
    /** * 每小时的毫秒数 */
    public static final int HOUR = MINUTES * 60;
    /** * 每天的毫秒数 */
    public static final int DAY = HOUR * 24;
    /** * 每周的毫秒数 */
    public static final int WEEK = DAY * 7;

    /**
     * 默认的格式化日期
     */
    private static final String DEFAULT_FULL_DATE = "yyyy-MM-dd";

    private DateTimeUtils() {
    }

    /**
     * 判断是否是同一天
     * 
     * @param firstTime
     *            ms
     * @param secondTime
     *            ms
     * @return
     */
    public static boolean isSameDay(long firstTime, long secondTime) {
        Date date1 = new Date();
        date1.setTime(firstTime);
        Date date2 = new Date();
        date2.setTime(secondTime);
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 将字符串的日期转换成int型，eg. 2010-09-09 -> 20100909
     * 
     * @param stringDate
     * @return
     */
    public static Integer convertStringToInteger(String stringDate) {
        try {
            if (StringUtils.isBlank(stringDate)) {
                return null;
            }
            return Integer.parseInt(stringDate.replaceAll("-", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串的日期转换成时间戳，eg. 2010-09-09 -> 1300000000000
     * 
     * @param stringDate
     * @return
     */
    public static Long convertStringToLong(String stringDate) {
        return convertStringToLong(stringDate, DEFAULT_FULL_DATE);
    }

    /**
     * 将字符串的日期转换成时间戳，eg. 2010-09-09 -> 1300000000000
     * 
     * @param stringDate
     * @return
     */
    public static Long convertStringToLong(String stringDate, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(stringDate).getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将字符串的日期转换成时间戳，eg. 1300000000000 -> 2010-09-09
     * 
     * @param stringDate
     * @return
     */
    public static String convertLongToString(Long timestamp) {
        return convertLongToString(timestamp, DEFAULT_FULL_DATE);
    }

    /**
     * 将字符串的日期转换成时间戳
     * 
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String convertLongToString(Long timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = new Date();
            date.setTime(timestamp);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将int的日期转换成字符串，eg.20100909 -> 2010-09-09
     * 
     * @param integerDate
     * @return
     */
    public static String convertIntegerDayToString(Integer integerDate) {
        if (integerDate == null) {
            return null;
        }
        String iDate = integerDate.toString();
        try {
            return iDate.substring(0, 4) + "-" + iDate.substring(4, 6) + "-"
                    + iDate.substring(6, 8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将int的日期转换成字符串，eg.20100909 -> 2010-09-09
     * 
     * @param integerDate
     * @return
     */
    public static String convertIntegerMonthToString(Integer integerMonth) {
        if (integerMonth == null) {
            return null;
        }
        String iDate = integerMonth.toString();
        try {
            return iDate.substring(0, 4) + "-" + iDate.substring(4, 6);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将时间戳转化为int型时间,eg. 1287494615088 -> 2010
     * 
     * @param timestamp
     * @return
     */
    public static Integer getDayFromTimestamp(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        date.setTime(timestamp);
        String format = sdf.format(date);
        return Integer.parseInt(format);
    }

    /**
     * 将时间戳转化为int型时间,eg. 1287494615088 -> 2010
     * 
     * @param timestamp
     * @return
     */
    public static Integer getYearFromTimestamp(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        date.setTime(timestamp);
        String format = sdf.format(date);
        return Integer.parseInt(format);
    }

    /**
     * 将字符串时间转换成分钟数 ， eg.12:30 -> 750 (12 * 60 + 30 )
     * 
     * @param stringTime
     * @return
     */
    public static Integer convertStringToMinute(String stringTime) {
        try {
            String[] split = stringTime.split(":");
            if (split.length != 2) {
                return null;
            }
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            return hour * 60 + minute;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串时间转换成分钟数 ，eg. 750 (12 * 60 + 30 ) -> 12:30
     * 
     * @param stringTime
     * @return
     */
    public static String convertMinuteToString(Integer time) {
        try {
            int hour = time / 60;
            int minute = time % 60;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取指定天的下一天
     * 
     * @param stringDate
     *            格式：yyyy-MM-dd
     * @return 格式：yyyy-MM-dd
     */
    public static String getNextDate(String stringDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(stringDate);
            date.setTime(date.getTime() + 24 * 60 * 60 * 1000);
            return sdf.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 得到某天的开始毫秒
     */
    public static Long getDayStartTimestamp(String stringDate) {
        return DateTimeUtils.convertStringToLong(stringDate);
    }

    /**
     * 得到某天的结束毫秒
     */
    public static Long getDayEndTimestamp(String stringDate) {
        Long timeStart = DateTimeUtils.convertStringToLong(stringDate);
        if (timeStart != null) {
            return timeStart + DateTimeUtils.DAY - 1;
        }
        return null;
    }
}
