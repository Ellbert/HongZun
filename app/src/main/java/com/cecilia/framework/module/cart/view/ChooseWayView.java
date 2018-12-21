package com.cecilia.framework.module.cart.view;

import io.reactivex.annotations.NonNull;

public interface ChooseWayView {

    void onGetSuccess(@NonNull String payInfo);

    void showAlipayResult(@NonNull String data);

    void onFailed();
}
