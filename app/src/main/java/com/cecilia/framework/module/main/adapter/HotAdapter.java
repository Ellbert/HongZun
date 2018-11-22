package com.cecilia.framework.module.main.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.module.main.bean.BoutiqueBean;
import com.cecilia.framework.module.main.bean.HotBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class HotAdapter extends BaseRvAdapter<HotBean> {


    public HotAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, HotBean data) {
//        ImageUtil.loadNetworkImage(mContext, data.getProduct_img(), (ImageView) holder.getView(R.id.iv_hot), true, false, null);
    }
}
