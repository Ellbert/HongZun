package com.cecilia.framework.module.mall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.activity.MainActivity;
import com.cecilia.framework.module.main.adapter.MainPagerAdapter;
import com.cecilia.framework.module.mall.fragment.MallFragment;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MallActivity extends BaseActivity {

    @BindView(R.id.tl_mall)
    TabLayout mTlMall;
    @BindView(R.id.vp_mall)
    NoScrollViewPager mVpMall;
    @BindView(R.id.tv_search)
    EditText mEtSearch;
    private List<Fragment> mFragments = new ArrayList<>();
    private int mIndex;

    public static void launch(Activity context, int index) {
        Intent intent = new Intent(context, MallActivity.class);
        intent.putExtra("index", index);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_mall;
    }

    @Override
    protected void initViews() {
        View mTab1 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv1 = mTab1.findViewById(R.id.tv_tab);
        tv1.setImageResource(R.drawable.bg_tb_all_selector);
        View mTab2 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv2 = mTab2.findViewById(R.id.tv_tab);
        tv2.setImageResource(R.drawable.bg_tb_mall_selector);
        View mTab3 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv3 = mTab3.findViewById(R.id.tv_tab);
        tv3.setImageResource(R.drawable.bg_tb_brand_selector);
        View mTab4 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv4 = mTab4.findViewById(R.id.tv_tab);
        tv4.setImageResource(R.drawable.bg_tb_food_selector);
        View mTab5 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv5 = mTab5.findViewById(R.id.tv_tab);
        tv5.setImageResource(R.drawable.bg_tb_makeup_selector);
        View mTab6 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv6 = mTab6.findViewById(R.id.tv_tab);
        tv6.setImageResource(R.drawable.bg_tb_luxury_selector);
        View mTab7 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv7 = mTab7.findViewById(R.id.tv_tab);
        tv7.setImageResource(R.drawable.bg_tb_woman_selector);
        View mTab8 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv8 = mTab8.findViewById(R.id.tv_tab);
        tv8.setImageResource(R.drawable.bg_tb_man_selector);
        View mTab9 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv9 = mTab9.findViewById(R.id.tv_tab);
        tv9.setImageResource(R.drawable.bg_tb_kid_selector);
        View mTab10 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv10 = mTab10.findViewById(R.id.tv_tab);
        tv10.setImageResource(R.drawable.bg_tb_sport_selector);
        View mTab11 = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView tv11 = mTab11.findViewById(R.id.tv_tab);
        tv11.setImageResource(R.drawable.bg_tb_digital_selector);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab1), 0);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab2), 1);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab3), 2);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab4), 3);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab5), 4);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab6), 5);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab7), 6);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab8), 7);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab9), 8);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab10), 9);
        mTlMall.addTab(mTlMall.newTab().setCustomView(mTab11), 10);
        LinearLayout linearLayout = (LinearLayout) mTlMall.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(30); // 设置分割线的pandding
        linearLayout.setDividerDrawable(ViewUtil.getDrawable(R.drawable.bg_tab_dividing));
        for (int i = -1; i < 10; i++) {
            mFragments.add(new MallFragment(i));
        }
    }

    @Override
    protected void initData() {
        mVpMall.setOffscreenPageLimit(10);
        mVpMall.setAdapter(new MainPagerAdapter(mFragments, getSupportFragmentManager()));
        mIndex = getIntent().getIntExtra("index", 0);
        mTlMall.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTlMall.getTabAt(mIndex).select();
            }
        }, 50);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mEtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTlMall.getTabAt(0).select();
            }
        });
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //先隐藏键盘
                    InputMethodManager imm = ((InputMethodManager) MallActivity.this.getSystemService(INPUT_METHOD_SERVICE));
                    if (imm != null) {
                        View view = MallActivity.this.getCurrentFocus();
                        if (view != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        }
                    }

                    //其次再做相应操作
                    String inputContent = mEtSearch.getText().toString();
                    MallFragment fragment = (MallFragment) mFragments.get(0);
                    fragment.setData(inputContent);
                }
                return false;
            }
        });
        mTlMall.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpMall.setCurrentItem(tab.getPosition());
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

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }
}
