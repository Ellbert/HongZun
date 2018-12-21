package com.cecilia.framework.module.login;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class LoginRealization {

//    public static void getAdvertiseData(NetworkObserver<List<AdvertisingBean>> observer) {
//        NetworkUtil.getInstance().setApi(HomeApi.class)
//                .getAdvertiseData()
//                .compose(AsynchronousUtil.<BaseBean<List<AdvertisingBean>>>setThread())
//                .subscribe(observer);
//    }
//
//    public static void getHomeData(NetworkObserver<HomeBean> observer) {
//        NetworkUtil.getInstance().setApi(HomeApi.class)
//                .getHomeData()
//                .compose(AsynchronousUtil.<BaseBean<HomeBean>>setThread())
//                .subscribe(observer);
//    }
//
//    public static void getRecommendData(int cuPage,NetworkObserver<PageBean<RecommendBean>> observer) {
//        NetworkUtil.getInstance().setApi(HomeApi.class)
//                .getRecommendData(String.valueOf(cuPage),String.valueOf(PAGE_SIZE))
//                .compose(AsynchronousUtil.<BaseBean<PageBean<RecommendBean>>>setThread())
//                .subscribe(observer);
//    }
//
//    public static void getMoreListData(NetworkObserver<List<MoreListBean>> observer){
//        NetworkUtil.getInstance().setApi(HomeApi.class)
//                .getCategoryData()
//                .compose(AsynchronousUtil.<BaseBean<List<MoreListBean>>>setThread())
//                .subscribe(observer);
//    }

    public static void getSMS(String phone,String type, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .getSMS(phone,type)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void register(String phone, String code, String invitationCode, String password, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .register(phone, code, invitationCode, password)
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }

    public static void retrieve(String phone, String code, String password, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .retrieve(phone, code, password)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void login(String phone, String password, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(LoginApi.class)
                .login(phone, password)
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }
}
