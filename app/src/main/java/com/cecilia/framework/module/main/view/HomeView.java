package com.cecilia.framework.module.main.view;

import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.bean.RecommendBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface HomeView {

    void getRecommendSuccess(@NonNull List<GoodsBean> data);

    void getFail();
}
