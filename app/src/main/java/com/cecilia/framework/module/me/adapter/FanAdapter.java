package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.UserBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.StringUtil;

public class FanAdapter extends BaseRvAdapter<UserBean> {

    public FanAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseViewHolder holder, UserBean data) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvLevel = holder.getView(R.id.tv_title);
        ImageView ivHeader = holder.getView(R.id.tv_header);
        tvName.setText(data.getTUsername());
        tvLevel.setText(StringUtil.getLevel(data.getTLevel()));
        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTHeadurl(), ivHeader, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(mContext));

    }

}
