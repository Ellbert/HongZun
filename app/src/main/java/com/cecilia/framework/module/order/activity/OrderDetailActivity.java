package com.cecilia.framework.module.order.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.activity.CartActivity;
import com.cecilia.framework.module.cart.adapter.SummitGoodsAdapter;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.order.bean.OrderDetailBean;
import com.cecilia.framework.module.order.presenter.OrderPresenter;
import com.cecilia.framework.module.order.view.OrderDetailView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements OrderDetailView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_get)
    TextView mTvGet;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_to_shop)
    TextView mTvShopName;
    @BindView(R.id.rv_goods_cart)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_goods_money)
    TextView mTvGoodsMoney;
    @BindView(R.id.tv_postage)
    TextView mTvPostage;
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney;
    @BindView(R.id.tv_pay_money)
    TextView mTvPayMoney;
    @BindView(R.id.tv_pay_way)
    TextView mTvPayWay;
    @BindView(R.id.tv_order_no)
    TextView mTvOrderNo;
    @BindView(R.id.tv_create_time)
    TextView mTvCreateTime;
    @BindView(R.id.tv_pay_time)
    TextView mTvPayTime;
    @BindView(R.id.tv_deal_time)
    TextView mTvDealTime;
    private int mOrderId;
    private boolean isBuy = false;
    private boolean visible = false;
    private OrderPresenter mOrderPresenter;
    private SummitGoodsAdapter mGoodsAdapter;

    public static void launch(Context context, int orderId, boolean flag, boolean visible) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("isBuy", flag);
        intent.putExtra("visible", visible);
        context.startActivity(intent);
    }

    public static void launch(Fragment context, int orderId, boolean flag, boolean visible) {
        Intent intent = new Intent(context.getContext(), OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("isBuy", flag);
        intent.putExtra("visible", visible);
        context.startActivityForResult(intent, 87);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mOrderPresenter = new OrderPresenter(this);
        mOrderId = getIntent().getIntExtra("orderId", 0);
        isBuy = getIntent().getBooleanExtra("isBuy", false);
        visible = getIntent().getBooleanExtra("visible", false);
        mTvTitleText.setText("订单详情");
        DialogUtil.createLoadingDialog(this, "加载中...", false, null);
        mOrderPresenter.getOrderDetail(mOrderId);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    public void onGetDetailSuccess(OrderDetailBean orderDetailBean) {
        int status = orderDetailBean.getTStatus();
        if (status == 3) {
            if (isBuy) {
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, visible, true);
            } else {
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, visible, false);
            }
            mRecyclerView.setAdapter(mGoodsAdapter);
        } else {
            mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, visible, isBuy);
            mRecyclerView.setAdapter(mGoodsAdapter);
        }
        mTvStatus.setText(StringUtil.getOrderString(status));
        mTvGet.setText("收件人：" + orderDetailBean.getTConsignee());
        mTvContact.setText("联系方式：" + orderDetailBean.getTTel());
        mTvAddress.setText("详细地址：" + orderDetailBean.getTAddress());
        mTvShopName.setText(orderDetailBean.getMerchant().getTName());
        mTvGoodsMoney.setText(getGoodsMoney(orderDetailBean.getGoodsList()));
        mTvPostage.setText(getPostageMoney(orderDetailBean.getGoodsList()));
        mTvOrderMoney.setText(ArithmeticalUtil.getMoneyString(orderDetailBean.getTTotalMoney()));
        mTvPayMoney.setText(ArithmeticalUtil.getMoneyString(orderDetailBean.getTPayMoney()));
        mTvPayWay.setText("支付宝");
        mTvOrderNo.setText(orderDetailBean.getTOutTradeNo());
        mGoodsAdapter.setDataList(orderDetailBean.getGoodsList());
        mTvCreateTime.setText(StringUtil.stampToDate(orderDetailBean.getTCreatetime()));
        if (orderDetailBean.getTPayTime() == 0) {
            mTvPayTime.setText("-");
        } else {
            mTvPayTime.setText(StringUtil.stampToDate(orderDetailBean.getTPayTime()));
        }
        if (orderDetailBean.getTReceiveTime() == 0) {
            mTvDealTime.setText("-");
        } else {
            mTvDealTime.setText(StringUtil.stampToDate(orderDetailBean.getTReceiveTime()));
        }
    }

    @Override
    public void onGetFailed() {

    }

    private String getGoodsMoney(List<GoodsBean> goodsBeans) {
        double sumMoney = 0;
        for (GoodsBean goodsBean : goodsBeans) {
            sumMoney = ArithmeticalUtil.add(sumMoney, goodsBean.getTGoodsMoney());
        }
        return ArithmeticalUtil.getMoneyString(sumMoney);
    }

    private String getPostageMoney(List<GoodsBean> goodsBean) {
        double sumMoney = 0;
        for (GoodsBean goodBean : goodsBean) {
            sumMoney = ArithmeticalUtil.add(sumMoney, goodBean.getTLogisticsMoney());
        }
        return ArithmeticalUtil.getMoneyString(sumMoney);
    }

    @Override
    protected void onFinish() {
        setResult(10);
        super.onFinish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 87) {
            DialogUtil.createLoadingDialog(this, "加载中...", false, null);
            mOrderPresenter.getOrderDetail(mOrderId);
        }
    }
}
