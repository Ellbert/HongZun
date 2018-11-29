package com.cecilia.framework.module.main.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.adapter.OrderListAdapter;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class OrderListFragment extends BaseFragment {

    public static final int ALL = 0;
    public static final int PAY = 1;
    public static final int GET = 2;
    public static final int FINISH = 3;
    public static final int COLLECT = 4;
    public static final int SHOP_ALL = 5;
    public static final int UNSENT= 6;
    public static final int RETURN = 7;
    public static final int SHOP_FINISH = 8;

    private int type;
    @BindView(R.id.lmrv_order)
    LoadMoreRecyclerView mLmrvOrder;
    @BindView(R.id.tv_nothing)
    TextView mTvNothing;
    private OrderListAdapter mOrderListAdapter;

    public OrderListFragment(int type) {
        this.type = type;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_order_type, null);
    }

    @Override
    public void initData() {
        mTvNothing.setVisibility(View.GONE);
        mLmrvOrder.setVisibility(View.VISIBLE);
        mLmrvOrder.setState(true, new LinearLayoutManager(getContext()));
        mOrderListAdapter = new OrderListAdapter(getContext(), type);
        mLmrvOrder.setAdapter(mOrderListAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mOrderListAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

}
