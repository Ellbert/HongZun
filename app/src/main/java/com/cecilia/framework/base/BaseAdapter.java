package com.cecilia.framework.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.utils.ListUtil;

import java.util.List;

/**
 * Adapter基类
 *
 * @author stone
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    public Context mContext;
    protected List<T> mData;
    protected int mPosition;

    public BaseAdapter(Context context){
        mContext = context;
    }

    public void setData(List<T> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ListUtil.getSize(mData);
    }

    @Override
    public T getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mPosition = position;
        BaseHolder<T> baseHolder;
        if (convertView == null) {
            baseHolder = getHolder();
        } else {
            baseHolder = (BaseHolder<T>) convertView.getTag();
        }
        baseHolder.setData(mData.get(position), position);
        return baseHolder.mHolderView;
    }

    /**
     * 设定ListView里的Item布局Holder
     */
    protected abstract BaseHolder<T> getHolder();

}
