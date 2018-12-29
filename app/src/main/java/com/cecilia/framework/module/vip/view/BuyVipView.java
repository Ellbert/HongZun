package com.cecilia.framework.module.vip.view;

import com.cecilia.framework.module.vip.bean.VipOrderBean;

import io.reactivex.annotations.NonNull;

public interface BuyVipView {

    void onCreateOrder(@NonNull VipOrderBean orderBean);

    void onGetSuccess(@NonNull String payInfo);

    void showAlipayResult(@NonNull String data);

    void onFailed();
}
