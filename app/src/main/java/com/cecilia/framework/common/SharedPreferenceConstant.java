package com.cecilia.framework.common;

/**
 * 本地SharedPreference保存常量类
 *
 * @author stone
 */

public class SharedPreferenceConstant {

    public static final String SHARED_PREFERENCE_FILE_NAME = "sharedPreferenceFileName";

    public class Base{
        public static final String TAG = "FileUtil";
        public static final boolean DEBUG = false;
    }

    public class GcGuang{
        public static final String IS_FIRST = "is_first";//是否是第一次启动app
        public static final String USER_INFO = "user_info";//用户信息
        public static final String JSESSIONID = "jsession_id";

        public static final String SIGN_KEY = "sign_key";//会话signkey
        public static final String DES_KEY = "des_key";//会话3deskey

        public static final String IS_LOGIN = "is_login";//保存是否登录的状态
        public static final String PAY_PASSWORD = "pay_password";//保存支付密码，即存即毁
        public static final String IS_LOGIN_IN_OTHER = "is_login_in_other";//保存账号是否在其他设备登录的状态

        public static final String IS_MANUAL_LOAD_PHOTO_IN_NOT_WIFI = "is_manual_load_photo_in_not_wifi";//非WiFi环境下是否手动下载图片
        public static final String WECHAT_PAY_RESP = "wechat_pay_resp";//微信支付结果返回值

        public static final String MY_INCOME = "my_income";//我的收益

        public static final String IS_FILE_IMAGE_URL = "image_url";//上传文件图片类型
    }

}
