package com.cecilia.framework.module.main.view;

import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.MoreListBean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public interface MoreView {

    void getDataSuccess(@NonNull List<GoodsBean> data);

    void showFail();
}
