package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapterEx;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.mall.activity.MallActivity;

public class MoreAdapterEx extends BaseRvAdapterEx {

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public MoreAdapterEx(RecyclerView.Adapter adapter, SwipeRefreshLayout mSwipeRefreshLayout, Context mContext) {
        super(adapter);
        this.mContext = mContext;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }

    @Override
    protected RecyclerView.ViewHolder initHeaderViewHolder(View headerView, int viewType) {
        return new BaseViewHolder(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder initFooterViewHolder(View footerView, int viewType) {
        return null;
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        baseViewHolder.getView(R.id.tv_mall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 1);
            }
        });
        baseViewHolder.getView(R.id.tv_brand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 2);
            }
        });
        baseViewHolder.getView(R.id.tv_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 3);
            }
        });
        baseViewHolder.getView(R.id.tv_makeup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 4);
            }
        });
        baseViewHolder.getView(R.id.tv_luxury).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 5);
            }
        });
        baseViewHolder.getView(R.id.tv_woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 6);
            }
        });
        baseViewHolder.getView(R.id.tv_man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 7);
            }
        });
        baseViewHolder.getView(R.id.tv_kid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 8);
            }
        });
        baseViewHolder.getView(R.id.tv_sport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext, 9);
            }
        });
        baseViewHolder.getView(R.id.tv_digital).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MallActivity.launch(mContext,10);
            }
        });
    }

    @Override
    protected void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
