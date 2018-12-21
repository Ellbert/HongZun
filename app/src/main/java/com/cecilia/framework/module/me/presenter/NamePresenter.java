package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.NameView;
import com.cecilia.framework.utils.ToastUtil;

public class NamePresenter {

    private NameView mNameView;

    public NamePresenter(NameView mNameView) {
        this.mNameView = mNameView;
    }

    public void updateName(String userId, String type, String info) {
        MeRealization.getUserInfo(userId, type, info, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mNameView.onUpdateSuccess();
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
