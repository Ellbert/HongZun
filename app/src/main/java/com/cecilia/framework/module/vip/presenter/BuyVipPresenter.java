package com.cecilia.framework.module.vip.presenter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.AsynchronousObserver;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.product.bean.PayResult;
import com.cecilia.framework.module.vip.VipRealization;
import com.cecilia.framework.module.vip.bean.VipOrderBean;
import com.cecilia.framework.module.vip.model.BuyVipModel;
import com.cecilia.framework.module.vip.view.BuyVipView;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class BuyVipPresenter {

    private BuyVipView mBuyVipView;
    private BuyVipModel mBuyVipModel;

    public BuyVipPresenter(BuyVipView mBuyVipView) {
        this.mBuyVipView = mBuyVipView;
        mBuyVipModel = new BuyVipModel();
    }

    public void createOrder(int userId, int cardId) {
        VipRealization.createOrder(userId, cardId, new NetworkObserver<VipOrderBean>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(VipOrderBean data, String other) {
                mBuyVipView.onCreateOrder(data);
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

    public void buy(int userId, int cardId, String subject) {
        VipRealization.buy(userId, cardId, subject, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mBuyVipView.onGetSuccess(data);
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

    public void doAlipayPay(final Activity activity, final String orderInfo) {
        Observable
                .create(new ObservableOnSubscribe<Map<String, String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<Map<String, String>> e) throws Exception {
                        e.onNext(mBuyVipModel.doAlipayPay(activity, orderInfo));
                    }
                })
                .compose(AsynchronousUtil.<Map<String, String>>setThread())
                .subscribe(new AsynchronousObserver<Map<String, String>>() {
                    @Override
                    protected void onFinish(Map<String, String> data) {
                        LogUtil.e("alipay data = " + data);
                        PayResult payResult = new PayResult(data);
                        LogUtil.e("alipay payResult = " + payResult);
                        mBuyVipView.showAlipayResult(payResult.getResultStatus());
                    }

                    @Override
                    protected void onException(Throwable e) {
                        ToastUtil.newSafelyShow(e.getMessage());
                        mBuyVipView.onFailed();
                    }
                });
    }
}
