package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class PhoneActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    private SendCodeAgainCount mCount;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, PhoneActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_phone;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mCount = new SendCodeAgainCount(60000, 1000);
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

    @OnClick({R.id.iv_back,R.id.tv_code,R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_code:
                if (!TextUtils.isEmpty(mEtPhone.getText().toString())) {
                    mTvCode.setEnabled(false);
                    mCount.start();
                }else {
                    ToastUtil.newShow("手机号码不能为空");
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                break;
        }
    }

    /**
     * 再次发送验证码倒计时类
     */
    class SendCodeAgainCount extends CountDownTimer {
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
}
