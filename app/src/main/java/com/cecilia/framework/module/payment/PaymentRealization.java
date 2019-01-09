package com.cecilia.framework.module.payment;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import java.util.List;

public class PaymentRealization {

    public static void paymentList(int userId, int type, int page, NetworkObserver<List<PaymentBean>> observer) {
        NetworkUtil.getInstance().setApi(PaymentApi.class)
                .paymentList(userId, type, page,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<PaymentBean>>>setThread())
                .subscribe(observer);
    }


    public static void merchantPaymentList(int merchantId, int type, int page, NetworkObserver<List<PaymentBean>> observer) {
        NetworkUtil.getInstance().setApi(PaymentApi.class)
                .merchantPaymentList(merchantId, type, page,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<PaymentBean>>>setThread())
                .subscribe(observer);
    }

    public static void withdrawRecord(int merchantId, int page, NetworkObserver<List<WithdrawBean>> observer) {
        NetworkUtil.getInstance().setApi(PaymentApi.class)
                .withdrawRecord(merchantId, page,GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<List<WithdrawBean>>>setThread())
                .subscribe(observer);
    }

    public static void getDetail(int userId, NetworkObserver<MessageBean> observer) {
        NetworkUtil.getInstance().setApi(PaymentApi.class)
                .getDetail(userId, GcGuangApplication.getsToken())
                .compose(AsynchronousUtil.<BaseBean<MessageBean>>setThread())
                .subscribe(observer);
    }
}
