package com.cecilia.framework;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.ViewUtil;

public class GcGuangApplication extends MultiDexApplication {

    private static Context sContext;
    private static Handler sHandler;
    private static long sMainThreadId;
    private static UserBean sUserBean;
    private static int sHeight;
    private static int sWidth;
    private static int sId;

    public static Context getContext() {
        return sContext;
    }

    public static Handler getHandler() {
        return sHandler;
    }

    public static long getMainThreadId() {
        return sMainThreadId;
    }

    @Override
    public void onCreate() {
        //上下文
        sContext = getApplicationContext();
        //主线程
        sHandler = new Handler();
        //主线程的ID
        sMainThreadId = Process.myTid();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        sHeight = dm.heightPixels;
        sWidth = dm.widthPixels;
        sUserBean = GuangUtil.loadUserInfo();

        initDisplayOpinion();
        super.onCreate();

//        //友盟分享
//        Config.DEBUG = true;
//        UMShareAPI.get(this);

        if (sUserBean != null) {
            sId = sUserBean.getTId();
        }
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ViewUtil.sDensity = dm.density;
        ViewUtil.sDensityDPI = dm.densityDpi;
        ViewUtil.sScreenWidth = dm.widthPixels;
        ViewUtil.sScreenHeight = dm.heightPixels;
        ViewUtil.sScreenWidthDp = DensityUtil.px2dp(sContext, dm.widthPixels);
        ViewUtil.sScreenHeightDp = DensityUtil.px2dp(sContext, dm.heightPixels);
    }

//    {
//        PlatformConfig.setWeixin("wxc49f0cb045688a72", "85afceb8eba4912ee87369427e02d079");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setSinaWeibo("3846244037", "6161f63750ddb248e920b98ead2bdf67", "http://www.sina.com");
//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(false);
//        config.isOpenShareEditActivity(true);
//        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
//        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
//        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
//    }


    public static int getHeight() {
        return sHeight;
    }

    public static int getWidth() {
        return sWidth;
    }

    public static UserBean getUserBean() {
        return sUserBean;
    }

    public static int getId() {
        return sId;
    }

    public static void setUserBean(UserBean sUserBean) {
        GcGuangApplication.sUserBean = sUserBean;
    }
}
