package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    /**
     * 时间格式化（yyyy-MM-dd HH:mm:ss），线程安全的
     */
    public static ThreadLocal<DateFormat> format = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    /**
     * 时间格式化（yyyy-MM-dd），线程安全的
     */
    public static ThreadLocal<DateFormat> formatyyyyMMdd = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /**
     * 时间格式化（HH:mm:ss），线程安全的
     */
    public static ThreadLocal<DateFormat> formatHHmmss = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss"));

    /**
     * @return 获取当前时间
     */
    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-HH-dd HH:mm:ss"));
    }

    /**
     * @param pattern 格式化字符串
     * @return 获取当前时间
     */
    public static String getNow(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * @return 获取当前时间戳
     */
    public static Long getTick() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * @param timeStamp 时间戳
     * @return 将时间戳转换成时间
     */
    public static Date toDate(long timeStamp) {
        return new Date(timeStamp);
    }

    /**
     * @param date  时间
     * @param years 要增加的年
     * @return 增加后的时间
     */
    public static Date addYears(Date date, int years) {
        return getDate(date, Calendar.YEAR, years);
    }

    /**
     * @param date 时间
     * @param day  要增加的天数
     * @return 增加后的时间
     */
    public static Date addDays(Date date, int day) {
        return getDate(date, Calendar.DATE, day);
    }

    /**
     * @param date  时间
     * @param hours 要增加的小时
     * @return 增加后的时间
     */
    public static Date addHours(Date date, int hours) {
        return getDate(date, Calendar.HOUR, hours);
    }

    /**
     * @param date    时间
     * @param minutes 要增加的分钟
     * @return 增加后的时间
     */
    public static Date addMinutes(Date date, int minutes) {
        return getDate(date, Calendar.MINUTE, minutes);
    }

    /**
     * @param date    时间
     * @param seconds 要增加的秒数
     * @return 增加后的时间
     */
    public static Date addSeconds(Date date, int seconds) {
        return getDate(date, Calendar.SECOND, seconds);
    }

    /**
     * @param date         时间
     * @param milliseconds 要增加的耗秒数
     * @return 增加后的时间
     */
    public static Date addMilliSeconds(Date date, int milliseconds) {
        return getDate(date, Calendar.MILLISECOND, milliseconds);
    }

    private static Date getDate(Date date, int field, int space) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, space);
        return calendar.getTime();
    }
}
