package com.cecilia.framework.module.product.activity;

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
import com.cecilia.framework.module.product.adapter.CommentAdapter;
import com.cecilia.framework.module.product.bean.CommentBean;
import com.cecilia.framework.module.product.presenter.CommentPresenter;
import com.cecilia.framework.module.product.view.CommentView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity implements CommentView, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener {

    @BindView(R.id.srl_comment)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_comment)
    LoadMoreRecyclerView mLoadMoreRecyclerView;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private CommentAdapter mCommentAdapter;
    private int mGoodsId;
    private int mPage = 1;
    private CommentPresenter mCommentPresenter;
    private List<CommentBean> mData;

    public static void launch(Context context, int goodsId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("goods_id", goodsId);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("全部评论");
        mLoadMoreRecyclerView.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mGoodsId = getIntent().getIntExtra("goods_id", 0);
        mCommentPresenter = new CommentPresenter(this);
        mCommentAdapter = new CommentAdapter(this);
        mLoadMoreRecyclerView.setAdapter(mCommentAdapter);
        onRefresh();
//        List<Object> list = new ArrayList<>();
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        mCommentAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLoadMoreRecyclerView.setOnLoadMoreListener(this);
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
    public void onGetCommentListSuccess(List<CommentBean> commentBeans) {
        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = commentBeans;
            mCommentAdapter.setData(commentBeans);
        } else {
            mCommentAdapter.addData(commentBeans);
        }
        if (commentBeans.size() < NetworkConstant.PAGE_SIZE) {
            mLoadMoreRecyclerView.setLoadMoreNull();
        } else {
            mLoadMoreRecyclerView.setLoadMoreFinish();
        }
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mData = null;
        mCommentPresenter.getCommentList(mSwipeRefreshLayout, mGoodsId, 0, mPage);
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mCommentPresenter.getCommentList(null, mGoodsId, 0, mPage);
    }
}
