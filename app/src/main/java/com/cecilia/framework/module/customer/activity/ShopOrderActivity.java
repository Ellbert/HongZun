package com.cecilia.framework.module.customer.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.module.main.fragment.OrderListFragment;
import com.cecilia.framework.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopOrderActivity extends BaseActivity {

    @BindView(R.id.tl_order)
    TabLayout mTlOrder;
    @BindView(R.id.vp_order)
    ViewPager mVpOrder;


    public static void launch(Context context) {
        Intent intent = new Intent(context, ShopOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shop_order;
    }

    @Override
    protected void initViews() {
        List<String> titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待发货");
        titleList.add("已发货");
        titleList.add("已评价");
        titleList.add("已完成");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OrderListFragment(OrderListFragment.SHOP,0));
        fragmentList.add(new OrderListFragment(OrderListFragment.SHOP,1));
        fragmentList.add(new OrderListFragment(OrderListFragment.SHOP,2));
        fragmentList.add(new OrderListFragment(OrderListFragment.SHOP,3));
        fragmentList.add(new OrderListFragment(OrderListFragment.SHOP,4));
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(titleList,fragmentList,getSupportFragmentManager());
        mVpOrder.setAdapter(tabFragmentAdapter);
        mTlOrder.setupWithViewPager(mVpOrder);
        mVpOrder.setOffscreenPageLimit(5);
        LinearLayout linearLayout = (LinearLayout) mTlOrder.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(30); // 设置分割线的pandding
        linearLayout.setDividerDrawable(ViewUtil.getDrawable(R.drawable.bg_tab_dividing));
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

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
