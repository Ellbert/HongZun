package com.cecilia.framework.module.cart;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CartApi {


    @FormUrlEncoded
    @POST(NetworkConstant.Cart.FIND_LIST)
    Observable<BaseBean<List<CartShopBean>>> findCartList(@Field("userId") int userId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.LOST_LIST)
    Observable<BaseBean<List<FailedGoodsBean>>> findFailedList(@Field("userId") int userId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.DELETE_CART)
    Observable<BaseBean<Object>> deleteCart(@Field("cartIds") String cartIds,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.UPDATE_NUMBER)
    Observable<BaseBean<Object>> updateNumber(@Field("cartId") int cartId, @Field("type") String type,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.TEMPORARY_LIST)
    Observable<BaseBean<List<CartShopBean>>> temporaryList(@Field("userId") int userId, @Field("cartIds") String cartIds,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.CREATE_ORDER_BY_CART)
    Observable<BaseBean<ArrayList<Integer>>> createOrder(@Field("userId") int userId, @Field("params") JSONArray jsonArray, @Field("addressId") String addressId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.BUY)
    Observable<BaseBean<String>> buy(@Field("orderIds") String orderIds,@Field("userId") int userId,@Field("subject") String subject,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.BUY_BU_HONG_BAO)
    Observable<BaseBean<Object>> buyByHongBao(@Field("orderIds") String orderIds,@Field("userId") int userId,@Field("token") String token);

}
