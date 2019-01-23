package com.cecilia.framework.module.customer.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.customer.adapter.IncomeAdapter;
import com.cecilia.framework.module.customer.adapter.ProductAdapter;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IncomeActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.lmrv_income_detail)
    LoadMoreRecyclerView mLmrvDetail;
    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrlList;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;
    @BindView(R.id.ll_total)
    LinearLayout mLlTotal;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private IncomeAdapter mIncomeAdapter;
    private int mMerchantId;

    public static void launch(Activity context,int merchantId) {
        Intent intent = new Intent(context, IncomeActivity.class);
        intent.putExtra("merchantId",merchantId);
        context.startActivityForResult(intent,0);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_income_detail;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("今日收入");
        mLlTotal.setVisibility(View.VISIBLE);
        mTvConfirm.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        mMerchantId = getIntent().getIntExtra("merchantId",0);
        mLmrvDetail.setState(true, new LinearLayoutManager(this));
        mIncomeAdapter = new IncomeAdapter(this);
        mLmrvDetail.setAdapter(mIncomeAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mIncomeAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlList.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        }
    }
}
