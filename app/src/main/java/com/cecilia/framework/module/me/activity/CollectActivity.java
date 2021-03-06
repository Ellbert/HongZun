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
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.main.adapter.OrderListAdapter;
import com.cecilia.framework.module.me.presenter.CollectPresenter;
import com.cecilia.framework.module.me.view.CollectView;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class CollectActivity extends BaseActivity implements CollectView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.srl_follow)
    SwipeRefreshLayout mSrlFollow;
    @BindView(R.id.lmrv_follow)
    LoadMoreRecyclerView mLmrvFollow;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_text1)
    TextView mTvText1;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    private OrderListAdapter mOrderListAdapter;
    private CollectPresenter mCollectPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), CollectActivity.class);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_follow;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("我的收藏");
        mLmrvFollow.setState(true, new LinearLayoutManager(this));
        mTvNothing.setCompoundDrawablesWithIntrinsicBounds(null, ViewUtil.getDrawable(R.mipmap.icn_no_collect), null, null);
        mTvNothing.setText("您还没有收藏哦~");
    }

    @Override
    protected void initData() {
        mCollectPresenter = new CollectPresenter(this);
        mOrderListAdapter = new OrderListAdapter(this, 5);
        mLmrvFollow.setAdapter(mOrderListAdapter);
        onRefresh();

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlFollow.setOnRefreshListener(this);
        mOrderListAdapter.setOnItemBuyClickListener(new OrderListAdapter.OnOrderItemClickListener() {
            @Override
            public void onItemClick(String info, int id) {
                DialogUtil.createLoadingDialog(CollectActivity.this, "取消中...", true, null);
                mCollectPresenter.removeCollect(String.valueOf(id));
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
    public void onGetSuccess(List<BaseGoodBean> list) {
        if (list.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mSrlFollow.setVisibility(VISIBLE);
            mTvText1.setVisibility(VISIBLE);
        }
        mTvText1.setText("共" + list.size() + "个收藏");
        mOrderListAdapter.setData(list);
        mLmrvFollow.setLoadMoreNull();
    }

    @Override
    public void onGetFailed() {
        mLmrvFollow.setLoadMoreNull();
        setResult(99);
        finish();
    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.newSafelyShow("删除成功！");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mCollectPresenter.getList(mSrlFollow, String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }
}
