package com.cecilia.framework.module.main.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.presenter.RechargePresenter;
import com.cecilia.framework.module.main.view.RechargeView;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.module.product.activity.ResultActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity implements RechargeView {

    @BindView(R.id.et_recharge)
    EditText mEtRecharge;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.tv_alipay)
    TextView mTvAlipay;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @BindView(R.id.ll_wechat)
    LinearLayout mLlWechat;
    @BindView(R.id.tv_wechat)
    TextView mTvWechat;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.cb_wechat)
    CheckBox mCbWechat;
    @BindView(R.id.tv_submit)
    ImageView mIvSubmit;
    @BindView(R.id.tv_edit)
    TextView mIvEdit;
    private long mMoney;
    private RechargePresenter mRechargePresenter;
    private Dialog mBuyDialog;

    public static void launch(Fragment context, long balance) {
        Intent intent = new Intent(context.getContext(), RechargeActivity.class);
        intent.putExtra("balance", balance);
        context.startActivityForResult(intent, 4);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("充值");
        mIvSubmit.setVisibility(View.GONE);
        mIvEdit.setVisibility(View.VISIBLE);
        mIvEdit.setText("账单");
        mMoney = getIntent().getLongExtra("balance", 0);
        mTvBalance.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mMoney));
    }

    @Override
    protected void initData() {
        mRechargePresenter = new RechargePresenter(this);
    }

    @Override
    protected void initDialog() {
        mBuyDialog = DialogUtil.createPromptDialog(this,
                "提示", "确定支付？", ViewUtil.getString(R.string.ok), new DialogUtil.OnDialogViewButtonClickListener() {
                    @Override
                    public boolean onClick() {
                        DialogUtil.createLoadingDialog(RechargeActivity.this, "创建中...", false, null);
                        long money = Double.valueOf(ArithmeticalUtil.mul(Double.parseDouble(mEtRecharge.getText().toString()), 100)).longValue();
                        mRechargePresenter.createOrder(GcGuangApplication.getId(), money);
                        return false;
                    }
                }, ViewUtil.getString(R.string.cancel), null, null);
    }

    @Override
    protected void initListener() {
        mEtRecharge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        mEtRecharge.setText(s);
                        mEtRecharge.setSelection(s.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtil.isNullOrEmpty(s.toString())) {
                    return;
                }
                double money = Double.parseDouble(s.toString());
//                mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
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

    @OnClick({R.id.tv_confirm, R.id.iv_back, R.id.ll_alipay, R.id.tv_alipay, R.id.cb_alipay, R.id.ll_wechat, R.id.tv_wechat, R.id.cb_wechat, R.id.tv_edit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
//                setResult(23);
//                finish();
                if (StringUtil.isNullOrEmpty(mEtRecharge.getText().toString()) || Double.parseDouble(mEtRecharge.getText().toString()) == 0) {
                    ToastUtil.newSafelyShow("请输入正确积分");
                    return;
                }
                mBuyDialog.show();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_alipay:
                mCbAlipay.setChecked(true);
                break;
            case R.id.tv_alipay:
                mCbAlipay.setChecked(true);
                break;
            case R.id.cb_alipay:
                mCbAlipay.setChecked(true);
                break;
            case R.id.tv_edit:
                PaymentActivity.launch(this, 2, 0);
                break;
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
    public void onCreateOrderSuccess(String orderId) {
        DialogUtil.createLoadingDialog(RechargeActivity.this, "下单中...", false, null);
        mRechargePresenter.buy(orderId, GcGuangApplication.getId(), "积分充值");
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @Override
    public void onGetSuccess(String payInfo) {
        mRechargePresenter.doAlipayPay(this, payInfo);
    }

    @Override
    public void showAlipayResult(String data) {
        ResultActivity.launch(this, data, 2);
        if ("9000".equals(data)) {
            finish();
        }
    }
}
