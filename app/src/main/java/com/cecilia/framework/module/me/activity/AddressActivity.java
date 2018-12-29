package com.cecilia.framework.module.me.activity;

import android.app.Activity;
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
import com.cecilia.framework.module.me.adapter.AddressAdapter;
import com.cecilia.framework.module.me.bean.AddressBean;
import com.cecilia.framework.module.me.presenter.AddressPresenter;
import com.cecilia.framework.module.me.view.AddressView;
import com.cecilia.framework.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements AddressView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    @BindView(R.id.srl_address)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private AddressAdapter mAddressAdapter;
    private AddressPresenter mAddressPresenter;

    public static void launch(Fragment context) {
        Intent intent = new Intent(context.getContext(), AddressActivity.class);
        context.startActivity(intent);
    }

    public static void launch(Activity context) {
        Intent intent = new Intent(context, AddressActivity.class);
        context.startActivityForResult(intent,54);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("我的地址");
    }

    @Override
    protected void initData() {
        mAddressPresenter = new AddressPresenter(this);
        mRvAddress.setLayoutManager(new LinearLayoutManager(this));
        mAddressAdapter = new AddressAdapter(this, R.layout.item_address, mAddressPresenter);
        mRvAddress.setAdapter(mAddressAdapter);
        onRefresh();
//        List<Object> list = new ArrayList<>();
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        list.add("dwdwasd");
//        mAddressActivity.setDataList(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void doEvents(EventBean event) {

    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                AddressEditActivity.launch(AddressActivity.this, null);
                break;
        }
    }

    @Override
    public void onGetSuccess(List<AddressBean> list) {
        mAddressAdapter.setDataList(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetFailed() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        mAddressPresenter.getAddressList(mSwipeRefreshLayout, String.valueOf(GcGuangApplication.getId()));
    }

    @Override
    public void onDeleteSuccess() {
        mSwipeRefreshLayout.setRefreshing(false);
        ToastUtil.newSafelyShow("删除成功");
        onRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddressPresenter.getAddressList(mSwipeRefreshLayout, String.valueOf(GcGuangApplication.getId()));
    }
}
