package com.cecilia.framework.module.me.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.PriceAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PriceActivity extends BaseActivity {

    @BindView(R.id.ll_nothing)
    LinearLayout mLlNoting;
    @BindView(R.id.lmrv_price)
    LoadMoreRecyclerView mLmrvPrice;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private PriceAdapter mPriceAdapter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), PriceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_price;
    }

    @Override
    protected void initViews() {
        mLlNoting.setVisibility(View.GONE);
        mTvTitleText.setText("我的奖金");
    }

    @Override
    protected void initData() {
//        mLlNoting.setVisibility(View.GONE);
//        mLmrvPrice.setVisibility(View.VISIBLE);
        mLmrvPrice.setState(true,new LinearLayoutManager(this));
        mPriceAdapter = new PriceAdapter(this);
        mLmrvPrice.setAdapter(mPriceAdapter);
//        List<Object> list = new ArrayList<>();
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        mPriceAdapter.setData(list);
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
