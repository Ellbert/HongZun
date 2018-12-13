package com.cecilia.framework.module.main;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class HomeRealization {

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

    public static void getUserInfo(String id, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getUserInfo(id)
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }

    public static void getRecommendList(NetworkObserver<List<GoodsBean>> observer){
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getRecommendList()
                .compose(AsynchronousUtil.<BaseBean<List<GoodsBean>>>setThread())
                .subscribe(observer);
    }
}
