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
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.presenter.WithdrawPresenter;
import com.cecilia.framework.module.customer.view.WithdrawView;
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
    @BindView(R.id.ll_union)
    LinearLayout mLlUnion;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_union)
    TextView mTvWechat;
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
    private BankCardBean mAddressBean;
    private WithdrawPresenter mMyBankCardPresenter;
    private List<BankCardBean> mAddressBeans;
    private int mRate;

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
        mAddressPopupWindow = new BankCardPopupWindow();
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
                if (StringUtil.isNullOrEmpty(s.toString())) {
                    mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
                    return;
                }
                money = Double.parseDouble(s.toString());
                if (money > ArithmeticalUtil.div(mMoney, 100)) {
                    ToastUtil.newSafelyShow("输入金额大于可提现金额");
                    mEvMoney.setText("0");
                    return;
                }
                mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
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

    @OnClick({R.id.iv_back, R.id.ll_union, R.id.cb_union, R.id.tv_confirm, R.id.tv_union})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_union:
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                break;
            case R.id.tv_union:
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                break;
            case R.id.cb_union:
                mAddressPopupWindow.initView(this, mAddressBeans, DensityUtil.dp2px(this, 350));
                mAddressPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbUnion.setChecked(true);
                break;
            case R.id.tv_confirm:
                if (StringUtil.isNullOrEmpty(mEvMoney.getText().toString()) || Double.parseDouble(mEvMoney.getText().toString()) == 0) {
                    ToastUtil.newSafelyShow("输入的金额不正确");
                    return;
                }
                if (mAddressBean == null) {
                    ToastUtil.newSafelyShow("请选择提现的银行卡");
                    return;
                }
                double money = ArithmeticalUtil.mul(Double.parseDouble(mEvMoney.getText().toString()), 100);
                DialogUtil.createLoadingDialog(this, "提交中...", false, null);
                mMyBankCardPresenter.withdraw(mShopId, mShopName, mAddressBean.getTId(), Double.valueOf(money).longValue());
                break;

        }
    }

    @Override
    public void onGetListSuccess(List<BankCardBean> list) {
        mAddressBeans = list;
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
}
