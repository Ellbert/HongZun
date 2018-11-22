package com.cecilia.framework.utils;

import android.widget.Toast;


/**
 * Toast统一管理类
 */
public class ToastUtil {

    private static Toast sToast;

    private ToastUtil() {
        throw new AssertionError();
    }

    /**
     * 整个项目都可以使用的短时间显示Toast
     */
    @SuppressWarnings("all")
    public static void newShow(String message) {
        if (sToast == null) {
            sToast = Toast.makeText(ViewUtil.getContext(), message, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(message);
        }
        sToast.show();
    }

    /**
     * 整个项目都可以使用的长时间显示Toast
     */
    @SuppressWarnings("all")
    public static void newShowLong(String message) {
        if (sToast == null) {
            sToast = Toast.makeText(ViewUtil.getContext(), message, Toast.LENGTH_LONG);
        } else {
            sToast.setText(message);
        }
        sToast.show();
    }

    /**
     * 整个项目都可以使用的，线程安全的长时间显示Toast
     */
    public static void newSafelyShow(final String message) {
        ViewUtil.postTaskInMain(new Runnable() {
            @Override
            public void run() {
                newShow(message);
            }
        });
    }

    /**
     * 整个项目都可以使用的，线程安全的长时间显示Toast
     */
    public static void newSafelyShowLong(final String message) {
        ViewUtil.postTaskInMain(new Runnable() {
            @Override
            public void run() {
                newShowLong(message);
            }
        });
    }

}
