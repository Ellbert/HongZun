package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.view.MoreView;
import com.cecilia.framework.utils.LogUtil;

import java.util.List;

public class MorePresenter {

    private MoreView mMoreView;

    public MorePresenter(MoreView mMoreView) {
        this.mMoreView = mMoreView;
    }

    public void getCategoryData(final SwipeRefreshLayout swipeRefreshLayout) {
        HomeRealization.getMoreListData(new NetworkObserver<List<MoreListBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<MoreListBean> data) {
               mMoreView.getDataSuccess(data);
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
