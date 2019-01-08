package com.cecilia.framework.module.main.presenter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.AsynchronousObserver;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.main.HomeRealization;
import com.cecilia.framework.module.main.model.RechargeModel;
import com.cecilia.framework.module.main.view.RechargeView;
import com.cecilia.framework.module.product.bean.PayResult;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RechargePresenter {

    private RechargeModel mRechargeModel;
    private RechargeView mRechargeView;

    public RechargePresenter(RechargeView mRechargeView) {
        this.mRechargeView = mRechargeView;
        this.mRechargeModel = new RechargeModel();
    }

    public void createOrder(int userId, long money) {
        HomeRealization.createOrder(userId, money, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mRechargeView.onCreateOrderSuccess(data);
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
                mRechargeView.onFailed();
            }
        });
    }

    public void buy(String orderIds, int userID, String subject) {
        HomeRealization.buy(orderIds, userID, subject, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mRechargeView.onGetSuccess(data);
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
                mRechargeView.onFailed();
            }
        });
    }

    public void doAlipayPay(final Activity activity, final String orderInfo) {
        Observable
                .create(new ObservableOnSubscribe<Map<String, String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<Map<String, String>> e) throws Exception {
                        e.onNext(mRechargeModel.doAlipayPay(activity, orderInfo));
                    }
                })
                .compose(AsynchronousUtil.<Map<String, String>>setThread())
                .subscribe(new AsynchronousObserver<Map<String, String>>() {
                    @Override
                    protected void onFinish(Map<String, String> data) {
                        LogUtil.e("alipay data = " + data);
                        PayResult payResult = new PayResult(data);
                        LogUtil.e("alipay payResult = " + payResult);
                        mRechargeView.showAlipayResult(payResult.getResultStatus());
                    }

                    @Override
                    protected void onException(Throwable e) {
                        ToastUtil.newSafelyShow(e.getMessage());
//                        mRechargeView.onFailed();
                    }
                });
    }
}
