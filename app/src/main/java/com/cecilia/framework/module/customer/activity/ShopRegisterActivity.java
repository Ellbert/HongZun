package com.cecilia.framework.module.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

public class ShopRegisterActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, ShopRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shop_register;
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

    @OnClick({R.id.tv_done, R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_done:
                EventBean eventBean = new EventBean(1);
                EventBus.getDefault().post(eventBean);
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
