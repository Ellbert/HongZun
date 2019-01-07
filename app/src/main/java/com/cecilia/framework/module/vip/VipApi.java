package com.cecilia.framework.module.vip;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.module.vip.bean.VipOrderBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface VipApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Vip.VIP_CARD_LIST)
    Observable<BaseBean<List<VipBean>>> vipList(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Vip.CREATE_ORDER)
    Observable<BaseBean<VipOrderBean>> createOrder(@Field("userId") int userId, @Field("cardId") int cardId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Vip.BUY_VIP)
    Observable<BaseBean<String>> buy(@Field("userId") int userId, @Field("orderId") int orderId, @Field("subject") String subject,@Field("token") String token);
}
