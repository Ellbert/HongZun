package com.cecilia.framework.module.product.presenter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.AsynchronousObserver;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.me.MeRealization;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.module.product.bean.PayResult;
import com.cecilia.framework.module.product.model.ProductModel;
import com.cecilia.framework.module.product.view.ProductView;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class ProductPresenter {

    private ProductView mProductView;
    private ProductModel mPayOrderModel;

    public ProductPresenter(ProductView mProductView) {
        this.mProductView = mProductView;
        mPayOrderModel = new ProductModel();
    }

    public void getDetail(int id, int userId) {
        ProductRealization.getGoodsDetail(id, userId, new NetworkObserver<GoodsBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(GoodsBean data, String other) {
                mProductView.getDetailSuccess(data, other);
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
        });
    }

    public void createOrder(int userId, int goodsId, String spec, String num, String addressId, String remake) {
        ProductRealization.createOrder(userId, goodsId, spec, num, addressId, remake, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mProductView.onCreateOrderSuccess(data);
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

    public void doAlipayPay(final Activity activity, final String orderInfo) {
        Observable
                .create(new ObservableOnSubscribe<Map<String, String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<Map<String, String>> e) throws Exception {
                        e.onNext(mPayOrderModel.doAlipayPay(activity, orderInfo));
                    }
                })
                .compose(AsynchronousUtil.<Map<String, String>>setThread())
                .subscribe(new AsynchronousObserver<Map<String, String>>() {
                    @Override
                    protected void onFinish(Map<String, String> data) {
                        LogUtil.e("alipay data = " + data);
                        PayResult payResult = new PayResult(data);
                        LogUtil.e("alipay payResult = " + payResult);
                        mProductView.showAlipayResult(payResult.getResultStatus());
                    }

                    @Override
                    protected void onException(Throwable e) {
                        ToastUtil.newSafelyShow(e.getMessage());
                        mProductView.onFailed();
                    }
                });
    }

    public void buy(String orderIds, int userID, String subject) {
        ProductRealization.buy(orderIds, userID, subject, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                LogUtil.e(data);
                mProductView.onBuySuccess(data);
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
        });
    }
}
