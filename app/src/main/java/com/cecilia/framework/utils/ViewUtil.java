package com.cecilia.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;

import java.io.File;

/**
 * @author stone
 */
public class ViewUtil {

    public static int sScreenWidth;
    public static int sScreenHeight;
    public static float sDensity;

    public static int sDensityDPI;
    public static float sScreenWidthDp;
    public static float sScreenHeightDp;

    private static final String TAG_FAKE_STATUS_BAR_VIEW = "tag_fake_status_bar_view";
    private static final String TAG_MARGIN_ADDED = "tag_margin_added";

    private ViewUtil() {
        throw new AssertionError();
    }

    /**
     * 得到应用的全局上下文
     */
    public static Context getContext() {
        return GcGuangApplication.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到String.xml中的字符串
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    /**
     * 得到String.xml中的字符串数组
     */
    public static String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     *
     */
    public static XmlResourceParser getAnimation(@AnimRes int resId) {
        return getResources().getAnimation(resId);
    }

    /**
     * 得到Color.xml中的颜色值
     */
    public static int getColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getColor(resId);
        } else {
            return getResources().getColor(resId);
        }
    }

    /**
     * 得到Drawable或Mipmap文件夹下中的图片文件
     */
    public static Drawable getDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getDrawable(resId);
        } else {
            return getResources().getDrawable(resId);
        }
    }

    /**
     * 得到包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到内部缓存目录
     */
    public static File getCacheDir() {
        return getContext().getCacheDir();
    }

    /**
     * 得到文件目录
     */
    public static File getFilesDir() {
        return getContext().getFilesDir();
    }

    /**
     * 得到外部缓存目录
     */
    public static File getExternalCacheDir() {
        return getContext().getExternalCacheDir();
    }

    /**
     * 得到资源管理器
     */
    public static AssetManager getAssets() {
        return getContext().getAssets();
    }

    /**
     * 得到主线程ID
     */
    public static long getMainThreadId() {
        return GcGuangApplication.getMainThreadId();
    }

    /**
     * 得到主线程的Handler
     */
    public static Handler getHandler() {
        return GcGuangApplication.getHandler();
    }

    /**
     * 将任务放置到主线程（UI线程）中执行
     */
    public static void postTaskInMain(Runnable task) {
        if (Process.myTid() == getMainThreadId()) {
            // 若在主线程中，就直接执行方法
            task.run();
        } else {
            // 若在子线程中，则post到主线程Handler中执行
            getHandler().post(task);
        }
    }

    /**
     * 将任务放置到子线程中执行
     */
    public static void postTaskInSub(Runnable task) {
        if (Process.myTid() == getMainThreadId()) {
            // 若在主线程中，则创建一个子线程去执行
            new Thread(task).run();
        } else {
            // 若在子线程中，就直接执行方法
            task.run();
        }
    }

    /**
     * 修改状态栏颜色（Android 4.4上才能生效）
     *
     * @return 是否能修改状态栏颜色
     */
    public static boolean setStatusBarColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColorInLollipop(activity, color);
            return true;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStatusBarColorInKitkat(activity, color);
            return true;
        } else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void setStatusBarColorInLollipop(Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (color == Color.TRANSPARENT) {
            window.getDecorView().setSystemUiVisibility((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) ?
                    (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR) : View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        window.setStatusBarColor(color);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void setStatusBarColorInKitkat(Activity activity, @ColorInt int color) {
        Window window = activity.getWindow();
        ViewGroup mDecorView = (ViewGroup) window.getDecorView();
        //设置Window为全透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        //获取父布局
        View mContentChild = mContentView.getChildAt(0);
        //如果已经存在假状态栏则移除，防止重复添加
        View fakeView = mDecorView.findViewWithTag(TAG_FAKE_STATUS_BAR_VIEW);
        if (fakeView != null) {
            mDecorView.removeView(fakeView);
        }
        // 不预留系统栏位置
        if (mContentChild != null) {
            ViewCompat.setFitsSystemWindows(mContentChild, false);
        }
        //获取状态栏高度
        int statusBarHeight = getStatusBarHeight();
        //添加一个View来作为状态栏的填充
        View mStatusBarView = new View(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        layoutParams.gravity = Gravity.TOP;
        mStatusBarView.setLayoutParams(layoutParams);
        mStatusBarView.setBackgroundColor(color);
        mStatusBarView.setTag(TAG_FAKE_STATUS_BAR_VIEW);
        mDecorView.addView(mStatusBarView);

        //设置子控件到状态栏的间距
        if (mContentChild != null && !TAG_MARGIN_ADDED.equals(mContentChild.getTag())) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mContentChild.getLayoutParams();
            lp.topMargin += statusBarHeight;
            mContentChild.setLayoutParams(lp);
            mContentChild.setTag(TAG_MARGIN_ADDED);
        }
        if (color != Color.TRANSPARENT) {
            //如果在Activity中使用了ActionBar则需要再将布局与状态栏的高度跳高一个ActionBar的高度，否则内容会被ActionBar遮挡
            int actionBarId = activity.getResources().getIdentifier("action_bar", "id", activity.getPackageName());
            View view = activity.findViewById(actionBarId);
            if (view != null) {
                TypedValue typedValue = new TypedValue();
                if (activity.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
                    int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, activity.getResources().getDisplayMetrics());
                    mContentView.setPadding(0, actionBarHeight, 0, 0);
                }
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置背景透明度
     *
     * @param alpha   透明度（0.0-1.0）
     * @param context 上下文
     */
    public static void setBackgroundAlpha(float alpha, Activity context) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = alpha;
        context.getWindow().setAttributes(lp);
    }

}
