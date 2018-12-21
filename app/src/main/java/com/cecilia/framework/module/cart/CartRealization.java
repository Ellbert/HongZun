package com.cecilia.framework.module.cart;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CartRealization {

    public static void getCartList(int userId, NetworkObserver<List<CartShopBean>> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .findCartList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<CartShopBean>>>setThread())
                .subscribe(observer);
    }

    public static void getLostList(int userId, NetworkObserver<List<FailedGoodsBean>> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .findFailedList(userId)
                .compose(AsynchronousUtil.<BaseBean<List<FailedGoodsBean>>>setThread())
                .subscribe(observer);
    }

    public static void deleteCart(String userId, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .deleteCart(userId)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void updateNumber(int cartId, String type, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .updateNumber(cartId, type)
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void temporaryList(int userId, String cartsId, NetworkObserver<List<CartShopBean>> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .temporaryList(userId, cartsId)
                .compose(AsynchronousUtil.<BaseBean<List<CartShopBean>>>setThread())
                .subscribe(observer);
    }

    public static void createOrder(int userId, JSONArray jsonArray, String addressId, NetworkObserver<ArrayList<Integer>> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .createOrder(userId,jsonArray,addressId)
                .compose(AsynchronousUtil.<BaseBean<ArrayList<Integer>>>setThread())
                .subscribe(observer);
    }


    public static void buy(String orderIds, int userID, String subject, NetworkObserver<String> observer) {
        NetworkUtil.getInstance().setApi(CartApi.class)
                .buy(orderIds, userID, subject)
                .compose(AsynchronousUtil.<BaseBean<String>>setThread())
                .subscribe(observer);
    }
}
