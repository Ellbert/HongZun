package com.cecilia.framework.module.mall.fragment;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.main.adapter.ProductAdapter;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.mall.presenter.MallPresenter;
import com.cecilia.framework.module.mall.view.MallView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class MallFragment extends BaseFragment implements MallView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lmrv_mall)
    LoadMoreRecyclerView mLmrvMall;
    @BindView(R.id.srl_mall)
    SwipeRefreshLayout mSrlMall;
    private ProductAdapter mMoreAdapter;
    private int mIndex;
    private int mClassifyId;
    private int mPage = 1;
    private MallPresenter mMallPresenter;
    private String mKeyword;
    private List<GoodsBean> mData;

    public MallFragment(int mIndex) {
        this.mIndex = mIndex;
        mClassifyId = getClassifyId();
    }

    @Override
    protected void onVisible() {
        if (mSrlMall != null) {
            onRefresh();
        }
    }

    @Override
    protected void onInvisible() {

    }

    @SuppressLint("InflateParams")
    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_mall, null);
    }

    @Override
    public void initData() {
        LogUtil.e("mClassifyId == " + mClassifyId);
        mMallPresenter = new MallPresenter(this);
        mLmrvMall.setState(true, new GridLayoutManager(getContext(), 2));
        mMoreAdapter = new ProductAdapter(getContext());
        mLmrvMall.setAdapter(mMoreAdapter);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlMall.setOnRefreshListener(this);
        mLmrvMall.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                MallFragment.this.onLoadMore();
            }
        });
    }

    public void setData(String keyword) {
        this.mKeyword = keyword;
        this.mPage = 1;
        onRefresh();
    }

    @Override
    public void onGetSuccess(List<GoodsBean> goodsBeans) {
        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = goodsBeans;
            mMoreAdapter.setData(goodsBeans);
        } else {
            mMoreAdapter.addData(goodsBeans);
        }
        if (goodsBeans.size() < NetworkConstant.PAGE_SIZE) {
            mLmrvMall.setLoadMoreNull();
        } else {
            mLmrvMall.setLoadMoreFinish();
        }
    }

    @Override
    public void onGetFailed() {

    }

    private void onLoadMore() {
        mPage++;
        switch (mIndex) {
            case -1:
                mMallPresenter.search(null, mKeyword, mPage);
                break;
            default:
                mMallPresenter.getGoodsList(null, mClassifyId, mPage);
                break;
        }
    }

    @Override
    public void onRefresh() {
        mData = null;
        mPage = 1;
        switch (mIndex) {
            case -1:
                mMallPresenter.search(mSrlMall, mKeyword, mPage);
                break;
            default:
                mMallPresenter.getGoodsList(mSrlMall, mClassifyId, mPage);
                break;
        }
    }

    @Override
    public void onGetGoodsListSuccess(List<GoodsBean> goodsBeans) {
        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = goodsBeans;
            mMoreAdapter.setData(goodsBeans);
        } else {
            mMoreAdapter.addData(goodsBeans);
        }
        if (goodsBeans.size() < NetworkConstant.PAGE_SIZE) {
            mLmrvMall.setLoadMoreNull();
        } else {
            mLmrvMall.setLoadMoreFinish();
        }
    }

    private int getClassifyId() {
        switch (mIndex) {
            case 0:
                return 10;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
        }
        return 0;
    }
}
