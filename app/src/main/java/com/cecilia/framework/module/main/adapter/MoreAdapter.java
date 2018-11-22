package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.bean.MoreListBean;
import com.cecilia.framework.module.main.bean.RecommendBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

public class MoreAdapter extends BaseLmrvAdapter <MoreListBean>{

    public MoreAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_mall_good, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, MoreListBean data) {
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_recommend), true, false, null);
    }
}
