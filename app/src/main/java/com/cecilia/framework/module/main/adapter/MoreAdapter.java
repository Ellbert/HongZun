package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.module.product.activity.ProductActivity;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

public class MoreAdapter extends BaseRvAdapter {

    public MoreAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, Object data) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductActivity.launch(mContext);
            }
        });
    }

    //    public MoreAdapter(Context context) {
//        super(context);
//    }

//    @Override
//    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
//        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_mall_good, parent, false));
//    }
//
//    @Override
//    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
////        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
//    }
}
