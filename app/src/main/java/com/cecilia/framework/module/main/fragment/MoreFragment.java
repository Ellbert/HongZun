package com.cecilia.framework.module.main.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapterEx;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.presenter.MorePresenter;
import com.cecilia.framework.module.main.view.MoreView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;

public class MoreFragment extends BaseFragment implements MoreView ,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.srl_more)
    SwipeRefreshLayout mSrlMore;
    @BindView(R.id.lmrv_more)
    LoadMoreRecyclerView mLmrvMore;

    private MorePresenter mMorePresenter;
    private MoreAdapter mMoreAdapter;
    private MoreAdapterEx mMoreAdapterEx;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public void onRefresh() {
        mMorePresenter.getCategoryData(mSrlMore);
    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_more, null);
    }

    @Override
    public void initData() {
        mLmrvMore.setState(true, new GridLayoutManager(getContext(), 2));
        mMoreAdapter = new MoreAdapter(getContext());
        mMoreAdapterEx = new MoreAdapterEx(mMoreAdapter,mSrlMore, this.getContext());
        mMoreAdapterEx.addHeaderView(View.inflate(this.getContext(), R.layout.layout_mall_header, null));
        mMoreAdapter.setHeadersCount(mMoreAdapterEx.getHeadersCount());
        mLmrvMore.setAdapter(mMoreAdapterEx);
        mLmrvMore.setNestedScrollingEnabled(false);
        mMorePresenter = new MorePresenter(this);
        mSrlMore.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlMore.setOnRefreshListener(this);
    }

    @Override
    public void getDataSuccess(List<MoreListBean> data) {
        LogUtil.e(data.size()+ " == data.size()");
        mMoreAdapter.setData(data);
        mMoreAdapterEx.synchronizedNotify();
    }

    @Override
    public void showFail() {

    }



}
