package com.cecilia.framework.module.product.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
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
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity implements CommentView, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerView.OnLoadMoreListener {

    @BindView(R.id.srl_comment)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_comment)
    LoadMoreRecyclerView mLoadMoreRecyclerView;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_all)
    TextView mTvAll;
    @BindView(R.id.tv_good)
    TextView mTvGood;
    @BindView(R.id.tv_middle)
    TextView mTvMiddle;
    @BindView(R.id.tv_bad)
    TextView mTvBad;
    private CommentAdapter mCommentAdapter;
    private int mGoodsId;
    private int mPage = 1;
    private CommentPresenter mCommentPresenter;
    private List<CommentBean> mData;
    private int mType ;

    public static void launch(Activity context, int goodsId) {
        Intent intent = new Intent(context, CommentActivity.class);
        intent.putExtra("goods_id", goodsId);
        context.startActivityForResult(intent,0);
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
        mTvAll.callOnClick();

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

    @OnClick({R.id.iv_back,R.id.tv_all,R.id.tv_good,R.id.tv_middle,R.id.tv_bad})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_all:
                mTvAll.setBackgroundResource(R.drawable.bg_btn_small_green_normal);
                mTvGood.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvMiddle.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvBad.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvAll.setTextColor(ViewUtil.getColor(R.color.color_main));
                mTvGood.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvMiddle.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvBad.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mType = 0 ;
                onRefresh();
                break;
            case R.id.tv_good:
                mTvGood.setBackgroundResource(R.drawable.bg_btn_small_green_normal);
                mTvAll.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvMiddle.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvBad.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvGood.setTextColor(ViewUtil.getColor(R.color.color_main));
                mTvAll.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvMiddle.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvBad.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mType = 1 ;
                onRefresh();
                break;
            case R.id.tv_middle:
                mTvMiddle.setBackgroundResource(R.drawable.bg_btn_small_green_normal);
                mTvGood.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvAll.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvBad.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvMiddle.setTextColor(ViewUtil.getColor(R.color.color_main));
                mTvGood.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvAll.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvBad.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mType = 2 ;
                onRefresh();
                break;
            case R.id.tv_bad:
                mTvBad.setBackgroundResource(R.drawable.bg_btn_small_green_normal);
                mTvBad.setTextColor(ViewUtil.getColor(R.color.color_main));
                mTvGood.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvMiddle.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvAll.setTextColor(ViewUtil.getColor(R.color.txt_first));
                mTvGood.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvMiddle.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mTvAll.setBackgroundResource(R.drawable.bg_btn_small_gray_normal);
                mType = 3 ;
                onRefresh();
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
        setResult(99);
        finish();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mData = null;
        mCommentPresenter.getCommentList(mSwipeRefreshLayout, mGoodsId, mType, mPage);
    }

    @Override
    public void onLoadMore() {
        mPage++;
        mCommentPresenter.getCommentList(null, mGoodsId, mType, mPage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }else {
            onRefresh();
        }
    }

}
