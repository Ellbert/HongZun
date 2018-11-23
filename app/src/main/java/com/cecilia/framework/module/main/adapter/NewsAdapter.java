package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.activity.NewDetailActivity;

public class NewsAdapter extends BaseLmrvAdapter {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_new, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
        holder.getView(R.id.tv_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewDetailActivity.launch(mContext);
            }
        });
    }
}
