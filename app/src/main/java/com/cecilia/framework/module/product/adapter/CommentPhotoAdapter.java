package com.cecilia.framework.module.product.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class CommentPhotoAdapter extends BaseRvAdapter<String> {

    private int mType;

    public CommentPhotoAdapter(Context context, int layoutId, int type) {
        super(context, layoutId);
        this.mType = type;
    }

    @Override
    public void bindData(BaseViewHolder holder, String data) {
        ImageView imageView;
        if (mType == 1) {
            imageView = holder.getView(R.id.iv_photos);
        } else {
            imageView = holder.getView(R.id.iv_comment);
        }
        ImageUtil.loadNetworkImage(mContext, data, imageView, null);
    }
}
