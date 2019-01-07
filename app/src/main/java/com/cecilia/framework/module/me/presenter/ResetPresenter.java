package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.ResetView;
import com.cecilia.framework.utils.ToastUtil;

public class ResetPresenter {

    private ResetView mResetView;

    public ResetPresenter(ResetView mResetView) {
        this.mResetView = mResetView;
    }

    public void resetPassword(String userId,String oldPwd,String pwd){
        MeRealization.updatePassword(userId, oldPwd, pwd, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mResetView.onResetSuccess();
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
                mResetView.onResetFailed();
            }
        });
    }
}
