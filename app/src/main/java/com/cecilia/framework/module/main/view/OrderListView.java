package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.OrderBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface OrderListView {

    void onGetListSuccess(@NonNull List<OrderBean> beanList);

    void onFailed();
}
