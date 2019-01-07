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
import com.cecilia.framework.utils.StringUtil;
import com.cecilia.framework.utils.ToastUtil;

public class BankCardAdapter extends BaseRvAdapter<BankCardBean> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemClickListener mOnSetDefaultListener;

    public BankCardAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, final BankCardBean data) {
        ((TextView)holder.getView(R.id.tv_bank_num)).setText(StringUtil.getStarBankCard(data.getTCardNum()+""));
        ((TextView)holder.getView(R.id.tv_bank)).setText(data.gettBankName()+"");
        final CheckBox checkBox = holder.getView(R.id.cb_default);
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
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBox.isChecked()) {
                    ToastUtil.newSafelyShow("此卡已设置为默认卡！");
                    notifyDataSetChanged();
                    return;
                }
                if (mOnSetDefaultListener != null){
                    mOnSetDefaultListener.onItemClick(v,data.getTId());
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnSetDefaultListener(OnItemClickListener mOnItemClickListener) {
        this.mOnSetDefaultListener = mOnItemClickListener;
    }
}
