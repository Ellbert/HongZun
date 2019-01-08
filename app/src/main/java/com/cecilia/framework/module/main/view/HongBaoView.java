package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;

import io.reactivex.annotations.NonNull;

public interface HongBaoView {

    void onGetWalletSuccess(@NonNull ShopPaymentBean shopPaymentBean);

    void onRedeliverySuccess();

    void onFinancialRechargeSuccess();

    void onFailed();
}
