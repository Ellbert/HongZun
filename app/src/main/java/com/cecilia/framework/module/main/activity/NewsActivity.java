package com.cecilia.framework.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.adapter.NewsAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.lmrv_news)
    LoadMoreRecyclerView mRvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSrlNews;
    private NewsAdapter mNewsAdapter;

    public static void launch(Context context) {
        Intent intent = new Intent(context, NewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mRvNews.setState(true, new LinearLayoutManager(this));
        mNewsAdapter = new NewsAdapter(this);
        mRvNews.setAdapter(mNewsAdapter);
        mRvNews.setNestedScrollingEnabled(false);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mNewsAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

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
}
