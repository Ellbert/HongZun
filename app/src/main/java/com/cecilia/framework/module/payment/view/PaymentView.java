package com.cecilia.framework.module.payment.view;

import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface PaymentView {

    void onGetListSuccess(@NonNull List<PaymentBean> data);

    void onGetMerchantListSuccess(@NonNull List<PaymentBean> data);

    void onGetWithdrawListSuccess(@NonNull List<WithdrawBean> data);

    void onFailed();
}
