package com.cecilia.framework.module.vip.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseRvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.module.vip.activity.BuyVipActivity;
import com.cecilia.framework.module.vip.bean.VipBean;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;

public class VipAdapter extends BaseRvAdapter<VipBean> {

    private int mLevel;

    public VipAdapter(Context context, int layoutId, int level) {
        super(context, layoutId);
        this.mLevel = level;
    }

    @Override
    public void bindData(BaseViewHolder holder, final VipBean data) {
        ImageView imageView = holder.getView(R.id.iv_photo);
        TextView textView = holder.getView(R.id.tv_buy);
        LogUtil.e("level == " + mLevel);
        if (data.getTId() < mLevel) {
            textView.setText("不可激活");
            ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTImage(), imageView, null);
        } else if (data.getTId() == mLevel) {
            textView.setText("已激活");
            ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTImage(), imageView, null);
        } else if (data.getTId() > mLevel) {
            textView.setText("点击激活");
            ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + data.getTImage(), imageView, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuyVipActivity.launch((Activity) mContext, data);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuyVipActivity.launch((Activity)mContext, data);
                }
            });
        }
    }
}
