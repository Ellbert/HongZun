package com.cecilia.framework.module.product;


import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Field;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class ProductRealization {

    public static void getGoodsDetail(int id, int userId, NetworkObserver<GoodsBean> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .getGoodsDetail(id, userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<GoodsBean>>setThread())
                .subscribe(observer);
    }

    public static void createOrder(int userId, int goodsId, String spec, String num, String addressId, String remark, String recommendUser, NetworkObserver<ArrayList<Integer>> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .createOrder(userId, goodsId, spec, num, addressId, remark, recommendUser, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<ArrayList<Integer>>>setThread())
                .subscribe(observer);
    }

    public static void buy(String orderIds, int userID, String subject, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .buy(orderIds, userID, subject, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void addCart(JsonObject object, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .addCart(object, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void addCollect(int userId, int goodsId, String goodsTitle, String pic, String price, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .addCollect(userId, goodsId, goodsTitle, pic, price, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void removeCollect(int userId, int goodsId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .removeCollect(userId, goodsId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .safeSubscribe(observer);
    }

    public static void getCommentList(int goodsId, int type, int page, NetworkObserver<PageBean<CommentBean>> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .commentList(goodsId, type, page, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<PageBean<CommentBean>>>setThread())
                .safeSubscribe(observer);
    }

    public static void getCommentList(int goodsId, NetworkObserver<List<CommentBean>> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .recentlyList(goodsId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<CommentBean>>>setThread())
                .safeSubscribe(observer);
    }

    public static void addFollow(int userId, int shopId, String name, String url, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .addFollow(userId, shopId, name, url, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void removeFollow(int userId, int shopId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .removeFollow(userId, shopId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }
}
