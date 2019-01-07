package com.cecilia.framework.module.customer.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.customer.ShopRealization;
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.view.WithdrawView;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class WithdrawPresenter {

    private WithdrawView mWithdrawView;

    public WithdrawPresenter(WithdrawView mWithdrawView) {
        this.mWithdrawView = mWithdrawView;
    }

    public void getList(final SwipeRefreshLayout swipeRefreshLayout, String userId) {
        MeRealization.getBankCardList(userId, new NetworkObserver<List<BankCardBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<BankCardBean> data,String other) {
                mWithdrawView.onGetListSuccess(data);
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

            }
        });
    }

    public void getRatio(){
        ShopRealization.getRatio(new NetworkObserver<RateBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(RateBean data, String other) {
                mWithdrawView.onGetRatioSuccess(data);
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

            }
        });
    }

    public void withdraw(int merchantId, String merchantName, int cardId, long money){
        ShopRealization.withdraw(merchantId, merchantName, cardId, money, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mWithdrawView.onWithdrawSuccess();
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

            }
        });
    }

}
