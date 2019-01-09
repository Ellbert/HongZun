package com.cecilia.framework.module.vip.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.product.activity.ResultActivity;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.module.vip.bean.VipOrderBean;
import com.cecilia.framework.module.vip.presenter.BuyVipPresenter;
import com.cecilia.framework.module.vip.view.BuyVipView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class BuyVipActivity extends BaseActivity implements BuyVipView {

    @BindView(R.id.iv_vip)
    ImageView mIvVip;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @BindView(R.id.cb_balance)
    CheckBox mCbBalance;
    private int mPayType = 1;
    private VipBean mVipBean;
    private Dialog mBuyDialog;
    private BuyVipPresenter mBuyVipPresenter;

    public static void launch(Activity context, VipBean vipBean) {
        Intent intent = new Intent(context, BuyVipActivity.class);
        intent.putExtra("vipBean", vipBean);
        context.startActivityForResult(intent, 0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_buy_vip;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("激活会员");
    }

    @Override
    protected void initData() {
        mVipBean = (VipBean) getIntent().getSerializableExtra("vipBean");
        ImageUtil.loadNetworkImage(this, NetworkConstant.IMAGE_URL + mVipBean.getTImage(), mIvVip, null);
        mTvMoney.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mVipBean.getTPrice()));
        mBuyVipPresenter = new BuyVipPresenter(this);
    }


    @Override
    protected void initDialog() {
        mBuyDialog = DialogUtil.createPromptDialog(this,
                "提示", "确定支付？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        DialogUtil.createLoadingDialog(BuyVipActivity.this, "创建订单中...", false, null);
                        mBuyVipPresenter.createOrder(GcGuangApplication.getId(), mVipBean.getTId());
                        return false;
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

    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.cb_alipay, R.id.tv_alipay, R.id.ll_alipay
//            , R.id.cb_balance, R.id.tv_balance, R.id.ll_balance
    })
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                mBuyDialog.show();
                break;
            case R.id.cb_alipay:
                mPayType = 1;
                mCbAlipay.setChecked(true);
                mCbBalance.setChecked(false);
                break;
            case R.id.tv_alipay:
                mPayType = 1;
                mCbAlipay.setChecked(true);
                mCbBalance.setChecked(false);
                break;
            case R.id.ll_alipay:
                mPayType = 1;
                mCbAlipay.setChecked(true);
                mCbBalance.setChecked(false);
                break;
//            case R.id.tv_balance:
//                mPayType = 2;
//                mCbBalance.setChecked(true);
//                mCbAlipay.setChecked(false);
//                break;
//            case R.id.cb_balance:
//                mPayType = 2;
//                mCbBalance.setChecked(true);
//                mCbAlipay.setChecked(false);
//                break;
//            case R.id.ll_balance:
//                mPayType = 2;
//                mCbBalance.setChecked(true);
//                mCbAlipay.setChecked(false);
//                break;
        }
    }

    @Override
    public void onCreateOrder(VipOrderBean orderBean) {
        DialogUtil.createLoadingDialog(BuyVipActivity.this, "购买中...", false, null);
        if (mPayType == 1) {
            mBuyVipPresenter.buy(GcGuangApplication.getId(), orderBean.getOrderId(), mVipBean.getTName() + " 会员激活");
        } else if (mPayType == 2) {
            mBuyVipPresenter.hongBaoPay(String.valueOf(orderBean.getOrderId()), GcGuangApplication.getId());
        }
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onGetSuccess(String payInfo) {
        mBuyVipPresenter.doAlipayPay(this, payInfo);
    }

    @Override
    public void showAlipayResult(String data) {
        ResultActivity.launch(this, data, 1);
        if ("9000".equals(data) && SharedPreferenceUtil.putInt(this, "level", mVipBean.getTId())) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }

    @Override
    public void onLoginFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onHongBaoPaySuccess() {
        ResultActivity.launch(this, "9000", 1);
        finish();
    }
}
