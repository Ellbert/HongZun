package com.cecilia.framework.module.order.view;

import com.cecilia.framework.module.order.bean.OrderDetailBean;

import io.reactivex.annotations.NonNull;

public interface OrderDetailView {

    void onGetDetailSuccess(@NonNull OrderDetailBean orderDetailBean);

    void onGetFailed();
}

