package com.cecilia.framework.module.main;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.module.main.bean.VersionBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HomeApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Home.GET_USER_INFO)
    Observable<BaseBean<UserBean>> getUserInfo(@Field("userId") int id, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.RECOMMEND_LIST)
    Observable<BaseBean<List<GoodsBean>>> getRecommendList(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Home.CHECK_SHOP_STATUS)
    Observable<BaseBean<ShopBean>> shopStatus(@Field("merchantId") String id, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.DELETE_ORDER)
    Observable<BaseBean<Object>> deleteOrder(@Field("orderId") int orderId, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.ORDER_LIST)
    Observable<BaseBean<List<OrderBean>>> getOrderList(@Field("userId") int id, @Field("type") int type, @Field("page") int page, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.CANCEL_ORDER)
    Observable<BaseBean<Object>> cancelOrder(@Field("orderId") int orderId, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.RECEIVE_ORDER)
    Observable<BaseBean<Object>> receiveOrder(@Field("orderId") int orderId, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Promotion.LIST)
    Observable<BaseBean<List<AdvertisingBean>>> promotionList(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Promotion.LAST_NOTICE)
    Observable<BaseBean<NoticeBean>> lastNotice(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Version.GET_VERSION)
    Observable<BaseBean<VersionBean>> getVersion(@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Promotion.NOTICE_LIST)
    Observable<BaseBean<List<NoticeBean>>> noticeList(@Field("page") int page, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Promotion.GET_QR_CODE)
    Observable<BaseBean<String>> getQrCode(@Field("userId") int userId, @Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Message.MESSAGE_COUNT)
    Observable<BaseBean<Integer>> getMessageCount(@Field("userId") int userId, @Field("token") String token);


    @FormUrlEncoded
    @POST(NetworkConstant.Goods.SUBMIT_COMMENT)
    Observable<BaseBean<Object>> submitComment(@Field("userId") int userId, @Field("username") String username, @Field("headurl") String headurl, @Field("orderId") int orderId, @Field("goodsId") int goodsId, @Field("type") int type, @Field("comment") String comment, @Field("img") String img, @Field("token") String token);


}
