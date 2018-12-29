package com.cecilia.framework.module.payment;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PaymentApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Payment.USER_PAYMENT_LIST)
    Observable<BaseBean<List<PaymentBean>>> paymentList(@Field("userId") int userId, @Field("type") int type, @Field("page") int page);

    @FormUrlEncoded
    @POST(NetworkConstant.Payment.MERCHANT_PAYMENT_LIST)
    Observable<BaseBean<List<PaymentBean>>> merchantPaymentList(@Field("merchantId") int merchantId, @Field("type") int type, @Field("page") int page);

    @FormUrlEncoded
    @POST(NetworkConstant.Payment.WITHDRAW_RECORD)
    Observable<BaseBean<List<WithdrawBean>>> withdrawRecord(@Field("merchantId") int merchantId, @Field("page") int page);
}
