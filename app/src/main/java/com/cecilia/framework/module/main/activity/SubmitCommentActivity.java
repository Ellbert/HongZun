package com.cecilia.framework.module.main.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SubmitCommentActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    public static void launch(Activity context) {
        Intent intent = new Intent(context, SubmitCommentActivity.class);
        context.startActivityForResult(intent, 827);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_submit_comment;
    }

    @Override
    protected void initViews() {

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

    @OnClick({R.id.iv_back, R.id.tv_submit})
    protected void onClick(View v) {
        switch (R.id.iv_back) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                setResult(RESULT_OK);
                finish();
                break;
        }
    }

}
