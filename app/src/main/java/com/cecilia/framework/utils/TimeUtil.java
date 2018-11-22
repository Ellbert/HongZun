package com.cecilia.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author law
 */

public class TimeUtil {

    public static String formatLongSecondToTimeStr(Long sec){
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = sec.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        if (hour > 24) {
            day = hour / 24;
            hour = hour % 24;
        }
        String dayStr = String.valueOf(day);
        String hourStr = String.valueOf(hour);
        String minStr = String.valueOf(minute);
        String secStr = String.valueOf(second);
        if (second < 10) {
            secStr = "0" + second;
        }
        if (minute < 10) {
            minStr = "0" + minute;
        }
        if (hour < 10) {
            hourStr = "0" + hour;
        }
        if (day < 10) {
            dayStr = "0" + day;
        }
        return dayStr + "天" + hourStr + "时" + minStr + "分" + secStr + "秒";
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timeDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }
}
