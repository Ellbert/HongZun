package com.cecilia.framework.module.main;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

public class HomeRealization {

    public static void deleteOrder(int orderId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .deleteOrder(orderId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getUserInfo(String id, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getUserInfo(id)
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }

    public static void getRecommendList(NetworkObserver<List<GoodsBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getRecommendList()
                .compose(AsynchronousUtil.<BaseBean<List<GoodsBean>>>setThread())
                .subscribe(observer);
    }

    public static void geShopStatus(String merchantId, NetworkObserver<ShopBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .shopStatus(merchantId)
                .compose(AsynchronousUtil.<BaseBean<ShopBean>>setThread())
                .subscribe(observer);
    }

    public static void geOrderList(int userId, int type, int page, NetworkObserver<List<OrderBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getOrderList(userId, type, page)
                .compose(AsynchronousUtil.<BaseBean<List<OrderBean>>>setThread())
                .subscribe(observer);
    }

    public static void receive(int orderId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .receiveOrder(orderId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void submitComment(int userId, String username, String headurl, int orderId, int goodsId, int type, String comment, String img, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .submitComment(userId, username, headurl, orderId, goodsId, type, comment, img)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getPromotionList(NetworkObserver<List<AdvertisingBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .promotionList()
                .compose(AsynchronousUtil.<BaseBean<List<AdvertisingBean>>>setThread())
                .subscribe(observer);
    }

    public static void lastNotice(NetworkObserver<NoticeBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .lastNotice()
                .compose(AsynchronousUtil.<BaseBean<NoticeBean>>setThread())
                .subscribe(observer);
    }

    public static void noticeList(int page, NetworkObserver<List<NoticeBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .noticeList(page)
                .compose(AsynchronousUtil.<BaseBean<List<NoticeBean>>>setThread())
                .subscribe(observer);
    }
}
