package com.cecilia.framework.module.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.main.view.OrderListView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class OrderListPresenter {

    private OrderListView mOrderListView;

    public OrderListPresenter(OrderListView mOrderListView) {
        this.mOrderListView = mOrderListView;
    }

    public void getList(final SwipeRefreshLayout swipeRefreshLayout, int userId, int type, int page) {
        HomeRealization.geOrderList(userId, type, page, new NetworkObserver<List<OrderBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<OrderBean> data, String other) {
                mOrderListView.onGetListSuccess(data);
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

    public void deleteOrder(int orderId) {
        HomeRealization.deleteOrder(orderId, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mOrderListView.onDeleteSuccess();
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

    public void receiveOrder(int orderId) {
        HomeRealization.receive(orderId, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mOrderListView.onReceiveSuccess();
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
