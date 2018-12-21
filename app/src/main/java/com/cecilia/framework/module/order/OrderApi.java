package com.cecilia.framework.module.order;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.order.bean.OrderDetailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Order.ORDER_DETAIL)
    Observable<BaseBean<OrderDetailBean>> orderDetail(@Field("orderId") int orderId);
}
