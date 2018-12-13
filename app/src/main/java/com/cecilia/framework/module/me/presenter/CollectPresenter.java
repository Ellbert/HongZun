package com.cecilia.framework.module.me.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.presenter.MePresenter;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.view.CollectView;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class CollectPresenter {

    private CollectView mNameView;

    public CollectPresenter(CollectView mNameView) {
        this.mNameView = mNameView;
    }

    public void getList(final SwipeRefreshLayout swipeRefreshLayout, String userId) {
        MeRealization.getCollectList(userId, new NetworkObserver<List<BaseGoodBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<BaseGoodBean> data,String other) {
                LogUtil.e(data.size() + " == size");
                mNameView.onGetSuccess(data);
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

    public void removeCollect(String id){
        MeRealization.removeCollect(id, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data,String other) {
                mNameView.onDeleteSuccess();
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
