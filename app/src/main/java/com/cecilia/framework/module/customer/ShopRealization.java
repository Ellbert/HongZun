package com.cecilia.framework.module.customer;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

public class ShopRealization {

    public static void getWallet(int merchantId, NetworkObserver<ShopPaymentBean> observer) {
        NetworkUtil.getInstance().setApi(ShopApi.class)
                .getWallet(merchantId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<ShopPaymentBean>>setThread())
                .subscribe(observer);
    }

    public static void getRatio(NetworkObserver<RateBean> observer) {
        NetworkUtil.getInstance().setApi(ShopApi.class)
                .getRatio(GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<RateBean>>setThread())
                .subscribe(observer);
    }

    public static void withdraw(int merchantId, String merchantName, int cardId, long money, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ShopApi.class)
                .withdraw(merchantId, merchantName, cardId, money, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }

    public static void withdrawByAlipay(int merchantId, String merchantName, long money, String account, String realName, NetworkObserver<Object> observer) {
        NetworkUtil.getInstance().setApi(ShopApi.class)
                .withdrawByAlipay(merchantId, merchantName, money, account, realName, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<Object>>setThread())
                .subscribe(observer);
    }
}
