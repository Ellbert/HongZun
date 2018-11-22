package com.cecilia.framework.module.product.view;

import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.product.bean.ProductListBean;

import io.reactivex.annotations.NonNull;

public interface ProductListView {

    void getListSuccess(@NonNull PageBean<ProductListBean> data);

    void showFail();
}
