package com.cecilia.framework.module.me;

import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.module.me.bean.MessageBean;

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
    Observable<BaseBean<List<BaseGoodBean>>> findCollectList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.GET_LIST)
    Observable<BaseBean<List<AddressBean>>> findAddressList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.DELETE)
    Observable<BaseBean<Object>> deleteAddress(@Field("addressId") String addressId);

    @FormUrlEncoded
    @POST(NetworkConstant.Address.SAVE)
    Observable<BaseBean<Object>> saveAddress(@Field("userId") String userId, @Field("name") String name, @Field("addressId") String addressId, @Field("address") String address, @Field("phone") String phone);

    @FormUrlEncoded
    @POST(NetworkConstant.Message.FIND_BY_USER)
    Observable<BaseBean<List<MessageBean>>> findMessage(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.Login.UPDATE_PASSWORD)
    Observable<BaseBean<Object>> updatePwd(@Field("userId") String userId, @Field("oldPwd") String oldPwd, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST(NetworkConstant.BankCard.BANK_CARD_LIST)
    Observable<BaseBean<List<BankCardBean>>> getBankCardList(@Field("userId") String userId);

    @FormUrlEncoded
    @POST(NetworkConstant.BankCard.DELETE_BANK_CARD)
    Observable<BaseBean<Object>> deleteBankCard(@Field("cardId") String id);

    @FormUrlEncoded
    @POST(NetworkConstant.BankCard.SAVE_BANK_CARD)
    Observable<BaseBean<Object>> saveBankCard(@Field("userId") String userId, @Field("cardId") String cardId, @Field("bankCardNum") String bankCardNum, @Field("bankType") String bankType, @Field("isDefault") String isDefault);

    @FormUrlEncoded
    @POST(NetworkConstant.Collect.REMOVE_COLLECT)
    Observable<BaseBean<Object>> removeCollect(@Field("collectId") String id);

    @FormUrlEncoded
    @POST(NetworkConstant.Follow.COLLECT_LIST)
    Observable<BaseBean<List<FollowBean>>> collectList(@Field("userId") String userId);

}
