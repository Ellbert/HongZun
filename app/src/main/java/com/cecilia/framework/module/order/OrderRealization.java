package com.cecilia.framework.module.order;

import com.cecilia.framework.general.BaseBean;
import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.module.order.bean.OrderDetailBean;
import com.cecilia.framework.utils.AsynchronousUtil;
import com.cecilia.framework.utils.NetworkUtil;

import static com.cecilia.framework.utils.AsynchronousUtil.setThread;

public class OrderRealization {

    public static void orderDetail(int orderId, NetworkObserver<OrderDetailBean> observer) {
        NetworkUtil.getInstance().setApi(OrderApi.class)
                .orderDetail(orderId)
                .compose(AsynchronousUtil.<BaseBean<OrderDetailBean>> setThread())
                .safeSubscribe(observer);
    }
}
