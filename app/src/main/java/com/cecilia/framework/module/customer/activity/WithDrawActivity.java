package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.activity.ChooseWayActivity;
import com.cecilia.framework.module.cart.widget.PayPasswordPopupWindow;
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.presenter.WithdrawPresenter;
import com.cecilia.framework.module.customer.view.WithdrawView;
import com.cecilia.framework.module.customer.widget.AlipayPopupWindow;
import com.cecilia.framework.module.customer.widget.BankCardPopupWindow;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.presenter.MyBankCardPresenter;
import com.cecilia.framework.module.me.view.MyBankCardView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WithDrawActivity extends BaseActivity implements WithdrawView {

    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.cb_union)
    CheckBox mCbUnion;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @BindView(R.id.ll_union)
    LinearLayout mLlUnion;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_union)
    TextView mTvWechat;
    @BindView(R.id.tv_alipay)
    TextView mTvAlipay;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.et_recharge)
    EditText mEvMoney;
    @BindView(R.id.tv_poundage)
    TextView mTvPoundage;
    private double mMoney;
    private int mShopId;
    private String mShopName;
    private BankCardPopupWindow mAddressPopupWindow;
    private AlipayPopupWindow mAlipayPopupWindow;
    private BankCardBean mAddressBean;
    private WithdrawPresenter mMyBankCardPresenter;
    private List<BankCardBean> mAddressBeans;
    private String mAlipayAccount;
    private String mAlipayName;
    private int mRate;
    private int mPayType = 1;
    private PayPasswordPopupWindow mPayPasswordPopupWindow;

    public static void launch(Activity context, double money, int shopId, String shopName) {
        Intent intent = new Intent(context, WithDrawActivity.class);
        intent.putExtra("money", money);
        intent.putExtra("shopId", shopId);
        intent.putExtra("shopName", shopName);
        context.startActivityForResult(intent, 47);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("提现");
        mMoney = getIntent().getDoubleExtra("money", 0);
        mTvBalance.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mMoney));
        mShopId = getIntent().getIntExtra("shopId", 0);
        mShopName = getIntent().getStringExtra("shopName");
    }

    @Override
    protected void initData() {
        mPayPasswordPopupWindow = new PayPasswordPopupWindow();
        mAddressPopupWindow = new BankCardPopupWindow();
        mAlipayPopupWindow = new AlipayPopupWindow();
        mMyBankCardPresenter = new WithdrawPresenter(this);
        DialogUtil.createLoadingDialog(this, "加载中...", false, null);
        mMyBankCardPresenter.getRatio();
        mMyBankCardPresenter.getList(null, String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mAddressPopupWindow.setOnAddressConfirmListener(new BankCardPopupWindow.OnAddressConfirmListener() {
            @Override
            public void onConfirm(BankCardBean addressBean) {
                mAddressBean = addressBean;
                mTvWechat.setText(addressBean.gettBankName() + "(" + StringUtil.getLastBankCard(addressBean.getTCardNum()) + ")");
            }
        });
        mAlipayPopupWindow.setOnAddressConfirmListener(new AlipayPopupWindow.OnConfirmListener() {
            @Override
            public void onConfirm(String account, String name) {
                mAlipayAccount = account;
                mAlipayName = name;
                mTvAlipay.setText("提现到(" + mAlipayAccount + ")");
            }
        });
        mEvMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEvMoney.setText(s);
                        mEvMoney.setSelection(s.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                double money = 0;
                if (StringUtil.isNullOrEmpty(s.toString()) || ".".equals(s.toString())) {
                    mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
                    return;
                }
                money = Double.parseDouble(s.toString());
                if (money > ArithmeticalUtil.div(mMoney, 100)) {
                    ToastUtil.newSafelyShow("输入积分大于可提现积分");
                    mEvMoney.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mMoney));
                    return;
                }
                mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
            }
        });
        mPayPasswordPopupWindow.setOnSkuConfirmListener(new PayPasswordPopupWindow.OnConfirmListener() {
            @Override
            public void onConfirm() {
                double money = ArithmeticalUtil.mul(Double.parseDouble(mEvMoney.getText().toString()), 100);
                if (mPayType == 1) {
                    DialogUtil.createLoadingDialog(WithDrawActivity.this, "提交中...", false, null);
                    mMyBankCardPresenter.withdraw(mShopId, mShopName, mAddressBean.getTId(), Double.valueOf(money).longValue());
                } else if (mPayType == 2) {
                    DialogUtil.createLoadingDialog(WithDrawActivity.this, "提交中...", false, null);
                    mMyBankCardPresenter.withdrawByAlipay(mShopId, mShopName, Double.valueOf(money).longValue(), mAlipayAccount, mAlipayName);
                }
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

    @OnClick({R.id.iv_back, R.id.ll_union, R.id.cb_union, R.id.tv_confirm, R.id.tv_union, R.id.cb_alipay, R.id.tv_alipay, R.id.ll_alipay})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_union:
                mPayType = 1;
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                mCbAlipay.setChecked(false);
                break;
            case R.id.tv_union:
                mPayType = 1;
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                mCbAlipay.setChecked(false);
                break;
            case R.id.cb_union:
                mPayType = 1;
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                mCbAlipay.setChecked(false);
                break;
            case R.id.ll_alipay:
                mPayType = 2;
                mAlipayPopupWindow.initView(this);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
                mCbUnion.setChecked(false);
                break;
            case R.id.tv_alipay:
                mPayType = 2;
                mAlipayPopupWindow.initView(this);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
                mCbUnion.setChecked(false);
                break;
            case R.id.cb_alipay:
                mPayType = 2;
                mAlipayPopupWindow.initView(this);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
                mCbUnion.setChecked(false);
                break;
            case R.id.tv_confirm:
                if (StringUtil.isNullOrEmpty(mEvMoney.getText().toString()) || ".".equals(mEvMoney.getText().toString()) || Double.parseDouble(mEvMoney.getText().toString()) <= 0) {
                    ToastUtil.newSafelyShow("输入的积分不正确");
                    return;
                }
                if (Double.parseDouble(mEvMoney.getText().toString()) < 1 && Double.parseDouble(mEvMoney.getText().toString()) > 0) {
                    ToastUtil.newSafelyShow("不能输入小于1的积分");
                    return;
                }
                if (mPayType == 1) {
                    if (mAddressBean == null) {
                        ToastUtil.newSafelyShow("请选择提现的银行卡");
                        return;
                    }
                    mPayPasswordPopupWindow.initView(WithDrawActivity.this);
                    mPayPasswordPopupWindow.showAtLocation(mTvTitleText, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else if (mPayType == 2) {
                    if (StringUtil.isNullOrEmpty(mAlipayAccount) || StringUtil.isNullOrEmpty(mAlipayName)) {
                        ToastUtil.newSafelyShow("请填写支付宝信息");
                        return;
                    }
                    mPayPasswordPopupWindow.initView(WithDrawActivity.this);
                    mPayPasswordPopupWindow.showAtLocation(mTvTitleText, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;

        }
    }

    @Override
    public void onGetListSuccess(List<BankCardBean> list) {
        DialogUtil.createLoadingDialog(this, "获取中...", false, null);
        mAddressBeans = list;
        for (BankCardBean bankCardBean : mAddressBeans) {
            if (bankCardBean.getTDefault() == 1) {
                mAddressBean = bankCardBean;
                mTvWechat.setText(mAddressBean.gettBankName() + "(" + StringUtil.getLastBankCard(mAddressBean.getTCardNum()) + ")");
            }
        }
        DialogUtil.dismissLoadingDialog();
    }

    @Override
    public void onFailed() {
        setResult(99);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        } else {
            DialogUtil.createLoadingDialog(this, "加载中...", false, null);
            mMyBankCardPresenter.getList(null, String.valueOf(GcGuangApplication.getId()));
        }
    }

    @Override
    public void onGetRatioSuccess(RateBean data) {
        mRate = data.gettService();
    }

    @Override
    public void onWithdrawSuccess() {
        ToastUtil.newSafelyShow("提现申请提交成功");
        finish();
    }

    @Override
    public void onAlipayWithdrawSuccess() {
        ToastUtil.newSafelyShow("提现申请提交成功");
        finish();
    }
}
