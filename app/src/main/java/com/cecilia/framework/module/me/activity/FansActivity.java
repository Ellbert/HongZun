package com.cecilia.framework.module.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.me.adapter.FansListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FansActivity extends BaseActivity {

    @BindView(R.id.tv_nothing)
    TextView mTvNoting;
    @BindView(R.id.tv_fans)
    TextView mTvFans;
    @BindView(R.id.rv_fans)
    RecyclerView mRvFans;
    private FansListAdapter mFansListAdapter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), FansActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initViews() {
        mRvFans.setLayoutManager(new LinearLayoutManager(this));
        mFansListAdapter = new FansListAdapter(this,R.layout.item_rv_fans_header);
        mRvFans.setAdapter(mFansListAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mFansListAdapter.setDataList(list);
    }

    @Override
    protected void initData() {

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

    @OnClick({R.id.iv_back})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
