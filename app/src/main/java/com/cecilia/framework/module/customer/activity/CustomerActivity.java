package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.module.me.activity.AddressEditActivity;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
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
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.rl_no_pass)
    RelativeLayout mRlNoPass;
    @BindView(R.id.rl_freeze)
    RelativeLayout mRlFreeze;
    @BindView(R.id.tv_introduction)
    TextView mTvIntroduction;
    @BindView(R.id.tv_name)
    TextView mTvShopName;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    private int mType;
    private ShopBean mShopBean;

    public static void launch(Context context, int type, ShopBean shopBean) {
        Intent intent = new Intent(context, CustomerActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("shop", shopBean);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void initViews() {
        mType = getIntent().getIntExtra("type", 0);
        mShopBean = (ShopBean) getIntent().getSerializableExtra("shop");
        setView();
    }

    private void setView() {
        switch (mType) {
            case -1:
                mTvTitleText.setText("企业商户");
                mRlNothing.setVisibility(View.VISIBLE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.GONE);
                mRlNoPass.setVisibility(View.GONE);
                mRlFreeze.setVisibility(View.GONE);
                break;
            case 0:
                mTvTitleText.setText("待审核");
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.VISIBLE);
                mSrlCustomer.setVisibility(View.GONE);
                mRlNoPass.setVisibility(View.GONE);
                mRlFreeze.setVisibility(View.GONE);
                break;
            case 1:
                mTvTitleText.setText("个人店铺");
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.VISIBLE);
                mRlNoPass.setVisibility(View.GONE);
                mRlFreeze.setVisibility(View.GONE);
                mTvIntroduction.setText(mShopBean.getTIntroduce());
                mTvShopName.setText(mShopBean.getTName());
                ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mShopBean.getTLogo(), mIvHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(this));

                break;
            case 2:
                mTvTitleText.setText("审核未通过");
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.GONE);
                mRlNoPass.setVisibility(View.VISIBLE);
                mRlFreeze.setVisibility(View.GONE);
                break;
            case 3:
                mTvTitleText.setText("冻结中");
                mRlNothing.setVisibility(View.GONE);
                mRlReviewing.setVisibility(View.GONE);
                mSrlCustomer.setVisibility(View.GONE);
                mRlNoPass.setVisibility(View.GONE);
                mRlFreeze.setVisibility(View.VISIBLE);
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
        mType = 0;
        setView();
    }

    @OnClick({R.id.tv_apply, R.id.tv_done, R.id.iv_back, R.id.tv_income_detail, R.id.iv_commodity_management,
            R.id.iv_revenue_management, R.id.iv_order_management, R.id.tv_no_pass_done, R.id.tv_freeze_done})
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
            case R.id.tv_no_pass_done:
                finish();
                break;
            case R.id.tv_freeze_done:
                finish();
                break;
        }
    }
}
