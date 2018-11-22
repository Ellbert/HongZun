package com.cecilia.framework.module.main.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.general.EventBean;
import com.cecilia.framework.module.main.activity.HongBaoActivity;
import com.cecilia.framework.module.main.activity.RechargeActivity;
import com.cecilia.framework.module.me.activity.CollectActivity;
import com.cecilia.framework.module.me.activity.DataActivity;
import com.cecilia.framework.module.me.activity.FansActivity;
import com.cecilia.framework.module.me.activity.FollowActivity;
import com.cecilia.framework.module.me.activity.PriceActivity;
import com.cecilia.framework.module.me.activity.SafetyActivity;
import com.cecilia.framework.utils.LogUtil;

import butterknife.OnClick;

public class MeFragment extends BaseFragment {

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_me, null);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.tv_recharge, R.id.tv_detail, R.id.iv_price, R.id.iv_fans, R.id.iv_follow, R.id.iv_collect,
            R.id.tv_data,R.id.tv_safety})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                RechargeActivity.launch(MeFragment.this);
                break;
            case R.id.tv_detail:
                HongBaoActivity.launch(MeFragment.this);
                break;
            case R.id.iv_price:
                PriceActivity.launch(MeFragment.this);
                break;
            case R.id.iv_fans:
                FansActivity.launch(MeFragment.this);
                break;
            case R.id.iv_follow:
                FollowActivity.launch(MeFragment.this);
                break;
            case R.id.iv_collect:
                CollectActivity.launch(MeFragment.this);
                break;
            case R.id.tv_data:
                DataActivity.launch(MeFragment.this);
                break;
            case R.id.tv_safety:
                SafetyActivity.launch(MeFragment.this);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
