package com.cecilia.framework.module.customer.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.customer.ShopRealization;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.module.customer.view.ShopPaymentView;

public class ShopPaymentPresenter {

    private ShopPaymentView mShopPaymentView;

    public ShopPaymentPresenter(ShopPaymentView mShopPaymentView) {
        this.mShopPaymentView = mShopPaymentView;
    }

    public void getWallet(final SwipeRefreshLayout swipeRefreshLayout, int merchantId) {
        ShopRealization.getWallet(merchantId, new NetworkObserver<ShopPaymentBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(ShopPaymentBean data, String other) {
                mShopPaymentView.onGetWalletSuccess(data);
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

            @Override
            protected void onLoginTimeOut() {
                mShopPaymentView.onFailed();
            }
        });
    }
}
