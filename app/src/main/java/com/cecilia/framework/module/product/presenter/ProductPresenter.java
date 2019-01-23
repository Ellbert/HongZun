package com.cecilia.framework.module.product.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.cecilia.framework.module.product.view.ProductView;
import com.cecilia.framework.utils.ToastUtil;
import com.google.gson.JsonObject;

import java.util.List;

public class ProductPresenter {

    private ProductView mProductView;

    public ProductPresenter(ProductView mProductView) {
        this.mProductView = mProductView;
    }

    public void getDetail(int id, int userId) {
        ProductRealization.getGoodsDetail(id, userId, new NetworkObserver<GoodsBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(GoodsBean data, String other) {
                mProductView.getDetailSuccess(data);
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
                mProductView.onFailed();
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
                mProductView.getAddressListSuccess(data);
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
                mProductView.onFailed();
            }
        });
    }

    public void addCart(JsonObject object) {
        ProductRealization.addCart(object, new NetworkObserver<Object>() {

            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mProductView.onAddCartSuccess();
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
                mProductView.onFailed();
            }
        });
    }

    public void addCollect(int userId, int goodsId, String goodsTitle, String pic, String price) {
        ProductRealization.addCollect(userId, goodsId, goodsTitle, pic, price, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mProductView.onAddCollectSuccess();
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
                mProductView.onFailed();
            }
        });
    }


    public void removeCollect(int userId, int goodsId) {
        ProductRealization.removeCollect(userId, goodsId, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mProductView.onRemoveCollectSuccess();
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
                mProductView.onFailed();
            }
        });
    }

    public void getRecentlyList(int goodsId) {
        ProductRealization.getCommentList(goodsId, new NetworkObserver<List<CommentBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(List<CommentBean> data, String other) {
                mProductView.onGetRecentlyListSuccess(data);
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
                mProductView.onFailed();
            }
        });
    }

    public void removeConcern(int id, int shopId) {
        ProductRealization.removeFollow(id, shopId, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mProductView.onRemoveFollowSuccess();
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
                mProductView.onFailed();
            }
        });
    }

    public void addFollow(int userId, int shopId, String name, String url) {
        ProductRealization.addFollow(userId, shopId, name, url, new NetworkObserver<Object>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(Object data, String other) {
                mProductView.onAddFollowSuccess();
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
                mProductView.onFailed();
            }
        });
    }
}
