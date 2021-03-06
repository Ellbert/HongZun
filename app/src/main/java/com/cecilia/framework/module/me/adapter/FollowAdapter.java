package com.cecilia.framework.module.me.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.me.bean.FollowBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;

public class FollowAdapter extends BaseLmrvAdapter<FollowBean> {

    private OnItemClickListener mOnItemClickListener;

    public FollowAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_follow, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, final FollowBean data) {
        TextView tvName = holder.getView(R.id.tv_name);
        ImageView imageView = holder.getView(R.id.iv_header);
        TextView tvCancel = holder.getView(R.id.tv_cancel);
        tvName.setText(data.getTName()+"");
//        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTPic(), imageView, null);
        ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL +  data.getTPic(), imageView, true, false, null, 0, 0, true, new jp.wasabeef.glide.transformations.CropCircleTransformation(mContext));
        RatingBar ratingBar = holder.getView(R.id.rb_follow);
        ratingBar.setRating(3);
        ratingBar.setIsIndicator(true);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, data.getTId());
                }
            }
        });
    }

    public void setOnCancelListener(OnItemClickListener onCancelListener) {
        this.mOnItemClickListener = onCancelListener;
    }
}
