package com.cecilia.framework.module.cart.view;

import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.me.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public interface SummitOrderView {
    void onGetListSuccess(@NonNull List<CartShopBean> data,@NonNull String other);
    void getAddressListSuccess(@NonNull List<AddressBean> list);
    void onCreateSuccess(@NonNull ArrayList<Integer> other);
    void onCreateOrderSuccess(@NonNull ArrayList<Integer> orderId);
    void onFailed();
}
