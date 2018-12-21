package com.cecilia.framework.module.me;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.bean.BankBean;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MeRealization {

    public static void getUserInfo(String userId, String type, String info, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .updateUserInfo(userId, type, info)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getAddressList(String userId, NetworkObserver<List<AddressBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findAddressList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<AddressBean>>>setThread())
                .subscribe(observer);
    }

    public static void deleteAddress(String addressId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .deleteAddress(addressId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void saveAddress(String userId, String name, String addressId, String address, String phone, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .saveAddress(userId, name, addressId, address, phone)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getCollectList(String userId, NetworkObserver<List<BaseGoodBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findCollectList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<BaseGoodBean>>>setThread())
                .subscribe(observer);
    }

    public static void getMessageList(int userId,int page,NetworkObserver<PageBean<MessageBean>> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findMessage(userId,page)
                .compose(AsynchronousUtil.<BaseBean<PageBean<MessageBean>>>setThread())
                .subscribe(observer);
    }

    public static void updatePassword(String userId,String oldPwd,String pwd,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .updatePwd(userId,oldPwd,pwd)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void saveBankCard(int userId,String username,int bankId,String cardNum,String isDefault,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .saveBankCard(userId,username,bankId,cardNum,isDefault)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getBankCardList(String userId,NetworkObserver<List<BankCardBean>> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .getBankCardList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<BankCardBean>>>setThread())
                .subscribe(observer);
    }

    public static void deleteBankCard(String id,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .deleteBankCard(id)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void removeCollect(String id,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .removeCollect(id)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getFollowList(String userId,NetworkObserver<List<FollowBean>> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .collectList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<FollowBean>>>setThread())
                .subscribe(observer);
    }

    public static void getBankList(NetworkObserver<List<BankBean>> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .getBankList()
                .compose(AsynchronousUtil.<BaseBean<List<BankBean>>>setThread())
                .subscribe(observer);
    }

    public static void setDefaultBankCart(int cardId,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .setDefaultBankCart(cardId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void uploadImage(File image, NetworkObserver<Object> observer){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", image.getName(), requestFile);
        NetworkUtil.getInstance().setApi(MeApi.class)
                .uploadImage(part)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void enter(JsonObject cardId, NetworkObserver<String> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .enter(cardId)
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void removeConcern(int id,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .removeConcern(id)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }
}
