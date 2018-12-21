package com.cecilia.framework.module.cart.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.cart.CartRealization;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;
import com.cecilia.framework.module.cart.view.CartView;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

public class CartPresenter {

    private CartView mCartView;

    public CartPresenter(CartView mCartView) {
        this.mCartView = mCartView;
    }

    public void getCartList(SwipeRefreshLayout swipeRefreshLayout, int userId) {
        CartRealization.getCartList(userId, new NetworkObserver<List<CartShopBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<CartShopBean> data, String other) {
                mCartView.onGetCartList(data);
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

    public void getFailedList(final SwipeRefreshLayout swipeRefreshLayout, int userId) {
        CartRealization.getLostList(userId, new NetworkObserver<List<FailedGoodsBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return swipeRefreshLayout;
            }

            @Override
            protected void onSuccess(List<FailedGoodsBean> data, String other) {
                mCartView.onGetFailedList(data);
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

    public void delete(String cartIds){
        CartRealization.deleteCart(cartIds, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mCartView.onDeleteSuccess();
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

    public void updateNumber(int cartId ,String type){
        CartRealization.updateNumber(cartId, type, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mCartView.onUpdateSuccess();
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
