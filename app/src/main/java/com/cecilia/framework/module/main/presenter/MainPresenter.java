package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.VersionBean;
import com.cecilia.framework.module.main.view.MainView;
import com.cecilia.framework.utils.ToastUtil;

public class MainPresenter {

    private MainView mMainView;

    public MainPresenter(MainView mMainView) {
        this.mMainView = mMainView;
    }

    public void getVersion() {
        HomeRealization.getVersion(new NetworkObserver<VersionBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(VersionBean data, String other) {
                mMainView.onGetVersionSuccess(data);
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }

            @Override
            protected void onLoginTimeOut() {
                mMainView.onFailed();
            }
        });
    }
}
