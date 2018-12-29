package com.cecilia.framework.module.vip.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseActivity;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.login.activity.LoginActivity;
import com.cecilia.framework.module.vip.adapter.VipAdapter;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.module.vip.presenter.VipPresenter;
import com.cecilia.framework.module.vip.view.VipView;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.SharedPreferenceUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VipActivity extends BaseActivity implements VipView {

    @BindView(R.id.rv_vip)
    RecyclerView mRvVip;
    @BindView(R.id.tv_title_text)
    TextView mTvTitleText;
    private VipAdapter mVipAdapter;
    private int mLevel;
    private VipPresenter mVipPresenter;

    public static void launch(Context context) {
        Intent intent = new Intent(context, VipActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initViews() {
        mTvTitleText.setText("会员卡");
    }

    @Override
    protected void initData() {
        mVipPresenter = new VipPresenter(this);
        mLevel = SharedPreferenceUtil.getInt(this, "level");
        mRvVip.setLayoutManager(new LinearLayoutManager(this));
        mVipPresenter.vipList();
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

    @Override
    public void onGetListSuccess(List<VipBean> data) {
        mVipAdapter = new VipAdapter(this, R.layout.item_vip, mLevel);
        mRvVip.setAdapter(mVipAdapter);
        mVipAdapter.setDataList(data);
    }

    @Override
    public void onFailed() {

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
        mLevel = SharedPreferenceUtil.getInt(this, "level");
        mVipPresenter.vipList();
    }
}
