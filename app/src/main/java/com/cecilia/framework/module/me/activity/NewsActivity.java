package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.FollowAdapter;
import com.cecilia.framework.module.me.adapter.NewsAdapter;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.module.me.presenter.MessagePresenter;
import com.cecilia.framework.module.me.view.MessageView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements MessageView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_no_news)
    TextView mTvNothing;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_news)
    LoadMoreRecyclerView mLmrvNews;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private NewsAdapter mNewsAdapter;
    private MessagePresenter mMessagePresenter;

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
        mTvTitleText.setText("消息中心");
        mLmrvNews.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mMessagePresenter = new MessagePresenter(this);
        mNewsAdapter = new NewsAdapter(this);
        mLmrvNews.setAdapter(mNewsAdapter);
        mLmrvNews.setNestedScrollingEnabled(false);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
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

    @Override
    public void onGetListSuccess(List<MessageBean> object) {
        if(object.size() == 0) {
            mTvNothing.setVisibility(View.VISIBLE);
            return;
        }
        mTvNothing.setVisibility(View.GONE);
        mNewsAdapter.setData(object);
        mLmrvNews.setLoadMoreNull();
    }

    @Override
    public void onGetFailed() {
        mLmrvNews.setLoadMoreNull();
    }

    @Override
    public void onRefresh() {
        mMessagePresenter.getMessageList(mSwipeRefreshLayout,String.valueOf(GcGuangApplication.getId()));
    }
}
