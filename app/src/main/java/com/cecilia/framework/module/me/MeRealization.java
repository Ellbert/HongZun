package com.cecilia.framework.module.me;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.bean.AddressBean;
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

    public static void getCollectList(String userId, NetworkObserver<List<Object>> observer) {
        NetworkUtil.getInstance().setApi(MeApi.class)
                .findCollectList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<Object>>>setThread())
                .subscribe(observer);
    }
}
