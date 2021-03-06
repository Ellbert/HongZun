package com.cecilia.framework.module.me;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
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
                .updateUserInfo(userId, type, info, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getAddressList(String userId, NetworkObserver<List<AddressBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findAddressList(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<AddressBean>>>setThread())
                .subscribe(observer);
    }

    public static void deleteAddress(String addressId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .deleteAddress(addressId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void saveAddress(String userId, String name, String addressId, String address, String phone, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .saveAddress(userId, name, addressId, address, phone, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getCollectList(String userId, NetworkObserver<List<BaseGoodBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findCollectList(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<BaseGoodBean>>>setThread())
                .subscribe(observer);
    }

    public static void getMessageList(int userId, int page, NetworkObserver<PageBean<MessageBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findMessage(userId, page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<PageBean<MessageBean>>>setThread())
                .subscribe(observer);
    }

    public static void updatePassword(String userId, String oldPwd, String pwd, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .updatePwd(userId, oldPwd, pwd, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void saveBankCard(int userId, String username, String bankName, String cardNum, String branch, String isDefault, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .saveBankCard(userId, username, bankName, cardNum, branch, isDefault, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getBankCardList(String userId, NetworkObserver<List<BankCardBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .getBankCardList(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<BankCardBean>>>setThread())
                .subscribe(observer);
    }

    public static void deleteBankCard(String id, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .deleteBankCard(id, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void removeCollect(String id, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .removeCollect(id, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getFollowList(String userId, NetworkObserver<List<FollowBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .collectList(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<FollowBean>>>setThread())
                .subscribe(observer);
    }

    public static void getBankList(NetworkObserver<List<BankBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .getBankList(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<BankBean>>>setThread())
                .subscribe(observer);
    }

    public static void setDefaultBankCart(int cardId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .setDefaultBankCart(cardId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void uploadImage(File image, NetworkObserver<Object> observer) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", image.getName(), requestFile);
        NetworkUtil.getInstance().setApi(MeApi.class)
                .uploadImage(part)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void enter(JsonObject cardId, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .enter(cardId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void removeConcern(int id, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .removeConcern(id, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void firstList(int userId, int page, NetworkObserver<PageBean<UserBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .firstFansList(userId, page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<PageBean<UserBean>>>setThread())
                .subscribe(observer);
    }

    public static void secondList(int userId, int page, NetworkObserver<PageBean<UserBean>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .secondFansList(userId, page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<PageBean<UserBean>>>setThread())
                .subscribe(observer);
    }

    public static void setPayPassword(int id, String code, String pwd, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .setPayPassword(id, code, pwd, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }
}
