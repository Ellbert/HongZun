package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.me.bean.MessageBean;
import com.cecilia.framework.module.payment.activity.PaymentDetailActivity;
import com.cecilia.framework.utils.StringUtil;

public class NewsAdapter extends BaseLmrvAdapter<MessageBean> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, final MessageBean data) {
        ((TextView) holder.getView(R.id.tv_title)).setText(data.getTMessageTitle());
        ((TextView) holder.getView(R.id.tv_text)).setText(data.getTMessageDescribe());
         holder.getView(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 PaymentDetailActivity.launch(mContext,6,data);
             }
         });
        if (data.getTCreatTime() == 0) return;
        ((TextView) holder.getView(R.id.tv_time)).setText(StringUtil.stampToDate(data.getTCreatTime()));
    }
}
