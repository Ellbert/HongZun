package com.cecilia.framework.module.mall.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseFragment;
import com.cecilia.framework.module.main.adapter.MoreAdapter;
import com.cecilia.framework.module.main.adapter.MoreAdapterEx;
import com.cecilia.framework.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MallFragment extends BaseFragment {

    @BindView(R.id.lmrv_mall)
    LoadMoreRecyclerView mLmrvMall;
    private MoreAdapter mMoreAdapter;

    @Override
    protected void onVisible() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    public View initViews(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_mall, null);
    }

    @Override
    public void initData() {
        mLmrvMall.setState(true, new GridLayoutManager(getContext(), 2));
        mMoreAdapter = new MoreAdapter(getContext());
        mLmrvMall.setAdapter(mMoreAdapter);
        List<Object> list = new ArrayList<>();
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        list.add("dwdwasd");
        mMoreAdapter.setData(list);
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initListener() {

    }
}
