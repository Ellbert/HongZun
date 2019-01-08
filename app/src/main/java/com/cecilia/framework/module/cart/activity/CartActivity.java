package com.cecilia.framework.module.cart.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.cart.adapter.CartGoodsAdapter;
import com.cecilia.framework.module.cart.adapter.CartShopAdapter;
import com.cecilia.framework.module.cart.adapter.FailureAdapter;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;
import com.cecilia.framework.module.cart.presenter.CartPresenter;
import com.cecilia.framework.module.cart.view.CartView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class CartActivity extends BaseActivity implements CartView, SwipeRefreshLayout.OnRefreshListener {

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
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.srl_cart)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_num)
    TextView mTvCartNum;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    @BindView(R.id.tv_shopping)
    TextView mTvShopping;
    @BindView(R.id.ll_cart)
    LinearLayout mLlCart;
    private CartShopAdapter mCartShopAdapter;
    private FailureAdapter mFailureAdapter;
    private List<CartShopBean> cartShopBeans;
    private CartPresenter mCartPresenter;
    private double mSumMoney;
    private String mCartIds;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), CartActivity.class);
        context.startActivityForResult(intent, 100);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initViews() {
        mTvEdit.setText("编辑");
        mTvTitleText.setText("我的购物车");
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
    }

    @Override
    protected void initData() {
        mCartPresenter = new CartPresenter(this);
        mCartPresenter.getFailedList(null, GcGuangApplication.getId());
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sumPrice = 0;
                String carts = "";
                boolean isCheck = mCbAll.isChecked();
                for (CartShopBean shopCarBean : cartShopBeans) {
                    shopCarBean.setSelected(isCheck);
                    for (CartGoodsBean childBean : shopCarBean.getGoods()) {
                        if (isCheck) {
                            double price = ArithmeticalUtil.mul(childBean.getTPrice(), childBean.getTNum());
                            sumPrice = ArithmeticalUtil.add(sumPrice, price);
                            carts += childBean.getTId() + "#";
                        }
                        childBean.setSelected(isCheck);
                    }
                }
                mSumMoney = sumPrice;
                mCartIds = carts;
                mTvSum.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mSumMoney));
                mCartShopAdapter.notifyDataSetChanged();
            }
        });
        mCartShopAdapter.setSumPrice(new CartShopAdapter.sumPrice() {
            @Override
            public void onSumPrice() {
                double sumPrice = 0;
                String carts = "";
                for (CartShopBean cartShopBean : cartShopBeans) {
                    for (CartGoodsBean cartGoodsBean : cartShopBean.getGoods()) {
                        if (cartGoodsBean.isSelected()) {
                            double price = ArithmeticalUtil.mul(cartGoodsBean.getTPrice(), cartGoodsBean.getTNum());
                            sumPrice = ArithmeticalUtil.add(sumPrice, price);
                            carts += cartGoodsBean.getTId() + "#";
                        }
                    }
                }
                mCartIds = carts;
                mSumMoney = sumPrice;
                mTvSum.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mSumMoney));
            }
        });
        mCartShopAdapter.setSumCheck(new CartShopAdapter.SumCheck() {
            @Override
            public void sunCheck() {
                boolean b = true;
                for (CartShopBean cartShopBean : cartShopBeans) {
                    for (CartGoodsBean cartGoodsBean : cartShopBean.getGoods()) {
                        if (!cartGoodsBean.isSelected()) {
                            b = false;
                        }
                    }
                }
                mCbAll.setChecked(b);

            }
        });
        mCartShopAdapter.setOnNumberChangeListener(new CartGoodsAdapter.OnNumberChangeListener() {
            @Override
            public void onChange(int id, String type) {
                DialogUtil.createLoadingDialog(CartActivity.this, "修改中...", false, null);
                mCartPresenter.updateNumber(id, type);
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

    @OnClick({R.id.tv_title_text, R.id.iv_back, R.id.tv_settlement, R.id.tv_shopping})
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
            case R.id.tv_settlement:
                if (StringUtil.isNullOrEmpty(mCartIds)) return;
                if (mTvSettlement.getText().equals("结算")) {
                    SummitOrderActivity.launch(this, mCartIds, null, null);
                } else {
                    DialogUtil.createLoadingDialog(this, "删除中...", false, null);
                    mCartPresenter.delete(mCartIds);
                }
                break;
            case R.id.tv_shopping:
                setResult(100);
                finish();
                break;
        }
    }

    @Override
    public void onGetCartList(List<CartShopBean> list) {
        cartShopBeans = list;
        if (list.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mTvShopping.setVisibility(View.GONE);
            mLlCart.setVisibility(VISIBLE);
        }
        mTvCartNum.setText("购物车共" + cartShopBeans.size() + "件商品");
        mSwipeRefreshLayout.setRefreshing(false);
        mCartShopAdapter.setDataList(list);
    }

    @Override
    public void onGetFailedList(List<FailedGoodsBean> list) {
        if (list.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mTvShopping.setVisibility(View.GONE);
            mLlCart.setVisibility(VISIBLE);
        }
        mFailureAdapter.setDataList(list);
    }

    @Override
    public void onGetFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onRefresh() {
        mSumMoney = 0;
        mCartIds = "";
        mCbAll.setChecked(false);
        mTvSum.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mSumMoney));
        mCartPresenter.getCartList(mSwipeRefreshLayout, GcGuangApplication.getId());
    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.newSafelyShow("删除成功");
        onRefresh();
    }

    @Override
    public void onUpdateSuccess() {
        onRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        } else {
            onRefresh();
        }
    }
}
