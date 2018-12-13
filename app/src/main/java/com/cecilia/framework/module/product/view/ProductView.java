package com.cecilia.framework.module.product.view;

import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.me.bean.AddressBean;

import java.util.List;

public interface ProductView {

    void getDetailSuccess(GoodsBean goodsBean,String other);

    void getAddressListSuccess(List<AddressBean> list);

    void onCreateOrderSuccess(String orderId);

    void onBuySuccess(String orderInfo);

    void showAlipayResult(String data);

    void onAddCartSuccess();

    void onAddCollectSuccess();

    void onRemoveCollectSuccess();

    void onFailed();
}
