package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.FollowAdapter;
import com.cecilia.framework.module.me.adapter.NewsAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {

    @BindView(R.id.rv_news)
    LoadMoreRecyclerView mLmrvNews;
    private NewsAdapter mNewsAdapter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), NewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_me_new;
    }

    @Override
    protected void initViews() {
        mLmrvNews.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mNewsAdapter = new NewsAdapter(this);
        mLmrvNews.setAdapter(mNewsAdapter);
//        mLmrvFollow.setNestedScrollingEnabled(false);
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
