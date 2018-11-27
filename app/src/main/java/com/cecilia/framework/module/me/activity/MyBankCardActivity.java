package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.BankCardAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyBankCardActivity extends BaseActivity {

    @BindView(R.id.rv_bank)
    RecyclerView mRvBank;
    private BankCardAdapter mBankCardAdapter;

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

    }

    @Override
    protected void initData() {
        mRvBank.setLayoutManager(new LinearLayoutManager(this));
        mBankCardAdapter = new BankCardAdapter(this,R.layout.item_bank_card);
        mRvBank.setAdapter(mBankCardAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mBankCardAdapter.setDataList(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back,R.id.tv_add})
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

}
