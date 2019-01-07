package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.activity.NewDetailActivity;
import com.cecilia.framework.module.main.activity.NewsActivity;
import com.cecilia.framework.module.main.bean.NoticeBean;

public class NewsAdapter extends BaseLmrvAdapter<NoticeBean> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_new, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, final NoticeBean data) {
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
        TextView textView = holder.getView(R.id.tv_text);
        textView.setText(data.getTTitle()+"");
        holder.getView(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDetailActivity.launch(mContext,data);
            }
        });
    }
}
