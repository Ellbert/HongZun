package com.cecilia.framework.utils;

import android.util.Log;

/**
 * @author stone
 */
public class LogUtil {

    private static boolean sIsLog = true;
    private static String sTag = "Cecilia";

    private LogUtil() {
        throw new AssertionError();
    }

    public static void e(String msg) {
        if (sIsLog)
            e(sTag, msg);
    }

    public static void v(String msg) {
        if (sIsLog)
            v(sTag, msg);
    }

    public static void d(String msg) {
        if (sIsLog)
            d(sTag, msg);
    }

    public static void i(String msg) {
        if (sIsLog)
            i(sTag, msg);
    }

    public static void w(String msg) {
        if (sIsLog)
            w(sTag, msg);
    }

    public static void e(String tag, String msg) {
        if (sIsLog)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (sIsLog)
            Log.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (sIsLog)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (sIsLog)
            Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (sIsLog)
            Log.w(tag, msg);
    }

}
