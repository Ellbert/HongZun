package com.cecilia.framework.module.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.cart.activity.ChooseWayActivity;
import com.cecilia.framework.module.customer.activity.ShopOrderActivity;
import com.cecilia.framework.module.customer.adapter.ShopOrderAdapter;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.main.activity.SubmitCommentActivity;
import com.cecilia.framework.module.main.adapter.OrderListAdapter;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.main.presenter.OrderListPresenter;
import com.cecilia.framework.module.main.view.OrderListView;
import com.cecilia.framework.module.order.activity.OrderDetailActivity;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class OrderListFragment extends BaseFragment implements OrderListView, SwipeRefreshLayout.OnRefreshListener {

    public static final int SHOP = 1;
    public static final int PERSONAL = 2;
    private int type;
    @BindView(R.id.lmrv_order)
    LoadMoreRecyclerView mLmrvOrder;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private OrderListAdapter mOrderListAdapter;
    private ShopOrderAdapter mShopOrderAdapter;
    private OrderListPresenter mOrderListPresenter;
    private int mPage = 1;
    private List mData;
    private int mUserType;

    public OrderListFragment(int userType, int type) {
        this.type = type;
        this.mUserType = userType;
    }

    @Override
    protected void onVisible() {
        if (mSwipeRefreshLayout != null) {
            onRefresh();
        }
    }

    @Override
    protected void onInvisible() {
//        LogUtil.e("onInvisible" + type);
    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_order_type, null);
    }

    @Override
    public void initData() {
        mOrderListPresenter = new OrderListPresenter(this);
        mLmrvOrder.setState(true, new LinearLayoutManager(getContext()));
        if (mUserType == SHOP) {
            mShopOrderAdapter = new ShopOrderAdapter(getContext(), type);
            mLmrvOrder.setAdapter(mShopOrderAdapter);
            mShopOrderAdapter.setOnItemBuyClickListener(new ShopOrderAdapter.OnOrderItemClickListener() {
                @Override
                public void onItemClick(String info, int id) {
                    OrderDetailActivity.launch(OrderListFragment.this, id, info);
                }
            });
        } else if (mUserType == PERSONAL) {
            mOrderListAdapter = new OrderListAdapter(getContext(), type);
            mLmrvOrder.setAdapter(mOrderListAdapter);
            mOrderListAdapter.setOnItemBuyClickListener(new OrderListAdapter.OnOrderItemClickListener() {
                @Override
                public void onItemClick(String info, int id) {
                    OrderDetailActivity.launch(OrderListFragment.this, id, info);
                }
            });
        }
        if (type == 0) {
            onRefresh();
        }
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLmrvOrder.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                switch (mUserType) {
                    case SHOP:
                        break;
                    case PERSONAL:
                        mPage++;
                        mOrderListPresenter.getList(null, GcGuangApplication.getId(), type, mPage);
                        break;
                }
            }
        });
    }

    @Override
    public void onGetListSuccess(List<OrderBean> beanList) {
        if (mPage == 1 && beanList.size() == 0) {
            mTvNothing.setVisibility(View.VISIBLE);
            mLmrvOrder.setVisibility(View.GONE);
            return;
        }
        // 分页数据处理
        if (mData == null || mData.size() == 0) {
            mData = beanList;
            mTvNothing.setVisibility(View.GONE);
            mLmrvOrder.setVisibility(View.VISIBLE);
            mOrderListAdapter.setData(beanList);
        } else {
            mOrderListAdapter.addData(beanList);
        }
        if (beanList.size() < NetworkConstant.PAGE_SIZE) {
            mLmrvOrder.setLoadMoreNull();
        } else {
            mLmrvOrder.setLoadMoreFinish();
        }
    }

    @Override
    public void onFailed() {
        LoginActivity.launch(this.getContext());
        this.mActivity.finish();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mData = null;
        switch (mUserType) {
            case SHOP:
                break;
            case PERSONAL:
                mOrderListPresenter.getList(mSwipeRefreshLayout, GcGuangApplication.getId(), type, mPage);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 99) {
//            LoginActivity.launch(this.getContext());
//            this.mActivity.finish();
//        } else {
        onRefresh();
//        }
    }

}
