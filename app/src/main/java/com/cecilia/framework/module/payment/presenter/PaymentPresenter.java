package com.cecilia.framework.module.payment.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.payment.PaymentRealization;
import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;
import com.cecilia.framework.module.payment.view.PaymentView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class PaymentPresenter {

    private PaymentView mPaymentView;

    public PaymentPresenter(PaymentView mPaymentView) {
        this.mPaymentView = mPaymentView;
    }

    public void paymentList(final SwipeRefreshLayout swipeRefreshLayout, int userId, int type, int page) {
        PaymentRealization.paymentList(userId, type, page, new NetworkObserver<List<PaymentBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<PaymentBean> data, String other) {
                mPaymentView.onGetListSuccess(data);
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

    public void getMerchantList(final SwipeRefreshLayout swipeRefreshLayout, int merchantId, int type, int page) {
        PaymentRealization.merchantPaymentList(merchantId, type, page, new NetworkObserver<List<PaymentBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<PaymentBean> data, String other) {
                mPaymentView.onGetMerchantListSuccess(data);
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

    public void withdrawRecord(final SwipeRefreshLayout swipeRefreshLayout, int merchantId, int page) {
        PaymentRealization.withdrawRecord(merchantId, page, new NetworkObserver<List<WithdrawBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<WithdrawBean> data, String other) {
                mPaymentView.onGetWithdrawListSuccess(data);
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {

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
