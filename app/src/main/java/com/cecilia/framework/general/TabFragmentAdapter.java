package com.cecilia.framework.general;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mData;

    public TabFragmentAdapter(List<String> data, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.mFragments = fragments;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }
}
