package com.cecilia.framework.module.login.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.login.LoginRealization;
import com.cecilia.framework.module.login.view.LoginView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.ArrayList;

public class LoginPresenter {

    private LoginView mLoginView;

    public LoginPresenter(LoginView mHomeView) {
        this.mLoginView = mHomeView;
    }

    public void getSms(String phone,String type) {
        LoginRealization.getSMS(phone,type, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mLoginView.getSmsSuccess();
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
        });
    }

    public void register(String phone, String code, String invitationCode, String password) {
        LoginRealization.register(phone, code, invitationCode, password, new NetworkObserver<UserBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(UserBean data,String other) {
                mLoginView.registerSuccess();
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
        });
    }

    public void retrieve(String phone, String code, String password) {
        LoginRealization.retrieve(phone, code, password, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mLoginView.retrieveSuccess();
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
        });
    }

    public void login(String phone, String password) {
        LoginRealization.login(phone, password, new NetworkObserver<UserBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(UserBean data,String other) {
                mLoginView.loginSuccess(data,other);
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
        });
    }
}
