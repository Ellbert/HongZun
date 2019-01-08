package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.FinancialBean;
import com.cecilia.framework.module.main.view.FinancialView;
import com.cecilia.framework.utils.ToastUtil;

public class FinancialPresenter {

    private FinancialView mFinancialView;

    public FinancialPresenter(FinancialView mFinancialView) {
        this.mFinancialView = mFinancialView;
    }

    public void getUserArrange(final SwipeRefreshLayout swipeRefreshLayout, int userId) {
        HomeRealization.getUserArrange(userId, new NetworkObserver<FinancialBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(FinancialBean data, String other) {
                mFinancialView.onGetSuccess(data);
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
                mFinancialView.onFailed();
            }
        });
    }
}
