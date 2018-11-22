package com.cecilia.framework.module.main.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;

import butterknife.OnClick;

public class RechargeActivity extends BaseActivity {

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), RechargeActivity.class);
        context.startActivityForResult(intent, 4);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_recharge;
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

    @OnClick({R.id.tv_confirm, R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                setResult(23);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
