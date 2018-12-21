package com.cecilia.framework.module.cart.presenter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.AsynchronousObserver;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.cart.CartRealization;
import com.cecilia.framework.module.cart.model.ChooseWayModel;
import com.cecilia.framework.module.cart.view.ChooseWayView;
import com.cecilia.framework.module.product.bean.PayResult;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class ChooseWayPresenter {

    private ChooseWayView mChooseWayView;
    private ChooseWayModel mPayOrderModel;

    public ChooseWayPresenter(ChooseWayView mChooseWayView) {
        this.mChooseWayView = mChooseWayView;
        mPayOrderModel = new ChooseWayModel();
    }

    public void buy(String orderIds, int userID, String subject) {
        CartRealization.buy(orderIds, userID, subject, new NetworkObserver<String>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(String data, String other) {
                mChooseWayView.onGetSuccess(data);
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
                        mChooseWayView.showAlipayResult(payResult.getResultStatus());
                    }

                    @Override
                    protected void onException(Throwable e) {
                        ToastUtil.newSafelyShow(e.getMessage());
                        mChooseWayView.onFailed();
                    }
                });
    }
}
