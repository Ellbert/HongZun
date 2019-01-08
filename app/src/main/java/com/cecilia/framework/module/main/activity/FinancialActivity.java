package com.cecilia.framework.module.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.adapter.FinancialAdapter;
import com.cecilia.framework.module.main.bean.FinancialBean;
import com.cecilia.framework.module.main.presenter.FinancialPresenter;
import com.cecilia.framework.module.main.view.FinancialView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FinancialActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, FinancialView {

    @BindView(R.id.tv_submit)
    ImageView mIvSubmit;
    @BindView(R.id.tv_edit)
    TextView mIvEdit;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.srl_financial)
    SwipeRefreshLayout mSrlFinancial;
    @BindView(R.id.rv_financial)
    LoadMoreRecyclerView mRvFinancial;
    private FinancialAdapter mFinancialAdapter;
    private FinancialPresenter mFinancialPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), FinancialActivity.class);
        context.startActivityForResult(intent, 6);
    }

    public static void launch(Activity context) {
        Intent intent = new Intent(context, FinancialActivity.class);
        context.startActivityForResult(intent, 6);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_financial;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("理财记录");
        mIvSubmit.setVisibility(View.GONE);
        mIvEdit.setVisibility(View.VISIBLE);
        mIvEdit.setText("泓宝");
        mRvFinancial.setNestedScrollingEnabled(false);
        mRvFinancial.setState(true, new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mFinancialPresenter = new FinancialPresenter(this);
        mFinancialAdapter = new FinancialAdapter(this);
        mRvFinancial.setAdapter(mFinancialAdapter);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSrlFinancial.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mFinancialPresenter.getUserArrange(mSrlFinancial, GcGuangApplication.getId());

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back, R.id.tv_edit})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                HongBaoActivity.launch(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 99) {
            setResult(99);
            finish();
        } else {
            onRefresh();
        }
    }

    @Override
    public void onGetSuccess(FinancialBean financialBean) {
        LogUtil.e(financialBean.toString());
        if (!StringUtil.isNullOrEmpty(financialBean.gettOrderNum())) {
            List<FinancialBean> data = new ArrayList<>();
            data.add(financialBean);
            mFinancialAdapter.setData(data);
            mRvFinancial.setLoadMoreNull();
        } else {
            List<FinancialBean> data = new ArrayList<>();
            mFinancialAdapter.setData(data);
            mRvFinancial.setLoadMoreFinish();
        }
    }

    @Override
    public void onFailed() {

    }
}
