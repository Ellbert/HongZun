package com.cecilia.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.cecilia.framework.BuildConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author stone
 */

public class NetworkUtil {

    /**
     * 订阅中的观察者对象列表
     */
    private static List<Disposable> sDisposables = new ArrayList<>();

    private static final long HTTP_TIME = 30;
    private static NetworkUtil sNetworkUtil;
    private final Retrofit mRetrofit;
    private final NetworkInterceptor mNetworkInterceptor;

    private NetworkUtil() {
        mNetworkInterceptor = new NetworkInterceptor();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .connectTimeout(HTTP_TIME, TimeUnit.SECONDS) // 设置连接超时时间
                        .readTimeout(HTTP_TIME, TimeUnit.SECONDS) // 设置读取超时时间
                        .writeTimeout(HTTP_TIME, TimeUnit.SECONDS) // 设置写入超时时间
                        .addInterceptor(mNetworkInterceptor) // 设置拦截器
                        .build())
                .build();
    }

    /**
     * 获取网络请求实例
     */
    public static NetworkUtil getInstance() {
        if (sNetworkUtil == null) {
            synchronized (NetworkUtil.class) {
                if (sNetworkUtil == null) {
                    sNetworkUtil = new NetworkUtil();
                }
            }
        }
        return sNetworkUtil;
    }

    public NetworkInterceptor getInterceptor() {
        return mNetworkInterceptor;
    }

    /**
     * 设置网络接口API
     */
    public <T> T setApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public static void clearObserver() {
        if (sDisposables != null && sDisposables.size() > 0) {
            for (Disposable disposable : sDisposables) {
                disposable.dispose();
            }
            sDisposables.clear();
        }
    }

    public static void addObserver(Disposable disposable) {
        sDisposables.add(disposable);
    }

    public static void removeObserver(Disposable disposable) {
        sDisposables.remove(disposable);
    }

    /**
     * 判断选择使用的是不是WiFi网络
     */
    public static boolean isWifi() {
        NetworkInfo activeNetInfo = ((ConnectivityManager) ViewUtil.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * OkHttp拦截器，用于给网络请求添加请求头
     */
    public static class NetworkInterceptor implements Interceptor {

        private String mSessionId = "";
//        private String mToken = "3c1120f99f9a411ab77e03a0fa0ed212";
//        private String mContentType = "application/x-www-form-urlencoded";

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            return chain.proceed(chain.request().newBuilder()
//                    .addHeader("Cookie", "PHPSESSID=" + mSessionId) // 设置请求头Cookie里的SessionId
//                    .addHeader("Token", mToken) // 设置请求头token
//                    .addHeader("Content-Type", mContentType) // 设置请求头token
                    .build());
        }

        public void setSessionId(String sessionId) {
            mSessionId = sessionId;
        }
    }
}
