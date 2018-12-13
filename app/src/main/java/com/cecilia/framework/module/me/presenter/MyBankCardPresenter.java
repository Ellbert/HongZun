package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.view.MyBankCardView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class MyBankCardPresenter {

    private MyBankCardView mMyBankCardView;

    public MyBankCardPresenter(MyBankCardView mMyBankCardView) {
        this.mMyBankCardView = mMyBankCardView;
    }

    public void getList(final SwipeRefreshLayout swipeRefreshLayout, String userId) {
        MeRealization.getBankCardList(userId, new NetworkObserver<List<BankCardBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<BankCardBean> data,String other) {
                mMyBankCardView.onGetListSuccess(data);
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

    public void deleteBankCard(String id) {
        MeRealization.deleteBankCard(id, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mMyBankCardView.onDeleteSuccess();
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
