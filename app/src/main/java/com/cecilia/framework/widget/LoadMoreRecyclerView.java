package com.cecilia.framework.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseRvAdapterEx;
import com.cecilia.framework.utils.LogUtil;

/**
 *
 */
public class LoadMoreRecyclerView extends RecyclerView {

    private boolean mIsCanLoadMore = false;
    private boolean mIsCanLoading = true;
    private int mDistance = 0;

    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnScrolledListener mOnScrolledListener;
    private LayoutManager mLayoutManager;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setState(false, new LinearLayoutManager(getContext()));
    }

    /**
     * 加载更多时，加载成功的时候要调用
     */
    public void setLoadMoreFinish() {
        if (!mIsCanLoadMore) {
            return;
        }
        mIsCanLoading = true;
        BaseLmrvAdapter adapter = null;
        if (getAdapter() instanceof BaseLmrvAdapter) {
            adapter = (BaseLmrvAdapter) getAdapter();
        } else if (getAdapter() instanceof BaseRvAdapterEx) {
            adapter = (BaseLmrvAdapter) ((BaseRvAdapterEx) getAdapter()).getInnerAdapter();
        }
        if (adapter != null) {
            adapter.setState(BaseLmrvAdapter.ALL_GONE);
        }
    }

    /**
     * 加载更多时，加载失败的时候要调用（一般为网络错误）
     */
    public void setLoadMoreError() {
        if (!mIsCanLoadMore) {
            return;
        }
        BaseLmrvAdapter adapter = null;
        if (getAdapter() instanceof BaseLmrvAdapter) {
            adapter = (BaseLmrvAdapter) getAdapter();
        } else if (getAdapter() instanceof BaseRvAdapterEx) {
            adapter = (BaseLmrvAdapter) ((BaseRvAdapterEx) getAdapter()).getInnerAdapter();
        }
        if (adapter != null) {
            adapter.setState(BaseLmrvAdapter.IS_ERROR);
            adapter.getTvError().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnLoadMoreListener.onLoadMore();
                }
            });
        }
    }

    /**
     * 加载更多时，没有数据的时候要调用
     */
    public void setLoadMoreNull() {
        if (!mIsCanLoadMore) {
            return;
        }
        BaseLmrvAdapter adapter = null;
        if (getAdapter() instanceof BaseLmrvAdapter) {
            adapter = (BaseLmrvAdapter) getAdapter();
        } else if (getAdapter() instanceof BaseRvAdapterEx) {
            adapter = (BaseLmrvAdapter) ((BaseRvAdapterEx) getAdapter()).getInnerAdapter();
        }
        if (adapter != null) {
            adapter.setState(BaseLmrvAdapter.IS_NULL);
        }
    }

    /**
     * @param isCan         是否可以加载更多（默认不可以false）
     * @param layoutManager 布局方式（默认设置为LinearLayoutManager）
     */
    public void setState(boolean isCan, LayoutManager layoutManager) {
        mIsCanLoadMore = isCan;
        mLayoutManager = layoutManager;
        this.setLayoutManager(mLayoutManager);
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                BaseLmrvAdapter adapter;
                if (getAdapter() instanceof BaseLmrvAdapter) {
                    adapter = (BaseLmrvAdapter) getAdapter();
                } else if (getAdapter() instanceof BaseRvAdapterEx &&
                        ((BaseRvAdapterEx) getAdapter()).getInnerAdapter() instanceof BaseLmrvAdapter) {
                    adapter = (BaseLmrvAdapter) ((BaseRvAdapterEx) getAdapter()).getInnerAdapter();
                } else {
                    return;
                }
                if (mOnScrolledListener != null) {
                    mDistance += dy;
                    mOnScrolledListener.onScrolled(mDistance);
                }
                if (!mIsCanLoadMore) {
                    adapter.setLoadMoreGone();
                    return;
                }
                if (mOnLoadMoreListener == null || dy <= 0) {
                    return;
                }
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                if (mLayoutManager instanceof GridLayoutManager) {
                    pastVisibleItems = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                } else if (mLayoutManager instanceof LinearLayoutManager) {
                    pastVisibleItems = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                }
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    if (mIsCanLoading) {
                        mIsCanLoading = false;
                        adapter.setState(BaseLmrvAdapter.IS_LOADING);
                        mOnLoadMoreListener.onLoadMore();
                    } else {
                        if (totalItemCount > 1 ) {
                            getAdapter().notifyItemChanged(getAdapter().getItemCount() - 1);
//                        getAdapter().notifyDataSetChanged();
                        }
                    }
                }
            }

        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public void setOnScrolledListener(OnScrolledListener onScrolledListener) {
        this.mOnScrolledListener = onScrolledListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public interface OnScrolledListener {
        void onScrolled(int distance);
    }

}
