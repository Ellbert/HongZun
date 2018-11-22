package com.cecilia.framework.utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author stone
 */

public class AsynchronousUtil {

    private AsynchronousUtil() {
        throw new AssertionError();
    }

    /**
     * 订阅中的观察者对象列表
     */
    private static List<Disposable> sDisposables = new ArrayList<>();

    /**
     * 设置异步操作在IO线程后回到UI线程
     */
    public static <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io()) // 请求进行时，将其放置于IO线程
                        .observeOn(AndroidSchedulers.mainThread()); // 请求完成后，将其放置于UI线程
            }
        };
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

}
