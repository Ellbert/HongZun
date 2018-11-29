package com.cecilia.framework.module.main.fragment;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.general.PageBean;
import com.cecilia.framework.module.main.adapter.MainAdapterEx;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.adapter.RecommendAdapter;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.HomeBean;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.module.main.presenter.HomePresenter;
import com.cecilia.framework.module.main.view.HomeView;
import com.cecilia.framework.module.me.activity.BankCardActivity;
import com.cecilia.framework.module.me.activity.MyBankCardActivity;
import com.cecilia.framework.module.payment.activity.PaymentActivity;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

public class MainFragment extends BaseFragment implements HomeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lmrv_recommend)
    LoadMoreRecyclerView mRvRecommend;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout mSrlMain;

    private HomePresenter mHomePresenter;
    private MoreAdapter mMoreAdapter;
    private MainAdapterEx mMainAdapterEx;
    private int mCuPage;
    private boolean isCanLoad;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @SuppressLint("InflateParams")
    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void initData() {
        mHomePresenter = new HomePresenter(this);
        mRvRecommend.setState(true, new GridLayoutManager(getContext(), 2));
        mMoreAdapter = new MoreAdapter(getContext());
        mMainAdapterEx = new MainAdapterEx(mMoreAdapter, mSrlMain, this.getContext());
        mMainAdapterEx.addHeaderView(View.inflate(this.getContext(), R.layout.item_rv_home_header, null));
        mMoreAdapter.setHeadersCount(mMainAdapterEx.getHeadersCount());
        mRvRecommend.setAdapter(mMainAdapterEx);
        mRvRecommend.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mRvRecommend.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        mSrlMain.setOnRefreshListener(this);
        mSrlMain.setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mCuPage = 1;
//        mHomePresenter.getAdvertising();
//        mHomePresenter.getHomeData(mSrlMain);
//        mHomePresenter.getRecommendData(mCuPage);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mMoreAdapter.setData(list);
        mMainAdapterEx.synchronizedNotify();
    }

    @Override
    public void gerAdvertisingSuccess(@NonNull List<AdvertisingBean> data) {
        mMainAdapterEx.setAdsData(data);
    }

    @Override
    public void getFail() {
//        ToastUtil.newSafelyShow("网络异常，请稍后再试！");
        mRvRecommend.setLoadMoreNull();
//        mLinearLayout.setVisibility(View.Vi);
    }

    @Override
    public void getHomeSuccess(@NonNull HomeBean data) {
        mMainAdapterEx.setHomeData(data);
    }

    @Override
    public void getRecommendSuccess(@NonNull PageBean<RecommendBean> data) {
//        isCanLoad = data.getHas_more();
//        if (data.getData().size() > 0 && data.getCurrent_page() == 1) {
//            mRecommendAdapter.setData(data.getData());
//        } else {
//            mRecommendAdapter.addData(data.getData());
//        }
    }



}

