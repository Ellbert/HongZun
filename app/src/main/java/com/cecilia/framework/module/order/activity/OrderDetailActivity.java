package com.cecilia.framework.module.order.activity;

import android.app.Activity;
import android.app.Dialog;
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
import com.cecilia.framework.module.cart.activity.ChooseWayActivity;
import com.cecilia.framework.module.cart.activity.SummitOrderActivity;
import com.cecilia.framework.module.cart.adapter.SummitGoodsAdapter;
import com.cecilia.framework.module.cart.bean.CartGoodsBean;
import com.cecilia.framework.module.cart.bean.CartShopBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.fragment.OrderListFragment;
import com.cecilia.framework.module.order.bean.OrderDetailBean;
import com.cecilia.framework.module.order.presenter.OrderPresenter;
import com.cecilia.framework.module.order.view.OrderDetailView;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import java.util.ArrayList;
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
    @BindView(R.id.tv_inform)
    TextView mTvInfo;
    private int mOrderId;
    private String mStringInfo;
    private Dialog mBuyDialog;
    private OrderPresenter mOrderPresenter;
    private SummitGoodsAdapter mGoodsAdapter;

    public static void launch(Fragment context, int orderId, String info) {
        Intent intent = new Intent(context.getContext(), OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("info", info);
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
        mStringInfo = getIntent().getStringExtra("info");
        mTvTitleText.setText("订单详情");
        DialogUtil.createLoadingDialog(this, "加载中...", false, null);
        mOrderPresenter.getOrderDetail(mOrderId);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTvInfo.setText(getBtnString());
    }

    @Override
    protected void initDialog() {
        mBuyDialog = DialogUtil.createPromptDialog(this,
                ViewUtil.getString(R.string.app_name), mStringInfo + "吗？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        switch (mTvInfo.getText().toString()) {
                            case "取消订单":
                                DialogUtil.createLoadingDialog(OrderDetailActivity.this, "取消中...", false, null);
                                mOrderPresenter.cancelOrder(mOrderId);
                                return false;
                            case "立即购买":
                                ArrayList<Integer> list = new ArrayList<>();
                                list.add(mOrderId);
                                ChooseWayActivity.launch(OrderDetailActivity.this, list);
                                return false;
                            case "确认收货":
                                DialogUtil.createLoadingDialog(OrderDetailActivity.this, "提交中...", false, null);
                                mOrderPresenter.receiveOrder(mOrderId);
                                return false;
                            case "删除订单":
                                DialogUtil.createLoadingDialog(OrderDetailActivity.this, "删除中...", false, null);
                                mOrderPresenter.deleteOrder(mOrderId);
                                return false;
                            case "投诉店家":
                                return false;
                            default:
                                return false;
                        }
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
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

    @OnClick({R.id.iv_back, R.id.tv_inform})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_inform:
                mBuyDialog.show();
                break;
        }
    }

    @Override
    public void onGetDetailSuccess(OrderDetailBean orderDetailBean) {
        int status = orderDetailBean.getTStatus();
        mTvStatus.setText(StringUtil.getOrderString(status));
        mTvGet.setText("收件人：" + orderDetailBean.getTConsignee());
        mTvContact.setText("联系方式：" + orderDetailBean.getTTel());
        mTvAddress.setText("详细地址：" + orderDetailBean.getTAddress());
        mTvShopName.setText(orderDetailBean.getMerchant().getTName()+"");
        mTvGoodsMoney.setText(getGoodsMoney(orderDetailBean.getGoodsList()));
        mTvPostage.setText(getPostageMoney(orderDetailBean.getGoodsList()));
        mTvOrderMoney.setText(ArithmeticalUtil.getMoneyString(orderDetailBean.getTTotalMoney()));
        mTvPayMoney.setText(ArithmeticalUtil.getMoneyString(orderDetailBean.getTPayMoney()));
        mTvPayWay.setText("支付宝");
        mTvOrderNo.setText(orderDetailBean.getTOutTradeNo()+"");
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
        setResult(99);
        finish();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 87) {
            DialogUtil.createLoadingDialog(this, "加载中...", false, null);
            mOrderPresenter.getOrderDetail(mOrderId);
        } else if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }

    private String getBtnString() {
        switch (mStringInfo) {
            case "取消订单":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "取消订单";
            case "立即购买":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "立即购买";
            case "确认收货":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "确认收货";
            case "删除订单":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "删除订单";
            case "立即评价":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "立即评价");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "投诉店家";
            case "再次购买":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "再次购买");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "投诉店家";
            case "投诉店家":
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "投诉店家");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.VISIBLE);
                return "投诉店家";
            default:
                mGoodsAdapter = new SummitGoodsAdapter(this, R.layout.item_commit_goods, "");
                mRecyclerView.setAdapter(mGoodsAdapter);
                mTvInfo.setVisibility(View.GONE);
                return "";
        }
    }

    @Override
    public void onCancelSuccess() {
        ToastUtil.newSafelyShow("取消成功");
        finish();
    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.newSafelyShow("删除成功");
        finish();
    }

    @Override
    public void onReceiveSuccess() {
        ToastUtil.newSafelyShow("收货成功");
        finish();
    }

}
