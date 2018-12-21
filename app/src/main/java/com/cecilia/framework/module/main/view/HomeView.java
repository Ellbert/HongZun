package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.ShopBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface HomeView {

    void getRecommendSuccess(@NonNull List<GoodsBean> data);

    void onShopStatusSuccess(@NonNull ShopBean data);

    void onGetPromotionListSuccess(@NonNull List<AdvertisingBean> data);

    void onGetNoticeSuccess(@NonNull NoticeBean data);

    void onFailed();
}
