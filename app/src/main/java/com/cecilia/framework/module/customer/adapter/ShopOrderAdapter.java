package com.cecilia.framework.module.customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.adapter.OrderListAdapter;

public class ShopOrderAdapter extends BaseLmrvAdapter {

    private OnOrderItemClickListener mOnItemClickListener;
    private int type;

    public ShopOrderAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }


    private void initView(BaseViewHolder holder, Object baseGoodBean) {
        TextView delete = holder.getView(R.id.tv_delete);
        TextView comment = holder.getView(R.id.tv_comment);
        TextView buy = holder.getView(R.id.tv_buy);
        TextView name = holder.getView(R.id.tv_name);
        ImageView header = holder.getView(R.id.iv_photo);
        TextView sales = holder.getView(R.id.tv_sales);
        TextView price = holder.getView(R.id.tv_price);
        buy.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        comment.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }
    }

    public void setOnItemBuyClickListener(OnOrderItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_order, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        initView(holder, data);
    }

    public interface OnOrderItemClickListener {
        void onItemClick(String info, int id);
    }

}
