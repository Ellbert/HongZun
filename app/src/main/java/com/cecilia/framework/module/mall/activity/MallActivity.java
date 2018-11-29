package com.cecilia.framework.module.mall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.activity.AddressEditActivity;
import com.cecilia.framework.utils.ViewUtil;

import butterknife.BindView;

public class MallActivity extends BaseActivity {

    @BindView(R.id.tl_mall)
    TabLayout mTlMall;
    @BindView(R.id.vp_mall)
    ViewPager mVpMall;

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, MallActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_mall;
    }

    @Override
    protected void initViews() {
        for (int i = 0; i < 10; i++) {
            TabLayout.Tab tab = mTlMall.newTab();
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
            ImageView tv =  view.findViewById(R.id.tv_tab);
            tv.setImageResource(R.drawable.bg_tb_home_selector);
            tab.setCustomView(view);
//            if (i == 0) tv.setFocusable(true);
            mTlMall.addTab(tab);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mTlMall.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                tab.getCustomView().setFocusable(true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                tab.getCustomView().setFocusable(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }
}
