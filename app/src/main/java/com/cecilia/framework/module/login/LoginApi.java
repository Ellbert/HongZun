package com.cecilia.framework.module.login;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

////    @FormUrlEncoded
//    @POST(NetworkConstant.Advertising.GET_ADVERTISING)
//    Observable<BaseBean<List<AdvertisingBean>>> getAdvertiseData();
//
//    @POST(NetworkConstant.Product.HOME)
//    Observable<BaseBean<HomeBean>> getHomeData();
//
//    @FormUrlEncoded
//    @POST(NetworkConstant.Product.RECOMMEND)
//    Observable<BaseBean<PageBean<RecommendBean>>> getRecommendData(@Field("page") String cuPage, @Field("size") String size);
//
////    @FormUrlEncoded
////    @POST(NetworkConstant.Category.ALL_CATEGORY)
////    Observable<BaseBean<List<MoreListBean>>> getCategoryData();

    @FormUrlEncoded
    @POST(NetworkConstant.Login.SEND_SMS)
    Observable<BaseBean<Object>> getSMS(@Field("phone") String phone,@Field("type") String type);

    @FormUrlEncoded
    @POST(NetworkConstant.Login.REGISTER)
    Observable<BaseBean<UserBean>> register(@Field("phone") String phone, @Field("code") String code, @Field("invitationCode") String invitationCode, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST(NetworkConstant.Login.RETRIEVE)
    Observable<BaseBean<Object>> retrieve(@Field("phone") String phone, @Field("code") String code, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST(NetworkConstant.Login.LOGIN)
    Observable<BaseBean<UserBean>> login(@Field("phone") String phone, @Field("pwd") String pwd);
}
