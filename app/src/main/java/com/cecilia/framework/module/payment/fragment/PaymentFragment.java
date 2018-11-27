package com.cecilia.framework.module.payment.fragment;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapterEx;
import com.cecilia.framework.module.payment.adapter.PaymentAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class PaymentFragment extends BaseFragment {

//    @BindView(R.id.srl_more)
//    SwipeRefreshLayout mSrlMore;
    @BindView(R.id.lmrv_payment)
    LoadMoreRecyclerView mLmrvPayment;
    private PaymentAdapter mPaymentAdapter;
    private int mType;

    public PaymentFragment(int mType) {
        this.mType = mType;
    }

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @SuppressLint("InflateParams")
    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_payment, null);
    }

    @Override
    public void initData() {
        mLmrvPayment.setState(true, new LinearLayoutManager(this.getContext()));
        mPaymentAdapter = new PaymentAdapter(getContext(),mType);
        mLmrvPayment.setAdapter(mPaymentAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mPaymentAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }
}
