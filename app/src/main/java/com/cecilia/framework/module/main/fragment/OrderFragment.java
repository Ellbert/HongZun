package com.cecilia.framework.module.main.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderFragment extends BaseFragment {

    @BindView(R.id.tl_order)
    TabLayout mTlOrder;
    @BindView(R.id.vp_order)
    NoScrollViewPager mVpOrder;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_order,null);
    }

    @Override
    public void initData() {
        List<String> titleList = new ArrayList<>();
        titleList.add("全部");
        titleList.add("待付款");
        titleList.add("待发货");
        titleList.add("待收货");
        titleList.add("待评价");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new OrderListFragment(OrderListFragment.PERSONAL,0));
        fragmentList.add(new OrderListFragment(OrderListFragment.PERSONAL,1));
        fragmentList.add(new OrderListFragment(OrderListFragment.PERSONAL,2));
        fragmentList.add(new OrderListFragment(OrderListFragment.PERSONAL,3));
        fragmentList.add(new OrderListFragment(OrderListFragment.PERSONAL,4));
        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(titleList,fragmentList,getFragmentManager());
        mVpOrder.setOffscreenPageLimit(5);
        mVpOrder.setAdapter(tabFragmentAdapter);
        mTlOrder.setupWithViewPager(mVpOrder);
        LinearLayout linearLayout = (LinearLayout) mTlOrder.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(30); // 设置分割线的pandding
        linearLayout.setDividerDrawable(ViewUtil.getDrawable(R.drawable.bg_tab_dividing));
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 99) {
//            LoginActivity.launch(this.getContext());
//            this.mActivity.finish();
//        }
    }
}
