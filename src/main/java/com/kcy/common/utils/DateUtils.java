package com.kcy.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd）
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }


    public static Date getDateStart(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断字符串是否是日期
     *
     * @param timeString
     * @return
     */
    public static boolean isDate(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(timeString);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @return
     */
    public static String dateFormat(Date timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(timestamp);
    }

    /**
     * 获取系统时间Timestamp
     *
     * @return
     */
    public static Timestamp getSysTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取系统时间Date
     *
     * @return
     */
    public static Date getSysDate() {
        return new Date();
    }

    /**
     * 生成时间随机数
     *
     * @return
     */
    public static String getDateRandom() {
        String s = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return s;
    }

    /**
     * 获取当前的年份
     * @return int  年份
     * */
    public static int getJustYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.DAY_OF_MONTH);
        return year;
    }

    /**
     * 获取当前的月份
     * @return int  月份
     * */
    public static int getJustMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.MONTH) + 1;
        return year;
    }

    /**
     * 获取当前多少号
     * @return int  号
     * */
    public static int getJustDay() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.DAY_OF_MONTH);
        return year;
    }

    /**
     * 获取当前时间的String格式
     * @param separator 分隔符 例:/ -
     * @return String
     * */
    public static String getTimePaperFormat(String separator) {
        Calendar c = Calendar .getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(Misc.isStringEmpty(separator)) {
            return year + "-" + (month+1) + "-" + day;
        }
        return year + separator + (month+1) + separator + day;
    }

    /**
     * 获取当前的毫秒数
     * @return Long
     * */
    public static Long getSystemCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 时间字符串转时间类
     * @param time 时间字符串(2018-11-22)
     * @return Date date类
     * */
    public static Date stringToDate(String time) {
        String pattern = "[0-9]{1,4}[-][0-9]{1,2}[-][0-9]{1,2}";
        boolean result = Pattern.matches(pattern, time);
        if(!result) {
            return null;
        }
        SimpleDateFormat dataUtils= new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dataUtils.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间字符串转时间类 自定义分隔符
     * @param time 时间字符串(2018-11-22)
     * @param separator 分隔符
     * @return Date date类
     * */
    public static Date stringToDate(String time, String separator) {
        String pattern = "[0-9]{1,4}[-][0-9]{1,2}[-][0-9]{1,2}";
        boolean result = Pattern.matches(pattern, time);
        if(!result) {
            return null;
        }
        SimpleDateFormat dataUtils= new SimpleDateFormat("yyyy"+separator+"MM"+separator+"dd");
        try {
            return dataUtils.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间字符串转时间类 带时分秒
     * @param time 时间字符串(2018-11-22 10:24:55)
     * @return Date date类
     * */
    public static Date stringToDateSelect(String time) {
        String pattern = "[0-9]{1,4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}";
        boolean result = Pattern.matches(pattern, time);
        if(!result) {
            return null;
        }
        SimpleDateFormat dataUtils= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dataUtils.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
