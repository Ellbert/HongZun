package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.me.bean.MessageDetailBean;

public class MessageDetailAdapter extends BaseRvAdapter<MessageDetailBean> {

    public MessageDetailAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, MessageDetailBean data) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvDetail = holder.getView(R.id.tv_detail);
        tvName.setText(data.getName());
        tvDetail.setText(data.getValue());
    }
}
