package com.cecilia.framework.module.vip.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.vip.VipRealization;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.module.vip.view.VipView;

import java.util.List;

public class VipPresenter {

    private VipView mVipView;

    public VipPresenter(VipView mVipView) {
        this.mVipView = mVipView;
    }

    public void vipList() {
        VipRealization.vipList(new NetworkObserver<List<VipBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<VipBean> data, String other) {
                mVipView.onGetListSuccess(data);
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
                mVipView.onFailed();
            }
        });
    }
}
