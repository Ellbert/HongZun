package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.activity.AddressEditActivity;
import com.cecilia.framework.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomerActivity extends BaseActivity {

    @BindView(R.id.rl_no_shop)
    RelativeLayout mRlNothing;
    @BindView(R.id.rl_reviewing)
    RelativeLayout mRlReviewing;
    @BindView(R.id.srl_customer)
    SwipeRefreshLayout mSrlCustomer;
    private int mType;

    public static void launch(Context context, int type) {
        Intent intent = new Intent(context, CustomerActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getIntExtra("type", 0);
        setView();
    }

    private void setView() {
        switch (mType) {
            case 1:
                mRlNothing.setVisibility(View.VISIBLE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.GONE);
                break;
            case 2:
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.VISIBLE);
                mSrlCustomer.setVisibility(View.GONE);
                break;
            case 3:
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void doEvents(EventBean event) {
        mType = 2;
        setView();
    }

    @OnClick({R.id.tv_apply, R.id.tv_done, R.id.iv_back, R.id.tv_income_detail, R.id.iv_commodity_management,
            R.id.iv_revenue_management, R.id.iv_order_management})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_apply:
                UserRegisterActivity.launch(CustomerActivity.this);
                break;
            case R.id.tv_done:
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_income_detail:
                ProductActivity.launch(CustomerActivity.this, 1);
                break;
            case R.id.iv_commodity_management:
                ProductActivity.launch(CustomerActivity.this, 2);
                break;
            case R.id.iv_revenue_management:
                break;
            case R.id.iv_order_management:
                ShopOrderActivity.launch(CustomerActivity.this);
                break;
        }
    }
}
