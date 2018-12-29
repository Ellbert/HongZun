package com.cecilia.framework.module.customer.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.bean.BankCardBean;
import com.cecilia.framework.utils.StringUtil;

public class BankCardAdapter extends BaseRvAdapter<BankCardBean> {

    private OnItemClickListener mOnItemClickListener;
    private int position = -1;

    public BankCardAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(final BaseViewHolder holder, BankCardBean data) {
        CheckBox checkBox = holder.getView(R.id.cb_union);
        TextView textView = holder.getView(R.id.tv_name);
        textView.setText(data.gettBankName() + "(" + StringUtil.getLastBankCard(data.getTCardNum()) + ")");
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
                    mOnItemClickListener.onItemClick(v, position);
                    notifyDataSetChanged();
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
