package com.cecilia.framework.module.product.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.me.activity.AddressActivity;
import com.cecilia.framework.module.me.activity.AddressEditActivity;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ResultActivity extends BaseActivity {

    @BindView(R.id.tv_success)
    TextView mTvSuccess;
    @BindView(R.id.tv_failed)
    TextView mTvFailed;
    @BindView(R.id.tv_done)
    TextView mTvDone;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private String code;

    public static void launch(Context context, String code) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_result;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("支付结果");
        code = getIntent().getStringExtra("code");
        initView();
    }

    private void initView() {
        if ("9000".equals(code)) {
            mTvFailed.setVisibility(View.GONE);
            mTvDone.setVisibility(View.VISIBLE);
            mTvSuccess.setVisibility(View.VISIBLE);
        } else {
            mTvFailed.setVisibility(View.VISIBLE);
            mTvDone.setVisibility(View.GONE);
            mTvSuccess.setVisibility(View.GONE);
            initTextView();
        }
    }

    private void initTextView() {
        SpannableStringBuilder style = new SpannableStringBuilder();
        //设置文字
        style.append("充值失败，请联系客服");
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
        style.setSpan(clickableSpan, 6, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvFailed.setText(style);
        //设置部分文字颜色
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ViewUtil.getColor(R.color.txt_blue));
        style.setSpan(foregroundColorSpan, 6, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //配置给TextView
        mTvFailed.setMovementMethod(LinkMovementMethod.getInstance());
        mTvFailed.setText(style);
    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.iv_back, R.id.tv_done})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_done:
                finish();
                break;
        }
    }
}
