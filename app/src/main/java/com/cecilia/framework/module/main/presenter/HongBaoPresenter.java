package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.view.HongBaoView;
import com.cecilia.framework.utils.ToastUtil;

public class HongBaoPresenter {

    private HongBaoView mHongBaoView;

    public HongBaoPresenter(HongBaoView mHongBaoView) {
        this.mHongBaoView = mHongBaoView;
    }

    public void getWallet(int userId) {
        HomeRealization.getWallet(userId, new NetworkObserver<ShopPaymentBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(ShopPaymentBean data, String other) {
                mHongBaoView.onGetWalletSuccess(data);
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
                mHongBaoView.onFailed();
            }
        });
    }

    public void financialRecharge(int userId, int money) {
        HomeRealization.financialRecharge(userId, money, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mHongBaoView.onFinancialRechargeSuccess();
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
                mHongBaoView.onFailed();
            }
        });
    }

    public void redelivery(int userId, int money) {
        HomeRealization.redelivery(userId, money, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mHongBaoView.onRedeliverySuccess();
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
                mHongBaoView.onFailed();
            }
        });
    }
}
