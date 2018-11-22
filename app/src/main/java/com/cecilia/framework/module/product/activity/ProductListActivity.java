package com.cecilia.framework.module.product.activity;

import android.content.Context;
import android.content.Intent;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.product.bean.ProductListBean;
import com.cecilia.framework.module.product.presenter.ProductListPresenter;
import com.cecilia.framework.module.product.view.ProductListView;
import com.cecilia.framework.utils.LogUtil;

public class ProductListActivity extends BaseActivity implements ProductListView {

    private ProductListPresenter mProductListPresenter;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initData() {
        mProductListPresenter = new ProductListPresenter(this);
        mProductListPresenter.getListData("0", "12", "0", 1);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @Override
    public void getListSuccess(PageBean<ProductListBean> data) {
        LogUtil.e(data + "");
    }

    @Override
    public void showFail() {

    }
}
