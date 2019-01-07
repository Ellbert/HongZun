package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.FansView;
import com.cecilia.framework.utils.ToastUtil;

public class FansPresenter {

    private FansView mFansView;

    public FansPresenter(FansView mFansView) {
        this.mFansView = mFansView;
    }

    public void getFirstList(int userId,int page){
        MeRealization.firstList(userId, page, new NetworkObserver<PageBean<UserBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(PageBean<UserBean> data, String other) {
                mFansView.onGetFirstListSuccess(data.getList());
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
                mFansView.onFailed();
            }

            @Override
            protected void onException(Throwable e) {
                mFansView.onFailed();
            }

            @Override
            protected void onTimeout() {
                mFansView.onFailed();
            }

            @Override
            protected void onLoginTimeOut() {
                mFansView.onLoginFailed();
            }
        });
    }

    public void getSecondList(int userId,int page){
        MeRealization.secondList(userId, page, new NetworkObserver<PageBean<UserBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(PageBean<UserBean> data, String other) {
                mFansView.onGetSecondListSuccess(data.getList());
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
                mFansView.onFailed();
            }

            @Override
            protected void onException(Throwable e) {
                mFansView.onFailed();
            }

            @Override
            protected void onTimeout() {
                mFansView.onFailed();
            }

            @Override
            protected void onLoginTimeOut() {
                mFansView.onLoginFailed();
            }
        });
    }
}


