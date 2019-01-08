package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.module.customer.presenter.ShopPaymentPresenter;
import com.cecilia.framework.module.customer.view.ShopPaymentView;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopPaymentActivity extends BaseActivity implements ShopPaymentView {

    @BindView(R.id.tv_all_income)
    TextView mTvIncome;
    @BindView(R.id.tv_no_record)
    TextView mTvNoRecord;
    @BindView(R.id.tv_can_withdraw)
    TextView mTvCanWithdraw;
    @BindView(R.id.tv_has_withdraw)
    TextView mTvHasWithdraw;
    @BindView(R.id.ll_withdraw)
    LinearLayout mLlWithdraw;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private int mShopId;
    private String mShopName;
    private double mMoney;
    private ShopPaymentPresenter mShopPaymentPresenter;

    public static void launch(Activity context, int shopId, String shopName) {
        Intent intent = new Intent(context, ShopPaymentActivity.class);
        intent.putExtra("shopId", shopId);
        intent.putExtra("shopName", shopName);
        context.startActivityForResult(intent, 0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shop_payment;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("收入管理");
        mShopId = getIntent().getIntExtra("shopId", 0);
        mShopName = getIntent().getStringExtra("shopName");
    }

    @Override
    protected void initData() {
        mShopPaymentPresenter = new ShopPaymentPresenter(this);
        DialogUtil.createLoadingDialog(this, "加载中...", false, null);
        mShopPaymentPresenter.getWallet(mShopId);
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
    public void onGetWalletSuccess(ShopPaymentBean shopPaymentBean) {
        mMoney = shopPaymentBean.getTMerchantBalance();
        mTvIncome.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.add(ArithmeticalUtil.add(shopPaymentBean.getTMerchantBalance(), shopPaymentBean.getTWaitMoney()), shopPaymentBean.getTTxMoney())));
        mTvCanWithdraw.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mMoney));
        mTvNoRecord.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(shopPaymentBean.getTWaitMoney()));
        mTvHasWithdraw.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(shopPaymentBean.getTTxMoney()));
        if (shopPaymentBean.getTMerchantBalance() > 0) {
            mLlWithdraw.setVisibility(View.VISIBLE);
        } else {
            mLlWithdraw.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @OnClick({R.id.iv_back, R.id.ll_has_withdraw, R.id.ll_can_withdraw, R.id.ll_no_record, R.id.tv_withdraw})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_no_record:
                PaymentActivity.launch(this, 0, mShopId);
                break;
            case R.id.ll_can_withdraw:
                PaymentActivity.launch(this, 1, mShopId);
                break;
            case R.id.ll_has_withdraw:
                PaymentActivity.launch(this, 2, mShopId);
                break;
            case R.id.tv_withdraw:
                WithDrawActivity.launch(this, mMoney, mShopId, mShopName);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        } else {
            DialogUtil.createLoadingDialog(this, "加载中...", false, null);
            mShopPaymentPresenter.getWallet(mShopId);
        }
    }
}
