package com.cecilia.framework.module.payment.view;

import com.cecilia.framework.module.me.bean.MessageBean;

import io.reactivex.annotations.NonNull;

public interface PaymentDetailView {

    void onGetSuccess(@NonNull MessageBean messageBean);

    void onFailed();
}
