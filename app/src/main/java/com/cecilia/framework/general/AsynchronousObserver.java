package com.cecilia.framework.general;

import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author stone
 */

public abstract class AsynchronousObserver<T> implements Observer<T> {

    private Disposable mDisposable;

    @Override
    public final void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
        AsynchronousUtil.addObserver(disposable);
    }

    @Override
    public final void onNext(@NonNull T data) {
        onAsynchronousEnd();
        onFinish(data);
    }

    @Override
    public final void onError(@NonNull Throwable e) {
        LogUtil.e("Asynchronous error = " + e.toString());
        onAsynchronousEnd();
        onException(e);
    }

    @Override
    public void onComplete() {
    }

    /**
     * 异步操作结束后调用
     */
    private void onAsynchronousEnd() {
        DialogUtil.dismissLoadingDialog();
        AsynchronousUtil.removeObserver(mDisposable);
    }

    /**
     * 异步操作结束
     */
    protected abstract void onFinish(@NonNull T data);

    /**
     * 异步操作出现异常
     */
    protected abstract void onException(@NonNull Throwable e);

}
