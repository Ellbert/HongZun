package com.cecilia.framework.module.product.view;

import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.product.bean.CommentBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface ProductView {

    void getDetailSuccess(@NonNull GoodsBean goodsBean);

    void getAddressListSuccess(@NonNull List<AddressBean> list);

    void onAddCartSuccess();

    void onAddCollectSuccess();

    void onRemoveCollectSuccess();

    void onGetRecentlyListSuccess(@NonNull List<CommentBean> list);

    void onRemoveFollowSuccess();

    void onAddFollowSuccess();

    void onFailed();
}
