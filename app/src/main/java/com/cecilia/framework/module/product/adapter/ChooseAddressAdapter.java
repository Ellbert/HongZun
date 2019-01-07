package com.cecilia.framework.module.product.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.bean.AddressBean;

public class ChooseAddressAdapter extends BaseRvAdapter<AddressBean> {

    private int position = -1;
    private OnItemClickListener mOnItemClickListener;

    public ChooseAddressAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(final BaseViewHolder holder, final AddressBean data) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAddress = holder.getView(R.id.tv_address);
        TextView tvPhone = holder.getView(R.id.tv_phone);
        CheckBox checkBox = holder.getView(R.id.cb_check);
        tvName.setText(data.getTName()+"");
        tvAddress.setText(data.getTAddress()+"");
        tvPhone.setText(data.getTPhone()+"");
        if (position == holder.getAdapterPosition()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v,position);
                    notifyDataSetChanged();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v,position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
