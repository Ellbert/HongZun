package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;

import butterknife.OnClick;

public class DataActivity extends BaseActivity {

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), DataActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_data;
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

    @OnClick({R.id.iv_back, R.id.tv_text1,R.id.tv_text2,R.id.tv_text3})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_text1:
                HeaderActivity.launch(DataActivity.this);
                break;
            case R.id.tv_text2:
                NameActivity.launch(DataActivity.this);
                break;
            case R.id.tv_text3:
                PhoneActivity.launch(DataActivity.this);
                break;
        }
    }
}
