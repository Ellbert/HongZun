package com.cecilia.framework.module.me.fragment;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.module.cart.activity.ChooseWayActivity;
import com.cecilia.framework.module.cart.widget.PayPasswordPopupWindow;
import com.cecilia.framework.module.customer.bean.RateBean;
import com.cecilia.framework.module.customer.bean.ShopPaymentBean;
import com.cecilia.framework.module.customer.widget.AlipayPopupWindow;
import com.cecilia.framework.module.main.presenter.HongBaoPresenter;
import com.cecilia.framework.module.main.view.HongBaoView;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class HongBaoFragment extends BaseFragment implements HongBaoView {

    @BindView(R.id.tv_text1)
    TextView mTvText1;
    @BindView(R.id.tv_text0)
    TextView mTvText0;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.et_balance)
    EditText mEtBalance;
    @BindView(R.id.tv_text4)
    TextView mTvText4;
    @BindView(R.id.tv_poundage)
    TextView mTvPoundage;
    @BindView(R.id.tv_text3)
    TextView mTvText3;
    @BindView(R.id.ll_union)
    LinearLayout mLlUnion;
    @BindView(R.id.tv_union)
    TextView mTvUnion;
    @BindView(R.id.cb_union)
    CheckBox mCbUnion;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.tv_alipay)
    TextView mTvAlipay;
    @BindView(R.id.cb_alipay)
    CheckBox mCbAlipay;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    public static final int CHANGE = 0;
    public static final int FINANCIAL = 1;
    public static final int WITHDRAW = 2;
    private int type;
    private int mRate;
    private long mBalance;
    private AlipayPopupWindow mAlipayPopupWindow;
    private HongBaoPresenter mHongBaoPresenter;
    private PayPasswordPopupWindow mPayPasswordPopupWindow;
    private String mAlipayAccount;
    private String mAlipayName;
    private int mUserId;
    private String mUserName;

    public HongBaoFragment(int type) {
        this.type = type;
    }

    @Override
    protected void onVisible() {
        if (mTvRecharge != null) {
            DialogUtil.createLoadingDialog(this.getContext(), "加载中...", false, null);
            mHongBaoPresenter.getWallet(GcGuangApplication.getId());
            if (type == WITHDRAW) {
                mHongBaoPresenter.getRatio();
            }
        }
    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_hongbao, null);
    }

    @Override
    public void initData() {
        mUserId = SharedPreferenceUtil.getInt(this.getContext(), "userId");
        mUserName = SharedPreferenceUtil.getString(this.getContext(), "userName");
        mAlipayPopupWindow = new AlipayPopupWindow();
        mHongBaoPresenter = new HongBaoPresenter(this);
        mPayPasswordPopupWindow = new PayPasswordPopupWindow();
        initView();
    }

    private void initView() {
        switch (type) {
            case CHANGE:
                mTvText0.setText("可转积分");
                mTvText1.setText("转换积分");
                mEtBalance.setHint("请输入转投积分");
                mTvConfirm.setText("确认转投");
                mTvText3.setVisibility(View.GONE);
                mTvText4.setVisibility(View.GONE);
                mLlUnion.setVisibility(View.GONE);
                mLlAlipay.setVisibility(View.GONE);
                mTvPoundage.setVisibility(View.GONE);
                DialogUtil.createLoadingDialog(this.getContext(), "加载中...", false, null);
                mHongBaoPresenter.getWallet(GcGuangApplication.getId());
                break;
            case FINANCIAL:
                mTvText1.setText("购买积分");
                mEtBalance.setHint("请输入购买积分");
                mTvConfirm.setText("积分充值");
                mTvText4.setText("释放率");
                mTvPoundage.setText("0%~10%");
                mTvText3.setVisibility(View.GONE);
                mLlUnion.setVisibility(View.GONE);
                mLlAlipay.setVisibility(View.GONE);
                mTvPoundage.setTextColor(ViewUtil.getColor(R.color.txt_first));
                break;
            case WITHDRAW:
                mTvText0.setText("可提积分");
                mTvText1.setText("需提积分");
                mEtBalance.setHint("请输入需要提现的积分");
                mLlUnion.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mEtBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtil.isNullOrEmpty(s.toString()) || ".".equals(s.toString())) {
                    return;
                }
                //删除.后面超过两位的数字
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtBalance.setText(s);
                        mEtBalance.setSelection(s.length());
                    }
                }
                double money = Double.parseDouble(s.toString());
                if (money > ArithmeticalUtil.div(mBalance, 100)) {
                    ToastUtil.newSafelyShow("输入积分大于可提现积分");
                    mEtBalance.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mBalance));
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
                if (money > ArithmeticalUtil.div(mBalance, 100)) {
                    ToastUtil.newSafelyShow("输入积分大于可提现积分");
                    mEtBalance.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mBalance));
                    return;
                }
                mTvPoundage.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(ArithmeticalUtil.mul(money, mRate)));
            }
        });
        mPayPasswordPopupWindow.setOnSkuConfirmListener(new PayPasswordPopupWindow.OnConfirmListener() {
            @Override
            public void onConfirm() {
                double money = ArithmeticalUtil.mul(Double.parseDouble(mEtBalance.getText().toString()), 100);
                DialogUtil.createLoadingDialog(HongBaoFragment.this.getContext(), "提交中...", false, null);
                if (type == CHANGE) {
                    mHongBaoPresenter.redelivery(GcGuangApplication.getId(), Double.valueOf(money).intValue());
                } else if (type == FINANCIAL) {
                    mHongBaoPresenter.financialRecharge(GcGuangApplication.getId(), Double.valueOf(money).intValue());
                } else if (type == WITHDRAW) {
                    mHongBaoPresenter.withdraw(mUserId, mUserName, Double.valueOf(money).intValue(), mAlipayAccount, mAlipayName);
                }
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
    }

    @OnClick({R.id.tv_confirm, R.id.cb_alipay, R.id.tv_alipay, R.id.ll_alipay})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (StringUtil.isNullOrEmpty(mEtBalance.getText().toString()) || ".".equals(mEtBalance.getText().toString()) || Double.parseDouble(mEtBalance.getText().toString()) <= 0) {
                    ToastUtil.newSafelyShow("输入的积分不正确");
                    return;
                }
                if (type == WITHDRAW) {
                    if (Double.parseDouble(mEtBalance.getText().toString()) < 1 && Double.parseDouble(mEtBalance.getText().toString()) > 0) {
                        ToastUtil.newSafelyShow("不能输入小于1的积分");
                        return;
                    }
                    if (StringUtil.isNullOrEmpty(mAlipayAccount) || StringUtil.isNullOrEmpty(mAlipayName)) {
                        ToastUtil.newSafelyShow("请填写支付宝信息");
                        return;
                    }
                }
                mPayPasswordPopupWindow.initView(HongBaoFragment.this.mActivity);
                mPayPasswordPopupWindow.showAtLocation(mLlAlipay, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.ll_alipay:
                mAlipayPopupWindow.initView(this.mActivity);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
                break;
            case R.id.tv_alipay:
                mAlipayPopupWindow.initView(this.mActivity);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
                break;
            case R.id.cb_alipay:
                mAlipayPopupWindow.initView(this.mActivity);
                mAlipayPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                mCbAlipay.setChecked(true);
        }
    }

    @Override
    public void onGetWalletSuccess(ShopPaymentBean shopPaymentBean) {
        if (type == FINANCIAL) {
            mBalance = shopPaymentBean.getTBalance();
            mTvRecharge.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mBalance));
        } else {
            mBalance = shopPaymentBean.getTHongBalance();
            mTvRecharge.setText(ArithmeticalUtil.getMoneyStringWithoutSymbol(mBalance));
        }
    }

    @Override
    public void onRedeliverySuccess() {
        ToastUtil.newSafelyShow("转投成功");
        DialogUtil.createLoadingDialog(this.getContext(), "加载中...", false, null);
        mHongBaoPresenter.getWallet(GcGuangApplication.getId());
    }

    @Override
    public void onFinancialRechargeSuccess() {
        ToastUtil.newSafelyShow("理财充值成功");
        DialogUtil.createLoadingDialog(this.getContext(), "加载中...", false, null);
        mHongBaoPresenter.getWallet(GcGuangApplication.getId());
    }

    @Override
    public void onWithdrawSuccess() {
        ToastUtil.newSafelyShow("提现申请成功");
        DialogUtil.createLoadingDialog(this.getContext(), "加载中...", false, null);
        mHongBaoPresenter.getWallet(GcGuangApplication.getId());
    }

    @Override
    public void onFailed() {
        mActivity.setResult(99);
        mActivity.finish();
    }

    @Override
    public void onGetRatioSuccess(RateBean data) {
        mRate = data.gettService();
    }
}
