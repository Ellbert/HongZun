package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.login.LoginRealization;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.PayPasswordView;
import com.cecilia.framework.utils.ToastUtil;

public class PayPasswordPresenter {

    private PayPasswordView mPayPasswordView;

    public PayPasswordPresenter(PayPasswordView mPayPassword) {
        this.mPayPasswordView = mPayPassword;
    }

    public void getSms(String phone, String type) {
        LoginRealization.getSMS(phone, type, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mPayPasswordView.getSmsSuccess();
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {
                ToastUtil.newSafelyShow(errorMsg);
            }

            @Override
            protected void onException(Throwable e) {
//                mLoginView.getFail();
            }

            @Override
            protected void onTimeout() {
//                mLoginView.getFail();
            }

            @Override
            protected void onLoginTimeOut() {
                mPayPasswordView.getFail();
            }
        });
    }

    public void setPayPassword(int userId, String code, String password) {
        MeRealization.setPayPassword(userId, code, password, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mPayPasswordView.onSetSuccess();
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
                mPayPasswordView.getFail();
            }
        });
    }
}
