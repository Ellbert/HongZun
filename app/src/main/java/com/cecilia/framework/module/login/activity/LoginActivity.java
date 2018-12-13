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

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.module.login.presenter.LoginPresenter;
import com.cecilia.framework.module.login.view.LoginView;
import com.cecilia.framework.module.login.widget.ForgetPopupWindow;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.utils.FileUtil;
import com.cecilia.framework.utils.GuangUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

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
    @BindView(R.id.btn_login)
    TextView mBtnLogin;
    private SendCodeAgainCount mCount;
    private LoginPresenter mLoginPresenter;
    private String mType;

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

    private void setCheckBoxStyle() {
        SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append("我已经阅读并同意服务条款");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                ToastUtil.newSafelyShow("点击事件");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                /**Remove the underline**/
                ds.setUnderlineText(false);
            }
        };
        style.setSpan(clickableSpan, 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCbRead.setText(style);
        mCbRead.setChecked(true);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewUtil.getColor(R.color.txt_blue));
        style.setSpan(foregroundColorSpan, 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        mCbRead.setMovementMethod(LinkMovementMethod.getInstance());
        mCbRead.setText(style);

    }

    @Override
    protected void initData() {
        mLoginPresenter = new LoginPresenter(this);
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
                mBtnLogin.setText("确认修改");
                mType = "1";
                break;
            case 2:
                mEtPassword.setHint("请输入密码");
                mTvForget.setText("忘记密码");
                mTvRegister.setVisibility(View.VISIBLE);
                mBtnLogin.setText("登录");
                break;
            case 3:
                setViewsDisplay(View.VISIBLE);
                mTvRegister.setVisibility(View.GONE);
                mTvForget.setText("密码登录");
                mBtnLogin.setText("注册");
                mType = "0";
                break;
        }
    }

    private void setViewsDisplay(int viewsDisplay) {
        mEtInvite.setText("");
        mEtCode.setText("");
        mEtPassword.setText("");
        mEtPhone.setText("");
        mView3.setVisibility(viewsDisplay);
        mView4.setVisibility(viewsDisplay);
//        mView5.setVisibility(viewsDisplay);
        mTvCode.setVisibility(viewsDisplay);
        mEtCode.setVisibility(viewsDisplay);
        mEtInvite.setVisibility(viewsDisplay);
//        mEtRecommend.setVisibility(viewsDisplay);
    }

    @OnClick({R.id.tv_forget, R.id.tv_register, R.id.tv_code, R.id.btn_login})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget:
                if (mTvForget.getText().toString().equals("忘记密码")) {
                    ForgetPopupWindow forgetPopupWindow = new ForgetPopupWindow();
                    forgetPopupWindow.initView(LoginActivity.this, 1);
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
                if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
                    ToastUtil.newShow("手机号码不能为空！");
                    return;
                }
                if (!StringUtil.isMobile(mEtPhone.getText().toString())) {
                    ToastUtil.newShow("请输入正确的手机号码！");
                    return;
                }
                mLoginPresenter.getSms(mEtPhone.getText().toString(),mType);
                mTvCode.setEnabled(false);
                mCount.start();
                break;
            case R.id.btn_login:
                String phone = mEtPhone.getText().toString();
                String code = mEtCode.getText().toString();
                String password = mEtPassword.getText().toString();
                String str = mEtInvite.getText().toString();
                String text = mBtnLogin.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtil.newShow("手机号码不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.newSafelyShow("密码不能为空！");
                    return;
                }
                if (text.equals("登录")) {
                    mLoginPresenter.login(phone, password);
                    return;
                }
                if (code.length() != 4) {
                    ToastUtil.newSafelyShow("验证码不正确！");
                    return;
                }
                if (text.equals("确认修改")) {
                    mLoginPresenter.retrieve(phone, code, password);
                    return;
                }
                if (text.equals("注册")) {
                    if (str.length() == 6 || StringUtil.isMobile(str)) {
                        mLoginPresenter.register(phone, code, str, password);
                    } else {
                        ToastUtil.newSafelyShow("输入的邀请码或邀请人手机号码不正确！");
                    }
                }
//                MainActivity.launch(LoginActivity.this);
//                finish();
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
        ToastUtil.newSafelyShow("验证码发送成功");
    }

    @Override
    public void getFail() {

    }

    @Override
    public void registerSuccess() {
        ToastUtil.newSafelyShow("账号注册成功");
        EventBean eventBean = new EventBean(2);
        EventBus.getDefault().post(eventBean);
    }

    @Override
    public void retrieveSuccess() {
        ToastUtil.newSafelyShow("密码修改成功");
        EventBean eventBean = new EventBean(2);
        EventBus.getDefault().post(eventBean);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        GuangUtil.saveUserInfo(userBean);
        GcGuangApplication.setUserBean(userBean);
        ToastUtil.newSafelyShow("登录成功！");
        MainActivity.launch(this);
        finish();
    }

}
