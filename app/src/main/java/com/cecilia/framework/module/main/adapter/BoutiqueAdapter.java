package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.cecilia.framework.GcGuangApplication;
import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.bean.BoutiqueBean;
import com.cecilia.framework.module.product.activity.ProductListActivity;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

public class BoutiqueAdapter extends BaseRvAdapter<BoutiqueBean> {


    public BoutiqueAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, BoutiqueBean data) {
//        ImageUtil.loadNetworkImage(mContext, data.getLogo(), (ImageView) holder.getView(R.id.iv_boutique), true, false, null);
        holder.getView(R.id.iv_boutique).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductListActivity.launch(mContext);
            }
        });
    }
}
