package com.cecilia.framework.module.product.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cecilia.framework.general.NetworkObserver;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.product.ProductRealization;
import com.cecilia.framework.module.product.bean.ProductListBean;
import com.cecilia.framework.module.product.view.ProductListView;

public class ProductListPresenter {

    private ProductListView mProductListView;

    public ProductListPresenter(ProductListView productListView) {
        this.mProductListView = productListView;
    }

    public void getListData(String id, String brandId, String isHot, int cuPage){
        ProductRealization.getProductListData(id, brandId, isHot, cuPage, new NetworkObserver<PageBean<ProductListBean>>() {
            @Override
            protected SwipeRefreshLayout getSwipeRefreshLayout() {
                return null;
            }

            @Override
            protected void onSuccess(PageBean<ProductListBean> data) {
                mProductListView.getListSuccess(data);
            }

            @Override
            protected void onFailure(int errorCode, String errorMsg) {

            }

            @Override
            protected void onException(Throwable e) {

            }

            @Override
            protected void onTimeout() {

            }
        });
    }
}
