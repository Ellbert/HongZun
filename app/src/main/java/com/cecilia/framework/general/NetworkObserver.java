package com.cecilia.framework.general;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.BuildConfig;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.cecilia.framework.common.NetworkConstant.SUCCESS;
import static com.cecilia.framework.common.NetworkConstant.SUCCESS_NO;

/**
 * @author stone
 */

public abstract class NetworkObserver<T> implements Observer<BaseBean<T>> {

    private Disposable mDisposable;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public final void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
        NetworkUtil.addObserver(disposable);
        mSwipeRefreshLayout = getSwipeRefreshLayout();
        if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    /**
     * 将下拉刷新的SwipeRefreshLayout传入（代码中就不需要去设置是否刷新成功了）<br/>
     * 不需要下拉刷新操作的话，传null就可以了
     */
    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();

//    /**
//     * 将上拉加载的Adapter传入（代码中就不需要去设置是否刷新成功了）<br/>
//     * 不需要上拉加载操作的话，传null就可以了
//     */
//    protected abstract BaseLmrvAdapter getBaseLmrvAdapter();

    @Override
    public final void onNext(@NonNull BaseBean<T> bean) {
        onNetworkEnd();
        if (bean == null) {
            onException(new Throwable("获取到的bean为空"));
            return;
        }
        if (bean.getResult() != SUCCESS ) {
            LogUtil.e("bean.getResult() == " + bean.getResult());
            LogUtil.e("bean.getMsg() == " + bean.getMsg());
            onFailure(bean.getResult(), bean.getMsg());
            return;
        }
        onSuccess(bean.getData());
    }

    @Override
    public final void onError(@NonNull Throwable e) {
        onNetworkEnd();
        LogUtil.e("Network error = " + e.toString());
        if (e instanceof SocketTimeoutException) {
            ToastUtil.newShow("网络连接超时");
            onTimeout();
        } else {
            ToastUtil.newShow(BuildConfig.ERROR_MSG);
            onException(e);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 网络请求结束后调用
     */
    private void onNetworkEnd() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        DialogUtil.dismissLoadingDialog();
        NetworkUtil.removeObserver(mDisposable);
    }

    /**
     * 网络请求成功，并得到数据
     */
    protected abstract void onSuccess(@NonNull T data);

    /**
     * 网络请求成功，但没得到需要的数据
     *
     * @param errorCode 后台返回的错误码
     */
    protected abstract void onFailure(@NonNull int errorCode, @NonNull String errorMsg);

    /**
     * 网络请求出现异常
     */
    protected abstract void onException(@NonNull Throwable e);

    /**
     * 网络请求超时
     */
    protected abstract void onTimeout();

}
