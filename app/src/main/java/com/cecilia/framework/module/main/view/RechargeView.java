package com.cecilia.framework.module.main.view;

import io.reactivex.annotations.NonNull;

public interface RechargeView {

    void onGetSuccess(@NonNull String payInfo);

    void showAlipayResult(@NonNull String data);

    void onCreateOrderSuccess(@NonNull String orderId);

    void onFailed();
}
