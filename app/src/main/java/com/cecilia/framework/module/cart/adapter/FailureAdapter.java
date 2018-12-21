package com.cecilia.framework.module.cart.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.cart.bean.FailedGoodsBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class FailureAdapter extends BaseRvAdapter<FailedGoodsBean> {

    public FailureAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, FailedGoodsBean data) {
        ImageView imageView = holder.getView(R.id.iv_header);
        TextView textView = holder.getView(R.id.tv_name);
        ImageUtil.loadNetworkImage(mContext,NetworkConstant.IMAGE_URL+data.getTPic(),imageView,null);
        textView.setText(data.getTGoodsTitle());
    }
}
