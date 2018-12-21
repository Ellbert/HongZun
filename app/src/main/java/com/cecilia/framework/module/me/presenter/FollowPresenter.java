package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.module.me.view.FollowView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class FollowPresenter {

    private FollowView mFollowView;

    public FollowPresenter(FollowView mFollowView) {
        this.mFollowView = mFollowView;
    }

    public void getFollowList(final SwipeRefreshLayout swipeRefreshLayout, String userId){
        MeRealization.getFollowList(userId, new NetworkObserver<List<FollowBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<FollowBean> data,String other) {
                mFollowView.onGetListSuccess(data);
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

    public void removeConcern(int id){
        MeRealization.removeConcern(id, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mFollowView.onRemoveSuccess();
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
