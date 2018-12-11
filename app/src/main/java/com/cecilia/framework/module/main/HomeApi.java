package com.cecilia.framework.module.main;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.bean.RecommendBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HomeApi {

////    @FormUrlEncoded
//    @POST(NetworkConstant.Advertising.GET_ADVERTISING)
//    Observable<BaseBean<List<AdvertisingBean>>> getAdvertiseData();
//
//    @POST(NetworkConstant.Product.HOME)
//    Observable<BaseBean<HomeBean>> getHomeData();
//
//    @FormUrlEncoded
//    @POST(NetworkConstant.Product.RECOMMEND)
//    Observable<BaseBean<PageBean<RecommendBean>>> getRecommendData(@Field("page") String cuPage,@Field("size") String size);
//
////    @FormUrlEncoded
//    @POST(NetworkConstant.Category.ALL_CATEGORY)
//    Observable<BaseBean<List<MoreListBean>>> getCategoryData();
//
@FormUrlEncoded
@POST(NetworkConstant.Home.GET_USER_INFO)
Observable<BaseBean<UserBean>> getUserInfo(@Field("id") String id);

}
