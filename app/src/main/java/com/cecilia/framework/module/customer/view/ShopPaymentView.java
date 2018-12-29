package com.cecilia.framework.module.customer.view;

import com.cecilia.framework.module.customer.bean.ShopPaymentBean;

import io.reactivex.annotations.NonNull;

public interface ShopPaymentView {

    void onGetWalletSuccess(@NonNull ShopPaymentBean shopPaymentBean);

    void onFailed();
}
