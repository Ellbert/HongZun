package com.cecilia.framework.module.product;


import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.google.gson.JsonObject;

import retrofit2.http.Field;

import static com.cecilia.framework.common.NetworkConstant.PAGE_SIZE;

public class ProductRealization {


    public static void getGoodsDetail(int id,int userId, NetworkObserver<GoodsBean> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .getGoodsDetail(id,userId)
                .compose(AsynchronousUtil.<BaseBean<GoodsBean>>setThread())
                .subscribe(observer);
    }

    public static void createOrder(int userId, int goodsId, String spec, String num, String addressId, String remark, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .createOrder(userId, goodsId, spec, num, addressId, remark)
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void buy(String orderIds, int userID, String subject, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .buy(orderIds, userID, subject)
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }

    public static void addCart(JsonObject object, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .addCart(object)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void addCollect(int userId,int goodsId,String goodsTitle,String pic,String price,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .addCollect(userId,goodsId,goodsTitle,pic,price)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void removeCollect(int userId ,int goodsId,NetworkObserver<Object> observer){
        NetworkUtil.getInstance().setApi(ProductApi.class)
                .removeCollect(userId,goodsId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .safeSubscribe(observer);
    }
}
