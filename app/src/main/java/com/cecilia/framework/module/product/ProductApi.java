package com.cecilia.framework.module.product;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.GOODS_DETAIL)
    Observable<BaseBean<GoodsBean>> getGoodsDetail(@Field("goodsId") int goodsId,@Field("userId") int userId);
//    Observable<BaseBean<PageBean<ProductListBean>>> getProductListData(@Field("id") String id, @Field("brand_id") String brand_id, @Field("ishot") String ishot, @Field("page") String page, @Field("size") String size);

    @FormUrlEncoded
    @POST(NetworkConstant.Order.BUY)
    Observable<BaseBean<String>> buy(@Field("orderIds") String orderIds,@Field("userId") int userId,@Field("subject") String subject);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.ADD_CART)
    Observable<BaseBean<Object>> addCart(@Field("data") JsonObject object);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.ADD_COLLECT)
    Observable<BaseBean<Object>> addCollect(@Field("userId") int userId,@Field("goodsId") int goodsId,@Field("goodsTitle") String goodsTitle,@Field("pic") String pic,@Field("price") String price);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.REMOVE_PRODUCT_COLLECT)
    Observable<BaseBean<Object>> removeCollect(@Field("userId") int userId,@Field("goodsId") int goodsId);

    @FormUrlEncoded
    @POST(NetworkConstant.Goods.CREATE_ORDER)
    Observable<BaseBean<String>> createOrder(@Field("userId") int userId, @Field("goodsId") int goodsId, @Field("spec") String spec, @Field("num") String num, @Field("addressId") String addressId, @Field("remark") String remark);
}
