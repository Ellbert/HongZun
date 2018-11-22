package com.cecilia.framework.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Holder基类
 *
 * @author stone
 */

public abstract class BaseHolder<T> {

    public BaseAdapter<T> mAdapter;
    public View mHolderView;
    public T mData;

    public BaseHolder(BaseAdapter<T> adapter) {
        mAdapter = adapter;
        mHolderView = getHolderView();
        mHolderView.setTag(this);
        ButterKnife.bind(this, mHolderView);
        initHolderView();
    }

    /**
     * 设置数据
     */
    public void setData(T data, int position) {
        mData = data;
        refreshHolderView(data, position);
    }

    /**
     * 返回Holder视图
     */
    protected abstract View getHolderView();

    /**
     * 初始化Holder视图（无须再变更的控件）
     */
    protected void initHolderView() {
    }

    /**
     * 刷新Holder视图
     */
    protected abstract void refreshHolderView(T data, int position);

}
