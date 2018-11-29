package com.cecilia.framework.module.customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;

public class ProductAdapter extends BaseLmrvAdapter {

    private int mType;

    public ProductAdapter(Context context,int type) {
        super(context);
        this.mType = type;
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return  new BaseViewHolder(layoutInflater.inflate(R.layout.item_income_detail, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        setView(holder);
    }

    private void setView(BaseViewHolder holder){
        switch (mType){
            case 1:

                break;
            case 2:
                holder.getView(R.id.tv_income).setVisibility(View.INVISIBLE);
                break;
        }
    }
}
