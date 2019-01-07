package com.cecilia.framework.module.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.customer.activity.CustomerActivity;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.main.adapter.MainAdapterEx;
import com.cecilia.framework.module.main.adapter.ProductAdapter;
import com.cecilia.framework.module.main.bean.AdvertisingBean;
import com.cecilia.framework.module.main.bean.GoodsBean;
import com.cecilia.framework.module.main.bean.NoticeBean;
import com.cecilia.framework.module.main.bean.ShopBean;
import com.cecilia.framework.module.main.presenter.HomePresenter;
import com.cecilia.framework.module.main.view.HomeView;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;

public class MainFragment extends BaseFragment implements HomeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lmrv_recommend)
    LoadMoreRecyclerView mRvRecommend;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout mSrlMain;
    private HomePresenter mHomePresenter;
    private ProductAdapter mMoreAdapter;
    private MainAdapterEx mMainAdapterEx;
//    private int mCuPage;
//    private boolean isCanLoad;

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
        mMoreAdapter = new ProductAdapter(getContext());
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
        mMainAdapterEx.setOnCustomerClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
//                int merchantId = SharedPreferenceUtil.getInt(mActivity, "merchantId", 0);
//                if (0 == merchantId) {
//                    CustomerActivity.launch(MainFragment.this.getContext(), -1, null);
//                } else {
                DialogUtil.createLoadingDialog(MainFragment.this.getContext(), "查询中...", false, null);
//                    mHomePresenter.getShopStatus(String.valueOf(merchantId));
                mHomePresenter.getUserInfo(GcGuangApplication.getId());
//                }
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
//        mRvRecommend.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//
//            }
//        });
        mSrlMain.setOnRefreshListener(this);
        mMoreAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                ProductActivity.launch(MainFragment.this.mActivity,id);
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mHomePresenter.getRecommendList(mSrlMain);
        mHomePresenter.getPromotionList();
        mHomePresenter.lastNotice();
//        mCuPage = 1;
//        mHomePresenter.getAdvertising();
//        mHomePresenter.getHomeData(mSrlMain);
//        mHomePresenter.getRecommendData(mCuPage);
//        List<Object> list = new ArrayList<>();
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        mMoreAdapter.setData(list);
//        mMainAdapterEx.synchronizedNotify();
    }

    @Override
    public void onFailed() {
//        ToastUtil.newSafelyShow("网络异常，请稍后再试！");
//        mRvRecommend.setLoadMoreNull();
//        mLinearLayout.setVisibility(View.Vi);
        LoginActivity.launch(this.getContext());
        this.mActivity.finish();
    }

    @Override
    public void getRecommendSuccess(@NonNull List<GoodsBean> data) {
        mMoreAdapter.setData(data);
        mRvRecommend.setLoadMoreNull();
        mMainAdapterEx.synchronizedNotify();
//        isCanLoad = data.getHas_more();
//        if (data.getData().size() > 0 && data.getCurrent_page() == 1) {
//            mRecommendAdapter.setData(data.getData());
//        } else {
//            mRecommendAdapter.addData(data.getData());
//        }
    }

    @Override
    public void onShopStatusSuccess(ShopBean data) {
        CustomerActivity.launch(MainFragment.this, data.getTStatus(), data);
    }

    @Override
    public void onGetPromotionListSuccess(List<AdvertisingBean> data) {
        mMainAdapterEx.setAdsData(data);
    }

    @Override
    public void onGetNoticeSuccess(NoticeBean data) {
        mMainAdapterEx.setNoticeData(data);
    }

    @Override
    public void onGetUserInfoSuccess(UserBean userBean, String other) {
        int merchantId = userBean.getTMerchantId();
        SharedPreferenceUtil.putInt(mActivity, "merchantId", userBean.getTMerchantId());
        if (0 == merchantId) {
            CustomerActivity.launch(MainFragment.this, -1, null);
        } else {
            DialogUtil.createLoadingDialog(MainFragment.this.getContext(), "查询中...", false, null);
            mHomePresenter.getShopStatus(String.valueOf(merchantId));
//        mHomePresenter.getUserInfo(GcGuangApplication.getId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 99) {
//            LoginActivity.launch(this.getContext());
//            this.mActivity.finish();
//        }
    }
}

