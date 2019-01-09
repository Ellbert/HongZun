package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.widget.PayPasswordPopupWindow;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.me.presenter.PayPasswordPresenter;
import com.cecilia.framework.module.me.view.PayPasswordView;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PayPasswordActivity extends BaseActivity implements PayPasswordView {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_new_pwd)
    EditText mEtOld;
    @BindView(R.id.et_branch)
    EditText mEtNew;
    @BindView(R.id.et_new_confirm)
    EditText mEtPhone;
    @BindView(R.id.et_bank_card_num)
    EditText mEtCode;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    private SendCodeAgainCount mCount;
    private PayPasswordPresenter mPayPasswordPresenter;
    private String mTel;
    private String mPayPassword;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, PayPasswordActivity.class);
        context.startActivityForResult(intent, 0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay_password;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("设置支付密码");
    }

    @Override
    protected void initData() {
        mTel = SharedPreferenceUtil.getString(this, "tel");
        mCount = new SendCodeAgainCount(60000, 1000);
        mPayPasswordPresenter = new PayPasswordPresenter(this);
        mEtPhone.setText(StringUtil.phoneSimplify(mTel));
        mEtPhone.setEnabled(false);
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

    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.tv_code})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                mPayPassword = mEtOld.getText().toString();
                String newPwd = mEtNew.getText().toString();
                String code = mEtCode.getText().toString();
                if (StringUtil.isNullOrEmpty(mPayPassword)) {
                    ToastUtil.newSafelyShow("支付密码不可为空");
                    return;
                }
                if (!(mPayPassword.length() == 6)) {
                    ToastUtil.newSafelyShow("支付密码必须为六位");
                    return;
                }
                if (StringUtil.isNullOrEmpty(newPwd)) {
                    ToastUtil.newSafelyShow("请输入确认支付密码");
                    return;
                }
                if (!mPayPassword.equals(newPwd)) {
                    ToastUtil.newSafelyShow("密码和确认密码不一致！");
                    return;
                }
                if (!StringUtil.isMobile(mTel)) {
                    ToastUtil.newSafelyShow("输入手机不正确");
                    return;
                }
                if (StringUtil.isNullOrEmpty(code)) {
                    ToastUtil.newSafelyShow("请输入验证码");
                    return;
                }
                DialogUtil.createLoadingDialog(this, "设置中...", false, null);
                mPayPasswordPresenter.setPayPassword(GcGuangApplication.getId(), code, mPayPassword);
                break;
            case R.id.tv_code:
                if (StringUtil.isNullOrEmpty(mEtPhone.getText().toString())) {
                    ToastUtil.newShow("手机号码不能为空！");
                    return;
                }
                if (!StringUtil.isMobile(mTel)) {
                    ToastUtil.newShow("请输入正确的手机号码！");
                    return;
                }
                DialogUtil.createLoadingDialog(this, "发送中...", false, null);
                mPayPasswordPresenter.getSms(mTel, "3");
                mTvCode.setEnabled(false);
                mCount.start();
                break;
        }
    }

    /**
     * 再次发送验证码倒计时类
     */
    public class SendCodeAgainCount extends CountDownTimer {
        private SendCodeAgainCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mTvCode.setEnabled(true);
            mTvCode.setText("获取验证码");
            mCount.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long sec = millisUntilFinished / 1000;
            mTvCode.setText(String.valueOf((sec < 10 ? "0" : "") + sec + "秒"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCount != null) {
            mCount.cancel();
        }
    }

    @Override
    public void getSmsSuccess() {
        ToastUtil.newSafelyShow("发送成功");
    }

    @Override
    public void getFail() {
        setResult(99);
        finish();
    }

    @Override
    public void onSetSuccess() {
        DialogUtil.createLoadingDialog(this, "保存中...", false, null);
        if (SharedPreferenceUtil.putInt(this, "payPassword", Integer.parseInt(mPayPassword))) {
            GcGuangApplication.setsPayPassword(Integer.parseInt(mPayPassword));
        }
        DialogUtil.dismissLoadingDialog();
        ToastUtil.newSafelyShow("设置成功");
        finish();
    }
}
