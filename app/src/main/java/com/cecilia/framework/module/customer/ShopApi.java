package com.cecilia.framework.module.customer;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ShopApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Shop.GET_WALLET)
    Observable<BaseBean<ShopPaymentBean>> getWallet(@Field("merchantId") int merchantId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Shop.WITHDRAW)
    Observable<BaseBean<Object>> withdraw(@Field("merchantId") int merchantId,@Field("merchantName") String merchantName,@Field("cardId") int cardId,@Field("money") long money,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Shop.GET_RATIO)
    Observable<BaseBean<RateBean>> getRatio(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Shop.WITHDRAW_ALIPAY)
    Observable<BaseBean<Object>> withdrawByAlipay(@Field("merchantId") int merchantId,@Field("merchantName") String merchantName,@Field("money") long money,@Field("account") String account,@Field("realName") String realName,@Field("token") String token);


}
