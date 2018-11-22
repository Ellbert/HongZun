package com.cecilia.framework.module.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.widget.NamePopupWindow;
import butterknife.OnClick;

public class NameActivity extends BaseActivity {

    public static void launch(Activity context) {
        Intent intent = new Intent(context, NameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_name;
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
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_submit:
                NamePopupWindow forgetPopupWindow = new NamePopupWindow();
                forgetPopupWindow.initView(NameActivity.this);
                forgetPopupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }
}
