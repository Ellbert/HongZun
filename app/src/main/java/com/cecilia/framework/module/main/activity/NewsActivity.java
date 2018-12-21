package com.cecilia.framework.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.adapter.NewsAdapter;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.presenter.NoticePresenter;
import com.cecilia.framework.module.main.view.NoticeView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements NoticeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lmrv_news)
    LoadMoreRecyclerView mRvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout mSrlNews;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private NewsAdapter mNewsAdapter;
    private NoticePresenter mNoticePresenter;
    private int mPage = 1;
    private List<NoticeBean> mData;

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
        mTvTitleText.setText("消息推送");
        mNoticePresenter = new NoticePresenter(this);
        mRvNews.setState(true, new LinearLayoutManager(this));
        mNewsAdapter = new NewsAdapter(this);
        mRvNews.setAdapter(mNewsAdapter);
        mRvNews.setNestedScrollingEnabled(false);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlNews.setOnRefreshListener(this);
        mRvNews.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                mNoticePresenter.noticeList(null, mPage);
            }
        });
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
    public void onGetListSuccess(List<NoticeBean> data) {
        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = data;
            mNewsAdapter.setData(data);
        } else {
            mNewsAdapter.addData(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
            mRvNews.setLoadMoreNull();
        } else {
            mRvNews.setLoadMoreFinish();
        }
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onRefresh() {
        mData = null;
        mPage = 1;
        mNoticePresenter.noticeList(mSrlNews, mPage);
    }

}
