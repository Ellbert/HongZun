package com.cecilia.framework.module.me.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;

import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class HongBaoFragment extends BaseFragment {

    public static final int CHANGE = 0;
    public static final int WITHDRAW = 1;
    private int type;

    public HongBaoFragment(int type) {
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
        return inflater.inflate(R.layout.fragment_hongbao,null);
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

    @OnClick({R.id.tv_confirm})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                HongBaoFragment.this.mActivity.setResult(5);
                HongBaoFragment.this.mActivity.finish();
                break;
        }
    }
}
