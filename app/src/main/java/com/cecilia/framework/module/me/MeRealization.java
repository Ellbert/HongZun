package com.cecilia.framework.module.me;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

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

    public static void getMessageList(String userId,NetworkObserver<List<MessageBean>> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findMessage(userId)
                .compose(AsynchronousUtil.<BaseBean<List<MessageBean>>>setThread())
                .subscribe(observer);
    }

    public static void updatePassword(String userId,String oldPwd,String pwd,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .updatePwd(userId,oldPwd,pwd)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void saveBankCard(String userId,String id,String bankCardNum,String bankType,String isDefault,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(MeApi.class)
                .saveBankCard(userId,id,bankCardNum,bankType,isDefault)
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
}
