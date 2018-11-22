package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

import java.util.Comparator;

public class RecommendAdapter extends BaseLmrvAdapter<RecommendBean> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_recommend, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, RecommendBean data) {
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
    }

}
