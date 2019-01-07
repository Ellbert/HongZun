package com.cecilia.framework.module.order.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.order.OrderRealization;
import com.cecilia.framework.module.order.bean.OrderDetailBean;
import com.cecilia.framework.module.order.view.OrderDetailView;
import com.cecilia.framework.utils.ToastUtil;

public class OrderPresenter {

    private OrderDetailView mOrderDetailView;

    public OrderPresenter(OrderDetailView mOrderDetailView) {
        this.mOrderDetailView = mOrderDetailView;
    }

    public void getOrderDetail(int orderId){
        OrderRealization.orderDetail(orderId, new NetworkObserver<OrderDetailBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(OrderDetailBean data, String other) {
                mOrderDetailView.onGetDetailSuccess(data);
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
                mOrderDetailView.onGetFailed();
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
                mOrderDetailView.onDeleteSuccess();
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
                mOrderDetailView.onGetFailed();
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
                mOrderDetailView.onReceiveSuccess();
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
                mOrderDetailView.onGetFailed();
            }
        });
    }

    public void cancelOrder(int orderId){
        HomeRealization.cancelOrder(orderId, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mOrderDetailView.onCancelSuccess();
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
                mOrderDetailView.onGetFailed();
            }
        });
    }

}
