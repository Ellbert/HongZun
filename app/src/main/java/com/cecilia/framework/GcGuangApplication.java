package com.cecilia.framework;

import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.multidex.MultiDexApplication;
import android.util.Config;
import android.util.DisplayMetrics;
import android.util.Log;

import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;

import java.lang.reflect.Field;

public class GcGuangApplication extends MultiDexApplication {

    private static Context sContext;
    private static Handler sHandler;
    private static long sMainThreadId;
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

        initDisplayOpinion();
        super.onCreate();

//        //友盟分享
//        Config.DEBUG = true;
//        UMShareAPI.get(this);

        UMConfigure.setLogEnabled(true);
        try {
            Class<?> aClass = Class.forName("com.umeng.commonsdk.UMConfigure");
            Field[] fs = aClass.getDeclaredFields();
            for (Field f:fs){
                Log.e("xxxxxx","ff="+f.getName()+"   "+f.getType().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "59892f08310c9307b60023d0", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "669c30a9584623e70e8cd01b0381dcb4");
        //PushSDK初始化(如使用推送SDK，必须调用此方法)
//        initUpush();

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
////        UMShareConfig config = new UMShareConfig();
////        config.isNeedAuthOnGetUserInfo(false);
////        config.isOpenShareEditActivity(true);
////        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
////        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
////        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
//    }

//    //友盟分享
//    private void initUmengShare(){
//        /**
//         * 如果项目的Manifest文件中已经配置【友盟+】的AppKey和Channel，则使用该方法初始化，同时不必再次传入AppKey和Channel两个参数。
//         */
//        PlatformConfig.setWeixin("wx3232432432", "ferfefre5324432fref3rwf");
//        PlatformConfig.setSinaWeibo("34432423dfew", "gfgreerrerererwe","http://sns.whalecloud.com");
//        PlatformConfig.setQQZone("3223354321", "r234r432ferfrwewe");
//        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(false);
//        config.isOpenShareEditActivity(true);
//        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
//        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_WEBVIEW);
//        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
//    }

    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");

    }


    public static int getHeight() {
        return sHeight;
    }

    public static int getWidth() {
        return sWidth;
    }

    public static int getId() {
        return sId;
    }

    public static void setId(int sId) {
        GcGuangApplication.sId = sId;
    }
}
