package com.cecilia.framework.module.payment.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.module.payment.PaymentRealization;
import com.cecilia.framework.module.payment.view.PaymentDetailView;
import com.cecilia.framework.utils.ToastUtil;

public class PaymentDetailPresenter {

    private PaymentDetailView mPaymentDetailView;

    public PaymentDetailPresenter(PaymentDetailView mPaymentDetailView) {
        this.mPaymentDetailView = mPaymentDetailView;
    }

    public void getDetail(int id) {
        PaymentRealization.getDetail(id, new NetworkObserver<MessageBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(MessageBean data, String other) {
                mPaymentDetailView.onGetSuccess(data);
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
                mPaymentDetailView.onFailed();
            }
        });
    }
}
