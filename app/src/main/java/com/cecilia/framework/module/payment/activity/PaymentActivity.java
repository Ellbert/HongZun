package com.cecilia.framework.module.payment.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.payment.adapter.PaymentListAdapter;
import com.cecilia.framework.module.payment.bean.PaymentBean;
import com.cecilia.framework.module.payment.bean.WithdrawBean;
import com.cecilia.framework.module.payment.presenter.PaymentPresenter;
import com.cecilia.framework.module.payment.view.PaymentView;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity implements PaymentView, SwipeRefreshLayout.OnRefreshListener {

    //    @BindView(R.id.vtb_payment)
//    VerticalTabLayout mVtbPayment;
//    @BindView(R.id.nsvp_payment)
//    NoScrollViewPager mNoScrollViewPager;
    @BindView(R.id.rv_payment)
    LoadMoreRecyclerView mLoadMoreRecyclerView;
    @BindView(R.id.srl_payment)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    private int mType;
    private int mMerchantId;
    private PaymentListAdapter mPaymentAdapter;
    private PaymentPresenter mPaymentPresenter;
    private int mPage = 1;
    private List mData;

    public static void launch(Activity context, int type, int merchantId) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("merchantId", merchantId);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initViews() {
//        List<String> titleList = new ArrayList<>();
//        titleList.add("提现");
//        titleList.add("充值");
//        titleList.add("消费记录");
//        titleList.add("理财收入");
//        titleList.add("推荐奖金");
//        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(new PaymentFragment(1));
//        fragmentList.add(new PaymentFragment(2));
//        fragmentList.add(new PaymentFragment(3));
//        fragmentList.add(new PaymentFragment(4));
//        fragmentList.add(new PaymentFragment(5));
//        TabFragmentAdapter tabFragmentAdapter = new TabFragmentAdapter(titleList, fragmentList, getSupportFragmentManager());
//        mNoScrollViewPager.setAdapter(tabFragmentAdapter);
//        mVtbPayment.setupWithViewPager(mNoScrollViewPager);
        mLoadMoreRecyclerView.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mTvTitleText.setText("账单明细");
        mType = getIntent().getIntExtra("type", 0);
        mMerchantId = getIntent().getIntExtra("merchantId", 0);
        mPaymentPresenter = new PaymentPresenter(this);
        mPaymentAdapter = new PaymentListAdapter(this);
        mLoadMoreRecyclerView.setAdapter(mPaymentAdapter);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mLoadMoreRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPage++;
                if (mMerchantId == 0) {
                    mPaymentPresenter.paymentList(null, GcGuangApplication.getId(), mType, mPage);
                } else {
                    if (mType == 2) {
                        mPaymentPresenter.withdrawRecord(null, mMerchantId, mPage);
                    } else {
                        mPaymentPresenter.getMerchantList(null, mMerchantId, mType, mPage);
                    }
                }
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
    public void onGetListSuccess(List<PaymentBean> data) {
        if (mPage == 1 && data.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = data;
            mPaymentAdapter.setData(data);
        } else {
            mPaymentAdapter.addData(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
            mLoadMoreRecyclerView.setLoadMoreNull();
        } else {
            mLoadMoreRecyclerView.setLoadMoreFinish();
        }
    }

    @Override
    public void onGetMerchantListSuccess(List<PaymentBean> data) {
        if (mPage == 1 && data.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = data;
            mPaymentAdapter.setData(data);
        } else {
            mPaymentAdapter.addData(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
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
        mData = null;
        mPage = 1;
        if (mMerchantId == 0) {
            mPaymentPresenter.paymentList(mSwipeRefreshLayout, GcGuangApplication.getId(), mType, mPage);
        } else {
            if (mType == 2) {
                mPaymentPresenter.withdrawRecord(mSwipeRefreshLayout, mMerchantId, mPage);
            } else {
                mPaymentPresenter.getMerchantList(mSwipeRefreshLayout, mMerchantId, mType, mPage);
            }
        }
    }

    @Override
    public void onGetWithdrawListSuccess(List<WithdrawBean> data) {
        if (mPage == 1 && data.size() > 0) {
            mTvNothing.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = data;
            mPaymentAdapter.setData(data);
        } else {
            mPaymentAdapter.addData(data);
        }
        if (data.size() < NetworkConstant.PAGE_SIZE) {
            mLoadMoreRecyclerView.setLoadMoreNull();
        } else {
            mLoadMoreRecyclerView.setLoadMoreFinish();
        }
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
