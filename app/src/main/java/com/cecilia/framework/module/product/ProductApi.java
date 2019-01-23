package com.cecilia.framework.module.product;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.GOODS_DETAIL)
    Observable<BaseBean<GoodsBean>> getGoodsDetail(@Field("goodsId") int goodsId, @Field("userId") int userId,@Field("token") String token);
//    Observable<BaseBean<PageBean<ProductListBean>>> getProductListData(@Field("id") String id, @Field("brand_id") String brand_id, @Field("ishot") String ishot, @Field("page") String page, @Field("size") String size);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.BUY)
    Observable<BaseBean<String>> buy(@Field("orderIds") String orderIds, @Field("userId") int userId, @Field("subject") String subject,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.ADD_CART)
    Observable<BaseBean<Object>> addCart(@Field("data") JsonObject object,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.ADD_COLLECT)
    Observable<BaseBean<Object>> addCollect(@Field("userId") int userId, @Field("goodsId") int goodsId, @Field("goodsTitle") String goodsTitle, @Field("pic") String pic, @Field("price") String price,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.REMOVE_PRODUCT_COLLECT)
    Observable<BaseBean<Object>> removeCollect(@Field("userId") int userId, @Field("goodsId") int goodsId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.COMMENT_LIST)
    Observable<BaseBean<PageBean<CommentBean>>> commentList(@Field("goodsId") int goodsId, @Field("type") int type, @Field("page") int page,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.RECENTLY_LIST)
    Observable<BaseBean<List<CommentBean>>> recentlyList(@Field("goodsId") int goodsId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Follow.REMOVE_FOLLOW_IN_GOODS)
    Observable<BaseBean<Object>> removeFollow(@Field("userId") int userId,@Field("merchantId") int merchantId,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.CREATE_ORDER)
    Observable<BaseBean<ArrayList<Integer>>> createOrder(@Field("userId") int userId, @Field("goodsId") int goodsId, @Field("spec") String spec, @Field("num") String num, @Field("addressId") String addressId, @Field("remark") String remark,@Field("recommendUser") String recommendUser,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Follow.ADD_FOLLOW)
    Observable<BaseBean<Object>> addFollow(@Field("userId") int userId, @Field("merchantId") int merchantId, @Field("name") String name, @Field("pic") String pic,@Field("token") String token);
}
