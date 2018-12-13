package com.cecilia.framework.module.me.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;

import butterknife.BindView;
import butterknife.OnClick;

public class SafetyActivity extends BaseActivity {

    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), SafetyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_safety;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("安全中心");
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

    @OnClick({R.id.iv_back, R.id.tv_reset})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_reset:
                ResetActivity.launch(SafetyActivity.this);
//                HeaderActivity.launch(DataActivity.this);
                break;
        }
    }
}
