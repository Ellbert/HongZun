package com.cecilia.framework.base;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Adapter拓展基类（为RecyclerView添加header、footer，支持多个）
 *
 * @author stone
 */

public abstract class BaseRvAdapterEx extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;

    protected List<?> mData;

    public BaseRvAdapterEx(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    public void setData(List<?> data) {
        mData = data;
        BaseRvAdapterEx.this.notifyDataSetChanged();
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    /**
     * 返回顶部视图的数量<br/>
     * （注意，则需要在添加完顶部视图后才能调用，不然数量会有误）
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * 返回尾部视图的数量<br/>
     * （注意，则需要在添加完尾部视图后才能调用，不然数量会有误）
     */
    public int getFootersCount() {
        return mFooterViews.size();
    }

    /**
     * 返回中部视图的数量
     */
    protected int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            return initHeaderViewHolder(mHeaderViews.get(viewType), viewType);
        }
        if (mFooterViews.get(viewType) != null) {
            return initFooterViewHolder(mFooterViews.get(viewType), viewType);
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    /**
     * 用于初始化顶部布局
     *
     * @param viewType 用来判断是哪个顶部布局（都是同一个布局时就无须判断了）<br/>
     *                 数值从{@link #BASE_ITEM_TYPE_HEADER}开始
     */
    protected abstract RecyclerView.ViewHolder initHeaderViewHolder(View headerView, int viewType);

    /**
     * 用于初始化尾部布局
     *
     * @param viewType 用来判断是哪个尾部布局（都是同一个布局时就无须判断了）<br/>
     *                 数值从{@link #BASE_ITEM_TYPE_FOOTER}开始
     */
    protected abstract RecyclerView.ViewHolder initFooterViewHolder(View footerView, int viewType);

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position)) {
            onBindHeaderViewHolder(holder, position);
            return;
        }
        if (isFooterViewPos(position)) {
            onBindFooterViewHolder(holder, position);
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    /**
     * 用于对顶部布局进行数据添加
     *
     * @param position 用来判断是哪个顶部布局（都是同一个布局时就无须判断了）<br/>
     *                 position < {@link #getHeadersCount()}
     */
    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 用于对尾部布局进行数据添加
     *
     * @param position 用来判断是哪个尾部布局（都是同一个布局时就无须判断了）<br/>
     *                 position >= {@link #getHeadersCount()} + {@link #getRealItemCount()}
     */
    protected abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    /**
     * 让RecyclerView在使用GridLayoutManager布局下，依旧让头部、尾部布局呈LinearLayoutManager布局
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (mHeaderViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    } else if (mFooterViews.get(viewType) != null) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (spanSizeLookup != null)
                        return spanSizeLookup.getSpanSize(position);
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    /**
     * 让RecyclerView在使用StaggeredGridLayoutManager布局下，依旧让头部、尾部布局呈LinearLayoutManager布局
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
            }
        }
    }

    public synchronized void synchronizedNotify() {
        this.notifyDataSetChanged();
    }
}
