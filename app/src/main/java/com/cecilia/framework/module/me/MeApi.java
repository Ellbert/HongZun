package com.cecilia.framework.module.me;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.module.me.bean.AddressBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MeApi {

    @FormUrlEncoded
    @POST(NetworkConstant.Me.UPDATE_USER_INFO)
    Observable<BaseBean<Object>> updateUserInfo(@Field("userId") String userId, @Field("type") String type, @Field("info") String info);

    @FormUrlEncoded
    @POST(NetworkConstant.Cart.FIND_LIST)
    Observable<BaseBean<Object>> findCartList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.GET_LIST)
    Observable<BaseBean<List<Object>>> findCollectList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.GET_LIST)
    Observable<BaseBean<List<AddressBean>>> findAddressList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.DELETE)
    Observable<BaseBean<Object>> deleteAddress(@Field("addressId") String addressId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.SAVE)
    Observable<BaseBean<Object>> saveAddress(@Field("userId") String userId, @Field("name") String name, @Field("addressId") String addressId, @Field("address") String address, @Field("phone") String phone);
}
