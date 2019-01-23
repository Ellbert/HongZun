package com.cecilia.framework.module.customer.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.customer.activity.ProductEditActivity;

public class ProductAdapter extends BaseLmrvAdapter {

    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_shop_goods, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        setView(holder);
    }

    private void setView(BaseViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductEditActivity.launch((Activity) mContext,2,0);
            }
        });
    }
}
