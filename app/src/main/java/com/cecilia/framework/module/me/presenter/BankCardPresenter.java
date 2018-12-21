package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.BankBean;
import com.cecilia.framework.module.me.view.BankCardView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class BankCardPresenter {

    private BankCardView mBankCardView;

    public BankCardPresenter(BankCardView mBankCardView) {
        this.mBankCardView = mBankCardView;
    }

    public void saveBankCard(int userId, String username, int bankId, String cardNum, String isDefault){
        MeRealization.saveBankCard(userId, username, bankId, cardNum, isDefault, new NetworkObserver<Object>() {

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

    public void getBankList(){
        MeRealization.getBankList(new NetworkObserver<List<BankBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<BankBean> data, String other) {
                mBankCardView.onGetBankListSuccess(data);
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
