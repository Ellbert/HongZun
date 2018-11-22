package com.cecilia.framework.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author stone
 */

public abstract class BaseLmrvAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int LOAD_MORE = 100;

    public static final int ALL_GONE = 0;
    public static final int IS_LOADING = 1;
    public static final int IS_ERROR = 2;
    public static final int IS_NULL = 3;

    private LoadMoreViewHolder mLoadMoreViewHolder;
    protected Context mContext;
    private List<T> mData;

    private int mState = IS_LOADING;
    private int mHeadersCount = 0;
    private boolean mIsShowTip = true;

    public BaseLmrvAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<T> data) {
        mData = data;
        BaseLmrvAdapter.this.notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        mData.addAll(data);
        BaseLmrvAdapter.this.notifyDataSetChanged();
    }

    /**
     * 当LoreMoreRecyclerView需要添加头部布局的时候，需要将头部布局的数量传入，不然将会导致底部加载更多布局异常<br/>
     * （使用{@link BaseRvAdapterEx}进行添加头部布局的时候，调用{@link BaseRvAdapterEx#getHeadersCount()}去传递头部布局的数量）
     */
    public void setHeadersCount(int headersCount) {
        mHeadersCount = headersCount;
    }

    @Override
    public int getItemCount() {
        return mData != null && mData.size() != 0 ? mData.size() + 1 : 0;
    }

    @Override
    public final int getItemViewType(int position) {
        if (position == this.getItemCount() - (mIsShowTip ? 1 : 0)) {
            return LOAD_MORE;
        }
        return getItemRecyclerViewType(position);
    }

    public int getItemRecyclerViewType(int position) {
        return 0;
    }

    @NonNull
    @Override
    public final BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        if (mLoadMoreViewHolder == null) {
            mLoadMoreViewHolder = new LoadMoreViewHolder(layoutInflater.inflate(R.layout.footer_rv_load_more, parent, false));
        }
        if (viewType == LOAD_MORE) {
            return mLoadMoreViewHolder;
        }
        return onCreateRecyclerViewHolder(layoutInflater, parent, viewType);
    }

    public abstract BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (position == this.getItemCount() - 1) {
            if (holder instanceof LoadMoreViewHolder) {
                LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
                loadMoreViewHolder.mLlLoading.setVisibility(View.GONE);
                loadMoreViewHolder.mTvError.setVisibility(View.GONE);
                loadMoreViewHolder.mTvNull.setVisibility(View.GONE);
                switch (mState) {
                    case ALL_GONE:
                        //mIsShowTip = false;
                        break;
                    case IS_LOADING:
                        mIsShowTip = true;
                        loadMoreViewHolder.mIvLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.rotate_loading));
                        loadMoreViewHolder.mLlLoading.setVisibility(View.VISIBLE);
                        break;
                    case IS_ERROR:
                        mIsShowTip = true;
                        loadMoreViewHolder.mTvError.setVisibility(View.VISIBLE);
                        break;
                    case IS_NULL:
                        mIsShowTip = true;
                        loadMoreViewHolder.mTvNull.setVisibility(View.VISIBLE);
                        break;
                }
                return;
            }
        }
        onBindRecyclerViewHolder(holder, mData.get(position));
    }

    public abstract void onBindRecyclerViewHolder(BaseViewHolder holder, T data);

    public void setState(int state) {
        mState = state;
        this.notifyItemChanged(getItemCount() - 1);
    }

    public void setLoadMoreGone() {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) mLoadMoreViewHolder.itemView.getLayoutParams();
        mLoadMoreViewHolder.itemView.setVisibility(View.GONE);
        param.height = 0;
        param.width = 0;
        mLoadMoreViewHolder.itemView.setLayoutParams(param);
    }

    public TextView getTvError() {
        return mLoadMoreViewHolder.mTvError;
    }

    /**
     * 让RecyclerView在使用GridLayoutManager布局下，依旧让尾部的加载更多布局呈LinearLayoutManager布局
     */
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position - mHeadersCount);
                    if (viewType == LOAD_MORE) {
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

    static class LoadMoreViewHolder extends BaseViewHolder {

        @BindView(R.id.ll_loading)
        LinearLayout mLlLoading;
        @BindView(R.id.iv_loading)
        ImageView mIvLoading;
        @BindView(R.id.tv_error)
        TextView mTvError;
        @BindView(R.id.tv_null)
        TextView mTvNull;
        @BindView(R.id.ll_load_more)
        LinearLayout mLlLoadMore;

        LoadMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
