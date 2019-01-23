package com.cecilia.framework.module.main;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.FinancialBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.module.main.bean.VersionBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

public class HomeRealization {

    public static void deleteOrder(int orderId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .deleteOrder(orderId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getUserInfo(int id, NetworkObserver<UserBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getUserInfo(id, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<UserBean>>setThread())
                .subscribe(observer);
    }

    public static void getRecommendList(NetworkObserver<List<GoodsBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getRecommendList(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<GoodsBean>>>setThread())
                .subscribe(observer);
    }

    public static void geShopStatus(String merchantId, NetworkObserver<ShopBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .shopStatus(merchantId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<ShopBean>>setThread())
                .subscribe(observer);
    }

    public static void geOrderList(int userId, int type, int page, NetworkObserver<List<OrderBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getOrderList(userId, type, page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<OrderBean>>>setThread())
                .subscribe(observer);
    }

    public static void receive(int orderId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .receiveOrder(orderId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void submitComment(int userId, String username, String headurl, int orderId, int goodsId, int type, String comment, String img, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .submitComment(userId, username, headurl, orderId, goodsId, type, comment, img, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void getPromotionList(NetworkObserver<List<AdvertisingBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .promotionList(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<AdvertisingBean>>>setThread())
                .subscribe(observer);
    }

    public static void lastNotice(NetworkObserver<NoticeBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .lastNotice(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<NoticeBean>>setThread())
                .subscribe(observer);
    }

    public static void cancelOrder(int orderId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .cancelOrder(orderId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void noticeList(int page, NetworkObserver<List<NoticeBean>> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .noticeList(page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<NoticeBean>>>setThread())
                .subscribe(observer);
    }

    public static void getVersion(NetworkObserver<VersionBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getVersion(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<VersionBean>>setThread())
                .subscribe(observer);
    }

    public static void getQrCode(int userId, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getQrCode(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void getMessageCount(int userId, NetworkObserver<Integer> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getMessageCount(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Integer>>setThread())
                .subscribe(observer);
    }

    public static void createOrder(int userId, long money, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .createOrder(userId, money, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void financialRecharge(int userId, long money, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .financialRecharge(userId, money, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void redelivery(int userId, long money, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .redelivery(userId, money, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void buy(String orderIds, int userID, String subject, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .buy(orderIds, userID, subject, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void getWallet(int userId, NetworkObserver<ShopPaymentBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getWallet(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<ShopPaymentBean>>setThread())
                .subscribe(observer);
    }

    public static void getUserArrange(int userId, NetworkObserver<FinancialBean> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .getUserArrange(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<FinancialBean>>setThread())
                .subscribe(observer);
    }

    public static void withdraw(int userId, String userName, long money, String account, String realName, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(HomeApi.class)
                .withdraw(userId, userName, money, account, realName, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }
}
