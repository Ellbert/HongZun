package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.adapter.BankCardAdapter;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.module.me.presenter.MyBankCardPresenter;
import com.cecilia.framework.module.me.view.MyBankCardView;
import com.cecilia.framework.utils.ToastUtil;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBankCardActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, MyBankCardView {

    @BindView(R.id.rv_bank)
    RecyclerView mRvBank;
    @BindView(R.id.srl_bank)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private BankCardAdapter mBankCardAdapter;
    private MyBankCardPresenter mMyBankCardPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), MyBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bank;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("我的银行卡");
    }

    @Override
    protected void initData() {
        mMyBankCardPresenter = new MyBankCardPresenter(this);
        mRvBank.setLayoutManager(new LinearLayoutManager(this));
        mBankCardAdapter = new BankCardAdapter(this, R.layout.item_bank_card);
        mRvBank.setAdapter(mBankCardAdapter);
        onRefresh();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBankCardAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int id) {
                mMyBankCardPresenter.deleteBankCard(String.valueOf(id));
            }

            @Override
            public void onItemLongClick(View view, int id) {

            }
        });
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back, R.id.tv_add})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add:
                BankCardActivity.launch(MyBankCardActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        mMyBankCardPresenter.getList(mSwipeRefreshLayout, String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    public void onGetListSuccess(List<BankCardBean> list) {
        mBankCardAdapter.setDataList(list);
    }

    @Override
    public void onFailed() {

    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.newSafelyShow("删除成功！");
        onRefresh();
    }
}
