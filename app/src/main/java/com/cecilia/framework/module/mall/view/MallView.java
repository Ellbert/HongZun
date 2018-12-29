package com.cecilia.framework.module.mall.view;

import com.cecilia.framework.module.main.bean.GoodsBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface MallView {

    void onGetSuccess(@NonNull List<GoodsBean> goodsBeans);

    void onGetGoodsListSuccess(@NonNull List<GoodsBean> goodsBeans);

    void onGetFailed();
}
