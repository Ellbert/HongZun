package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.adapter.OrderListAdapter;
import com.cecilia.framework.module.me.adapter.FollowAdapter;
import com.cecilia.framework.module.me.presenter.CollectPresenter;
import com.cecilia.framework.module.me.view.CollectView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cecilia.framework.module.main.fragment.OrderListFragment.COLLECT;

public class CollectActivity extends BaseActivity implements CollectView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_follow)
    SwipeRefreshLayout mSrlFollow;
    @BindView(R.id.lmrv_follow)
    LoadMoreRecyclerView mLmrvFollow;
    private OrderListAdapter mOrderListAdapter;
    private CollectPresenter mCollectPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), CollectActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initViews() {
        mLmrvFollow.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mCollectPresenter = new CollectPresenter(this);
        mOrderListAdapter = new OrderListAdapter(this, COLLECT);
        mLmrvFollow.setAdapter(mOrderListAdapter);
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
    public void onGetSuccess(List<Object> list) {
        mOrderListAdapter.setData(list);
    }

    @Override
    public void onGetFailed() {

    }

    @Override
    public void onRefresh() {
        mCollectPresenter.getList(String.valueOf(GcGuangApplication.getId()));
    }
}
