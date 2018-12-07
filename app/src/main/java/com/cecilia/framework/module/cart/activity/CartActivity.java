package com.cecilia.framework.module.cart.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.adapter.CartGoodsAdapter;
import com.cecilia.framework.module.cart.adapter.CartShopAdapter;
import com.cecilia.framework.module.cart.adapter.FailureAdapter;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.utils.LogUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartActivity extends BaseActivity {

    @BindView(R.id.rv_shop_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.rv_failure)
    RecyclerView mRvFailure;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    @BindView(R.id.tv_submit)
    ImageView mIvSummit;
    @BindView(R.id.tv_sum)
    TextView mTvSum;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.cb_all)
    CheckBox mCbAll;
    private CartShopAdapter mCartShopAdapter;
    private FailureAdapter mFailureAdapter;
    private List<CartShopBean> cartShopBeans = new ArrayList<>();

    public static void launch(Context context) {
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initViews() {
        mTvEdit.setText("编辑");
        mTvEdit.setVisibility(View.VISIBLE);
        mIvSummit.setVisibility(View.GONE);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCartShopAdapter = new CartShopAdapter(this, R.layout.item_cart_shop);
        mRecyclerView.setAdapter(mCartShopAdapter);
        mRvFailure.setNestedScrollingEnabled(false);
        mRvFailure.setLayoutManager(new LinearLayoutManager(this));
        mFailureAdapter = new FailureAdapter(this, R.layout.item_failure);
        mRvFailure.setAdapter(mFailureAdapter);
        mRecyclerView.setFocusableInTouchMode(false);
        List<CartGoodsBean> cartGoodsBeans1 = new ArrayList<>();
        cartGoodsBeans1.add(new CartGoodsBean(888.88));
        cartGoodsBeans1.add(new CartGoodsBean(888.88));
        cartGoodsBeans1.add(new CartGoodsBean(888.88));
        cartGoodsBeans1.add(new CartGoodsBean(888.88));
        List<CartGoodsBean> cartGoodsBeans2 = new ArrayList<>();
        cartGoodsBeans2.add(new CartGoodsBean(888.88));
        cartGoodsBeans2.add(new CartGoodsBean(888.88));
        cartGoodsBeans2.add(new CartGoodsBean(888.88));
        cartGoodsBeans2.add(new CartGoodsBean(888.88));
        List<CartGoodsBean> cartGoodsBeans3 = new ArrayList<>();
        cartGoodsBeans3.add(new CartGoodsBean(888.88));
        cartGoodsBeans3.add(new CartGoodsBean(888.88));
        cartGoodsBeans3.add(new CartGoodsBean(888.88));
        cartGoodsBeans3.add(new CartGoodsBean(888.88));
        List<CartGoodsBean> cartGoodsBeans4 = new ArrayList<>();
        cartGoodsBeans4.add(new CartGoodsBean(888.88));
        cartGoodsBeans4.add(new CartGoodsBean(888.88));
        cartGoodsBeans4.add(new CartGoodsBean(888.88));
        cartGoodsBeans4.add(new CartGoodsBean(888.88));
        cartShopBeans.add(new CartShopBean(cartGoodsBeans1));
        cartShopBeans.add(new CartShopBean(cartGoodsBeans2));
        cartShopBeans.add(new CartShopBean(cartGoodsBeans3));
        cartShopBeans.add(new CartShopBean(cartGoodsBeans4));
    }

    @Override
    protected void initData() {
        mCartShopAdapter.setDataList(cartShopBeans);
        mFailureAdapter.setDataList(cartShopBeans);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double price = 0;
                for (CartShopBean shopCarBean : cartShopBeans) {
                    for (CartGoodsBean childBean : shopCarBean.getCartGoodsBeans()) {
                        if (mCbAll.isChecked()) {
                            price += childBean.getPrice();
                            childBean.setSelected(true);
                        } else {
                            childBean.setSelected(false);
                        }
                    }
                }
                mTvSum.setText(price + "");
                mCartShopAdapter.notifyDataSetChanged();
            }
        });
        mCartShopAdapter.setSumPrice(new CartShopAdapter.sumPrice() {
            @Override
            public void onSumPrice() {
                double price = 0;
                for (CartShopBean cartShopBean : cartShopBeans) {
                    for (CartGoodsBean cartGoodsBean : cartShopBean.getCartGoodsBeans()) {
                        if (cartGoodsBean.isSelected()) {
                            price += cartGoodsBean.getPrice();
                        }
                    }
                }
                mTvSum.setText(price + "");
            }
        });
        mCartShopAdapter.setSumCheck(new CartShopAdapter.SumCheck() {
            @Override
            public void sunCheck() {
                boolean b = true;
                for (CartShopBean cartShopBean : cartShopBeans) {
                    for (CartGoodsBean cartGoodsBean : cartShopBean.getCartGoodsBeans()) {
                        if (!cartGoodsBean.isSelected()) {
                            b = false;
                        }
                    }
                }
                mCbAll.setChecked(b);

            }
        });
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.tv_title_text, R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_text:
                if (mTvEdit.getText().equals("编辑")) {
                    mTvEdit.setText("完成");
                    mTvSum.setVisibility(View.INVISIBLE);
                    mTvSettlement.setText("删除");
                } else {
                    mTvEdit.setText("编辑");
                    mTvSum.setVisibility(View.VISIBLE);
                    mTvSettlement.setText("结算");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private String getIdString() {

        return "";
    }
}
