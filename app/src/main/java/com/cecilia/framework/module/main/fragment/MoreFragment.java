package com.cecilia.framework.module.main.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.TabFragmentAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapterEx;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.presenter.MorePresenter;
import com.cecilia.framework.module.main.view.MoreView;
import com.cecilia.framework.module.mall.activity.MallActivity;
import com.cecilia.framework.utils.DensityUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ViewUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreFragment extends BaseFragment implements MoreView ,SwipeRefreshLayout.OnRefreshListener,LoadMoreRecyclerView.OnScrolledListener {

    @BindView(R.id.srl_more)
    SwipeRefreshLayout mSrlMore;
    @BindView(R.id.lmrv_more)
    LoadMoreRecyclerView mLmrvMore;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_back_black)
    ImageView mIvBackBlack;
    @BindView(R.id.iv_back_white)
    ImageView mIvBackWhite;
    private MorePresenter mMorePresenter;
    private MoreAdapter mMoreAdapter;
    private MoreAdapterEx mMoreAdapterEx;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {
//        baseViewHolder.getView(R.id.tv_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MallActivity.launch(mContext,0);
//            }
//        });
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
        mLmrvMore.setOnScrolledListener(this);
    }

    @Override
    public void getDataSuccess(List<MoreListBean> data) {
        mMoreAdapter.setData(data);
        mMoreAdapterEx.synchronizedNotify();
    }

    @Override
    public void showFail() {

    }

    @Override
    public void onScrolled(int distance) {
        int mHeadHeight = DensityUtil.dp2px(this.getContext(),350);
        float f = (float) distance / (float) mHeadHeight;
//        if (mRlHeadNoBg == null)
//            return;
        if (f <= 0) {

        } else if (f >= 1) {

        } else {
        }
    }

    @OnClick({R.id.iv_back_black,R.id.iv_back_white,R.id.tv_search})
    protected void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back_black:
//                MallActivity.launch(MoreFragment.this.getContext(),0);
                break;
            case R.id.iv_back_white:
//                MallActivity.launch(MoreFragment.this.getContext(),0);
                break;
            case R.id.tv_search:
                MallActivity.launch(MoreFragment.this.getContext(),0);
                break;
        }
    }

}
