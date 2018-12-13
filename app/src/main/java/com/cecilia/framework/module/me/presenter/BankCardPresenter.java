package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.BankCardView;
import com.cecilia.framework.utils.ToastUtil;

public class BankCardPresenter {

    private BankCardView mBankCardView;

    public BankCardPresenter(BankCardView mBankCardView) {
        this.mBankCardView = mBankCardView;
    }

    public void saveBankCard(String userId, String id, String bankCardNum, String bankType, String isDefault){
        MeRealization.saveBankCard(userId, id, bankCardNum, bankType, isDefault, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mBankCardView.onSaveSuccess();
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
