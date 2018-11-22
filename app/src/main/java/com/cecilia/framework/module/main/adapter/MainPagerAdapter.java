package com.cecilia.framework.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 为ViewPager添加布局（Fragment），绑定和处理fragments和viewpager之间的逻辑关系
 * <p>
 * Created with IntelliJ IDEA.
 * Author: wangjie  email:tiantian.china.2@gmail.com
 * Date: 13-10-11
 * Time: 下午3:03
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments; // 每个Fragment对应一个Page
    private FragmentManager mFragmentManager;

    public MainPagerAdapter(List<Fragment> fragments, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.mFragments = fragments;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}