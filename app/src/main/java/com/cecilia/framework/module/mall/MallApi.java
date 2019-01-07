package com.cecilia.framework.module.mall;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MallApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Mall.SEARCH)
    Observable<BaseBean<PageBean<GoodsBean>>> search(@Field("search") String search, @Field("page") int page,@Field("token") String token);

    @FormUrlEncoded
    @POST(NetworkConstant.Mall.GOODS_LIST)
    Observable<BaseBean<PageBean<GoodsBean>>> goodsList(@Field("classifyId") int classifyId, @Field("page") int page,@Field("token") String token);
}
