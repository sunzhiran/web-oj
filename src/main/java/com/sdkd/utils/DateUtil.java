package com.sdkd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by zhiran.sun on 2017/5/8.
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化日期，timepattern
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat(TIME_PATTERN).format(date);
    }

    /**
     * 解析日期
     * @param when
     * @return
     */
    public static Date parseDate(String when) {
        Date result = null;
        try {
            result = new SimpleDateFormat(TIME_PATTERN).parse(when);
        } catch (ParseException e) {
        }
        return result;
    }

    /**
     * 解析日期，返回时间戳
     * @param when
     * @return
     */
    public static Timestamp parseTimestamp(String when) {
        Timestamp result = null;
        try {
            result = new Timestamp((new SimpleDateFormat(TIME_PATTERN).parse(when)).getTime());
        } catch (ParseException e) {
        }
        return result;
    }

}
