package com.cecilia.framework.module.cart.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.cart.CartRealization;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.view.SummitOrderView;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class SummitOrderPresenter {

    private SummitOrderView mSummitOrderView;

    public SummitOrderPresenter(SummitOrderView mSummitOrderView) {
        this.mSummitOrderView = mSummitOrderView;
    }

    public void getTemporaryList(int userId, String cartIds) {
        CartRealization.temporaryList(userId, cartIds, new NetworkObserver<List<CartShopBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<CartShopBean> data, String other) {
                mSummitOrderView.onGetListSuccess(data, other);
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
                mSummitOrderView.onFailed();
            }
        });
    }

    public void getAddressList(String userId) {
        MeRealization.getAddressList(userId, new NetworkObserver<List<AddressBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<AddressBean> data, String other) {
                mSummitOrderView.getAddressListSuccess(data);
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
                mSummitOrderView.onFailed();

            }
        });
    }

    public void createOrder(int userId, JSONArray jsonArray, String addressId) {
        CartRealization.createOrder(userId, jsonArray, addressId, new NetworkObserver<ArrayList<Integer>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(ArrayList<Integer> data, String other) {
                LogUtil.e(data.toString());
                mSummitOrderView.onCreateSuccess(data);
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
                mSummitOrderView.onFailed();

            }
        });
    }

    public void createOrder(int userId, int goodsId, String spec, String num, String addressId, String remake) {
        ProductRealization.createOrder(userId, goodsId, spec, num, addressId, remake, new NetworkObserver<ArrayList<Integer>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(ArrayList<Integer> data, String other) {
                mSummitOrderView.onCreateOrderSuccess(data);
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
                mSummitOrderView.onFailed();

            }
        });
    }

}
