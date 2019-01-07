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

    public static final int ALL = 0;
    public static final int PAY = 1;
    public static final int SEND = 2;
    public static final int GET = 3;
    public static final int COMMENT = 4;
    public static final int COLLECT = 5;
    public static final int SHOP_ALL = 6;
    public static final int UNSENT = 7;
    public static final int RETURN = 8;
    public static final int SHOP_FINISH = 9;
    private int type;
    @BindView(R.id.lmrv_order)
    LoadMoreRecyclerView mLmrvOrder;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private OrderListAdapter mOrderListAdapter;
    private OrderListPresenter mOrderListPresenter;
    private int mPage = 1;
    private List mData;

    public OrderListFragment(int type) {
        this.type = type;
    }

    @Override
    protected void onVisible() {
        if (mSwipeRefreshLayout != null) {
            initDataList();
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
        mOrderListAdapter = new OrderListAdapter(getContext(), type);
        mLmrvOrder.setAdapter(mOrderListAdapter);
        if (type == ALL) {
            initDataList();
        }
    }

    private void initDataList() {
        switch (type) {
            case COLLECT:
                break;
            case SHOP_ALL:
                break;
            case UNSENT:
                break;
            case RETURN:
                break;
            case SHOP_FINISH:
                break;
            default:
                onRefresh();
                break;
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
                mPage++;
                mOrderListPresenter.getList(null, GcGuangApplication.getId(), type, mPage);
            }
        });
        mOrderListAdapter.setOnItemBuyClickListener(new OrderListAdapter.OnOrderItemClickListener() {
            @Override
            public void onItemClick(String info, int id) {
                OrderDetailActivity.launch(OrderListFragment.this, id, info);
            }
        });
    }

    @Override
    public void onGetListSuccess(List<OrderBean> beanList) {
        mSwipeRefreshLayout.setRefreshing(false);
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
        mOrderListPresenter.getList(mSwipeRefreshLayout, GcGuangApplication.getId(), type, mPage);
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
