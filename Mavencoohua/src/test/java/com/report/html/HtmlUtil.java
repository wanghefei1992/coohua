package com.report.html;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlUtil {
    public HtmlUtil() {
    }

    public static String getPercnet(double p_numerator, double p_denominator) {
        double percent = p_numerator / p_denominator;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(1);
        return nt.format(percent);
    }

    public static String getCalcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

    public static String formatCurrentTime() {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date resultdate = new Date(System.currentTimeMillis());
        return date_format.format(resultdate);
    }

    public static String getDeltaTime(long p_startTime, long p_endTime) {
        long day = (p_endTime - p_startTime) / 86400000L;
        long hour = (p_endTime - p_startTime) / 3600000L - day * 24L;
        long min = (p_endTime - p_startTime) / 60000L - day * 24L * 60L - hour * 60L;
        long s = (p_endTime - p_startTime) / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        return day + "天" + hour + "小时" + min + "分" + s + "秒";
    }

    public static void sleep(int p_time) {
        try {
            Thread.sleep((long)(p_time * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}
