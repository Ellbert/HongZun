package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.FollowAdapter;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.module.me.presenter.FollowPresenter;
import com.cecilia.framework.module.me.view.FollowView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FollowActivity extends BaseActivity implements FollowView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_follow)
    SwipeRefreshLayout mSrlFollow;
    @BindView(R.id.lmrv_follow)
    LoadMoreRecyclerView mLmrvFollow;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private FollowAdapter mFollowAdapter;
    private FollowPresenter mFollowPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), FollowActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("我的关注");
        mLmrvFollow.setState(true, new LinearLayoutManager(this));

    }

    @Override
    protected void initData() {
        mFollowPresenter = new FollowPresenter(this);
        mFollowAdapter = new FollowAdapter(this);
        mLmrvFollow.setAdapter(mFollowAdapter);
        onRefresh();
//        mLmrvFollow.setNestedScrollingEnabled(false);
//        List<Object> list = new ArrayList<>();
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        mFollowAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlFollow.setOnRefreshListener(this);
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
    public void onGetListSuccess(List<FollowBean> list) {
        mFollowAdapter.setData(list);
        mLmrvFollow.setLoadMoreNull();
    }

    @Override
    public void onGetFailed() {
        mLmrvFollow.setLoadMoreNull();
    }

    @Override
    public void onRefresh() {
        mFollowPresenter.getFollowList(mSrlFollow,String.valueOf(GcGuangApplication.getId()));
    }
}
