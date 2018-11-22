package com.cecilia.framework.utils;

import android.content.Context;
import android.text.TextUtils;

import com.cecilia.framework.common.SharedPreferenceConstant;
import com.cecilia.framework.general.UserBean;

import static com.cecilia.framework.utils.SharedPreferenceUtil.getBoolean;
import static com.cecilia.framework.utils.SharedPreferenceUtil.getString;
import static com.cecilia.framework.utils.SharedPreferenceUtil.putBoolean;
import static com.cecilia.framework.utils.SharedPreferenceUtil.putString;

/**
 * @author stone
 */
public class GuangUtil {

    //private static String account;
    private static String sSignKey;
    private static String sDesKey;
    private static UserBean sUser;
    private static Boolean sIsFirst;
    //private static boolean sIsVisitorLogin;
    private static String sSessionId;
    private static Boolean sIsLogin;
    private static Boolean sIsLoginInOther;
    private static String sPayPassword;
    private static Boolean sIsManualLoadPhoto;

    private GuangUtil() {
        throw new AssertionError();
    }

    //public static void storeAccount(Context context, Account account) {
    //    saveBaseObject(context, account, SharedPreferenceConstant.GcGuang.USER_ACCOUNT);
    //}

    //public static Account getAccount(Context context) {
    //    return (Account) loadBaseObject(context, SharedPreferenceConstant.GcGuang.USER_ACCOUNT);
    //}

    public static void saveSignKey(Context context, String signKey) {
        sSignKey = signKey;
        putString(context, SharedPreferenceConstant.GcGuang.SIGN_KEY, signKey);
    }

    public static String loadSignKey(Context context) {
        if (TextUtils.isEmpty(sSignKey)) {
            sSignKey = getString(context, SharedPreferenceConstant.GcGuang.SIGN_KEY);
        }
        return sSignKey;
    }

    public static void saveDesKey(Context context, String desKey) {
        sDesKey = desKey;
        putString(context, SharedPreferenceConstant.GcGuang.DES_KEY, desKey);
    }

    public static String loadDesKey(Context context) {
        if (TextUtils.isEmpty(sDesKey)) {
            sDesKey = getString(context, SharedPreferenceConstant.GcGuang.DES_KEY);
        }
        return sDesKey;
    }

    public static void saveUserInfo(UserBean user) {
        sUser = user;
        FileUtil.saveBaseObject(user, SharedPreferenceConstant.GcGuang.USER_INFO);
    }

    /**
     * 存储用户信息
     */
    public static UserBean loadUserInfo() {
        if (sUser == null) {
            sUser = (UserBean) FileUtil.loadBaseObject(SharedPreferenceConstant.GcGuang.USER_INFO);
        }
        return sUser;
    }

    /**
     * 保存用户是否第一次登陆信息
     */
    public static void saveFirstMessage(Context context, boolean isFirst) {
        sIsFirst = isFirst;
        putBoolean(context, SharedPreferenceConstant.GcGuang.IS_FIRST, isFirst);
    }

    /**
     * 获取用户是否第一次登陆信息
     */
    public static boolean loadFirstMessage(Context context) {
        if (sIsFirst == null) {
            sIsFirst = getBoolean(context, SharedPreferenceConstant.GcGuang.IS_FIRST, true);
        }
        return sIsFirst;
    }

    //public static void saveIsVisitorLogin(Context context, boolean isLogin) {
    //    putBoolean(context, SharedPreferenceConstant.GcGuang.VISITOR_IS_LOGIN, isLogin);
    //}

    //public static boolean loadIsVisitorLogin(Context context) {
    //    return getBoolean(context, SharedPreferenceConstant.GcGuang.VISITOR_IS_LOGIN, true);
    //}

    public static void saveSessionId(Context context, String sessionId) {
        sSessionId = sessionId;
        putString(context, SharedPreferenceConstant.GcGuang.JSESSIONID, sessionId);
    }

    public static String loadSessionId(Context context) {
        if (TextUtils.isEmpty(sSessionId)) {
            sSessionId = getString(context, SharedPreferenceConstant.GcGuang.JSESSIONID, "5253F66BB099425A717BF3802099E2EE");
        }
        return sSessionId;
    }

    /**
     * 保存登录状态
     */
    public static void saveIsLogin(Context context, boolean isLogin) {
        if (isLogin == sIsLogin) {
            return;
        }
        sIsLogin = isLogin;
        putBoolean(context, SharedPreferenceConstant.GcGuang.IS_LOGIN, isLogin);
    }

    public static boolean loadIsLogin(Context context) {
        if (sIsLogin == null) {
            sIsLogin = getBoolean(context, SharedPreferenceConstant.GcGuang.IS_LOGIN);
        }
        return sIsLogin;
    }

    /**
     * 保存账号是否在其他设备登录状态
     */
    public static void saveIsLoginInOther(Context context, boolean isLogin) {
        if (isLogin == sIsLoginInOther) {
            return;
        }
        sIsLoginInOther = isLogin;
        putBoolean(context, SharedPreferenceConstant.GcGuang.IS_LOGIN_IN_OTHER, isLogin);
    }

    public static boolean loadIsLoginInOther(Context context) {
        if (sIsLoginInOther == null) {
            sIsLoginInOther = getBoolean(context, SharedPreferenceConstant.GcGuang.IS_LOGIN_IN_OTHER);
        }
        return sIsLoginInOther;
    }

    /**
     * 保存支付密码
     */
    public static void savePayPassword(Context context, String payPassword) {
        if (payPassword.equals(sPayPassword)) {
            return;
        }
        sPayPassword = payPassword;
        putString(context, SharedPreferenceConstant.GcGuang.PAY_PASSWORD, payPassword);
    }

    /**
     * 取出支付密码并销毁保存数据
     */
    /*public static String loadPayPassword(Context context) {
        SharedPreferences share = context.getSharedPreferences(SharedPreferenceConstant.GcGuang.PAY_PASSWORD, context.MODE_PRIVATE);
        String pay = share.getString(SharedPreferenceConstant.GcGuang.PAY_PASSWORD, "masi11");
        SharedPreferences.Editor editorPay = share.edit();
        editorPay.clear();
        editorPay.apply();
        return pay;
    }*/
    public static String loadPayPassword(Context context) {
        if (TextUtils.isEmpty(sPayPassword)) {
            sPayPassword = getString(context, SharedPreferenceConstant.GcGuang.PAY_PASSWORD, null);
        }
        return sPayPassword;
    }

    /**
     * 保存非WiFi环境下是否手动下载图片
     */
    public static void saveIsManualLoadPhotoInNotWiFi(Context context, boolean isManualLoadPhoto) {
        sIsManualLoadPhoto = isManualLoadPhoto;
        putBoolean(context, SharedPreferenceConstant.GcGuang.IS_MANUAL_LOAD_PHOTO_IN_NOT_WIFI, isManualLoadPhoto);
    }

    public static boolean loadIsManualLoadPhotoInNotWiFi(Context context) {
        if (sIsManualLoadPhoto == null) {
            sIsManualLoadPhoto = getBoolean(context, SharedPreferenceConstant.GcGuang.IS_MANUAL_LOAD_PHOTO_IN_NOT_WIFI);
        }
        return sIsManualLoadPhoto;
    }

}
