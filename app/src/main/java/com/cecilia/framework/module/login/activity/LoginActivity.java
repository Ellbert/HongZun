package com.cecilia.framework.module.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.widget.ForgetPopupWindow;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_forget)
    TextView mTvForget;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_code)
    TextView mTvCode;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.et_invite_code)
    EditText mEtInvite;
//    @BindView(R.id.et_recommend_number)
//    EditText mEtRecommend;
    @BindView(R.id.cb_is_read)
    CheckBox mCbRead;
    @BindView(R.id.v3)
    View mView3;
    @BindView(R.id.v4)
    View mView4;
//    @BindView(R.id.v5)
//    View mView5;
    private SendCodeAgainCount mCount;

    public static void launch(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        setCheckBoxStyle();
    }

    private void setCheckBoxStyle(){
        SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append("我已经阅读并同意服务条款");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(LoginActivity.this, "触发点击事件!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                /**Remove the underline**/
                ds.setUnderlineText(false);
            }
        };
        style.setSpan(clickableSpan, 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCbRead.setText(style);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewUtil.getColor(R.color.txt_blue));
        style.setSpan(foregroundColorSpan, 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        mCbRead.setMovementMethod(LinkMovementMethod.getInstance());
        mCbRead.setText(style);

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
        return true;
    }

    @Override
    protected void doEvents(EventBean event) {
        int type = event.getType();
        setViewsDisplay(View.GONE);
        switch (type) {
            case 1:
                mEtPassword.setHint("请输入新密码");
                mTvCode.setVisibility(View.VISIBLE);
                mEtCode.setVisibility(View.VISIBLE);
                mView3.setVisibility(View.VISIBLE);
                mTvRegister.setVisibility(View.VISIBLE);
                mTvForget.setText("密码登录");
                break;
            case 2:
                mEtPassword.setHint("请输入密码");
                mTvForget.setText("忘记密码");
                mTvRegister.setVisibility(View.VISIBLE);
                break;
            case 3:
                setViewsDisplay(View.VISIBLE);
                mTvRegister.setVisibility(View.GONE);
                mTvForget.setText("密码登录");
                break;
        }
    }

    private void setViewsDisplay(int viewsDisplay){
        mView3.setVisibility(viewsDisplay);
        mView4.setVisibility(viewsDisplay);
//        mView5.setVisibility(viewsDisplay);
        mTvCode.setVisibility(viewsDisplay);
        mEtCode.setVisibility(viewsDisplay);
        mEtInvite.setVisibility(viewsDisplay);
//        mEtRecommend.setVisibility(viewsDisplay);
    }

    @OnClick({R.id.tv_forget,R.id.tv_register,R.id.tv_code,R.id.btn_login})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                if (mTvForget.getText().toString().equals("忘记密码")) {
                    ForgetPopupWindow forgetPopupWindow = new ForgetPopupWindow();
                    forgetPopupWindow.initView(LoginActivity.this,1);
                    forgetPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    EventBean eventBean = new EventBean(2);
                    EventBus.getDefault().post(eventBean);
                }
                break;
            case R.id.tv_register:
                EventBean eventBean = new EventBean(3);
                EventBus.getDefault().post(eventBean);
                break;
            case R.id.tv_code:
                if (!TextUtils.isEmpty(mEtPhone.getText().toString())) {
                    mTvCode.setEnabled(false);
                    mCount.start();
                }else {
                    ToastUtil.newShow("手机号码不能为空");
                }
                break;
            case R.id.btn_login:
                MainActivity.launch(LoginActivity.this);
                finish();
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

}
