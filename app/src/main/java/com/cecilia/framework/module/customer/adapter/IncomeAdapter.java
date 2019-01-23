package com.cecilia.framework.module.customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;

public class IncomeAdapter extends BaseLmrvAdapter {


    public IncomeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return  new BaseViewHolder(layoutInflater.inflate(R.layout.item_income, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        setView(holder);
    }

    private void setView(BaseViewHolder holder){

    }
}
