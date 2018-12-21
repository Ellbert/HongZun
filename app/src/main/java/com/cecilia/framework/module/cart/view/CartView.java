package com.cecilia.framework.module.cart.view;

import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface CartView {

    void onGetCartList(@NonNull List<CartShopBean> list);
    void onGetFailedList(@NonNull List<FailedGoodsBean> list);
    void onDeleteSuccess();
    void onUpdateSuccess();
    void onGetFailed();
}
