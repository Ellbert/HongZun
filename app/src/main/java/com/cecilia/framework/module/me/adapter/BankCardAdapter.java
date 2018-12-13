package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.bean.BankCardBean;

public class BankCardAdapter extends BaseRvAdapter<BankCardBean> {

    private OnItemClickListener mOnItemClickListener;

    public BankCardAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, final BankCardBean data) {
        ((TextView)holder.getView(R.id.tv_bank_num)).setText(data.getTCardNum());
        ((TextView)holder.getView(R.id.tv_bank)).setText(data.getTBankType());
        CheckBox checkBox = holder.getView(R.id.cb_default);
        if (data.getTDefault() == 0) {
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
        }
        holder.getView(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, data.getTId());
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
