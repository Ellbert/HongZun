package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.view.MeView;
import com.cecilia.framework.utils.ToastUtil;

public class MePresenter {

    private MeView mMeView;

    public MePresenter(MeView mMeView) {
        this.mMeView = mMeView;
    }

    public void getUserInfo(final SwipeRefreshLayout swipeRefreshLayout, int id) {
        HomeRealization.getUserInfo(id, new NetworkObserver<UserBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(UserBean data,String other) {
                mMeView.onGetUserInfoSuccess(data,other);
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
                mMeView.onGetUserInfoFail();
            }
        });
    }

    public void getMessageCount(int userId){
        HomeRealization.getMessageCount(userId, new NetworkObserver<Integer>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Integer data, String other) {
                mMeView.onGetMessageCountSuccess(data);
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
                mMeView.onGetUserInfoFail();
            }
        });
    }
}
