package com.cecilia.framework.module.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.customer.adapter.ProductAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductActivity extends BaseActivity {

    @BindView(R.id.lmrv_income_detail)
    LoadMoreRecyclerView mLmrvDetail;
    private ProductAdapter mProductAdapter;
    private int mType;

    public static void launch(Context context, int type) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_income_detail;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
        mType = getIntent().getIntExtra("type", 0);
        mLmrvDetail.setState(true, new LinearLayoutManager(this));
        mProductAdapter = new ProductAdapter(this, mType);
        mLmrvDetail.setAdapter(mProductAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mProductAdapter.setData(list);
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

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
